package tcp;

import java.io.IOException;

public interface Server {
    /**
     *
     * @param port to keep open
     * @return
     * @throws IOException
     */
    Connections acceptConnection(int port) throws IOException;
}
