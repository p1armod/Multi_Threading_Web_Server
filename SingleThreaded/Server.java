package SingleThreaded;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8080);
            server.setSoTimeout(15000);
            while (true) {
                System.out.println("Waiting for client");
                Socket client = server.accept();
                System.out.println("Connection Established with " + client.getRemoteSocketAddress());
                InputStream in = client.getInputStream();
                byte[] buffer = new byte[1024];
                int len = in.read(buffer); // <-- does NOT wait for newline

                String line = new String(buffer, 0, len);
                System.out.println("Received: " + line);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                bw.write("Hello From Server " + client.getRemoteSocketAddress());
                bw.newLine();
                bw.flush();
                in.close();
                bw.close();
                client.close();
            }

            // server.close();
        } catch (SocketTimeoutException st) {
            System.out.println("Socket Timeout Executed");
        } catch (IOException e) {
            System.out.println("Problem in establishing connection \n" + e);
        }

    }
}
