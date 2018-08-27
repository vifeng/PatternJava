package question2;

import question1.*;
import org.jdom.*;
import org.jdom.output.*;
import java.io.ByteArrayOutputStream;
public class Main {
    public static void main(String[] args) throws Exception {
        GroupeDeContributeurs g = new GroupeDeContributeurs("g");
        g.ajouter(new Contributeur("g_a",100));
        g.ajouter(new Contributeur("g_b",200));
        g.ajouter(new Contributeur("g_c",300));
        GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
        //GroupeDeContributeurs g2 = new GroupeDeContributeurs("g2");
        g1.ajouter(new Contributeur("g1_a1",100));
        g1.ajouter(new Contributeur("g1_b1",200));
        //g1.ajouter(g2);
        g.ajouter(g1);
        if(g.accepter(new CompositeValide())){
            System.out.println(arbreXML(g));
            if(g.accepter(new DebitMaximal())>= 50){
                g.debit(50);
                System.out.println(arbreXML(g));
            }
        }else{
            System.out.println("Composite invalide"); 
        }
    }

    /** Retourne dans une String, un arbre en XML, utilisée en question4. */
    public static String arbreXML(Cotisant c) throws Exception{
        ByteArrayOutputStream baos = null;
        String result = new String();
        Element racine = c.accepter(new VisiteurToXML());
        Document document = new Document(racine);        
        XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
        try{
            baos = new ByteArrayOutputStream();
            out.output(document, baos);
            result = baos.toString();
        }finally{
            baos.close();
        }
        return result;
    }
}
