package j;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gui extends JFrame {
	
    private String [] ButtonTexts ;
	private JButton [] B ;
    private JLabel L ;
    private JTextField T ;
    private JPanel P ;
    String Path = "" ;
    private standard_quantization sq ;
    private Decompresion je;
    public Gui()
    {
     P = new JPanel();	
     L = new JLabel("Enter # Of Levels");
     T = new JTextField(5);
     P.add( L );
     P.add( T );
     setTitle("Quantizer");
     setSize( 400 , 100 );
     setVisible( true );
     set_Buttons();
     setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
     P.setLayout( new FlowLayout() );
     add( P );
    }
    public void set_Buttons()
    {
     ButtonTexts = new String [] { "Compress" , "Decompress" };
     B = new JButton [ ButtonTexts.length ];
     for( int i=0; i<ButtonTexts.length; i++)
     {
     B[i] = new JButton( ButtonTexts[i] );
     B[i].addActionListener( new Button_Handler() );
     //B[i].setSize( 10 , 10 );
     P.add( B[i] );
     }
    repaint();
    revalidate();
    }
     class Button_Handler implements ActionListener{
    	public void actionPerformed( ActionEvent AE)
    	{
    	 if( AE.getSource() == B[0] )
    	 {
    	  System.out.println("Compreession");
    	  JFileChooser FC = new JFileChooser(".");
    	  
    	  int Result = JFileChooser.APPROVE_OPTION ;
    	  
    	  if( Result == FC.showOpenDialog( null ) )
    		  Path = FC.getSelectedFile().getAbsolutePath();
    	  sq = new standard_quantization();
    	  standard_quantization.readImage( Path );
  		  sq.checkRange( Integer.parseInt( T.getText() ) );
  		  sq.writeToFile();
    	 }
    	 else if( AE.getSource() == B[1] )
    	 {
    		 JFileChooser FC = new JFileChooser(".");
       	  
       	  int Result = JFileChooser.APPROVE_OPTION ;
       	  
       	  if( Result == FC.showOpenDialog( null ) )
       		  
       		  Path = FC.getSelectedFile().getAbsolutePath();
       	  
    		 je = new Decompresion();
    		 try {
				je.readFile(Path);
				je.writeImage();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		 
       	  System.out.println("Decompreession");
       	 }
    	}
    }
}
