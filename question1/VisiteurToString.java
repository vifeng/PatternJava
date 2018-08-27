package question1;

public class VisiteurToString implements Visiteur<String>{

  public String visite(Contributeur cpt){
    return "<Contributeur : (" + cpt.nom() + ", " + cpt.solde() + ")>";
  }
  
  public String visite(GroupeDeContributeurs grp){
    String res = "<Groupe " + grp.nom() + " : (";
    for( Cotisant c : grp)
      res = res + c.accepter(this);
    
    return res + ">";
  }
}
