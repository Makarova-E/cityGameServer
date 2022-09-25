import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final int PORT = 8989;
    private static final String HOST = "127.0.0.1";


    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try (Socket clientSocket = new Socket(HOST, PORT);
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(
                         new InputStreamReader(clientSocket.getInputStream()))) {

                String text = in.readLine();
                System.out.println(text); // или ?? или город
                String cityFirst = scanner.nextLine();
                out.println(cityFirst);
                System.out.println(in.readLine());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
