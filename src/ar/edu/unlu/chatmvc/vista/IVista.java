package ar.edu.unlu.chatmvc.vista;

import ar.edu.unlu.chatmvc.modelo.IMensaje;
import ar.edu.unlu.chatmvc.modelo.IUsuario;

public interface IVista {
    void mostrarListaUsuarios(IUsuario[] usuarios);
    void mostrarChat(IMensaje[] mensajes);
    void iniciar();
}
