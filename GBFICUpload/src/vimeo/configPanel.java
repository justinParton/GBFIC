package vimeo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import vimeo.mainPanel.MyRunnable;
@SuppressWarnings({ "unused", "serial" })
class configPanel extends JPanel
{ 
 //Data declarations 
	
	private JButton clear, exit, submit; 
    private JLabel configheadLabel, configsubHeadLabel, configreqHeadLabel, configVimeoKeyLabel, configVimeoSecretLabel, configvimeoToken1Label, configvimeotoken2Label, configFileDefaultLabel, configArchiveLabel, version; 
	private JTextField configVimeoKey, configVimeoSecret, configvimeoToken1, configvimeoToken2, configvideoTitle,configArchiveDefault;
	public JTextField configFileDefault;   
    private String fileDefault,archiveDefault;
    
public configPanel() 
{
  //Text Labels
	configheadLabel = new JLabel ("Network Configuration");
	configheadLabel.setFont(new Font("serif", Font.BOLD, 20));
	configsubHeadLabel = new JLabel ("Vimeo Api Settings");
	configsubHeadLabel.setFont(new Font("serif", Font.ITALIC,12));
	configreqHeadLabel = new JLabel ("All Of These Settings Need To Be Accurate");
	configreqHeadLabel.setFont(new Font("serif",Font.PLAIN,16));
	configVimeoKeyLabel = new JLabel ("Key: ");
	configVimeoSecretLabel = new JLabel ("Secret: ");
	configvimeoToken1Label = new JLabel ("Token 1: ");
	configvimeotoken2Label = new JLabel ("Token 2: ");
	configFileDefaultLabel = new JLabel ("Default Location: ");
	configArchiveLabel = new JLabel ("Archive Location: ");
	version = new JLabel ("Version: 1.0");

	//Text Input Variables
	configVimeoKey = new JTextField (15);
	configVimeoSecret = new JTextField (15);
	configvimeoToken1 = new JTextField (15);
	configvimeoToken2 = new JTextField (15);
	configFileDefault = new JTextField (15);
	configArchiveDefault = new JTextField (15);
	Properties props = new Properties();
	 try {
			props.load(new FileReader(vimeoUpload.configFilePath));
			 configVimeoKey.setText(props.getProperty("key"));
			 configVimeoSecret.setText(props.getProperty("secret"));
			 configvimeoToken1.setText(props.getProperty("token1"));
			 configvimeoToken2.setText(props.getProperty("token2"));
			 configFileDefault.setText(props.getProperty("defaultfile"));
			 configArchiveDefault.setText(props.getProperty("archive"));
	 } catch (FileNotFoundException e1) {
				e1.printStackTrace();		
		} catch (IOException e) {
				e.printStackTrace();
		}

           
	//Buttons variables!
		submit = new JButton ("SAVE");
		clear = new JButton ("CLEAR"); 

	ButtonListener listener = new ButtonListener();
	//The addActionListener method adds the "listener" object to the button object "submit"
	submit.addActionListener (listener);
	clear.addActionListener (listener);
	
	
	//Grid Layouts
	setLayout(new GridBagLayout());
	GridBagConstraints constraints = new GridBagConstraints();
	
	constraints.gridx = 0;
	constraints.gridy = 0;
	constraints.gridwidth = 2;
	constraints.gridheight = 1;
	constraints.insets = new Insets (5, 5, 80, 5); 
	constraints.anchor = GridBagConstraints.CENTER;
	add (configheadLabel, constraints);
	
	constraints.gridx = 0;
	constraints.gridy = 0;
	constraints.gridwidth = 2;
	constraints.gridheight = 1;
	constraints.insets = new Insets (5, 5, 40, 5); 
	constraints.anchor = GridBagConstraints.CENTER;
	add (configsubHeadLabel, constraints);
	
	constraints.gridx = 0;
	constraints.gridy = 0;
	constraints.gridwidth = 0;
	constraints.gridheight = 1;
	constraints.insets = new Insets (5, 5, -60, 5); 
	constraints.anchor = GridBagConstraints.CENTER;
	add (configreqHeadLabel, constraints);
	
	constraints.gridx = 0;
	constraints.gridy = 1;
	constraints.gridwidth = 1;
	constraints.gridheight = 1;
	constraints.insets = new Insets (5, 5, 5, 5); 
	constraints.anchor = GridBagConstraints.EAST;
	add (configVimeoKeyLabel, constraints);
	
	constraints.gridx = 1;
	constraints.gridy = 1;
	constraints.gridwidth = 1;
	constraints.gridheight = 1;
	constraints.insets = new Insets (5, 5, 5, 5); 
	constraints.anchor = GridBagConstraints.WEST;
	add (configVimeoKey, constraints);
	
	// same Y Axis    
	constraints.gridx = 0;
	constraints.gridy = 2;
	constraints.gridwidth = 1;
	constraints.gridheight = 1;
	constraints.insets = new Insets (5, 79, 5, 5); 
	constraints.anchor = GridBagConstraints.WEST;
	add (configVimeoSecretLabel, constraints);
	
	constraints.gridx = 1;
	constraints.gridy = 2;
	constraints.gridwidth = 1;
	constraints.gridheight = 1;
	constraints.insets = new Insets (5, 5, 5, 5); 
	constraints.anchor = GridBagConstraints.WEST;
	add (configVimeoSecret, constraints);
	
	
	constraints.gridx = 0;
	constraints.gridy = 3;
	constraints.gridwidth = 1;
	constraints.gridheight = 1;
	constraints.insets = new Insets (5, 65, 5, 5); 
	constraints.anchor = GridBagConstraints.WEST;
	add (configvimeoToken1Label, constraints);
	
	constraints.gridx = 1;
	constraints.gridy = 3;
	constraints.gridwidth = 1;
	constraints.gridheight = 1;
	constraints.insets = new Insets (5,5, 5, 5); 
	constraints.anchor = GridBagConstraints.WEST;
	add (configvimeoToken1, constraints);
	//End of Objects on Same Y Axis
	
	constraints.gridx = 0;
	constraints.gridy = 5;
	constraints.gridwidth = 1;
	constraints.gridheight = 1;
	constraints.insets = new Insets (5, 5, 5, 5); 
	constraints.anchor = GridBagConstraints.EAST;
	add (configvimeotoken2Label, constraints);
	
	constraints.gridx = 1;
	constraints.gridy = 5;
	constraints.gridwidth = 1;
	constraints.gridheight = 1;
	constraints.insets = new Insets (5, 5, 5, 5); 
	constraints.anchor = GridBagConstraints.WEST;     
	add (configvimeoToken2, constraints);
	
	constraints.gridx = 0;
	constraints.gridy = 6;
	constraints.gridwidth = 1;
	constraints.gridheight = 1;
	constraints.insets = new Insets (5, 5, 5, 5); 
	constraints.anchor = GridBagConstraints.EAST;
	add (configFileDefaultLabel, constraints);
	
	constraints.gridx = 1;
	constraints.gridy = 6;
	constraints.gridwidth = 1;
	constraints.gridheight = 1;
	constraints.insets = new Insets (5, 5, 5, 5); 
	constraints.anchor = GridBagConstraints.WEST;     
	add (configFileDefault, constraints);
	
	constraints.gridx = 0;
	constraints.gridy = 7;
	constraints.gridwidth = 1;
	constraints.gridheight = 1;
	constraints.insets = new Insets (5, 5, 5, 5); 
	constraints.anchor = GridBagConstraints.EAST;
	add (configArchiveLabel, constraints);
	
	constraints.gridx = 1;
	constraints.gridy = 7;
	constraints.gridwidth = 1;
	constraints.gridheight = 1;
	constraints.insets = new Insets (5, 5, 5, 5); 
	constraints.anchor = GridBagConstraints.WEST;     
	add (configArchiveDefault, constraints);
	
	constraints.gridx = 0;
	constraints.gridy = 8;
	constraints.gridwidth = 2;
	constraints.gridheight = 1;
	constraints.insets = new Insets (25, 15, 5, 5); 
	constraints.anchor = GridBagConstraints.WEST;
	add (submit, constraints);
	
	constraints.gridx = 0;
	constraints.gridy = 8;
	constraints.gridwidth = 2;
	constraints.gridheight = 1;
	constraints.insets = new Insets (25, 100, 5, 5); 
	constraints.anchor = GridBagConstraints.WEST;
	add (clear, constraints);

	
	constraints.gridx = 0;
	constraints.gridy = 8;
	constraints.gridwidth = 2;
	constraints.gridheight = 1;
	constraints.insets = new Insets (25, 185, 5, 5); 
	constraints.anchor = GridBagConstraints.WEST;
	add (version, constraints);
	setBackground (Color.LIGHT_GRAY);
	}
    
 
	
	private class ButtonListener implements ActionListener
	{
		  public void actionPerformed (ActionEvent event)
		    {
		      String keyFile = configVimeoKey.getText();    
		      String secretFile = configVimeoSecret.getText();
		      String token1File = configvimeoToken1.getText();
		      String token2File = configvimeoToken2.getText();
		      
		      fileDefault = configFileDefault.getText().replace("\\", "\\\\");
		      archiveDefault = configArchiveDefault.getText().replace("\\", "\\\\");
		      
		      PrintWriter writer;
		      if (event.getSource() == submit)
		      {
		    	  boolean errorField = false;
		    	  
				    if ( configVimeoKey.getText().length() == 0){
				    	configVimeoKey.setBackground(Color.YELLOW);
				    	errorField = true;
				    }
				    if (configVimeoSecret.getText().length() == 0){
				    	configVimeoSecret.setBackground(Color.YELLOW);
				    	errorField = true;
				    }
				    if (configvimeoToken1.getText().length() == 0){
				    	configvimeoToken1.setBackground(Color.YELLOW);
				    	errorField = true;
				    }
				    if (configvimeoToken2.getText().length() == 0 ){
				    	configvimeoToken2.setBackground(Color.YELLOW);
				    	errorField = true;
				    	}
				    if (configFileDefault.getText().length() == 0 ){
				    	configFileDefault.setBackground(Color.YELLOW);
				    	errorField = true;
				    	}
				    if (configArchiveDefault.getText().length() == 0 ){
				    	configArchiveDefault.setBackground(Color.YELLOW);
				    	errorField = true;
				    	}
				    
				    if(errorField)
				     { 
				      configreqHeadLabel.setText("Please Complete the Highlighted Fields."); 
				      configreqHeadLabel.setForeground(Color.RED);
				      configreqHeadLabel.setFont(new Font("serif",Font.BOLD,16));
				                  
				     }
			       
			else {
				try {
				    writer = new PrintWriter(vimeoUpload.configFilePath, "UTF-8");
				    writer.println("key="+keyFile);
			        writer.println("secret="+secretFile);
			        writer.println("token1="+token1File);
			        writer.println("token2="+token2File);
			        writer.println("defaultfile="+fileDefault);
			        writer.println("archive="+archiveDefault);
			        writer.close();
			        new mainPanel().fileLock("hello");
			        vimeoUpload.tab.setSelectedIndex(0);
     				} 
				catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					   e.printStackTrace();
				} 
				catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
				}
		      }
		      }
		      //Clears Everything + the red text
		      if (event.getSource() == clear)
		      {
		    		configVimeoKey.setText(null);
		    		configVimeoSecret.setText(null);
		    		configvimeoToken1.setText(null);
		    		configvimeoToken2.setText(null);
		    		configVimeoKey.setBackground(Color.WHITE);
		    		configVimeoSecret.setBackground(Color.WHITE);
		    		configvimeoToken1.setBackground(Color.WHITE);
		    		configvimeoToken2.setBackground(Color.WHITE);
		    		configFileDefault.setBackground(Color.WHITE);
		    		configArchiveDefault.setBackground(Color.WHITE);
		    		configreqHeadLabel.setText(null); 
		    		
		      }
		    
		      
		    }
		
		  
		}

}
