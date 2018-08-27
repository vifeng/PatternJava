package question1;

public abstract interface Visiteur<T> {
    
    public abstract T visite(Contributeur c);
    public abstract T visite(GroupeDeContributeurs g);
    
}
