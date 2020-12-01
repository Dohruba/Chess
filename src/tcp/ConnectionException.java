package tcp;

public class ConnectionException extends Exception{

    public ConnectionException() {super();}
    public ConnectionException(String message) { super(message);}
    public ConnectionException(String message, Throwable t) { super(message, t);}
}
