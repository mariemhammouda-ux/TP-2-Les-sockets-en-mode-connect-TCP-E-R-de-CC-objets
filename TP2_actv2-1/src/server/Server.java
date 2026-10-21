package server;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Serveur démarré...");
            System.out.println("En attente d'un client...");
            try (Socket socket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(
                         new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(
                         socket.getOutputStream(), true)) {
                System.out.println("Client connecté !");
                String x;
                while ((x = in.readLine()) != null) {
                    if (x.equalsIgnoreCase("exit")) {
                        break;
                    }
                    System.out.println("Opération reçue : " + x);
                    try {
                        double resultat = calculer(x);

                        out.println(resultat);

                        System.out.println("Résultat : " + resultat);

                    } catch (IllegalArgumentException e) {

                        out.println("Erreur : " + e.getMessage());
                        System.out.println("Erreur : " + e.getMessage());
                    }
                }
                System.out.println("Connexion fermée.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static double calculer(String x) {
        x = x.replaceAll("\\s+", "");
        int a1 = x.indexOf('*');
        int a2 = x.indexOf('+');
        int a3 = x.indexOf('-');
        int a4 = x.indexOf('/');
        int a5 = x.indexOf("**");
        int pos;
        String operateur;
        if (a5 >= 0) {
            pos = a5;
            operateur = "**";
        } else if (a1 >= 0) {
            pos = a1;
            operateur = "*";
        } else if (a2 > 0) {
            pos = a2;
            operateur = "+";
        } else if (a3 > 0) {
            pos = a3;
            operateur = "-";
        } else if (a4 >= 0) {
            pos = a4;
            operateur = "/";
        } else {
            throw new IllegalArgumentException("Opérateur invalide.");
        }

        String partie1 = x.substring(0, pos);
        String partie2 = x.substring(pos + operateur.length());

        if (partie1.isEmpty() || partie2.isEmpty()) {
            throw new IllegalArgumentException("Opération incomplète.");
        }
        double n1;
        double n2;
        try {
            n1 = Double.parseDouble(partie1);
            n2 = Double.parseDouble(partie2);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Les opérandes doivent être numériques.");
        }
        switch (operateur) {
            case "+":
                return n1 + n2;

            case "-":
                return n1 - n2;
            case "*":
                return n1 * n2;
            case "/":
                if (n2 == 0) {
                    throw new IllegalArgumentException("Division par zéro impossible.");
                }
                return n1 / n2;
            case "**":
                return Math.pow(n1, n2);
            default:
                throw new IllegalArgumentException("Opérateur invalide.");
        }
    }
}