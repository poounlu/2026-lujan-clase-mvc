package ar.edu.unlu.chatmvc;

import ar.edu.unlu.chatmvc.controlador.Controlador;
import ar.edu.unlu.chatmvc.modelo.Chat;
import ar.edu.unlu.chatmvc.vista.IVista;
import ar.edu.unlu.chatmvc.vista.VistaConsola;

public class App {
    public static void main(String[] args) {
        // Instanciar el modelo unico
        Chat modelo = new Chat();

        // Creación del controlador
        Controlador controlador = new Controlador(modelo);

        // Crear la vista y vincularla con el controlador
        IVista vista = new VistaConsola(controlador);

        vista.iniciar();
    }
}
