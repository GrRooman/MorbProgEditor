
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JSplitPane;
import java.awt.Color;

public class new extends JFrame 
{
static new thenew;

JPanel pnPanel0;
JTextArea taArea0;

JPanel pnPanel2;
JProgressBar pbProgressBar4;
JLabel lbLabel7;
JLabel lbLabel8;
JSplitPane sppSplitPane0;

JPanel pnPanel3;
JTextArea taArea2ds;

JPanel pnPanel4;
/**
 */
public static void main( String args[] ) 
{
   try 
   {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
   }
   catch ( ClassNotFoundException e ) 
   {
   }
   catch ( InstantiationException e ) 
   {
   }
   catch ( IllegalAccessException e ) 
   {
   }
   catch ( UnsupportedLookAndFeelException e ) 
   {
   }
   thenew = new new();
} 

/**
 */
public new() 
{
   super( "TITLE" );

   pnPanel0 = new JPanel();
   GridBagLayout gbPanel0 = new GridBagLayout();
   GridBagConstraints gbcPanel0 = new GridBagConstraints();
   pnPanel0.setLayout( gbPanel0 );

   taArea0 = new JTextArea(2,10);
   gbcPanel0.gridx = 0;
   gbcPanel0.gridy = 1;
   gbcPanel0.gridwidth = 15;
   gbcPanel0.gridheight = 17;
   gbcPanel0.fill = GridBagConstraints.BOTH;
   gbcPanel0.weightx = 1;
   gbcPanel0.weighty = 1;
   gbcPanel0.anchor = GridBagConstraints.NORTH;
   gbPanel0.setConstraints( taArea0, gbcPanel0 );
   pnPanel0.add( taArea0 );

   pnPanel2 = new JPanel();
   pnPanel2.setBorder( BorderFactory.createTitledBorder( "" ) );
   GridBagLayout gbPanel2 = new GridBagLayout();
   GridBagConstraints gbcPanel2 = new GridBagConstraints();
   pnPanel2.setLayout( gbPanel2 );

   pbProgressBar4 = new JProgressBar( );
   gbcPanel2.gridx = 17;
   gbcPanel2.gridy = 0;
   gbcPanel2.gridwidth = 5;
   gbcPanel2.gridheight = 1;
   gbcPanel2.fill = GridBagConstraints.BOTH;
   gbcPanel2.weightx = 0;
   gbcPanel2.weighty = 0;
   gbcPanel2.anchor = GridBagConstraints.NORTH;
   gbPanel2.setConstraints( pbProgressBar4, gbcPanel2 );
   pnPanel2.add( pbProgressBar4 );

   lbLabel7 = new JLabel( ""  );
   lbLabel7.setIcon( new ImageIcon( "" ) );
   gbcPanel2.gridx = 0;
   gbcPanel2.gridy = 0;
   gbcPanel2.gridwidth = 12;
   gbcPanel2.gridheight = 1;
   gbcPanel2.fill = GridBagConstraints.HORIZONTAL;
   gbcPanel2.weightx = 1;
   gbcPanel2.weighty = 0;
   gbcPanel2.anchor = GridBagConstraints.NORTH;
   gbPanel2.setConstraints( lbLabel7, gbcPanel2 );
   pnPanel2.add( lbLabel7 );

   lbLabel8 = new JLabel( ""  );
   gbcPanel2.gridx = 12;
   gbcPanel2.gridy = 0;
   gbcPanel2.gridwidth = 5;
   gbcPanel2.gridheight = 1;
   gbcPanel2.fill = GridBagConstraints.BOTH;
   gbcPanel2.weightx = 1;
   gbcPanel2.weighty = 1;
   gbcPanel2.anchor = GridBagConstraints.NORTH;
   gbPanel2.setConstraints( lbLabel8, gbcPanel2 );
   pnPanel2.add( lbLabel8 );
   gbcPanel0.gridx = 0;
   gbcPanel0.gridy = 19;
   gbcPanel0.gridwidth = 20;
   gbcPanel0.gridheight = 1;
   gbcPanel0.fill = GridBagConstraints.BOTH;
   gbcPanel0.weightx = 1;
   gbcPanel0.weighty = 0;
   gbcPanel0.anchor = GridBagConstraints.SOUTH;
   gbPanel0.setConstraints( pnPanel2, gbcPanel0 );
   pnPanel0.add( pnPanel2 );

   sppSplitPane0 = new JSplitPane( );
   sppSplitPane0.setDividerLocation( 159 );
   sppSplitPane0.setForeground( new Color( 0,0,0 ) );
   sppSplitPane0.setLastDividerLocation( -1 );

   pnPanel3 = new JPanel();
   GridBagLayout gbPanel3 = new GridBagLayout();
   GridBagConstraints gbcPanel3 = new GridBagConstraints();
   pnPanel3.setLayout( gbPanel3 );

   taArea2ds = new JTextArea(2,10);
   gbcPanel3.gridx = 0;
   gbcPanel3.gridy = 0;
   gbcPanel3.gridwidth = 10;
   gbcPanel3.gridheight = 1;
   gbcPanel3.fill = GridBagConstraints.BOTH;
   gbcPanel3.weightx = 1;
   gbcPanel3.weighty = 1;
   gbcPanel3.anchor = GridBagConstraints.NORTH;
   gbPanel3.setConstraints( taArea2ds, gbcPanel3 );
   pnPanel3.add( taArea2ds );
   sppSplitPane0.setLeftComponent(pnPanel3);

   pnPanel4 = new JPanel();
   GridBagLayout gbPanel4 = new GridBagLayout();
   GridBagConstraints gbcPanel4 = new GridBagConstraints();
   pnPanel4.setLayout( gbPanel4 );
   sppSplitPane0.setRightComponent(pnPanel4);
   gbcPanel0.gridx = 0;
   gbcPanel0.gridy = 18;
   gbcPanel0.gridwidth = 20;
   gbcPanel0.gridheight = 1;
   gbcPanel0.fill = GridBagConstraints.BOTH;
   gbcPanel0.weightx = 0;
   gbcPanel0.weighty = 0;
   gbcPanel0.anchor = GridBagConstraints.SOUTH;
   gbPanel0.setConstraints( sppSplitPane0, gbcPanel0 );
   pnPanel0.add( sppSplitPane0 );

   setDefaultCloseOperation( EXIT_ON_CLOSE );

   setContentPane( pnPanel0 );
   pack();
   setVisible( true );
} 
} 
