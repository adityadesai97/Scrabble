import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.io.BufferedReader;
import java.io.BufferedWriter ;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;


class Grid extends Thread{
    int time=60;
    static Socket socket;
	int i,j,l,n,m = 0;
	int counter = time;
	int k=225;
	int flag=0;
	int flag1=0;
	int store,tempo;
	int[] arr=new int[7];
	String ch,ch2,let,pos;
	static String letters[]=new String[100];
	JLabel current;
	JLabel message;
    static JButton grid[];
    JButton temp;
    static JButton tile[];
    static JButton done;
	JButton cancel;
	static Timer t;
	JLabel timer;
    JFrame f, menu;
    Random r=new Random();
     /* -> Buttons in the Menu */ JButton Exit; static JButton Host; 
     /* -> Messages in the Menu */ static JLabel status, title;
     
    
    public  static int check = 0;
    
    
    Grid(){
    f=new JFrame("Scrabble1");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    /*try {
		f.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\Users\\USER\\Desktop\\desert.jpg")))));
	} catch (IOException e) {
		e.printStackTrace();
	}
	f.pack();*/
	
	
    Exit = new JButton("Exit the Game"); Exit.setActionCommand("0"); Exit.addActionListener(new OptionPaneListeners()); Exit.setBounds(700, 500, 300, 100);
	Host = new JButton("Play!!"); Host.setActionCommand("1"); Host.addActionListener(new OptionPaneListeners()); Host.setBounds(700, 300, 300, 100);
	f.add(Exit); f.add(Host);
    
	
	title = new JLabel("Scrabble 2.0 created by ANA developers PVT. LTD.",SwingConstants.CENTER);
    title.setFont(new Font("Arial", Font.PLAIN, 50)); title.setForeground(Color.WHITE);
	title.setBounds(300,100,1400,50);
    
    status = new JLabel();
    status.setFont(new Font("Arial", Font.PLAIN, 50)); status.setForeground(Color.WHITE);
    status.setBounds(500,700,1400,50);
    
    f.add(title);f.add(status);

    for(i=0;i<12;i++){
		letters[i]="e";
	}
	for(i=12;i<30;i++){
		letters[i]="a";
		i++;
		letters[i]="i";
	}
   	for(i=30;i<38;i++){
		letters[i]="o";
	}
	for(i=38;i<56;i++){
		letters[i]="n";
		i++;
		letters[i]="r";
		i++;
		letters[i]="t";
	}
	for(i=56;i<72;i++){
		letters[i]="l";
		i++;
		letters[i]="s";
		i++;
		letters[i]="u";
		i++;
		letters[i]="d";
	}
	for(i=72;i<75;i++){
		letters[i]="g";
	}
	for(i=75;i<93;i++){
		letters[i]="b";
		i++;
		letters[i]="c";
		i++;
		letters[i]="m";
		i++;
		letters[i]="p";
		i++;
		letters[i]="f";
		i++;
		letters[i]="h";
		i++;
		letters[i]="v";
		i++;
		letters[i]="w";
		i++;
		letters[i]="y";
	}
	for(i=93;i<98;i++){
		letters[i]="k";
		i++;
		letters[i]="j";
		i++;
		letters[i]="x";
		i++;
		letters[i]="q";
		i++;
		letters[i]="z";
   	}
	for(i=98;i<100;i++){
		letters[i]="_";
	}
    

    
	
	
	
    current=new JLabel("",SwingConstants.CENTER);
    current.setFont(new Font("Arial", Font.PLAIN, 30));
	current.setBounds(700,350,700,50);
	

	message=new JLabel("",SwingConstants.CENTER);
    message.setFont(new Font("Arial", Font.PLAIN, 30));
	message.setBounds(700,402,700,50);
	


    done=new JButton("Done");
    done.setBounds(1000,500,100,50);
    done.setActionCommand("233");
    done.addActionListener(new ButtonClickListener());
    
    
    cancel = new JButton("Cancel");
    cancel.setBounds(1000, 440, 100, 50);
    cancel.addActionListener(new ButtonClickListener());
    cancel.setActionCommand("232");
    cancel.setVisible(false);
    
    f.setExtendedState(JFrame.MAXIMIZED_BOTH);
    f.setLayout(null);
    f.setVisible(true);
	
    timer=new JLabel("",SwingConstants.CENTER);
	timer.setBounds(1000,550,100,50);
	
	t = new Timer(1000, new TimerListener());
    }
    
    
    private void updateUI(){
    	status.setText("Lets play!");
    	t.start();
        addGrid();
	    addTiles();
	    addColor();
		f.add(cancel);f.add(done);f.add(message);f.add(current);f.add(timer);
		title.setText(null);
		Host.setVisible(false); Exit.setVisible(false);
    }
    private void addGrid(){
    	
    	grid =new JButton[225];
        for(i = 2;l < 225; i = i + 47){
        	for(j = 2; j < 780; j = j+53){
        		temp = new JButton();
        		temp.setBounds(j, i, 52, 45);
        		grid[l] = temp;
        		grid[l].setText("");
        		grid[l].setFont(new Font("Arial", Font.PLAIN, 7));
        		grid[l].setActionCommand(Integer.toString(l));
        		grid[l].addActionListener(new ButtonClickListener());
        		f.add(grid[l]);
        		l++;
        	}
        }
    }

    private void addTiles(){
    	tile=new JButton[7];
    	l=0;
    	for(i = 830; l < 7; i = i + 62) {
    		    temp=new JButton();
        		temp.setBounds(i, 200, 60, 50);
        		tile[l] = temp;
        		tile[l].setText("");
        		tile[l].setFont(new Font("Arial", Font.BOLD, 25));
        		tile[l].setActionCommand(Integer.toString(k));
        		tile[l].addActionListener(new ButtonClickListener());
        		f.add(tile[l]);
        		l++;
        		k++;
        }
    	tile[0].setText(randomiser());
    	tile[1].setText(randomiser());
    	tile[2].setText(randomiser());
    	tile[3].setText(randomiser());
    	tile[4].setText(randomiser());
    	tile[5].setText(randomiser());
    	tile[6].setText(randomiser());
    }

    private String randomiser(){
    	n=r.nextInt(99);
    	if(letters[n].equals("")){
    		ch2=randomiser();
    	}
    	else{
    		ch2=letters[n];
    		letters[n]="";
    	}
		return ch2;
    }

    private void addColor(){
    	for(l=0;l<224;l++){
    		grid[l].setBackground(Color.GREEN);
    	}
    	grid[112].setBackground(Color.BLACK);
    	grid[0].setBackground(Color.RED);		grid[3].setBackground(Color.CYAN);   
        grid[7].setBackground(Color.RED);		grid[179].setBackground(Color.CYAN);	 
        grid[14].setBackground(Color.RED);		grid[11].setBackground(Color.CYAN);
        grid[105].setBackground(Color.RED);		grid[45].setBackground(Color.CYAN);
        grid[119].setBackground(Color.RED);		grid[59].setBackground(Color.CYAN);
        grid[217].setBackground(Color.RED);		grid[165].setBackground(Color.CYAN);
        grid[210].setBackground(Color.RED);		grid[213].setBackground(Color.CYAN);
        grid[224].setBackground(Color.RED);		grid[221].setBackground(Color.CYAN);
        										grid[96].setBackground(Color.CYAN);
        										grid[98].setBackground(Color.CYAN);
        										grid[126].setBackground(Color.CYAN);
        										grid[128].setBackground(Color.CYAN);
        									    grid[52].setBackground(Color.CYAN);	
        									    grid[172].setBackground(Color.CYAN);
        								        grid[88].setBackground(Color.BLUE);
        								        grid[108].setBackground(Color.CYAN);	
        								        grid[116].setBackground(Color.CYAN);
        								        grid[36].setBackground(Color.CYAN);
        								        grid[38].setBackground(Color.CYAN);
        								        grid[92].setBackground(Color.CYAN);
        								        grid[122].setBackground(Color.CYAN);
        								        grid[102].setBackground(Color.CYAN);
        								        grid[132].setBackground(Color.CYAN);
        								        grid[186].setBackground(Color.CYAN);
        								        grid[188].setBackground(Color.CYAN);
       
        grid[16].setBackground(Color.PINK);		grid[204].setBackground(Color.BLUE);
        grid[28].setBackground(Color.PINK);		grid[20].setBackground(Color.BLUE);
        grid[56].setBackground(Color.PINK);		grid[24].setBackground(Color.BLUE);
        grid[32].setBackground(Color.PINK);		grid[24].setBackground(Color.BLUE);
        grid[42].setBackground(Color.PINK);		grid[76].setBackground(Color.BLUE);	
        grid[48].setBackground(Color.PINK);		grid[88].setBackground(Color.BLUE);
        grid[64].setBackground(Color.PINK);		grid[80].setBackground(Color.BLUE);	
        grid[70].setBackground(Color.PINK);		grid[84].setBackground(Color.BLUE);
        grid[182].setBackground(Color.PINK);	grid[140].setBackground(Color.BLUE);
        grid[192].setBackground(Color.PINK);	grid[144].setBackground(Color.BLUE);
        grid[154].setBackground(Color.PINK);	grid[136].setBackground(Color.BLUE);
        grid[160].setBackground(Color.PINK);	grid[148].setBackground(Color.BLUE);
        grid[168].setBackground(Color.PINK);	grid[200].setBackground(Color.BLUE);
        grid[176].setBackground(Color.PINK);
        grid[196].setBackground(Color.PINK);
        grid[208].setBackground(Color.PINK);
    }

   
    	
    	
    public void run(){
    	
    	updateUI();
		while(true){
			DataInputStream din;
			try {
				din = new DataInputStream(socket.getInputStream());
				String character=(din.readUTF());
				if(character.equals("-1")){
					for(int i=0;i<7;i++){
			    		tile[i].setVisible(true);
			    	}
					done.setVisible(true);
					t.start();
					continue;
				}
				String position=(din.readUTF());
				position=position.replace("\n", "");
				System.out.println(character);
				System.out.println(position);
				int z=Integer.parseInt(position);
				if(z!=400)
				{
					grid[z].setText(character);
					grid[z].setFont(new Font("Arial", Font.BOLD, 25));
					for(int i=0;i<100;i++){
						if(letters[i].equals(character)){
							letters[i]="";
							break;
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
    

    private class ButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            try
            {
           	DataInputStream din=new DataInputStream(socket.getInputStream());  
           	DataOutputStream dout=new DataOutputStream(socket.getOutputStream()); 
           	BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
        String command = e.getActionCommand();
        if(Integer.parseInt(command)<225){
        	for(int l=0;l<225;l++){
            	if( command.equals(Integer.toString(l))){
            		if(flag==0){
            			message.setText("Choose a tile first");
            		}
            		else{
            			if(!grid[l].getText().equals("")){
            				message.setText("This tile is already occupied");
            			}
            			else{
            				pos=Integer.toString(l)+"\n";
                            dout.writeUTF(pos);
                            dout.flush();
                            //System.out.println("Sent "+pos);
                            cancel.setVisible(false);
            				grid[l].setText(ch);
            				grid[l].setFont(new Font("Arial", Font.BOLD, 25));
                    		current.setText("");
                    		message.setText("");
                    		flag--;
                    		flag1++;
                    		tempo=l;
                    		
            			}
            		}
                 }
            }
        }
        else if(Integer.parseInt(command)>=225 && Integer.parseInt(command)<232){
        	k=225;
        	for(l=0;l<7;l++,k++){
        		if(command.equals(Integer.toString(k)) && flag==0 && !tile[l].getText().equals("")){
        			if(tile[l].getText().equals("_")){
        				while(true){
        					ch2= JOptionPane.showInputDialog(f,"Letter?");
            				if((int)ch2.charAt(0)>=97 && (int)ch2.charAt(0)<=122){
            					ch=ch2;
            					break;
            				}
        				}
        			}
        			else{
        				ch=tile[l].getText();
        			}
        			let=ch+"\n";
                    dout.writeUTF(let);
                    dout.flush();
                    //System.out.println("Sent "+let);
        			arr[m]=l;
        			//m++;
        			cancel.setVisible(true);
        			store = l;
        			tile[l].setText("");
        			current.setText("Tile chosen is "+"' "+ch+" '");
        			message.setText("");
        			flag++;
        		}
        	}
        }
        
        else if(Integer.parseInt(command)==232){
    		if(flag1==1)
    		{
    			//dout.writeUTF("400");
    			//dout.flush();
    			flag1=0;
    			grid[tempo].setText("");
    			tile[store].setText(ch);
    			cancel.setVisible(false);
    			
    		}
    		else
    		{
    			dout.writeUTF("400");
    			dout.flush();
    			tile[store].setText(ch);
				current.setText("");
				flag = 0;
				cancel.setVisible(false);
			}
    	}
        else if(Integer.parseInt(command)==233){
    		if(flag == 0){
    			if(counter!=0){
    				for(l=0;l<7;l++){
                		if(tile[l].getText().equals("")){
                    		tile[l].setText(randomiser());
                    		if(tile[l].getText().equals("")){
                    			message.setText("Tiles are over");
                    		}
                		}
                	}
    				t.stop();
    				counter=time;
    				timer.setText("Player 2's turn");
    				for(int i=0;i<7;i++){
    		    		tile[i].setVisible(false);
    				}
    				done.setVisible(false);
    				dout.writeUTF("-1");
    				dout.flush();
    			}
            	message.setText("");
        	}
        	else{
        		message.setText("Place tile first");
        	}
    	}
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
    }
    
    private class OptionPaneListeners implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		String s = e.getActionCommand();
    		if(Integer.parseInt(s) == 1){
    				start();
    		}
    		
    		else{
    			System.exit(0);
    		}
    	}
    }
     
    class TimerListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            timer.setText(""+ (counter--));

            if (counter == 0){
            	t.stop();
            	counter=time;
            	//t.removeActionListener(this);
            	timer.setText("Time is up. Player 2's turn.");
            	done.setVisible(false);
            	for(i=0;i<7;i++){
            		tile[i].setVisible(false);
            	} 
            	try {
            		DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
					dout.writeUTF("-1");
					dout.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
                  

        }

    }
}



public class Networking {
	public static void main(String[] args){
	    new Grid();	
		try
		{	
			Grid.Host.setVisible(false);
			Grid.status.setText("Waiting for Player 2 to join!");
			
			int port = 25000;
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("Waiting for Player 2");
			
		/* -> */	Grid.socket = serverSocket.accept();  /* The most important step */
			
			Grid.Host.setVisible(true);
			Grid.status.setText("Connected to Player 2!");
		}
		catch (Exception error)
		{
			error.printStackTrace();
		}
    }
}
