package ar.edu.unlu.chatmvc.modelo;

import ar.edu.unlu.chatmvc.observer.Observable;

import java.util.ArrayList;
import java.util.HashMap;

public class Chat extends Observable {
    private HashMap<Integer, IUsuario> usuarios;
    private ArrayList<IMensaje> mensajes;

    public Chat() {
        this.usuarios = new HashMap<>();
        this.mensajes = new ArrayList<>();
    }

    public IUsuario conectarUsuario(String nombre) {
        IUsuario u = new Usuario(nombre);
        this.usuarios.put(u.getId(), u);
        this.enviarMensajeDelSistema("Usuario " + nombre + " conectado");
        this.notificarObservadores(Eventos.CAMBIO_LISTA_USUARIOS);
        return u;
    }

    public void desconectarUsuario(int usuarioId) {
        IUsuario iUsuario = this.usuarios.get(usuarioId);
        if (iUsuario != null) {
            this.usuarios.remove(usuarioId);
            this.enviarMensajeDelSistema("Usuario " + iUsuario.getNombre() + " desconectado");
            this.notificarObservadores(Eventos.CAMBIO_LISTA_USUARIOS);
        }
    }

    public IMensaje[] getMensajes() {
        IMensaje[] resultado = new Mensaje[this.mensajes.size()];
        return this.mensajes.toArray(resultado);
    }

    public IUsuario[] getUsuarios() {
        IUsuario[] resultado = new IUsuario[this.usuarios.size()];
        return this.usuarios.values().toArray(resultado);
    }

    public void enviarMensaje(String texto, IUsuario iUsuario) {
        this.mensajes.add(new Mensaje(texto, iUsuario));
        this.notificarObservadores(Eventos.NUEVO_MENSAJE);
    }

    public void enviarMensajeDelSistema(String texto) {
        this.mensajes.add(new Mensaje(texto));
        this.notificarObservadores(Eventos.NUEVO_MENSAJE);
    }
}
