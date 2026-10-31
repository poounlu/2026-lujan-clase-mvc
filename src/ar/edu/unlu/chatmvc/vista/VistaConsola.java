package ar.edu.unlu.chatmvc.vista;

import ar.edu.unlu.chatmvc.controlador.Controlador;
import ar.edu.unlu.chatmvc.modelo.IMensaje;
import ar.edu.unlu.chatmvc.modelo.IUsuario;

import java.util.Scanner;

public class VistaConsola implements IVista {
    private final Controlador controlador;
    private final Scanner scanner;
    private boolean ejecutando = false;

    public VistaConsola(Controlador controlador) {
        // programación defensiva
        this.controlador = controlador;
        this.scanner = new Scanner(System.in); // es el teclado
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
        this.ejecutando = true;
        System.out.println("===============================");
        System.out.println("        Sala de Chat Interactiva (MVC)");
        System.out.println("===============================");

        // 1. Registrarse de forma obligatoria
        while (this.controlador.getUsuario() == null && this.ejecutando) {
            System.out.print("Ingrese su nombre de usuario: ");
            String nombre = this.scanner.nextLine();
            try {
                this.controlador.conectarUsuario(nombre);
            } catch (IllegalArgumentException e) {
                this.mostrarError("Nombre invalido: " + e.getMessage());
            }
        }

        // 2. Bucle iterativo de comandos
        while (this.ejecutando) {
            this.imprimirMenu();
            String opcion = this.scanner.nextLine().trim();
            this.procesarOpcion(opcion);
        }
    }

    private void procesarOpcion(String opcion) {
        switch (opcion) {
            case "1":
                System.out.println(getPrompt() + "Escriba su mensaje: ");
                String texto = scanner.nextLine();
                try {
                    this.controlador.enviarMensaje(texto);
                } catch (IllegalArgumentException | IllegalStateException e) {
                    this.mostrarError(e.getMessage());
                }
                break;
            case "2":
                this.controlador.solicitarListaUsuarios();
                break;
            case "3":
                this.controlador.solicitarListaMensajes();
                break;
            case "0":
                this.controlador.desconectarUsuario();
                this.ejecutando = false;
                System.out.println(">>> Desconectando de la sala. Sesión de chat finalizada");
                break;
            default:
                this.mostrarError("Comando no reconocido. Ingrese una opción de menú.");
                break;
        }
    }

    private void imprimirMenu() {
        System.out.println("\n[1: Enviar Mensaje | 2: Ver Usuarios | 3: Ver Historial | 0: Salir]");
        System.out.println(this.getPrompt() + " Ingrese opción: ");
    }

    public void mostrarError(String mensajeError) {
        System.out.println(" [!] ERROR: " + mensajeError);
    }

    private String getPrompt() {
        IUsuario u = controlador.getUsuario();
        return (u != null) ? "[" + u.getNombre() + "]" : "[ANONIMO]";
    }
}
