package ar.edu.unlu.chatmvc.vista;

import ar.edu.unlu.chatmvc.controlador.Controlador;
import ar.edu.unlu.chatmvc.modelo.IMensaje;
import ar.edu.unlu.chatmvc.modelo.IUsuario;

public class VistaConsola implements IVista {
    public final Controlador controlador;

    public VistaConsola(Controlador controlador) {
        // programación defensiva
        this.controlador = controlador;
        this.controlador.setVista(this);
    }

    @Override
    public void mostrarListaUsuarios(IUsuario[] usuarios) {
        System.out.println("===== Lista de usuarios: ");
        for (IUsuario u : usuarios) {
            System.out.println("  - " + u.getNombre() + " - ID: "+ u.getId());
        }
        System.out.println("==========================");
    }

    @Override
    public void mostrarChat(IMensaje[] mensajes) {
        System.out.println("===== Chats: ");
        for (IMensaje m : mensajes) {
            if (m.isMensajeDelSistema()) {
                System.out.println("[SISTEMA]" + m.getTexto());
            } else {
                System.out.println("[" + m.getUsuario() + "]" + m.getTexto());
            }
        }
        System.out.println("==========================");
    }

    @Override
    public void iniciar() {
        System.out.println(">>> Vista Consola de Chat");
    }
}
