package screen;

import engine.eng;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class sc extends JPanel implements cons{
	JPanel p = new JPanel(),p1=new JPanel();
	
	public int you=0;
	public int comp=0;
	
	public JLabel l [] = new JLabel [5]; 
	String Str []={"Control:   q - start game","Racket movement: "," drag it with your mouse","SCORE:   COMP  "+comp+"  "+you+"  YOU","   Difficulty level:   "};
	public String slozh[]={"1","2","3"};
	public JComboBox levelChooser = new JComboBox(slozh);
	
	public int kidX=skidX;
	public int kidY=skidY;
	public int balX=sbalX;
	public int balY=sbalY;
	public int compX=scompX;
	public int compY=scompY;

public sc(){
	eng ENG = new eng(this);
	addMouseMotionListener(ENG);
	addKeyListener(ENG);
	levelChooser.addItemListener(ENG);
	
	for (int i=0;i<l.length;i++){
		l[i]=new JLabel (Str[i]);
		l[i].setFont(new Font("",Font.BOLD,20));
		if(i<3){
		add(l[i]);
	}}
	
	levelChooser.setFocusable(false); 	
	p.setLayout(new BorderLayout());
	p1.setLayout(new GridLayout(1,2,5,5));
	p1.add(l[4]);
	p1.add(levelChooser);
	p.add("North",l[3]);
	p.add("Center",this);
	p.add("South",p1);
	setFocusable(true); 
	l[3].setHorizontalAlignment(JLabel.CENTER);
	}	

@Override
public void paintComponent(Graphics gr){
	
	gr.setColor(Color.GREEN);
	gr.fillRect(0,0,tblW,tblH);
	
	gr.setColor(Color.YELLOW);
	gr.drawRect(10, 10, tblW-20, tblH-20);
	gr.drawRect(10, (tblH-tblH/4)/2, tblW/13, tblH/4);
	gr.drawRect(tblW-tblW/13-10,(tblH-tblH/4)/2,tblW/13,tblH/4);
	gr.drawLine(tblW/2, 10, tblW/2, tblH-10);
	gr.drawOval(tblW/2-tblH/8, tblH/2-tblH/8, tblH/4, tblH/4);
	gr.fillOval(tblW/2-3, tblH/2-3, 6, 6);
	
	gr.setColor(Color.RED);
	gr.fillRect(compX,compY , 5, 40);
	gr.fillRect(kidX, kidY, 5, 40);
	gr.fillOval(balX, balY, balSize, balSize);
}

public static void main (String[]args){

		sc SC = new sc();

	JFrame fr = new JFrame("Ping Pong Game by Artem");
	fr.setIconImage(new ImageIcon(SC.getClass().getResource("/ten.png")).getImage());
	fr.setResizable(false);
	fr.setContentPane(SC.p);
	fr.setBounds(700,400,tblW+15,tblH+100);
	fr.setVisible(true);
	fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
}
}
