package GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;

import javax.swing.*;

public class Reel extends JFrame{
	    
	private static int countWinn;
    private static int countLoss;
    private static double timeCount;
    private static int Avgcredit;
	
	public static double getTimeCount() {
		return timeCount;
	}
	public static void setTimeCount(double timeCount) {
		Reel.timeCount = timeCount;
	}
	public static int getAvgcredit() {
		return Avgcredit;
	}
	public static void setAvgcredit(int avgcredit) {
		Avgcredit = avgcredit;
	}
	symbol obj=new symbol();
	public static int getCountWinn() {
		return countWinn;
	}
	public static void setCountWinn(int countWinn) {
		Reel.countWinn = countWinn;
	}
	public static int getCountLoss() {
		return countLoss;
	}
	public static void setCountLoss(int countLoss) {
		Reel.countLoss = countLoss;
	}
	
	public ArrayList<ImageIcon> img=obj.images;
	private JLabel lblimg1,lblimg2,lblimg3,lblcredit,lblbet,lblstat,getcreditArea,getBetArea,getStats,topic;
	private JButton btn1,btn2,btn3,btn4,btn5,btn6;
	private int reel1=1,reel2=2,reel3=3;
	private JTextArea creditArea,betArea,stats;

	Container contentPane;
	
	int countCoin=10;//stating number of add coins in the begin
	int betCoin=1;//starting number of bet coins in the begining
	int betMax;
	int countNumWinn=0;//number of winn times in the game
 	int countNumloss=0;//number of loss time in the game
 	int countTimes=0;//number of time game play
    
 	Thread t1;//thread that use for first image 
    Thread t2;//thread that use for second image 
    Thread t3;//thread that use for third image 
	Random r=new Random();
	 
	Reel num;//object that pass statistics to the statistic window
	 
	 int value;//value of the symbols
	 int avgCredit=0;//sum of bet credits in a game
	 int avgCredit1=0;//sum of win bet credits
	 int avgCredit2=0;//um of loss bet credits

	 int Credit=0;
	 
	public Reel(){
		
	setLayout(new GridLayout(5,5,5,5));
	
	//resizing window doesn't affect the gui when using this layout
	contentPane = getContentPane();
	contentPane.setLayout(new GridBagLayout());
    contentPane.setBackground(Color.WHITE);//set contentpane color
       
        GridBagConstraints design = new  GridBagConstraints();
        design.fill = GridBagConstraints.HORIZONTAL;
        design.insets = new Insets(10,10,10,10);
	
	obj.SetImage();
	
	topic=new JLabel("JACKPOT" , JLabel.CENTER);
    design.gridx = 1;
    design.gridy = 1;
    topic.setForeground(Color.RED);
    topic.setFont(new Font("Segoe UI Black", Font.BOLD, 40));
    
    contentPane.add(topic,design);
	
	lblimg1 = new JLabel();//label that set image 1 in the reel
	 lblimg1.setBorder(BorderFactory.createMatteBorder(5, 20, 5, 20, Color.RED));
	 lblimg1.addMouseListener(new click());//it wait the first thread until it start
	 
	lblimg2 = new JLabel();//label that set image 2 in the reel
	 lblimg2.setBorder(BorderFactory.createMatteBorder(5, 20, 5, 20, Color.RED));
	 lblimg2.addMouseListener(new click());//it wait the second thread until it start

	lblimg3 = new JLabel();//label that set image 1 in the reel
	 lblimg3.setBorder(BorderFactory.createMatteBorder(5, 20, 5, 20, Color.RED));
	 lblimg3.addMouseListener(new click());//it wait the third thread until it start

	
	lblimg1.setIcon(img.get(0));//starting image of first label in begin
	lblimg2.setIcon(img.get(0));//starting image of second label in begin
	lblimg3.setIcon(img.get(0));//starting image of third label in begin
	
	design.gridx = 0;//x axis in lblimg1
	design.gridy = 2;//y axis in lblimg1

    contentPane.add(lblimg1,design);
    
    design.gridx = 1;
    design.gridy = 2;

    contentPane.add(lblimg2,design);
    
    design.gridx = 2;
    design.gridy = 2;

    contentPane.add(lblimg3,design);
	    
	    btn1 = new JButton("SPIN");//spin button processes
	    btn1.addActionListener(new spinner1());
	    btn1.addActionListener(new spinner2());
	    btn1.addActionListener(new spinner3());
	 
	    btn2 = new JButton("BET ONE");//bet one button action
	    btn2.addActionListener(new betCoin());
	   
	    btn3 = new JButton("BET MAX");//betmax button procees
	    btn3.addActionListener(new betCoinMax());
	    
	    btn4 = new JButton("RESET");//reset button process
	    btn4.addActionListener(new Reset());
	    
	    btn5 = new JButton("ADD COIN");//add coin button process
	    btn5.addActionListener(new addOneCoin());
	    
	    btn6 = new JButton("STATISTIC");//statistics button process
	    btn6.addActionListener(new Stats());
	    
	    design.gridx = 1;
	    design.gridy = 3;
	    btn1.setForeground(Color.BLUE);
	    btn1.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
	    //btn1.setBackground(Color.GRAY);
	    contentPane.add(btn1,design);
	    
	    design.gridx = 1;
	    design.gridy = 4;
	    contentPane.add(btn2,design);
	    
	    design.gridx = 2;
	    design.gridy = 4;
	    contentPane.add(btn3,design);
	    
	    design.gridx = 0;
	    design.gridy = 5;
	    btn4.setForeground(Color.RED);
	    btn4.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
	    //btn1.setBackground(Color.DARK_GRAY);
	    contentPane.add(btn4,design);
	    
	    design.gridx = 0;
	    design.gridy = 4;
	    contentPane.add(btn5,design);
	    
	    design.gridx = 1;
	    design.gridy = 5;
	    contentPane.add(btn6,design);
	    
	    lblcredit=new JLabel("CREDIT AREA");
	    design.gridx = 0;
	    design.gridy = 6;
	    lblcredit.setForeground(Color.RED);
	    contentPane.add(lblcredit,design);

        getcreditArea=new JLabel("10");
        design.gridx = 1;
        design.gridy = 6;
        getcreditArea.setForeground(Color.RED);
        contentPane.add(getcreditArea,design);
        
        lblbet=new JLabel("BET AREA");
        design.gridx = 0;
        design.gridy = 7;
        lblbet.setForeground(Color.RED);
        contentPane.add(lblbet,design);

        getBetArea=new JLabel("0");
        design.gridx = 1;
        design.gridy = 7;
        getBetArea.setForeground(Color.RED);
        contentPane.add(getBetArea,design);
       
	}
	
	class addOneCoin implements ActionListener{//Add coin class
		
		 public void actionPerformed(ActionEvent event) {
			 String count1=getcreditArea.getText();
			 int countInt=Integer.parseInt(count1);

			 if(!getBetArea.getText().isEmpty()){

			 String bet=getBetArea.getText();
			 int betInt=Integer.parseInt(bet);
			 
			 countInt++;
			 
			 }else{
				 countInt++;
 
			 }
             String count=String.valueOf(countInt);
			 
			 getcreditArea.setText(count);
		 }
		 
	}
	
	class betCoin implements ActionListener{//Bet coin class
		
		 int countAll;
		 
		 public void actionPerformed(ActionEvent event1) {
			 
			 String countAvailable;
			 String countAddcoin=getcreditArea.getText();
		     int countCoin=Integer.parseInt(countAddcoin);
			 
				 if(countCoin<=0 ){
				 
				 JOptionPane.showMessageDialog(null,"Please add coins first",  "Alert", JOptionPane.INFORMATION_MESSAGE);

				}else{
			    
					if(getBetArea.getText().isEmpty()){
					 countAvailable=String.valueOf(betCoin++);

					}else{
						
			 int countInt=Integer.parseInt(getBetArea.getText());
			 //int w=Integer.parseInt(getcreditArea.getText());
			 //int y=Integer.parseInt(getBetArea.getText());
			 //int x=w-y;
			 
			  countAll=++countInt;

			 if(getcreditArea.getText().equals("0")){
				 JOptionPane.showMessageDialog(null,"Please add coins first",  "Alert", JOptionPane.INFORMATION_MESSAGE);

			 }

			 countAvailable=String.valueOf(countAll);
			 
			 }
			 getBetArea.setText(countAvailable);
			 String betArea=getBetArea.getText();
		   

		     //int betInt=Integer.parseInt(betArea);
			  
		     if(!betArea.isEmpty() && countCoin>0){
				 
				  int credit=countCoin-1;
				  if(credit>=0){
				  String credit1=String.valueOf(credit);
				  getcreditArea.setText(credit1);
				  }
			  }
		}
			 
	}	 
		 
}
	class betCoinMax implements ActionListener{
		 String count;
		 int bet;

		 public void actionPerformed(ActionEvent event) {
			 String creditArea=getcreditArea.getText();
			 int crInt=Integer.parseInt(creditArea);
			 if(getBetArea.getText().isEmpty()){
				  bet=3;
				 
			 }else{
			 int countInt=Integer.parseInt(getBetArea.getText());
				 bet=countInt+3;
			
			 
			 }
			 int betting=Integer.parseInt(getBetArea.getText());
			 int Total=crInt-3;
			 if(Total>=0 ){
				 int Total1=crInt-3;

			 getcreditArea.setText(String.valueOf(Total1));
			 count=String.valueOf(bet);
			 }else{
				 JOptionPane.showMessageDialog(null,"ERROR",  "Please add coins first", JOptionPane.INFORMATION_MESSAGE);

				 
			 }
			 getBetArea.setText(count);

		 }
	}
	class Reset implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			getcreditArea.setText("10");	
			getBetArea.setText("0");
			Statistics ss=new Statistics(num);
			ss.statisReset();
		
		}
		
	}
	
	public class spinner1 implements Runnable,ActionListener{

		@Override
		public void run() {//thread
            reel1();
           
           }
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(getBetArea.getText().isEmpty() || getBetArea.getText().equals("0")){
				 
				JOptionPane.showMessageDialog(null,"Plaese bet first",  "Alert", JOptionPane.INFORMATION_MESSAGE);

				
			}else{
				
                 t1=new Thread(new spinner1()); 
                  
			     t1.start();
			     	
			}
		}
		}
	 public void reel1(){
		
		int num=0;
		
		for(int i=0;i<1000;i++){
			
			num=r.nextInt(6);
			lblimg1.setIcon(img.get(num));
			try {
				t1.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
	 
	
	 
	 public class spinner2 implements Runnable,ActionListener{

			@Override
			public void run() {
	            reel2();
	           

	           }
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(getBetArea.getText().isEmpty() || getBetArea.getText().equals("0")){
					 //JOptionPane.showMessageDialog(null,"Plaese bet first",  "Alert", JOptionPane.INFORMATION_MESSAGE);

					
				}else{
					
	                  t2=new Thread(new spinner2()); 
	                  
				     t2.start();
				     

					
				}
			}
			}
		 public void reel2(){
			int num=0;
			for(int i=0;i<1000;i++){
				
				num=r.nextInt(6);
				lblimg2.setIcon(img.get(num));
				try {
					t2.sleep(3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
		}
		 public class spinner3 implements Runnable,ActionListener{

				public void run() {
		            reel3();
		           

		           }

				public void actionPerformed(ActionEvent arg0) {
					if(getBetArea.getText().isEmpty() || getBetArea.getText().equals("0")){
							
					}else{
						
		                  t3=new Thread(new spinner3()); 
		                  
					     t3.start();	
					}
				}
				}
			
		 		public void reel3(){
				int num=0;
				for(int i=0;i<1000;i++){
					
					num=r.nextInt(6);
					lblimg3.setIcon(img.get(num));
					try {
						t3.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				
				
			}
			

	 class click implements MouseListener{

	        @Override
	        public void mouseClicked(MouseEvent e) {
	            try{
	               synchronized (t1) {
	                  t1.stop();
	                } 
	               synchronized (t2) {
		              t2.stop();
		            } synchronized (t3) {
			          t3.stop();
		            } 
		                
		    compare();
		    
            }catch(Exception ex){}
	        }

	        @Override
	        public void mousePressed(MouseEvent e) {
	        }

	        @Override
	        public void mouseReleased(MouseEvent e) {
	        }

	        @Override
	        public void mouseEntered(MouseEvent e) {
	        }

	        @Override
	        public void mouseExited(MouseEvent e) {
	        }
	        
	        }
	 public Reel compare(){
		 
		 boolean flag=false;
		 if(lblimg1.getIcon()==lblimg2.getIcon()){
			 flag=true;
		       for(int i=0;i<6;i++){
		        	if(lblimg1.getIcon()==img.get(i)){
		        		switch(i){
		        		case 0:{
		        			value=7;
		        		}break;
		        		case 1:{
		        			value=6;
		        		}break;
		        		case 2:{
		        			value=5;
		        		}break;
		        		case 3:{
		        			value=4;
		        		}break;
		        		case 4:{
		        			value=3;
		        		}break;
		        		case 5:{
		        			value=2;
		        		}break;
		        		
		        		}
		              	}	
		              }
		        	
		        	}else if(lblimg1.getIcon()==lblimg3.getIcon()){
		   			 flag=true;

		        		for(int i=0;i<6;i++){
		    	        	if(lblimg1.getIcon()==img.get(i)){
		    	        		switch(i){
		    	        		case 0:{
		    	        			value=7;
		    	        		}break;
		    	        		case 1:{
		    	        			value=6;
		    	        		}break;
		    	        		case 2:{
		    	        			value=5;
		    	        		}break;
		    	        		case 3:{
		    	        			value=4;
		    	        		}break;
		    	        		case 4:{
		    	        			value=3;
		    	        		}break;
		    	        		case 5:{
		    	        			value=2;
		    	        		}break;
		    	        		
		    	        		}
		    	              	}	
		    	              }

		        	}else if(lblimg2.getIcon()==lblimg3.getIcon()){
		   			 flag=true;

		        		for(int i=0;i<6;i++){
		    	        	if(lblimg2.getIcon()==img.get(i)){
		    	        		switch(i){
		    	        		case 0:{
		    	        			value=7;
		    	        		}break;
		    	        		case 1:{
		    	        			value=6;
		    	        		}break;
		    	        		case 2:{
		    	        			value=5;
		    	        		}break;
		    	        		case 3:{
		    	        			value=4;
		    	        		}break;
		    	        		case 4:{
		    	        			value=3;
		    	        		}break;
		    	        		case 5:{
		    	        			value=2;
		    	        		}break;
		    	        		
		    	        		}
		    	              	}	
		    	              }

		        		
		        	}else if(lblimg2.getIcon()==lblimg3.getIcon() && lblimg1.getIcon()==lblimg3.getIcon()){
		   			 flag=true;

		        		for(int i=0;i<6;i++){
		    	        	if(lblimg1.getIcon()==img.get(i)){
		    	        		switch(i){
		    	        		case 0:{
		    	        			value=7;
		    	        		}break;
		    	        		case 1:{
		    	        			value=6;
		    	        		}break;
		    	        		case 2:{
		    	        			value=5;
		    	        		}break;
		    	        		case 3:{
		    	        			value=4;
		    	        		}break;
		    	        		case 4:{
		    	        			value=3;
		    	        		}break;
		    	        		case 5:{
		    	        			value=2;
		    	        		}break;
		    	        		
		    	        		}
		    	              	}	
		    	              }        		
		        	}
		 
		 if(flag){
	        	
	        	countNumWinn++;
	        	
                num.setCountWinn(countNumWinn);
	        	
                countTimes++;
	        	
	        	Credit=Integer.parseInt(getBetArea.getText());
	        	avgCredit1=avgCredit1+Credit;
	        	
				    int credits=Integer.parseInt(getcreditArea.getText());
			        int bet=Integer.parseInt(getBetArea.getText());
					JOptionPane.showMessageDialog(null,"You Won the match, you got "+(bet*value) + " credits" 
							 , "congratulation", JOptionPane.INFORMATION_MESSAGE);
			        int Total=credits+bet*value;
			        String Tot=String.valueOf(Total);
			        getcreditArea.setText(Tot);
				    getBetArea.setText("0");

	        }else{
	        	countNumloss++;
	        	num.setCountLoss(countNumloss);
	        	countTimes++;
	        	Credit=Integer.parseInt(getBetArea.getText());
		        	
	        	avgCredit2=avgCredit2-Credit;

				 JOptionPane.showMessageDialog(null,"You loss the match",  "Bad Luck", JOptionPane.INFORMATION_MESSAGE);
				 getBetArea.setText("0");

	        }
		 avgCredit=avgCredit2+avgCredit1;
		 num.setAvgcredit(avgCredit);
		 num.setTimeCount(countTimes);
	      
			return num;
		 	 
	 }
	 
	 class Stats implements ActionListener{
		 
			public void actionPerformed(ActionEvent arg0) {
                       new Statistics(num);				
					}
			}
	 
}


