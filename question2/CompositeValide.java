package question2;

import question1.*;
import question1.Visiteur;

public class CompositeValide implements Visiteur<Boolean>{
  // Le solde de chaque contributeur doit être supérieur ou égal à 0 
  // et il n’existe pas de groupe n’ayant pas de contributeurs.
  
  public Boolean visite(Contributeur c){
    return c.solde() >= 0 ;
  }
  
  public Boolean visite(GroupeDeContributeurs g){
    boolean res = false;
    if (g.nombreDeCotisants() ==0) return false;
    for(Cotisant c : g){
        res = c.accepter(this);
    }
    return res ;
  }
}