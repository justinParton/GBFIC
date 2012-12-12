package vimeo;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import vimeo.vimPanel;


@SuppressWarnings("unused")
public class vimeoUpload{ 
	  public static String OS = System.getProperty("os.name"); 
	  
	  public static String configFilePath = "config.ini";
	  public static JTabbedPane tab;
	  
		  public static void main(String[] args){
			  if (OS.startsWith("windows")){
				  vimeoUpload.configFilePath = ".config.ini";
			  }
			  
			  vimeoUpload ar = new vimeoUpload();
		  }

		  public vimeoUpload(){
			  JFrame frame = new JFrame("GBFIC Broadcast Network");
			  
			  tab = new JTabbedPane();
			  frame.add(tab, BorderLayout.CENTER);
			  
			    //Panel tab 1
			  vimPanel panel = new vimPanel();
			  tab.add("Upload", panel);
			  
			  //Panel tab 2
			  configPanel panel1 = new configPanel();
			  tab.add("Settings", panel1);
			  
			//Panel tab 2
			  resultsPanel panel2 = new resultsPanel();
			  tab.add("Results", panel2);
			  
			  //check if config exists. send to index 1 if not.
			  
			  File config = new File(configFilePath);
			  if(!config.exists()){
				  tab.setSelectedIndex(1);
			  }
			  
			  frame.setResizable(false);
			  frame.setSize(470, 580);
			  frame.setVisible(true);
			  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  	  }
	}
