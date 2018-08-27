package question2;

import question1.*;
import java.util.Iterator;
import java.util.List;

public class DebitMaximal implements Visiteur<Integer>{

    public Integer visite(Contributeur c){
        return c.solde(); 
    }

    public Integer visite(GroupeDeContributeurs g){
        int res = g.getChildren().get(0).solde();
        int test = 0;
        for(Cotisant c : g){
            test = c.accepter(this);
            if (test < res)
                res = test;
        }
        return res ;
    }
}