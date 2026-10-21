package server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import model.Operation;
public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Serveur démarré...");
            System.out.println("En attente d'un client...");
            try (Socket socket = serverSocket.accept()) {
                System.out.println("Client connecté !");
                ObjectOutputStream out =
                        new ObjectOutputStream(socket.getOutputStream());
                out.flush();

                ObjectInputStream in =
                        new ObjectInputStream(socket.getInputStream());
                while (true) {
                    Object objet = in.readObject();

                    if (objet == null) {
                        break;
                    }
                    Operation op = (Operation) objet;
                    double a = op.getOperande1();
                    double b = op.getOperande2();
                    String operateur = op.getOperateur();
                    System.out.println("Opération reçue : "
                            + a + " " + operateur + " " + b);
                    double resultat;
                    switch (operateur) {
                        case "+":
                            resultat = a + b;
                            break;
                        case "-":
                            resultat = a - b;
                            break;
                        case "*":
                            resultat = a * b;
                            break;
                        case "/":
                            if (b == 0) {
                                out.writeObject(
                                        "Erreur : division par zéro impossible.");
                                out.flush();
                                continue;
                            }
                            resultat = a / b;
                            break;
                        default:
                            out.writeObject("Erreur : opérateur invalide.");
                            out.flush();
                            continue;
                    }
                    out.writeObject(resultat);
                    out.flush();
                    System.out.println("Résultat envoyé : " + resultat);
                }
            } catch (ClassNotFoundException e) {
                System.out.println("Classe de l'objet reçu inconnue.");
            }
            System.out.println("Connexion fermée.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
