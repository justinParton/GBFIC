package vimeo;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings({ "unused", "serial" })
class mainPanel extends JPanel
{ 
 //Data declarations 
 //vimPanel	
 private JLabel headLabel,subHeadLabel,reqHeadLabel, summaryLabel, videoFileLabel,videoTitleLabel, videoDateLabel,videoAuthorLabel,VimeoSecretLabel,vimeoToken1Label, vimeotoken2Label; 
 private JButton submit, clear, exit; 
 private JTextField videoTitle,videoAuthor, VimeoSecret, vimeoToken1, vimeoToken2,videoDate;
 JTextArea results;
 String[] monthName = {"January", "February","March","April","May","June","July","August","September","October", "November","December"};
int yearSelect;
private int year;
 private Properties props = new Properties();
 private String t,f,keyInfo,secretInfo,token1Info,token2Info,titleInfo,videoInfo,dateInfo,AuthorInfo,monthSelect,daySelect; 
 private JComboBox Month,Day,Year;
 public JTextField videoFile;
 
 public Properties upInfo(){
	try {
		props.load(new FileReader(vimeoUpload.configFilePath));
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		results.setText("The Configurations are Unreachable");
	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		 resultsPanel.textarea.append("the File is corrupt. Please Re-input all Settings and Save");
	}
	
	
	//Set Month Variables
	 Calendar cal = Calendar.getInstance();
	 year = cal.get(Calendar.YEAR);
	 monthSelect = monthName[cal.get(Calendar.MONTH)];
	 daySelect = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
	 String yearSelect = Integer.toString(cal.get(Calendar.YEAR));
	
	 Month.setSelectedItem(monthSelect);
	 Day.setSelectedItem(daySelect);
	 Year.setSelectedItem(yearSelect); 
      
    //display Video File
	 videoFile.setText(props.getProperty("defaultfile"));
	 
  return props;
 }
 public void fileLock(String update){
	f = update;
	videoFile.setText(f);
}

public void fetchKeys(){
	
	//Config File Information
    keyInfo = props.getProperty("key");
    secretInfo = props.getProperty("secret");
    token1Info = props.getProperty("token1");
    token2Info = props.getProperty("token2");
    videoInfo = new configPanel().configFileDefault.getText();
    
    //Main Page Settings
	titleInfo = videoTitle.getText();
	dateInfo = Month.getSelectedItem() + " " + Day.getSelectedItem()+ ", " + Year.getSelectedItem();
	AuthorInfo =  videoAuthor.getText();
	}
 
public mainPanel() 
 {
	
	//Label Titles
    headLabel = new JLabel ("GBFIC Broadcast Upload System");
    headLabel.setFont(new Font("serif", Font.BOLD, 20));
    subHeadLabel = new JLabel ("Reaching the World For Christ");
    subHeadLabel.setFont(new Font("serif", Font.ITALIC,12));
    reqHeadLabel = new JLabel ("Please Complete the Form to Begin Uploading");
    reqHeadLabel.setFont(new Font("serif",Font.PLAIN,16));     
    videoFileLabel = new JLabel ("File Location: ");
    videoTitleLabel = new JLabel ("Sermon Title: ");
    videoDateLabel = new JLabel ("Sermon Date: ");
    videoAuthorLabel = new JLabel ("Speaker: ");
   

    //Text Fields
    videoTitle = new JTextField (15); 
    videoFile = new JTextField (15);
    videoAuthor = new JTextField (15); 
    
    //Buttons Titles
    submit = new JButton ("UPLOAD"); 
    clear = new JButton ("CLEAR"); 
    exit = new JButton ("EXIT"); 
    
    //ComboBox List
    Month = new JComboBox();
    Month.addItem("January");
    Month.addItem("February");
    Month.addItem("March");
    Month.addItem("April");
    Month.addItem("May");
    Month.addItem("June");
    Month.addItem("July");
    Month.addItem("August");
    Month.addItem("September");
    Month.addItem("October");
    Month.addItem("Novovember");
    Month.addItem("December");
    
    Day = new JComboBox();
    Day.addItem("1");
    Day.addItem("2");
    Day.addItem("3");
    Day.addItem("4");
    Day.addItem("5");
    Day.addItem("6");
    Day.addItem("7");
    Day.addItem("8");
    Day.addItem("9");
    Day.addItem("10");
    Day.addItem("11");
    Day.addItem("12");
    Day.addItem("13");
    Day.addItem("14");
    Day.addItem("15");
    Day.addItem("16");
    Day.addItem("17");
    Day.addItem("18");
    Day.addItem("19");
    Day.addItem("20");
    Day.addItem("21");
    Day.addItem("22");
    Day.addItem("23");
    Day.addItem("24");
    Day.addItem("25");
    Day.addItem("26");
    Day.addItem("27");
    Day.addItem("28");
    Day.addItem("29");
    Day.addItem("30");
    Day.addItem("31");
    
    Year = new JComboBox();
    Year.addItem("2012");
    Year.addItem("2013");
    Year.addItem("2014");
    Year.addItem("2015");
    Year.addItem("2016");
    Year.addItem("2017");
    Year.addItem("2018");
    Year.addItem("2019");
    Year.addItem("2020");
    Year.addItem("2021");
    Year.addItem("2022");
    Year.addItem("2023");
    Year.addItem("2024");
 
    //set File Field
 	ButtonListener listener = new ButtonListener();
    submit.addActionListener (listener);
    clear.addActionListener (listener);
    exit.addActionListener (listener);
 
    //Grid Layouts
    setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();

    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.gridwidth = 2;
    constraints.gridheight = 1;
    constraints.insets = new Insets (5, 5, 80, 5); 
    constraints.anchor = GridBagConstraints.CENTER;
    add (headLabel, constraints);
    
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.gridwidth = 2;
    constraints.gridheight = 1;
    constraints.insets = new Insets (5, 5, 40, 5); 
    constraints.anchor = GridBagConstraints.CENTER;
    add (subHeadLabel, constraints);
    
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.gridwidth = 0;
    constraints.gridheight = 1;
    constraints.insets = new Insets (5, 5, -60, 5); 
    constraints.anchor = GridBagConstraints.CENTER;
    add (reqHeadLabel, constraints);
    
    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.insets = new Insets (5, 5, 5, 5); 
    constraints.anchor = GridBagConstraints.EAST;
    add (videoTitleLabel, constraints);
    
    constraints.gridx = 1;
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.insets = new Insets (5, 5, 5, 5); 
    constraints.anchor = GridBagConstraints.WEST;
    add (videoTitle, constraints);
    
    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.insets = new Insets (5, 5, 5, 5); 
    constraints.anchor = GridBagConstraints.EAST;
    add (videoAuthorLabel, constraints);
    
    constraints.gridx = 1;
    constraints.gridy = 2;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.insets = new Insets (5, 5, 5, 5); 
    constraints.anchor = GridBagConstraints.WEST;
    add (videoAuthor, constraints);
   
    constraints.gridx = 0;
    constraints.gridy = 3;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.insets = new Insets (5, 5, 5, 5); 
    constraints.anchor = GridBagConstraints.EAST;
    add (videoDateLabel, constraints);
    
    constraints.gridx = 1;
    constraints.gridy = 3;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.insets = new Insets (5, 5, 5, 5); 
    constraints.anchor = GridBagConstraints.WEST;
    add (Month, constraints);
    constraints.gridx = 1;
    constraints.gridy = 3;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.insets = new Insets (5, 135, 5, 5); 
    constraints.anchor = GridBagConstraints.WEST;
    add (Day, constraints);
    constraints.gridx = 1;
    constraints.gridy = 3;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.insets = new Insets (5, 205, 5, 5); 
    constraints.anchor = GridBagConstraints.WEST;
    add (Year, constraints);
    
    
    constraints.gridx = 0;
    constraints.gridy = 4;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.insets = new Insets (5, 5, 5, 5); 
    constraints.anchor = GridBagConstraints.EAST;
    add (videoFileLabel, constraints);
    
    constraints.gridx = 1;
    constraints.gridy = 4;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.insets = new Insets (5, 5, 5, 5); 
    constraints.anchor = GridBagConstraints.WEST;
    add (videoFile, constraints);
    
    
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
    constraints.insets = new Insets (25, 155, 5, 5); 
    constraints.anchor = GridBagConstraints.WEST;
    add (clear, constraints);
    
    constraints.gridx = 0;
    constraints.gridy = 8;
    constraints.gridwidth = 2;
    constraints.gridheight = 1;
    constraints.insets = new Insets (25, 285, 5, 5); 
    constraints.anchor = GridBagConstraints.WEST;
    add (exit, constraints);
    
    props = upInfo();
    videoFile.setForeground(Color.GRAY);
    setBackground (Color.LIGHT_GRAY);
    
 }

class MyRunnable implements Runnable {     
 	    public void run() { 
 	    	  vimeo vim1 = new vimeo(videoInfo,titleInfo,dateInfo,keyInfo,secretInfo,token1Info,token2Info); 
 				try {
 					String runner = vim1.vimeoUpload(null);
 					results.setText(runner);
 					
 				} catch (Exception e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
 	       
 	   } 
 	} 

 
 private class ButtonListener implements ActionListener
 {
      public void actionPerformed (ActionEvent event)
    {
          
      if (event.getSource() == submit)
      {
    	  boolean errorField = false;
    	  
		    if (videoTitle.getText().length() == 0){
		    	videoTitle.setBackground(Color.YELLOW);
		    	errorField = true;
		    }
		    if (videoAuthor.getText().length() == 0 ){
		    	videoAuthor.setBackground(Color.YELLOW);
		    	errorField = true;
		    	}
		    if(errorField)
		     { 
		      reqHeadLabel.setText("Please Complete the Highlighted Fields."); 
		      reqHeadLabel.setForeground(Color.RED);
		      reqHeadLabel.setFont(new Font("serif",Font.BOLD,16));
		                  
		     }
		    else {
	 	  fetchKeys();	
     	  MyRunnable myRunnable = new MyRunnable(); 
    	  Thread myThread = new Thread(myRunnable);
    	  myThread.start();
    	  resultsPanel.textarea.setText("The Process has Started");
    	  vimeoUpload.tab.setSelectedIndex(2);
		    }
      }
      
      //Clears Everything + the red text
      if (event.getSource() == clear)
      {     
    	  resultsPanel.textarea.setText("The Process has Started");
    	  vimeoUpload.tab.setSelectedIndex(2); 
        
      }
      
      if (event.getSource() == exit)
      {
      	System.exit(0);	          
      }
      
    }
   } 
 
}
