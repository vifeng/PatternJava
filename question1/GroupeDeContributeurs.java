package question1;

import java.util.Iterator;
import java.util.NoSuchElementException;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class GroupeDeContributeurs extends Cotisant implements Iterable<Cotisant>{
  private List<Cotisant> liste;
  
  public GroupeDeContributeurs(String nomDuGroupe){
    super(nomDuGroupe);
    liste = new ArrayList();
  }
  
  public void ajouter(Cotisant cotisant){
    if (cotisant != null){
      liste.add(cotisant);
      cotisant.parent = this;
    }
  }
  
  public int nombreDeCotisants(){
    int nombre = 0;
    Iterator<Cotisant> li = liste.iterator();
    while(li.hasNext()){
        nombre += li.next().nombreDeCotisants();
    }
    return nombre;
  }
  
  public String toString(){
    String str = new String();
    Iterator<Cotisant> li = liste.iterator();
    while(li.hasNext()){
        str += li.next().toString();
    }
    return str;
  }
  
  public List<Cotisant> getChildren(){
      List<Cotisant> children = new ArrayList();
      Iterator<Cotisant> li = liste.iterator();
    while(li.hasNext()){
            children.add(li.next());
    }
    return children;
  }
  
  public void debit(int somme) throws SoldeDebiteurException{
    Iterator<Cotisant> li = liste.iterator();
    while(li.hasNext()){
        li.next().debit(somme);
    }
  }
  
  public void credit(int somme){
    Iterator<Cotisant> li = liste.iterator();
    while(li.hasNext()){
        li.next().credit(somme);
    }
  }
  
  public int solde(){
    int solde = 0;
    Iterator<Cotisant> li = liste.iterator();
    while(li.hasNext()){
        solde += li.next().solde();
    }
    return solde;
  }
  
  // méthodes fournies
  
 public Iterator<Cotisant> iterator(){
    return new GroupeIterator(liste.iterator());
  }
  
  private class GroupeIterator implements Iterator<Cotisant>{
    private Stack<Iterator<Cotisant>> stk;
    
    public GroupeIterator(Iterator<Cotisant> iterator){
      this.stk = new Stack<Iterator<Cotisant>>();
      this.stk.push(iterator);
    }
    
    public boolean hasNext(){
      if(stk.empty()){
        return false;
      }else{
         Iterator<Cotisant> iterator = stk.peek();
         if( !iterator.hasNext()){
           stk.pop();
           return hasNext();
         }else{
           return true;
         }
      }
    }
    
    public Cotisant next(){
      if(hasNext()){
        Iterator<Cotisant> iterator = stk.peek();
        Cotisant cpt = iterator.next();
        if(cpt instanceof GroupeDeContributeurs){
          GroupeDeContributeurs gr = (GroupeDeContributeurs)cpt;
          stk.push(gr.liste.iterator());
        }
        return cpt;
      }else{
        throw new NoSuchElementException();
      }
    }
    public void remove(){
      throw new UnsupportedOperationException();
    }
  }
  

  public <T> T accepter(Visiteur<T> visiteur){
    return visiteur.visite(this);
  }
  

}
