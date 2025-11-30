package ThreadPool;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        try {
            ServerSocket server = new ServerSocket(8080);
            System.out.println("Server started listening on port 8080");
            server.setSoTimeout(15000);
            while (true) {
                Socket client = server.accept();
                threadPool.execute(new Executor(client));
            }
        } catch (SocketTimeoutException st) {
            System.out.println("Socket Timed Out " + st);
        } catch (IOException e) {
            System.out.println("Problem in establishing connection with server " + e);
        }
        threadPool.shutdown();
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
