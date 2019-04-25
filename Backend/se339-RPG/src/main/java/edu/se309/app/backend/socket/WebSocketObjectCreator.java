package edu.se309.app.backend.socket;

/**
 * Class for creating shared objects for the web socket
 */
public class WebSocketObjectCreator {

    /**
     * Used to create a common Chat object to be shared by the web socket
     *
     * @return a new Chat object
     */
    public static Chat chat() {
        return new Chat();
    }
}
