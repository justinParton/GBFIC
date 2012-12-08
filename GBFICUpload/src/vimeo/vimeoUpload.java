package vimeo;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import vimeo.vimPanel;


@SuppressWarnings("unused")
public class vimeoUpload{
	  JTabbedPane tab;
	  public static void main(String[] args){
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
	  
	  frame.setResizable(false);
	  frame.setSize(470, 580);
	  frame.setVisible(true);
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  }
	  
	}