package ar.edu.unlu.chatmvc.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChatTest {

    @Test
    public void testEnvioMensajeNoVerificaSalida() {
        Chat chat = new Chat();
        IUsuario ana = chat.conectarUsuario("Ana");
        chat.enviarMensaje("¡Hola a todos!", ana);
        assertEquals(2, chat.getMensajes().length);
    }
}
