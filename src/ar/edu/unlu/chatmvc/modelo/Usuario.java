package ar.edu.unlu.chatmvc.modelo;

import java.util.Objects;

public class Usuario implements IUsuario {
    private static int ID = 0;
    private int id;
    private String nombre;

    public Usuario(String nombre) {
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("Nombre de usuario no puede ser nulo o vacio");
        this.nombre = nombre;
        this.id = Usuario.ID++;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        // programación defensiva
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof IUsuario)) return false;
        IUsuario usu = (IUsuario) obj;
        return this.getId() == usu.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
