# Khet Java Project

Ce projet est un jeu de Khet développé en Java en utilisant AWT et Swing. Suivez les instructions ci-dessous pour configurer et exécuter le projet sur votre système.

## Prérequis

- Java Development Kit (JDK)
- Visual Studio Code (VSCode) ou Eclipse

## Installation et Exécution

### Utilisation de Visual Studio Code

1. Décompressez le fichier du projet dans un répertoire de votre choix.
2. Ouvrez Visual Studio Code.
3. Sélectionnez `File > Open Folder...` et naviguez jusqu'au dossier du projet décompressé.
4. Le point d'entrée principal du programme se trouve dans `src/.java`.
5. Ouvrez le fichier `.java`, vous trouverez le main tout en bas du fichier, et exécutez-le en utilisant la fonctionnalité d'exécution de VSCode.

### Utilisation d'Eclipse

1. Décompressez le fichier du projet dans un répertoire de votre choix.
2. Ouvrez Eclipse.
3. Sélectionnez `File > Open Projects from File System...`.
4. Utilisez le navigateur de fichiers pour sélectionner le dossier du projet décompressé et cliquez sur `Finish`.
5. Naviguez jusqu'à `src/.java` dans l'explorateur de projets Eclipse.
6. Ouvrez le fichier `.java`, vous trouverez le main tout en bas du fichier, et exécutez-le en tant qu'application Java.

### Terminal

Sinon dans le terminal vous tapez :
1. javac -d bin src/main/java/khet/*.java src/main/java/khet/**/*.java
2. java -cp bin khet.Main


## Support

Si vous rencontrez des problèmes lors de la configuration ou de l'exécution du projet, veuillez consulter la documentation appropriée de votre IDE ou soumettre une issue dans le dépôt du projet.