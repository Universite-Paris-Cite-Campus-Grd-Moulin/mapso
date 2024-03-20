package model.enums;

public enum TypeInteraction {
    NONE, // Aucune interaction: le laser continue tout droit
    REFLEXION, // Le laser est réfléchi par une pièce
    DIVISION, // Le laser est divisé en deux par la pièce Horus
    ABSORPTION // Le laser est absorbé par une pièce, par exemple, le Pharaon
}
