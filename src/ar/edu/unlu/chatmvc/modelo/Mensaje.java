package ar.edu.unlu.chatmvc.modelo;

import java.time.LocalDateTime;

public class Mensaje implements IMensaje {
    private LocalDateTime fecha;
    private IUsuario iUsuario;
    private String texto;

    public Mensaje(String texto, IUsuario iUsuario) {
        this.fecha = LocalDateTime.now();
        this.iUsuario = iUsuario;
        this.texto = texto;
    }

    public Mensaje(String texto) {
        this(texto, null);
    }

    @Override
    public LocalDateTime getFecha() {
        return this.fecha;
    }

    @Override
    public IUsuario getUsuario() {
        return this.iUsuario;
    }

    @Override
    public String getTexto() {
        return this.texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public boolean isMensajeDelSistema() {
        return this.iUsuario == null;
    }
}
