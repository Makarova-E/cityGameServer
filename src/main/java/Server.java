import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 8989;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT);) { // стартуем сервер один(!) раз
            System.out.println("Server started");
            String city = null;
            while (true) { // в цикле(!) принимаем подключения
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ) {
                    if (city == null) {
                        out.println("???");
                        city = in.readLine();
                        out.println("OK");

                    } else {
                        out.println(city);

                        String cityNext = in.readLine();
                        if (cityNext.toLowerCase().charAt(0) ==
                                city.toLowerCase().charAt(city.length() - 1)) {
                            out.println("OK");
                            city = cityNext;
                            out.println(city);
                        } else {
                            out.println("NOT OK");
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
