package question4;

import question1.Contributeur;
import question1.GroupeDeContributeurs;
import question2.*;
import question3.*;
import static question2.Main.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.jdom.*;
import org.jdom.output.*;
import java.io.ByteArrayOutputStream;

public class IHM extends JFrame {

    private JTextArea resultat = new JTextArea("", 7,60);
    private JButton debiter = new JButton("débiter");
    private JButton crediter = new JButton("créditer");
    private JTextField somme = new JTextField(4);

    private GroupeDeContributeurs g;

    public IHM() {
        this.setTitle("Cotisant");
        Container container = this.getContentPane();
        somme.setText("40");
        container.setLayout(new BorderLayout());

        container.add(resultat, BorderLayout.NORTH);
        JPanel p = new JPanel(new FlowLayout());
        p.add(somme);
        p.add(debiter);
        p.add(crediter);
        container.add(p, BorderLayout.SOUTH);

        g = new GroupeDeContributeurs("g");
        g.ajouter(new Contributeur("g_a",100));
        g.ajouter(new Contributeur("g_b",50));
        g.ajouter(new Contributeur("g_c",150));
        GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
        g1.ajouter(new Contributeur("g1_a1",70));
        g1.ajouter(new Contributeur("g1_b1",200));
        g.ajouter(g1);
       
        try{
            resultat.setText(Main.arbreXML(g)); //actualiser();
        }catch(Exception e){}

        debiter.addActionListener(new ActionListener(){ 
                public void actionPerformed(ActionEvent ae){                             
                    AbstractTransaction transaction = new TransactionDebit(g); 
                    try{                             
                        transaction.debit(Integer.parseInt(somme.getText())); 
                        resultat.setText(Main.arbreXML(g)); 
                    }catch(Exception e){} 
                } 
            }
        );
        crediter.addActionListener(new ActionListener(){ 
                public void actionPerformed(ActionEvent ae){                             
                    AbstractTransaction transaction = new TransactionDebit(g);                            
                    try{          
                        transaction.crediter(Integer.parseInt(somme.getText())); 
                        resultat.setText(Main.arbreXML(g)); 
                    }catch(Exception e){} 
                } 
            }
        );
            
        this.pack();
        this.setVisible(true);
    }
        
    public JTextField getSomme(){ 
        return somme;
    }

    public static void main() {
        new IHM();    
    }    

}