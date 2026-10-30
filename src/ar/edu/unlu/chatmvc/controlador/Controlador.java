package ar.edu.unlu.chatmvc.controlador;

import ar.edu.unlu.chatmvc.modelo.Chat;
import ar.edu.unlu.chatmvc.modelo.Eventos;
import ar.edu.unlu.chatmvc.modelo.IUsuario;
import ar.edu.unlu.chatmvc.observer.IObservable;
import ar.edu.unlu.chatmvc.observer.IObservador;
import ar.edu.unlu.chatmvc.vista.IVista;
import ar.edu.unlu.chatmvc.vista.VistaConsola;

public class Controlador implements IObservador {
    private IVista vista;
    private final Chat chat;
    private IUsuario usuario;

    public Controlador(Chat chat) {
        // programcion defensiva
        this.chat = chat;
        this.chat.agregarObservador(this);
    }

    @Override
    public void actualizar(IObservable observable, Object e) {
        Eventos evento = (Eventos) e;
        switch (evento) {
            case NUEVO_MENSAJE:
                this.vista.mostrarChat(this.chat.getMensajes());
                break;
            case CAMBIO_LISTA_USUARIOS:
                this.vista.mostrarListaUsuarios(this.chat.getUsuarios());
                break;
        }
    }

    public void setVista(IVista vista) {
        this.vista = vista;
    }

    public IUsuario conectarUsuario(String username) {
        this.usuario = this.chat.conectarUsuario(username);
        return usuario;
    }

    public void desconectarUsuario() {
        this.chat.desconectarUsuario(usuario.getId());
        this.usuario = null;
    }

    public void enviarMensaje(String s) {
        this.chat.enviarMensaje(s, usuario);
    }
}
