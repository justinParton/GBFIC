package vimeo;


	import java.awt.Color;
	import java.awt.Font;
	import java.awt.GridBagConstraints;
	import java.awt.GridBagLayout;
	import java.awt.Insets;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	import javax.swing.JTextArea;


	@SuppressWarnings({ "unused", "serial" })
class resultsPanel extends JPanel
	{ 
	 //Data declarations 
	 //vimPanel	
	 private JLabel summaryLabel; 
	 JTextArea results;
	 
	public resultsPanel() 
	 {
		  //Text Labels

	    summaryLabel = new JLabel ("Information:              ");
	    summaryLabel.setFont(new Font("serif",Font.PLAIN,16));      
	 
	    
	    //Text Box Label
	    results = new JTextArea (12, 35);

	 
	    //Grid Layouts
	    setLayout(new GridBagLayout());
	    GridBagConstraints constraints = new GridBagConstraints();

	    
	    constraints.gridx = 0;
	    constraints.gridy = 9;
	    constraints.gridwidth = 2;
	    constraints.gridheight = 1;
	    constraints.insets = new Insets (35, 5, 90, 5); 
	    constraints.anchor = GridBagConstraints.CENTER;
	    add (summaryLabel, constraints);
	    
	    constraints.gridx = 0;
	    constraints.gridy = 9;
	    constraints.gridwidth = 2;
	    constraints.gridheight = 1;
	    constraints.insets = new Insets (35, 5, 5, 5); 
	    constraints.anchor = GridBagConstraints.CENTER;
	    add (results, constraints);
	    
	    setBackground (Color.LIGHT_GRAY);
	    
	 }
	 
	}
