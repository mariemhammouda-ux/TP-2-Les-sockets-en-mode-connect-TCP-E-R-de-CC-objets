package client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Operation;
public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234);
             Scanner scanner = new Scanner(System.in)) {
            System.out.println("Connecté au serveur !");
            ObjectOutputStream out =
                    new ObjectOutputStream(socket.getOutputStream());
            out.flush();

            ObjectInputStream in =
                    new ObjectInputStream(socket.getInputStream());
            Pattern pattern = Pattern.compile(
                    "^\\s*(-?\\d+(?:\\.\\d+)?)\\s*([+*/-])\\s*(-?\\d+(?:\\.\\d+)?)\\s*$"
            );
            while (true) {
                System.out.print("Entrez une opération (exit pour quitter) : ");
                String saisie = scanner.nextLine().trim();
                if (saisie.equalsIgnoreCase("exit")) {
                    break;
                }
                Matcher matcher = pattern.matcher(saisie);
                if (!matcher.matches()) {
                    System.out.println("Syntaxe incorrecte !");
                    System.out.println("Exemple : 5*55 ou 10+3");
                    continue;
                }
                double a = Double.parseDouble(matcher.group(1));
                String operateur = matcher.group(2);
                double b = Double.parseDouble(matcher.group(3));
                Operation op = new Operation(a, operateur, b);
                out.writeObject(op);
                out.flush();
                Object reponse = in.readObject();
                if (reponse instanceof String) {
                    System.out.println(reponse);
                } else if (reponse instanceof Double) {
                    System.out.println("Résultat reçu : " + reponse);
                }
            }
            System.out.println("Client déconnecté.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}