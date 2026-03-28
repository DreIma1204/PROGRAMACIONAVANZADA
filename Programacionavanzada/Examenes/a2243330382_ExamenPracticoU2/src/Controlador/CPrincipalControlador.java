package Controlador;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import Modelo.*;
import Utilidades.*;
import Vista.VPrincipal;

public class CPrincipalControlador {

    private final VPrincipal vista;
    private final MRepositorioEvaluaciones repositorio;
    private MAlmacenEvaluaciones almacen;
    private List<MFilaCatalogo> catalogoBase;
    private MEvaluacionRegistro registroActual;

    public CPrincipalControlador(VPrincipal vista, MRepositorioEvaluaciones repositorio) {
        this.vista = vista;
        this.repositorio = repositorio;
        this.catalogoBase = new ArrayList<>();
        this.registroActual = new MEvaluacionRegistro();
        configurarEventos();
    }

    public static void iniciar() {
        VPrincipal vista = new VPrincipal();
        MRepositorioEvaluaciones repo = new MRepositorioEvaluaciones(Paths.get("evaluaciones.json"));
        CPrincipalControlador ctrl = new CPrincipalControlador(vista, repo);
        ctrl.preparar();
        vista.setVisible(true);
    }

    private void preparar() {
        try {
            almacen = repositorio.cargar();
            catalogoBase = UExcelCatalogoLector.leerCatalogo(Paths.get("Datosbase.xlsx"));
            actualizarComboProfesores();
            actualizarComboRegistros();
            actualizarSemaforo();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(vista, "Error al cargar datos: " + e.getMessage());
        }
    }

    private void configurarEventos() {
        vista.obtenerBotonGuardar().addActionListener(e -> accionGuardar());
        vista.obtenerBotonNuevo().addActionListener(e -> accionNuevo());
        vista.obtenerBotonCargar().addActionListener(e -> accionCargar());
        vista.obtenerBotonSugerirAlumnos().addActionListener(e -> accionSugerirAlumnos());
        vista.obtenerComboProfesor().addActionListener(e -> actualizarComboAsignaturas());
        vista.obtenerComboAsignatura().addActionListener(e -> actualizarComboGrupos());
    }

    private void actualizarComboProfesores() {
        vista.obtenerComboProfesor().removeAllItems();
        catalogoBase.stream()
                .map(MFilaCatalogo::obtenerProfesor)
                .distinct()
                .sorted()
                .forEach(vista.obtenerComboProfesor()::addItem);
    }

    private void actualizarComboAsignaturas() {
        String prof = (String) vista.obtenerComboProfesor().getSelectedItem();
        vista.obtenerComboAsignatura().removeAllItems();
        if (prof == null) return;
        catalogoBase.stream()
                .filter(f -> f.obtenerProfesor().equals(prof))
                .map(MFilaCatalogo::obtenerAsignatura)
                .distinct()
                .sorted()
                .forEach(vista.obtenerComboAsignatura()::addItem);
    }

    private void actualizarComboGrupos() {
        String prof = (String) vista.obtenerComboProfesor().getSelectedItem();
        String asig = (String) vista.obtenerComboAsignatura().getSelectedItem();
        vista.obtenerComboGrupo().removeAllItems();
        if (prof == null || asig == null) return;
        catalogoBase.stream()
                .filter(f -> f.obtenerProfesor().equals(prof) && f.obtenerAsignatura().equals(asig))
                .map(MFilaCatalogo::obtenerGrupo)
                .distinct()
                .sorted()
                .forEach(vista.obtenerComboGrupo()::addItem);
    }

    private void accionSugerirAlumnos() {
        String prof = (String) vista.obtenerComboProfesor().getSelectedItem();
        String asig = (String) vista.obtenerComboAsignatura().getSelectedItem();
        String grup = (String) vista.obtenerComboGrupo().getSelectedItem();

        if (prof == null || asig == null || grup == null) {
            JOptionPane.showMessageDialog(vista, "Seleccione Profesor, Asignatura y Grupo primero.");
            return;
        }

        List<String> alumnos = catalogoBase.stream()
                .filter(f -> f.obtenerProfesor().equals(prof) && 
                             f.obtenerAsignatura().equals(asig) && 
                             f.obtenerGrupo().equals(grup))
                .map(MFilaCatalogo::obtenerAlumno)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        if (alumnos.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "No se encontraron alumnos para esta selección.");
            return;
        }

        registroActual.obtenerEquipos().clear();
        registroActual.obtenerFilasRubrica().clear();

        for (String nombre : alumnos) {
            registroActual.obtenerEquipos().add(new MEquipo(nombre, 0.0));
            MFilaRubrica fila = new MFilaRubrica();
            fila.establecerNombreAlumno(nombre);
            registroActual.obtenerFilasRubrica().add(fila);
        }

        vista.obtenerPanelProductoIntegrador().cargarDesdeRegistro(registroActual);
        vista.obtenerPanelRubrica().cargarDesdeRegistro(registroActual);
    }

    private void accionGuardar() {
        registroActual.establecerProfesor((String) vista.obtenerComboProfesor().getSelectedItem());
        registroActual.establecerAsignatura((String) vista.obtenerComboAsignatura().getSelectedItem());
        registroActual.establecerGrupo((String) vista.obtenerComboGrupo().getSelectedItem());
        
        vista.obtenerPanelProductoIntegrador().volcarARegistro(registroActual);
        vista.obtenerPanelRubrica().volcarARegistro(registroActual);
        vista.obtenerPanelListaCotejo().volcarARegistro(registroActual);

        String nuevoId = UIdentificadorUtil.generarId(registroActual.obtenerAsignatura(), 
                                                      registroActual.obtenerProfesor(), 
                                                      registroActual.obtenerGrupo());
        
        if (UValidadorDuplicados.existeDuplicado(almacen.obtenerEvaluaciones(), registroActual, registroActual.obtenerId())) {
            JOptionPane.showMessageDialog(vista, "Ya existe un registro para esta combinación.");
            return;
        }

        registroActual.establecerId(nuevoId);
        if (!almacen.obtenerEvaluaciones().contains(registroActual)) {
            almacen.obtenerEvaluaciones().add(registroActual);
        }

        try {
            repositorio.guardar(almacen);
            actualizarComboRegistros();
            actualizarSemaforo();
            JOptionPane.showMessageDialog(vista, "Guardado exitosamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(vista, "Error al guardar: " + e.getMessage());
        }
    }

    private void accionNuevo() {
        registroActual = new MEvaluacionRegistro();
        vista.obtenerPanelProductoIntegrador().cargarDesdeRegistro(registroActual);
        vista.obtenerPanelRubrica().cargarDesdeRegistro(registroActual);
        vista.obtenerPanelListaCotejo().cargarDesdeRegistro(registroActual);
        actualizarSemaforo();
    }

    private void accionCargar() {
        String id = (String) vista.obtenerComboRegistros().getSelectedItem();
        if (id == null) return;
        almacen.obtenerEvaluaciones().stream()
                .filter(r -> r.obtenerId().equals(id))
                .findFirst().ifPresent(r -> {
                    registroActual = r;
                    vista.obtenerComboProfesor().setSelectedItem(r.obtenerProfesor());
                    vista.obtenerComboAsignatura().setSelectedItem(r.obtenerAsignatura());
                    vista.obtenerComboGrupo().setSelectedItem(r.obtenerGrupo());
                    vista.obtenerPanelProductoIntegrador().cargarDesdeRegistro(r);
                    vista.obtenerPanelRubrica().cargarDesdeRegistro(r);
                    vista.obtenerPanelListaCotejo().cargarDesdeRegistro(r);
                    actualizarSemaforo();
                });
    }

    private void actualizarComboRegistros() {
        vista.obtenerComboRegistros().removeAllItems();
        almacen.obtenerEvaluaciones().forEach(r -> vista.obtenerComboRegistros().addItem(r.obtenerId()));
    }

    private void actualizarSemaforo() {
        int estado = UEstadoSemaforoUtil.calcularEstado(registroActual);
        vista.obtenerEtiquetaSemaforo().setIcon(UIconoSemaforoUtil.crearCirculo(20, UEstadoSemaforoUtil.colorPorEstado(estado)));
    }
}