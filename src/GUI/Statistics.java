package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.Reel.Stats;

public class Statistics extends JFrame{

	private JButton saveStats;
	private JLabel graphics1,graphics2,graphics3;

	Container contentPane;
	
	public Statistics(Reel num){
	
		setLayout(new GridLayout(5,5,5,5));
	
	//resizing window doesn't affect the gui when using this layout
	contentPane = getContentPane();
	contentPane.setLayout(new GridBagLayout());
    contentPane.setBackground(Color.WHITE);//set contentpane color
       
        GridBagConstraints design = new  GridBagConstraints();
        design.fill = GridBagConstraints.HORIZONTAL;
        design.insets = new Insets(10,10,10,10);
	
		this.setTitle("STATISTICS");
		this.setSize(600,400);
		this.setLocationRelativeTo(null);
	    this.setResizable(true);
	    
	    int y= num.getCountWinn();
	    int x=num.getCountLoss();
	
      double counting=num.getTimeCount();
      int avg=num.getAvgcredit();
      double totAvg=avg/counting;
      
	  saveStats=new JButton("SAVE STATUS");
	  design.gridx = 2;
	  design.gridy = 4;
	  saveStats.setForeground(Color.RED);
	  contentPane.add(saveStats,design);
	  
	  saveStats.addActionListener(new save());
	  
	  graphics1=new JLabel();
	  design.gridx = 1;
	  design.gridy = 1;
	  graphics1.setForeground(Color.RED);
	  contentPane.add(graphics1,design);
	  
	  graphics2=new JLabel();
	  design.gridx = 1;
	  design.gridy = 2;
	  graphics2.setForeground(Color.RED);
	  contentPane.add(graphics2,design);
	  
	  int tot=0;
      
	  graphics3=new JLabel(" THE CREDITS THAT NETTED PER GAME  : "+tot);
	  design.gridx = 1;
	  design.gridy = 3;
	  graphics3.setForeground(Color.RED);
	  contentPane.add(graphics3,design);
	  
	  graphics1.setText(" NUMBER OF TIMES WIN THE GAME         : "+y);
	  graphics2.setText(" NUMBER OF TIMES lOSS THE GAME        : "+x);
	  
	  if(totAvg>=0 || totAvg<0){
		  
      graphics3.setText("THE CREDITS THAT NETTED PER GAME    : "+totAvg);
	  
	  }
	  
	  this.setVisible(true);
  
  }

	class save implements ActionListener{
    	 
    	 public void Writter(){
    		
        	 try {
        		 
        		Date date = new Date() ;
        	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
        		File fw = new File(dateFormat.format(date) + ".txt") ;
				PrintWriter pw=new PrintWriter(fw);
				pw.println(graphics1.getText());
				pw.println(graphics2.getText());
				pw.close();
			} catch (IOException e) {
			}
     		
		}

	public void actionPerformed(ActionEvent e) {
                 
			Writter();
		}
     	 
	}
    
     public void statisReset(){
      graphics1.setText(" NUMBER OF TIMES WON THE GAME       : 0");
	  graphics2.setText(" NUMBER OF TIMES lOSS THE GAME      : 0");
	  graphics3.setText(" THE CREDITS THAT NETTED PER GAME   : 0");
	  
     }
}
