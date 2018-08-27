package question3;

import question1.*;

public class TestsTransaction extends junit.framework.TestCase{

    public void testDebitEnErreur(){
        GroupeDeContributeurs g = new GroupeDeContributeurs("g");
        g.ajouter(new Contributeur("g_a",300));
        g.ajouter(new Contributeur("g_b",200));
        g.ajouter(new Contributeur("g_c",100));

        int ancienSolde = g.solde();
        System.out.println(" solde: " + g.solde());

        try{
            g.debit(120);
            fail("une exception est attendue, voir rollbackTransaction");
        }catch(Exception e){
            assertTrue( e instanceof SoldeDebiteurException);
        }

        System.out.println(" solde: " + g.solde());
        assertEquals(" ce solde n'est pas transactionnel, c'est bien une erreur !!! ", ancienSolde, g.solde());

    }

    public void testDebitAvecTransaction(){
        GroupeDeContributeurs g = new GroupeDeContributeurs("g");
        g.ajouter(new Contributeur("g_a",300));
        g.ajouter(new Contributeur("g_b",200));
        g.ajouter(new Contributeur("g_c",100));
        int ancienSolde = g.solde();

        AbstractTransaction transaction = new TransactionDebit(g);
        try{
            transaction.debit(120);
        }catch(Exception e){}
        assertEquals(" Transaction erronée , l'ancien solde n'est pas restitué !!! ", ancienSolde, g.solde());
    }

    public void testDebitAvecTransaction2(){  

        GroupeDeContributeurs g = new GroupeDeContributeurs("g");
        g.ajouter(new Contributeur("g_a",100));
        g.ajouter(new Contributeur("g_b",200));
        g.ajouter(new Contributeur("g_c",300));
        GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
        g1.ajouter(new Contributeur("g1_a",100));
        g1.ajouter(new Contributeur("g1_b",200));
        GroupeDeContributeurs g11 = new GroupeDeContributeurs("g11");
        g11.ajouter(new Contributeur("g11_a",100));
        g1.ajouter(g11);
        GroupeDeContributeurs g2 = new GroupeDeContributeurs("g2");
        g2.ajouter(new Contributeur("g2_a",100));
        g2.ajouter(new Contributeur("g2_b",200));
        g2.ajouter(new Contributeur("g2_c",400));
        g2.ajouter(new Contributeur("g2_d",200));
        GroupeDeContributeurs g22 = new GroupeDeContributeurs("g22");
        g22.ajouter(new Contributeur("g22_a",100));
        g22.ajouter(new Contributeur("g22_b",200));
        g22.ajouter(new Contributeur("g22_c",400));
        g22.ajouter(new Contributeur("g22_d",200));
        GroupeDeContributeurs g222 = new GroupeDeContributeurs("g222");
        g222.ajouter(new Contributeur("g222_a",100));
        g222.ajouter(new Contributeur("g222_b",200));
        g222.ajouter(new Contributeur("g222_c",40));
        g222.ajouter(new Contributeur("g222_d",200));
        g22.ajouter(g222);
        g2.ajouter(g22);
        g.ajouter(g1);
        g.ajouter(g2);

        AbstractTransaction transaction = new TransactionDebit(g);
        int ancienSolde = g.solde();
        try{
            transaction.debit(50);
            fail();
        }catch(Exception e){
        }

        assertTrue(" solde erroné, revoyez la transaction !!! ", g.solde()==ancienSolde);
    }

    public void testTransactionDebitSure5(){  

        GroupeDeContributeurs g = new GroupeDeContributeurs("g");
        g.ajouter(new Contributeur("g_a",100));
        g.ajouter(new Contributeur("g_b",200));
        g.ajouter(new Contributeur("g_c",300));
        GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
        g1.ajouter(new Contributeur("g1_a",100));
        g1.ajouter(new Contributeur("g1_b",200));
        GroupeDeContributeurs g11 = new GroupeDeContributeurs("g11");
        g11.ajouter(new Contributeur("g11_a",100));
        g1.ajouter(g11);
        GroupeDeContributeurs g2 = new GroupeDeContributeurs("g2");
        g2.ajouter(new Contributeur("g2_a",100));
        g2.ajouter(new Contributeur("g2_b",200));
        g2.ajouter(new Contributeur("g2_c",400));
        g2.ajouter(new Contributeur("g2_d",200));
        GroupeDeContributeurs g22 = new GroupeDeContributeurs("g22");
        g22.ajouter(new Contributeur("g22_a",100));
        g22.ajouter(new Contributeur("g22_b",200));
        g22.ajouter(new Contributeur("g22_c",400));
        g22.ajouter(new Contributeur("g22_d",200));
        GroupeDeContributeurs g222 = new GroupeDeContributeurs("g222");
        g222.ajouter(new Contributeur("g222_a",100));
        g222.ajouter(new Contributeur("g222_b",200));
        g222.ajouter(new Contributeur("g222_c",40));
        g222.ajouter(new Contributeur("g222_d",200));
        g22.ajouter(g222);
        g2.ajouter(g22);
        g.ajouter(g1);
        g.ajouter(g2);

        AbstractTransaction transaction = new TransactionDebit(g);
        try{
            transaction.debit(30);
        }catch(Exception e){
            fail();
        }
        int ancienSolde = g.solde();
        transaction = new TransactionDebit(g);
        try{
            transaction.debit(30);
            fail();
        }catch(Exception e){
        }  	    
        assertTrue(" solde erroné, revoyez la transaction !!! ", g.solde()== ancienSolde);

    }

    public void testTransactionsImbriquees(){  

        GroupeDeContributeurs g = new GroupeDeContributeurs("g");
        g.ajouter(new Contributeur("g_a",100));
        g.ajouter(new Contributeur("g_b",200));
        g.ajouter(new Contributeur("g_c",300));
        GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
        g1.ajouter(new Contributeur("g1_a",100));
        g1.ajouter(new Contributeur("g1_b",200));
        GroupeDeContributeurs g11 = new GroupeDeContributeurs("g11");
        g11.ajouter(new Contributeur("g11_a",100));
        g1.ajouter(g11);
        GroupeDeContributeurs g2 = new GroupeDeContributeurs("g2");
        g2.ajouter(new Contributeur("g2_a",100));
        g2.ajouter(new Contributeur("g2_b",200));
        g2.ajouter(new Contributeur("g2_c",400));
        g2.ajouter(new Contributeur("g2_d",200));
        GroupeDeContributeurs g22 = new GroupeDeContributeurs("g22");
        g22.ajouter(new Contributeur("g22_a",100));
        g22.ajouter(new Contributeur("g22_b",200));
        g22.ajouter(new Contributeur("g22_c",400));
        g22.ajouter(new Contributeur("g22_d",200));
        GroupeDeContributeurs g222 = new GroupeDeContributeurs("g222");
        g222.ajouter(new Contributeur("g222_a",100));
        g222.ajouter(new Contributeur("g222_b",200));
        g222.ajouter(new Contributeur("g222_c",400));
        g222.ajouter(new Contributeur("g222_d",200));
        g22.ajouter(g222);
        g2.ajouter(g22);
        g.ajouter(g1);
        g.ajouter(g2);

        int ancienSolde = g.solde();
        AbstractTransaction transaction = new TransactionDebit(g);
        try{
            transaction.beginTransaction();
            transaction.debit(30);
            transaction.beginTransaction();
            transaction.debit(30);
            transaction.endTransaction();
            transaction.endTransaction();
        }catch(Exception e){
            fail("reyoyez les transactions imbriquées");
        }
        g.credit(60);
        assertEquals(" solde erroné, revoyez la transaction !!! ", ancienSolde, g.solde());
    }
}

