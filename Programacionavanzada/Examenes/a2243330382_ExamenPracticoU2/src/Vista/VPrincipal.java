package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class VPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JComboBox<String> comboProfesor;
    private final JComboBox<String> comboAsignatura;
    private final JComboBox<String> comboGrupo;
    private final JComboBox<String> comboRegistros;
    private final JButton botonCargar;
    private final JButton botonNuevo;
    private final JButton botonGuardar;
    private final JButton botonEliminar;
    private final JButton botonCarpetaReportes;
    private final JButton botonSugerirAlumnos;
    private final JLabel etiquetaSemaforo;
    private final VPanelProductoIntegrador panelProductoIntegrador;
    private final VPanelRubrica panelRubrica;
    private final VPanelListaCotejo panelListaCotejo;
    private final JTabbedPane pestanas;

    public VPrincipal() {
        setTitle("SAE-AE — Sistema Automatizado de Evaluación de Atributos de Egreso");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 750);
        setLocationRelativeTo(null);

        JPanel panelNorte = new JPanel(new BorderLayout());
        panelNorte.setBackground(new Color(0x1E3A5F));
        panelNorte.setBorder(new EmptyBorder(10, 15, 10, 15));

        JPanel panelCombos = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelCombos.setOpaque(false);

        comboProfesor = new JComboBox<>();
        comboAsignatura = new JComboBox<>();
        comboGrupo = new JComboBox<>();
        
        Dimension dim = new Dimension(200, 28);
        comboProfesor.setPreferredSize(dim);
        comboAsignatura.setPreferredSize(dim);
        comboGrupo.setPreferredSize(new Dimension(80, 28));

        panelCombos.add(crearEtiquetaBlanca("Profesor:"));
        panelCombos.add(comboProfesor);
        panelCombos.add(crearEtiquetaBlanca("Asignatura:"));
        panelCombos.add(comboAsignatura);
        panelCombos.add(crearEtiquetaBlanca("Grupo:"));
        panelCombos.add(comboGrupo);

        botonSugerirAlumnos = new JButton("Sugerir Alumnos");
        estilizarBoton(botonSugerirAlumnos);
        panelCombos.add(botonSugerirAlumnos);

        panelNorte.add(panelCombos, BorderLayout.WEST);

        etiquetaSemaforo = new JLabel();
        panelNorte.add(etiquetaSemaforo, BorderLayout.EAST);

        add(panelNorte, BorderLayout.NORTH);

        pestanas = new JTabbedPane();
        panelProductoIntegrador = new VPanelProductoIntegrador();
        panelRubrica = new VPanelRubrica();
        panelListaCotejo = new VPanelListaCotejo();

        pestanas.addTab("Producto Integrador / Equipos", panelProductoIntegrador);
        pestanas.addTab("Rúbrica de Evaluación", panelRubrica);
        pestanas.addTab("Lista de Cotejo", panelListaCotejo);

        add(pestanas, BorderLayout.CENTER);

        JPanel panelSur = new JPanel(new BorderLayout());
        panelSur.setBorder(new EmptyBorder(10, 15, 10, 15));

        JPanel panelGestion = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        comboRegistros = new JComboBox<>();
        comboRegistros.setPreferredSize(new Dimension(350, 28));
        botonCargar = new JButton("Cargar");
        botonNuevo = new JButton("Nuevo");
        
        panelGestion.add(new JLabel("Registros Guardados:"));
        panelGestion.add(comboRegistros);
        panelGestion.add(botonCargar);
        panelGestion.add(botonNuevo);

        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        botonCarpetaReportes = new JButton("Carpeta Reportes");
        botonEliminar = new JButton("Eliminar");
        botonGuardar = new JButton("Guardar Registro");
        botonGuardar.setBackground(new Color(0x28a745));
        botonGuardar.setForeground(Color.WHITE);

        panelAcciones.add(botonCarpetaReportes);
        panelAcciones.add(botonEliminar);
        panelAcciones.add(botonGuardar);

        panelSur.add(panelGestion, BorderLayout.WEST);
        panelSur.add(panelAcciones, BorderLayout.EAST);

        add(panelSur, BorderLayout.SOUTH);
    }

    private JLabel crearEtiquetaBlanca(String texto) {
        JLabel l = new JLabel(texto);
        l.setForeground(Color.WHITE);
        l.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        return l;
    }

    private void estilizarBoton(JButton b) {
        b.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
        b.setBackground(new Color(0x2E4A6F));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
    }

    public JComboBox<String> obtenerComboProfesor() { return comboProfesor; }
    public JComboBox<String> obtenerComboAsignatura() { return comboAsignatura; }
    public JComboBox<String> obtenerComboGrupo() { return comboGrupo; }
    public JComboBox<String> obtenerComboRegistros() { return comboRegistros; }
    public JButton obtenerBotonCargar() { return botonCargar; }
    public JButton obtenerBotonNuevo() { return botonNuevo; }
    public JButton obtenerBotonSugerirAlumnos() { return botonSugerirAlumnos; }
    public JButton obtenerBotonGuardar() { return botonGuardar; }
    public JButton obtenerBotonEliminar() { return botonEliminar; }
    public JButton obtenerBotonCarpetaReportes() { return botonCarpetaReportes; }
    public JLabel obtenerEtiquetaSemaforo() { return etiquetaSemaforo; }
    public VPanelProductoIntegrador obtenerPanelProductoIntegrador() { return panelProductoIntegrador; }
    public VPanelRubrica obtenerPanelRubrica() { return panelRubrica; }
    public VPanelListaCotejo obtenerPanelListaCotejo() { return panelListaCotejo; }
}