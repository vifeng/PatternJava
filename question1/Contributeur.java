package question1;

public class Contributeur extends Cotisant{
    private int solde;

    public Contributeur(String nom, int somme){
        super(nom);
        if (somme < 0) throw new RuntimeException("la somme allou�e lors de la cr�ation ne peut �tre n�gative ???");
        solde = somme;
    }

    public int solde(){
        return this.solde;
    }

    public int nombreDeCotisants(){
        return 1;
    }

    public void debit(int somme) throws SoldeDebiteurException{
        if (somme < 0) throw new RuntimeException("nombre n�gatif !!!");
        if (solde-somme < 0) throw new SoldeDebiteurException("SoldeDebiteur");
        solde -= somme;
    }

    /**
     * throws RuntimeException new RuntimeException("nombre n�gatif !!!");
     */
    public  void credit(int somme){
        if (somme < 0) throw new RuntimeException("nombre n�gatif !!!");
        solde += somme;
    }

    /**
     * throws RuntimeException new RuntimeException("nombre n�gatif !!!");
     */
    public void affecterSolde(int somme){
        // if(somme <0) throw new RuntimeException("nombre n�gatif !!!");
        try{
            debit(solde()); credit(somme);// mode �l�gant ... 
        }catch(SoldeDebiteurException sde){ 
            // exception peu probable
            this.solde = somme; // mode efficace ...
        }
    }

    public <T> T accepter(Visiteur<T> visiteur){
        return visiteur.visite(this);
    }

    public String toString(){
        return "<Contributeur : " + nom + "," + solde + ">";
    }

}
