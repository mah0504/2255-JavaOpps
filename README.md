# README

## Titre du projet
2255-JavaOpss/Robotix 

## Descriptions

Cette livraison constitue notre projet final pour une application qui implémente Robotix.
Notre application permet de réaliser toutes les fonctionnalités et requêtes demandées.
Elle offre une première expérience de ce à quoi peut ressembler une vraie application Robotix.
C'est une base pertinente pour la phase de maintenance, il reste plusieurs possibilités d'amélioaration.
certains bugs persistent comment la sensibilité aux entrées du clavier et traitement de structures nulles
ou l'implémentation d'une méthode abstraite pour lire et écrire dans un Json. 

En ce qui concerne la configuration du Maven, nous avons choisi de stocker nos données dans des fichiers Json et
d'utiliser des tests d'assertation (True ou False) avec la librairie Junit.


## Fonctionnalités

- Lire et Écrire dans des Json 

- Choix du rôle

    - Profil Utilisateur
        - S inscrire
        - Confirmer l'inscription
        - Se connecter
                  - Menu Utilisateur
                    - Modifier son profil
                    - Gérer sa flotte
                        - Enregister un Robot
                        - Supprimer un Robot
                        - Trouver des composantes
                            - par nom
                            - par type
                            - par fournisseur
                        - Acheter des composantes
                    - Gérer ses activités
                        - Inscription à une activité
                        - Désinscription à une activité
                    - Voir l état de ses robots
                        - Affichage général
                        - Affichage complet
                    - Trouver un Fournisseur
                        - par nom
                        - par type de composante
                    - Voir ses notifications
                  

    - Profil Fournisseur
        - S inscrire
        - Confirmer l'inscription
        - Se connecter
        - Menu Fournisseur
            - Modifier son profil 
            - Enregistrer une composante
            - Supprimer une composante
            - Modifier une composante
            - Voir ses composantes 

#######################################################################################
## Organisation des fichiers

Le projet est origanisé selon la structure suivante

├── Exigences/
│   └── Diagramme de cas d utilisation
├── Analyse/
│   └── Diagrammes d activités
├── Conception/
│   ├── Diagramme de classes
│   └── Diagrammes de séquence
├── Implémentation/
│   ├── src
        ├── main
            ├── java /-- code source de l'application
            └── resources 
                ├── javaDoc
                ├── META-INF/MANIFEST.MF /-- pour créer l'executable jar
                ├── utilisateurs.json
                ├── fournisseurs.json
                └── activites.json
                       
        └── test/java /-- code source des tests 
        
│   └── target
        ├── différents dossiers pour la compilation
        └── 2255-JavaOpps-1.0-SNAPSHOT.jar
│   └── pom.xml
├── rapport/rapport.html
└── README.md
```
