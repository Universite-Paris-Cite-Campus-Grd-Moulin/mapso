package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import model.enums.Couleur;
import model.enums.Direction;
import model.enums.TypeDePion;

public class PlateauLoader {

    public static Plateau loadFromFile(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(filePath));
        Plateau plateau = new Plateau();

        for (String line : lines) {
            String[] parts = line.split(",\\s*");
            int y = Integer.parseInt(parts[0]);
            int x = Integer.parseInt(parts[1]);
            String token = parts[2];

            Pion pion = parseToken(token, x, y);
            if (pion != null) {
                plateau.placePion(x, y, pion);
            }
        }
        return plateau;
    }

    private static Pion parseToken(String token, int x, int y) {
        if (token.length() < 3) return null;
    
        String typeCode = token.substring(0, 2);
        char dirCode = token.charAt(2);
        Direction direction = parseDirection(dirCode);
        
        switch (typeCode) {
            case "PyR": return new Pion(TypeDePion.PYRAMIDE, direction, Couleur.ROUGE, x, y);
            case "PyJ": return new Pion(TypeDePion.PYRAMIDE, direction, Couleur.JAUNE, x, y);
            case "ObR": return new Pion(TypeDePion.OBELISQUE, direction, Couleur.ROUGE, x, y);
            case "ObJ": return new Pion(TypeDePion.OBELISQUE, direction, Couleur.JAUNE, x, y);
            case "DjR": return new Pion(TypeDePion.DJED, direction, Couleur.ROUGE, x, y);
            case "DjJ": return new Pion(TypeDePion.DJED, direction, Couleur.JAUNE, x, y);
            case "HoR": return new Pion(TypeDePion.HORUS, direction, Couleur.ROUGE, x, y);
            case "HoJ": return new Pion(TypeDePion.HORUS, direction, Couleur.JAUNE, x, y);
            case "PhR": return new Pion(TypeDePion.PHARAON, direction, Couleur.ROUGE, x, y);
            case "PhJ": return new Pion(TypeDePion.PHARAON, direction, Couleur.JAUNE, x, y);
            default: return null; 
        }
    }    

    private static Direction parseDirection(char dirCode) {
        return switch (dirCode) {
            case 'N' -> Direction.NORD;
            case 'E' -> Direction.EST;
            case 'S' -> Direction.SUD;
            case 'O' -> Direction.OUEST;
            default -> Direction.NONE; 
        };
    }
    
}
