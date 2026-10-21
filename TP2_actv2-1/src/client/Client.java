package client;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(
                     socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {
            System.out.println("Connecté au serveur !");
            System.out.println("Tapez exit pour quitter.");
            while (true) {
                System.out.print("Entrez une opération : ");
                String operation = scanner.nextLine().trim();
                if (operation.equalsIgnoreCase("exit")) {
                    out.println("exit");
                    break;
                }
                if (operation.isEmpty()) {
                    System.out.println("Veuillez saisir une opération.");
                    continue;
                }
                out.println(operation);
                String resultat = in.readLine();

                if (resultat == null) {
                    System.out.println("Le serveur a fermé la connexion.");
                    break;
                }
                System.out.println("Résultat reçu : " + resultat);
            }
            System.out.println("Client déconnecté.");
        } catch (IOException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
    }
}