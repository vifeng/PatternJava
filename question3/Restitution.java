package question3;

import question1.*;
import java.util.*;

public class Restitution implements Visiteur<Cotisant>{
    private HashMap<Cotisant,Integer> state;

    public Restitution(HashMap<Cotisant,Integer> state){
        this.state = state;
    }

    public Cotisant visite(Contributeur c){
        c.affecterSolde(state.get(c)); 
        return c; 
    }

    public Cotisant visite(GroupeDeContributeurs g){
        for(Cotisant c : g.getChildren()){
            c.accepter(this); 
        } 
        return g; 
    }
}

