package tcp;

import java.io.IOException;

public interface Client {
    /**
     *
     * @param hostname to connect to
     * @param port to connect to
     * @return
     * @throws IOException
     */
    Connections connect(String hostname, int port) throws IOException;
}
