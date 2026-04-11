package Controlador;

import Modelo.BaseDatos;
import Vista.VentanaMateria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MateriaControlador implements ActionListener {

    private BaseDatos modelo;
    private VentanaMateria vista;

    public MateriaControlador(BaseDatos modelo, VentanaMateria vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.btnActualizar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnActualizar) {
            actualizarMateria();
        }
    }

    private void actualizarMateria() {
        String id = vista.txtId.getText();
        String nuevoNombre = vista.txtNombre.getText();

        Map<String, Object> valores = new HashMap<>();
        valores.put("nombre", nuevoNombre);

        Object[] params = {id};
        modelo.modificar("materias", valores, "id = ?", params);
    }
}