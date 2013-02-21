//Package Declaration //
package VOIP;

//Java Package Support //
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.InterruptedException;
import javax.swing.*;
import javax.sound.sampled.LineUnavailableException;

//Internal Package Support //
// { Not Applicable }

/**
 * 
 * VOIP/VOIP_Gui.java
 * 
 * @author(s)	: Ian Middleton, Zach Ogle, Matthew J Swann
 * @version  	: 1.0
 * Last Update	: 2013-02-20
 * Update By	: Matthew J Swann
 * 
 * 
 */

public class VOIP_Gui extends JFrame{
	
	// Static Variables
	private static final long serialVersionUID = 1L;

	// GUI Variables
	private JButton button_one, button_two, button_three;
	
	private JLabel label_one, label_two, label_three, label_four,
					label_five, label_six;
	
	private JPanel panel_one, primary;
    
	private JTextArea text_area_one;
	
	private JTextField text_field_one, text_field_two;
	
	private TransmitListener TransmitListener;
	private KillSwitch KillSwitch;
	
	// VOIP Variables
	private SocketSender sender;
	private SocketReceiver receiver;
	
	/**
     * Constructor.
     */
	public VOIP_Gui() throws IOException,
						IOException, LineUnavailableException{
		
		// JButtons
		this.button_one = new JButton(" :: Connect");
		this.button_one.setFont(new Font("Helvetica", Font.BOLD, 12));
		this.button_one.setPreferredSize(new Dimension(150,50));
		TransmitListener = new TransmitListener();
		this.button_one.addActionListener(TransmitListener);
		
		this.button_two = new JButton("{ Disconnect }");
		KillSwitch      = new KillSwitch();
		this.button_two.addActionListener(KillSwitch);
						
		// JLabels
		this.label_one   = new JLabel("Enter IP Connection ::");
		this.label_two   = new JLabel("");
		this.label_three = new JLabel("");
		this.label_four  = new JLabel("");
		this.label_five  = new JLabel("");
		
		// JPanels
		this.panel_one = new JPanel();
		this.primary   = new JPanel();
				
		// JTextArea
		this.text_area_one = new JTextArea();
				
		// JTextField
		this.text_field_one = new JTextField(15);
		this.text_field_two = new JTextField(15);
		
		// Control Block
		this.compile_components();
		
	} // end_voip_gui_constructor
	
	
    /**
     * Places components on the appropriate panels.
     */	
	public void compile_components(){
		
		this.getContentPane().add(primary);
		
		//this.update_area(); IF NECESSARY RE FRESH
		
		// Panel One additions
		this.panel_one.setLayout(new GridLayout(4, 4));
		this.panel_one.add(this.label_one);	
		this.panel_one.add(this.label_two);
		this.panel_one.add(this.text_field_one);
		this.panel_one.add(this.button_one);
		this.panel_one.add(this.label_three);
		this.panel_one.add(this.label_four);
		this.panel_one.add(this.label_five);
		this.panel_one.add(this.button_two);

		
		// Primary Panel additions
		this.primary.setPreferredSize(new Dimension(4*100,3*100));
		this.primary.add(this.panel_one);
	} // end_voip_gui_compile_components
	
	
	/**
	 * Updates the UI. May not be necessary
	 */
	public void update_area(){
		
		// re-establish variables
		
		// refresh UI
	}
	
	/**
	 * Action listener tied to the ____________ button.
	 */
	private class TransmitListener implements ActionListener{
		
		public void actionPerformed(ActionEvent event){
			
			String ip_address = text_field_one.getText();
			
			try{
				sender = new SocketSender(ip_address);
				receiver = new SocketReceiver();
			}
			catch (Exception e){
				// skip it
			}	
			sender.start();
			receiver.start();
			
			System.out.println(ip_address);
			
			text_field_one.setText("");
			
		} // end TransmitListener.actionPerformed()
	}// end TransmitListener()
	
	
	private class KillSwitch implements ActionListener{
		
		public void actionPerformed(ActionEvent event){
			
			System.exit(0);
		}		
	}
		
	
		/**
		 * 
		 * 
		 * Main run.
		 * @param args
		 * @throws IOException
		 * 
		 */
	    public static void main(String[] args) throws IOException,
	    							IOException, LineUnavailableException{
			VOIP_Gui teh_gui = new VOIP_Gui();
			
			teh_gui.setTitle("VOIP to end all VOIP's");
			teh_gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			teh_gui.pack();
			teh_gui.setVisible(true);
		} // end_main()		
	} // end_class_declaration