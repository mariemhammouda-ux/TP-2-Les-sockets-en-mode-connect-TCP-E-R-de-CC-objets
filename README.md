# TP2 : Les sockets en mode connecté TCP
**Étudiante :** Mariem Hammouda  
**Niveau :** LSI3  
**Module :** Développement d'applications réparties
## Présentation
Ce TP a pour objectif de comprendre la communication entre un client et un serveur à l'aide des sockets TCP en Java.

Deux activités sont réalisées :

- **Activité 2-1 :** Échange de chaînes de caractères entre le client et le serveur pour effectuer des opérations arithmétiques.
- **Activité 2-2 :** Échange d'objets Java entre le client et le serveur à l'aide de la sérialisation.

---

## Activité 2-1 : Échange de chaînes de caractères

### Objectif

Développer une application client-serveur permettant au client d'envoyer une opération arithmétique sous forme de chaîne de caractères. Le serveur analyse l'opération, effectue le calcul et renvoie le résultat au client.

### Fonctionnement

1. Le serveur démarre et attend une connexion.
2. Le client se connecte au serveur via une socket TCP.
3. Le client saisit une opération, par exemple `5*55`.
4. Le serveur reçoit la chaîne, analyse l'opération et calcule le résultat.
5. Le serveur renvoie le résultat au client.
6. Le client affiche le résultat reçu.

Les opérations prises en charge sont l'addition (`+`), la soustraction (`-`), la multiplication (`*`), la division (`/`) et, si elle est implémentée, la puissance (`**`).

### Technologies utilisées

- Java
- `ServerSocket` et `Socket`
- Flux d'entrée et de sortie
- `BufferedReader` et `PrintWriter`

### Captures d'écran

Les captures des résultats de cette activité sont disponibles dans le dossier :

![Capture 1](./TP2/TP2_actv2-1/images/actv1.1.png)
![Capture 2](./TP2/TP2_actv2-1/images/actv1.2.png)


---

## Activité 2-2 : Échange d'objets Java entre le client et le serveur

### Objectif

Développer une application client-serveur permettant d'envoyer une opération arithmétique sous forme d'objet Java. L'objet contient les deux opérandes et l'opérateur.

### Classe Operation

La classe `Operation` représente une opération arithmétique. Elle contient :

- `operande1` : le premier nombre ;
- `operateur` : l'opérateur arithmétique ;
- `operande2` : le deuxième nombre.

La classe implémente l'interface `Serializable` afin de permettre la sérialisation de l'objet et son échange sur le réseau.

### Fonctionnement

1. Le serveur démarre et attend la connexion du client.
2. Le client saisit une opération arithmétique.
3. Le client crée un objet `Operation` contenant les opérandes et l'opérateur.
4. L'objet est envoyé au serveur à l'aide de `ObjectOutputStream`.
5. Le serveur reçoit l'objet avec `ObjectInputStream`.
6. Le serveur effectue le calcul et renvoie le résultat.
7. Le client reçoit et affiche le résultat.

### Technologies utilisées

- Java
- `ServerSocket` et `Socket`
- `Serializable`
- `ObjectOutputStream` et `ObjectInputStream`

### Captures d'écran

Les captures des résultats de cette activité sont disponibles dans le dossier :

![Capture 1 - Activité 2-2](./TP2/TP2_Actv2-2/images/actv2.1.png)
![Capture 2 - Activité 2-2](./TP2/TP2_Actv2-2/images/actv2.2.png)


---

## Conclusion

Ce TP a permis de mettre en pratique la communication client-serveur avec les sockets TCP en Java.

La première activité repose sur l'échange de chaînes de caractères, tandis que la deuxième utilise des objets Java sérialisables. Ces deux approches permettent de comprendre différentes méthodes de transmission des données entre un client et un serveur.

---

## Organisation du projet

```text
MariemHammouda_LSI3_DevAppReparties/
└── TP2/
    ├── README.md
    ├── TP2_actv2-1/
    │   └── images/
    │       └── [captures des résultats]
    └── TP2_Actv2-2/
        └── images/
            └── [captures des résultats]
```