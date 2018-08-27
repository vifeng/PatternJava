package question4;


import javax.swing.*;
import java.awt.*;

public class TestIHM extends junit.framework.TestCase
{

  private JFrame f;
  
  
  protected void setUp() throws java.lang.Exception{
		f = new IHM();
		f.pack();
		f.setVisible(true);

  }
  
  protected void tearDown()  throws java.lang.Exception{
    f.setVisible(false);
    f.dispose();
//     if(f != null) {
//         EventQueue.invokeLater(new Runnable() {
//             public void run() {
//                 f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
//             }
//         });
//     }
// 		WindowEvent wev = new WindowEvent(f, WindowEvent.WINDOW_CLOSING);
//     Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);

  }
  
  public void testIHM() throws Exception{ // juste le bon IHM
    try{
  		Container panel = f.getContentPane();
  		Component[] components = panel.getComponents();
  		assertEquals(components.length, 2);

  		assertTrue(" Est-ce la bonne IHM ?", components[0] instanceof JTextArea);
  		assertTrue(" Est-ce la bonne IHM ?", components[1] instanceof JPanel);
  		
   		Component[] subComponents = ((JPanel)components[1]).getComponents();
   		assertEquals(subComponents.length, 3);
   		assertTrue(" Est-ce la bonne IHM ?",subComponents[0] instanceof JTextField);
   	  assertTrue(" Est-ce la bonne IHM ?",subComponents[1] instanceof JButton); // debiter
   		assertTrue(" Est-ce la bonne IHM ?",subComponents[2] instanceof JButton); // crediter
  			
    }catch(Exception e){
      fail("exception inattendue ! " + e.getClass().getName());
    }
	}
}
