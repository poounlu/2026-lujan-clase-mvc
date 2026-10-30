package ar.edu.unlu.chatmvc.pruebas;

import ar.edu.unlu.chatmvc.controlador.Controlador;
import ar.edu.unlu.chatmvc.modelo.Chat;
import ar.edu.unlu.chatmvc.modelo.IUsuario;
import ar.edu.unlu.chatmvc.vista.IVista;
import ar.edu.unlu.chatmvc.vista.VistaConsola;

public class Prueba {

    public static void main(String[] args) {

        // 1 instancia compartida por los controllers / vista
        Chat chat = new Chat();

        // 2 Controlador + Vista de Cliente 1
        Controlador ctrlAna = new Controlador(chat);
        IVista vistaAna = new VistaConsola(ctrlAna);

        // 3 Controlador + Vista de Cliente 2
        Controlador ctrlJuan = new Controlador(chat);
        IVista vistaJuan = new VistaConsola(ctrlJuan);

        ctrlAna.conectarUsuario("Ana");
        ctrlJuan.conectarUsuario("Juan");

        ctrlAna.enviarMensaje("¡Hola a todos!");
        ctrlJuan.enviarMensaje("¡Hola Ana! ¿Todo bien?");

        ctrlJuan.desconectarUsuario();
    }
}
