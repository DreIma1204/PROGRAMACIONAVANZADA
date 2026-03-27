package Controlador;

import Modelo.*;
import java.util.ArrayList;

public class InicializadorDatos {
    
    public static void cargarTodo(ListaProductos inventario) {
       
        ArrayList<Producto> lista = inventario.getTodos();
    	
        // ABARROTES
        lista.add(new Abarrotes("AB01", "Aceite", 45.0, 20, "img/aceite.jpg"));
        lista.add(new Abarrotes("AB02", "Arroz", 22.5, 50, "img/arroz.jpg"));
        lista.add(new Abarrotes("AB03", "Frijol", 35.0, 40, "img/frijol.jpg"));
        lista.add(new Abarrotes("AB04", "Pasta", 12.0, 100, "img/pasta.jpg"));
        lista.add(new Abarrotes("AB05", "Azúcar", 28.0, 30, "img/azucar.jpg"));
        
        // BEBIDAS
        lista.add(new Bebidas("BE01", "Agua", 15.0, 60, "img/agua.jpg"));
        lista.add(new Bebidas("BE02", "Refresco", 38.0, 45, "img/refresco.jpg"));
        lista.add(new Bebidas("BE03", "Jugo", 25.0, 20, "img/jugo.jpg"));
        lista.add(new Bebidas("BE04", "Café", 85.0, 15, "img/cafe.jpg"));
        lista.add(new Bebidas("BE05", "Té", 40.0, 25, "img/te.jpg"));
        
        // LÁCTEOS
        lista.add(new LacteosyHuevos("LH01", "Leche", 26.0, 30, "img/leche.jpg"));
        lista.add(new LacteosyHuevos("LH02", "Huevo", 85.0, 10, "img/huevo.jpg"));
        lista.add(new LacteosyHuevos("LH03", "Yogurt", 42.0, 15, "img/yogurt.jpg"));
        lista.add(new LacteosyHuevos("LH04", "Mantequilla", 28.5, 25, "img/mante.jpg"));
        lista.add(new LacteosyHuevos("LH05", "Crema", 32.0, 12, "img/crema.jpg"));
        
        // FRUTAS
        lista.add(new FrutasyVerduras("FV01", "Manzana", 45.0, 100, "img/manzana.jpg"));
        lista.add(new FrutasyVerduras("FV02", "Plátano", 22.0, 150, "img/platano.jpg"));
        lista.add(new FrutasyVerduras("FV03", "Tomate", 35.0, 80, "img/tomate.jpg"));
        lista.add(new FrutasyVerduras("FV04", "Lechuga", 18.0, 40, "img/lechuga.jpg"));
        lista.add(new FrutasyVerduras("FV05", "Aguacate", 75.0, 30, "img/aguacate.jpg"));
        
        // CARNES
        lista.add(new CarnesyPescado("CP01", "Pollo", 120.0, 15, "img/pollo.jpg"));
        lista.add(new CarnesyPescado("CP02", "Res", 185.0, 10, "img/res.jpg"));
        lista.add(new CarnesyPescado("CP03", "Cerdo", 110.0, 12, "img/cerdo.jpg"));
        lista.add(new CarnesyPescado("CP04", "Pescado", 140.0, 8, "img/pescado.jpg"));
        lista.add(new CarnesyPescado("CP05", "Camarón", 220.0, 5, "img/camaron.jpg"));
        
        // SALCHICHONERÍA
        lista.add(new Salchichoneria("SA01", "Jamón", 65.0, 20, "img/jamon.jpg"));
        lista.add(new Salchichoneria("SA02", "Salchicha", 42.0, 15, "img/salchicha.jpg"));
        lista.add(new Salchichoneria("SA03", "Tocino", 78.0, 10, "img/tocino.jpg"));
        lista.add(new Salchichoneria("SA04", "Queso", 85.0, 12, "img/queso.jpg"));
        lista.add(new Salchichoneria("SA05", "Chorizo", 55.0, 18, "img/chorizo.jpg"));
        
        // LIMPIEZA --
        lista.add(new LimpiezaDelHogar("LM01", "Detergente", 95.0, 20, "img/det.jpg"));
        lista.add(new LimpiezaDelHogar("LM02", "Suavizante", 45.0, 15, "img/suav.jpg"));
        lista.add(new LimpiezaDelHogar("LM03", "Cloro", 18.0, 50, "img/cloro.jpg"));
        lista.add(new LimpiezaDelHogar("LM04", "Papel Hig.", 35.0, 40, "img/papel.jpg"));
        lista.add(new LimpiezaDelHogar("LM05", "Escoba", 65.0, 10, "img/escoba.jpg"));
        
        // CUIDADO PERSONAL --
        lista.add(new CuidadoPersonal("CN01", "Shampoo", 75.0, 20, "img/shamp.jpg"));
        lista.add(new CuidadoPersonal("CN02", "Jabón", 18.5, 50, "img/jabon.jpg"));
        lista.add(new CuidadoPersonal("CN03", "Pasta Dent.", 32.0, 40, "img/pastaD.jpg"));
        lista.add(new CuidadoPersonal("CN04", "Desodorante", 58.0, 25, "img/deso.jpg"));
        lista.add(new CuidadoPersonal("CN05", "Crema", 85.0, 15, "img/cremaM.jpg"));
        
        // SNACKS
        lista.add(new SnacksyDulceria("SD01", "Papas", 18.0, 60, "img/papas.jpg"));
        lista.add(new SnacksyDulceria("SD02", "Galletas", 22.0, 45, "img/galletas.jpg"));
        lista.add(new SnacksyDulceria("SD03", "Chocolate", 15.5, 100, "img/choco.jpg"));
        lista.add(new SnacksyDulceria("SD04", "Gomitas", 12.0, 80, "img/gomitas.jpg"));
        lista.add(new SnacksyDulceria("SD05", "Cacahuate", 14.0, 50, "img/cacahuate.jpg"));

        // Guardamos los archivos JSON en el disco duro
        for (Producto p : lista) { p.generarArchivoJSON(); }
    }
}