package engine;

import screen.sc;
import screen.cons;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.lang.Thread;
import java.lang.Runnable;
import java.lang.Math;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class eng implements MouseMotionListener, KeyListener, Runnable, cons, ItemListener{

	boolean rs=true;
	Thread tr = new Thread(this);
	boolean left=true;
	boolean right =false;
	boolean stop = false;
	boolean vniz=true;
	boolean vverh=false;
	int spd=8;
	int t = 80;
	int conpl=4;
	int conadd=7;
	int nt = t-20;
	int nnt = t-30;
	
	sc SC;
	public eng(sc SC){
		this.SC = SC;
		
		
		tr.start();   
		tr.suspend(); 
	}
	
	public void pause(){
		SC.levelChooser.setEnabled(true);
		tr.suspend();
	}
	public void resume(){
		SC.levelChooser.setEnabled(false);
		tr.resume(); 
	}
	
	public void mouseDragged(java.awt.event.MouseEvent evt){
		if((evt.getY()-20)<SC.kidY && SC.kidY>10){
			SC.kidY-=10;
			SC.repaint();
		}
		if((evt.getY()-20)>SC.kidY && SC.kidY<250){
			SC.kidY+=10;
			SC.repaint();
		}
	
	}
	public void mouseMoved(java.awt.event.MouseEvent evt){
	}
	
	public void keyPressed(KeyEvent evt){
		
			if(evt.getKeyCode()==KeyEvent.VK_UP && SC.kidY>20){
			SC.kidY-=20;
			SC.repaint();
			}
		if(evt.getKeyCode()==KeyEvent.VK_DOWN && SC.kidY<240){
			SC.kidY+=20;
			SC.repaint();
		}
		if (evt.getKeyChar()=='q'){    
			for(int i=0;i<SC.l.length-2;i++){
				SC.l[i].setVisible(false);
			}
		resume();
			}
		if (evt.getKeyChar()=='s'){
			pause();	}
		if(evt.getKeyChar()=='g'){
		resume();
		
		}}
	public void keyReleased(KeyEvent evt){
	}
	public void keyTyped(KeyEvent evt){
	}
	
	public void itemStateChanged(ItemEvent e){
		
		if(e.getStateChange()==ItemEvent.SELECTED){
			pause();
			int d =SC.levelChooser.getSelectedIndex();
			
			for (int i=0;i<SC.slozh.length;i++){
			if(d==i){
				
				spd-=i*2;     //Levels
				conadd+=i*2;
				conpl+=i*2;
				t+=i*15;
				nt=t-20;
				nnt=t-10;
				
			}}}}
	
	public void random(){      
		while(true){
		int k = (int)(Math.random()*9);
		int r =(int)(Math.random()*2);
		if(k==2|k==4|k==6|k==8|k==20){
			if(r==1){
				SC.balY-=k;
			}
			else{
			SC.balY+=k;}
			break;
		}
	}}
	
	public void run(){   
							//Movement of the ball
		int count = 0;
	
		while(true){
						
			if(left){
				SC.balX-=2;
				
				if(vniz){  // rebound
					SC.balY+=2;
				if(SC.balY==tblH-20){
					vniz=false;
					vverh=true;
					}}
				if(vverh){
					SC.balY-=2;
				if(SC.balY==10){
					vverh=false;
					vniz=true;
					}}
								
				if(SC.balX==14&(SC.balY>SC.compY-10 & SC.balY<SC.compY+rocH)){  //Computer racket
					left=false;
					right=true;
					count++;
					if(count==conadd){    
						t=nt;
					}
					if (count==conadd+conpl){
						t=nnt;
					}
				}
				else if(SC.balX==2){
					SC.you+=1;
					SC.l[3].setText("Score:    COMP  "+SC.comp+"  "+SC.you+"  YOU");
					count=0;
					t+=30;     
					pause();
					SC.balX=24;
					random();
					left=false;
					right=true;
					System.out.println(t);
				}
				
				if(SC.balX<t){
					if(SC.compY>SC.balY+6 & SC.compY>10){
						SC.compY-=10;}
					else if(SC.compY<SC.balY-38 &  SC.compY<tblH-50){
						SC.compY+=10;}
				}}
		
			if(right){
				SC.balX+=2;
				
				if(vniz){ // rebound
					SC.balY+=2;
				if(SC.balY==tblH-20){
					vniz=false;
					vverh=true;
					}}
				if(vverh){
					SC.balY-=2;
				if(SC.balY==10){
					vverh=false;
					vniz=true;
					}}
								
		if(SC.balX==SC.kidX-8&(SC.balY>SC.kidY-10&SC.balY<SC.kidY+rocH)){  //kid beats off
			right=false;
			left=true;}
		else if(SC.balX==tblW-10) {
			SC.comp+=1;
			SC.l[3].setText("Score:    COMP  "+SC.comp+"  "+SC.you+"  YOU");
			pause();
			SC.balX=362;
			random();
			right=false;
			left=true;
		}}
				
	try {
		tr.sleep(spd);    
	}catch(InterruptedException e){
		e.printStackTrace();
	}
	SC.repaint();
		}
	}}
