package vimeo;


	import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


	@SuppressWarnings({ "unused", "serial" })
class resultsPanel extends JPanel
	{ 
	 //Data declarations 
	 //vimPanel	
	 private JLabel summaryLabel;
	 static JTextArea textarea = new JTextArea();

	 static JScrollPane results = new JScrollPane(textarea);
	 
	 public resultsPanel() 
	 {
		  //Text Labels
		final int fontSize = 11; 
		textarea.setLineWrap(true);
		results.setPreferredSize(new Dimension(400, 400));
	    summaryLabel = new JLabel ("Information:              ");
	    summaryLabel.setFont(new Font("serif",Font.PLAIN,16));      
	    
	    

	    //Grid Layouts
	    setLayout(new GridBagLayout());
	    GridBagConstraints constraints = new GridBagConstraints();

	    
	    constraints.gridx = 0;
	    constraints.gridy = 1;
	    constraints.gridwidth = 2;
	    constraints.gridheight = 1;
	    constraints.insets = new Insets (5, 5, 5, 5); 
	    constraints.anchor = GridBagConstraints.CENTER;
	    add (summaryLabel, constraints);
	    
	    constraints.gridx = 0;
	    constraints.gridy = 2;
	    constraints.gridwidth = 2;
	    constraints.gridheight = 1;
	    constraints.insets = new Insets (5, 5, 5, 5); 
	    constraints.anchor = GridBagConstraints.CENTER;
	    add (results, constraints);
	    setBackground (Color.LIGHT_GRAY);
	    
	    results.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener()  
	    {  
	    public void adjustmentValueChanged(AdjustmentEvent e)  
	    {  
	    textarea.select(textarea.getCaretPosition()*fontSize ,0);  
	    }  
	    }  
	    ); 
	   
	 }
	 
	}
