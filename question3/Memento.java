package question3;

import question1.*;
import question2.*;
import java.util.*;

public class Memento {
    // Note : Un usage du patron Memento, 
    //        d’un premier visiteur pour la sauvegarde et 
    //        d’un second pour la restitution du composite, 
    //        représentent une solution possible. 
    private HashMap<Cotisant,Integer> state; 

    public Memento(Cotisant c) {
        // sauvegarde du solde de c
        state = c.accepter(new Sauvegarde());
    }

    public void setState(Cotisant c) {
        // restitution du solde de c 
        c.accepter(new Restitution(state));
    }

}