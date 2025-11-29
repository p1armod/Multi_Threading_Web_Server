package MultiThreaded;

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
                Socket client = server.accept();
                new Thread(new Executor(client)).start();
            }
        } catch (SocketTimeoutException st) {
            System.out.println("Socket Timeout Exception Executed " + st);
        } catch (IOException e) {
            System.out.println("Error in connection establishing " + e);
        }
    }
}

class Executor implements Runnable {
    private Socket client;

    Executor(Socket socket) {
        client = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Connection Established with " + client.getRemoteSocketAddress());
            InputStream in = client.getInputStream();
            byte[] buffer = new byte[1024];
            int len = in.read(buffer);

            String line = new String(buffer, 0, len);
            System.out.println("Received: " + line);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            bw.write("Hello From Server " + client.getRemoteSocketAddress());
            bw.newLine();
            bw.flush();
            in.close();
            bw.close();
            client.close();
        } catch (IOException e) {
            System.out.println("Problem in executing Thread " + e);
        }
    }
}
