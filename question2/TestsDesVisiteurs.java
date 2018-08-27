package question2;

import java.util.*;
import question1.*;

public class TestsDesVisiteurs extends junit.framework.TestCase{

    public void testACompleter(){
        //fail(" cette méthode de tests, est à compléter....");
        
        // test pour CompositeValide
            // Le solde de chaque contributeur doit être supérieur ou égal à 0 
            // et il n’existe pas de groupe n’ayant pas de contributeurs
            try{
            GroupeDeContributeurs g = new GroupeDeContributeurs("g");
            Contributeur c = new Contributeur("g_a",10);
            g.ajouter(c);
            assertTrue("Solde incorrect, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));
                       
            GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
            g.ajouter(g1);
            assertFalse(" Ce composite n'est pas valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));

            g1.ajouter(new Contributeur("c",100));
            assertTrue(" Ce composite est valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));
            
            g.ajouter(g1);
            assertTrue(" Ce composite est valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));
        
        }catch(Exception e){
            fail("exception inattendue !!! " + e.getMessage());
        }
        
        
        // test pour DebitMaximal
        try{
            GroupeDeContributeurs g4 = new GroupeDeContributeurs("g");
            Contributeur c1 = new Contributeur("g_a",50);
            Contributeur c2 = new Contributeur("g_b",100);
            Contributeur c3 = new Contributeur("g_a",1000);
            g4.ajouter(c1);
            g4.ajouter(c2);
            g4.ajouter(c3);
            assertEquals("DebitMaximal faux!!!", 50 ,(int)g4.accepter(new DebitMaximal()));
            
            GroupeDeContributeurs g5 = new GroupeDeContributeurs("g1");
            Contributeur c4 = new Contributeur("g_a",10);
            Contributeur c5 = new Contributeur("g_b",100);
            g5.ajouter(c4);
            g5.ajouter(c5);
            g4.ajouter(g5);
            assertEquals("DebitMaximal faux!!!", 10 ,(int)g4.accepter(new DebitMaximal()));        
        }catch(Exception e){
            fail("exception inattendue !!! " + e.getMessage());
        }
    }

    public void testTroisContributeurs(){
        try{
            GroupeDeContributeurs g = new GroupeDeContributeurs("g");
            g.ajouter(new Contributeur("g_a",100));
            g.ajouter(new Contributeur("g_b",200));
            g.ajouter(new Contributeur("g_c",300));
            assertTrue(" Ce composite est valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));
        }catch(Exception e){
            fail("exception inattendue !!! " + e.getMessage());
        }
    }

    public void testCompositeValide(){
        try{
            GroupeDeContributeurs g = new GroupeDeContributeurs("g");
            assertFalse(" Ce composite n'est pas valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));

            GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
            g.ajouter(g1);
            assertFalse(" Ce composite n'est pas valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));

            g1.ajouter(new Contributeur("c",100));
            assertTrue(" Ce composite est valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));

        }catch(Exception e){
            fail("exception inattendue !!! " + e.getMessage());
        }
    }

    public void testTroisContributeursUnGroupe(){
        try{
            GroupeDeContributeurs g = new GroupeDeContributeurs("g");
            g.ajouter(new Contributeur("g_a",100));
            g.ajouter(new Contributeur("g_b",200));
            g.ajouter(new Contributeur("g_c",300));
            assertTrue(" Ce composite est valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));
            assertEquals(" Revoyez DébitMaximal !!!", new Integer(100), g.accepter(new DebitMaximal()));
            GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
            g.ajouter(g1);
            assertFalse(" Ce composite n'est pas valide, revoyez CompositeValide !!!", g1.accepter(new CompositeValide()));
        }catch(Exception e){
            fail("exception inattendue !!! " + e.getMessage());
        }
    }

    public void testUnContributeurUnGroupeAvecLeMemeNom(){
        try{
            GroupeDeContributeurs g = new GroupeDeContributeurs("g_a");
            g.ajouter(new Contributeur("g_a",100));
            g.ajouter(new Contributeur("g_b",200));
            g.ajouter(new Contributeur("g_c",300));
            g.ajouter(new Contributeur("g_d",80));
            assertTrue(" Ce composite est valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));
            assertFalse(" Ce composite a au moins un doublon, revoyez SansDoublon !!!", g.accepter(new SansDoublon()));
        }catch(Exception e){
            fail("exception inattendue !!! " + e.getMessage());
        }
    }
}

