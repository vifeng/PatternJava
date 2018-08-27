package question2;

import question1.*;
import java.util.Set;
import java.util.TreeSet;

public class SansDoublon implements Visiteur<Boolean>{
  public Boolean visite(Contributeur c){
    return this.equals(c); 
  }
  
  public Boolean visite(GroupeDeContributeurs g){
    boolean res = false;
    for(Cotisant c : g){
        res = c.accepter(this);
    }
    return res ;
  }
  
}