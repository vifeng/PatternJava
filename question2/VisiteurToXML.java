package question2;

import question1.Cotisant;
import question1.Contributeur;
import question1.GroupeDeContributeurs;
import question1.Visiteur;
import org.jdom.*;

public class VisiteurToXML implements Visiteur<Element> {

    public VisiteurToXML() {}

    public Element visite(Contributeur c){
        Element elt = new Element("contributeur");
        elt.setAttribute("nom", c.nom());
        elt.setAttribute("solde", Integer.toString(c.solde()));
        return elt;
    }

    public Element visite(GroupeDeContributeurs g) {
        Element elt = new Element("groupe");
        elt.setAttribute("nom", g.nom());
        elt.setAttribute("solde", Integer.toString(g.solde()));
        for (Cotisant c  : g.getChildren()) {
            elt.addContent(c.accepter(this));
        }
        return elt;
    }  
}

