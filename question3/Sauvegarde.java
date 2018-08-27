package question3;

import question1.*;
import java.util.*;

public class Sauvegarde implements Visiteur<HashMap<Cotisant, Integer>>{

    public HashMap<Cotisant, Integer> visite(Contributeur c){
        HashMap<Cotisant,Integer> sauvegarde = new HashMap<Cotisant, Integer>(); 
        sauvegarde.put(c, c.solde());
        return sauvegarde;
    }

    public HashMap<Cotisant, Integer> visite(GroupeDeContributeurs g){
        HashMap<Cotisant, Integer> sauvegarde = new HashMap<Cotisant, Integer>(); 
        for(Cotisant c : g){
            sauvegarde.putAll(c.accepter(this));
        }

        return sauvegarde;
    }
}
