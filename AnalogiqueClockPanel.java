package ClockApp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JPanel;

class AnalogiqueClockPanel extends JPanel { 
	//décalare les variables our l'analogique 
	public int h,min,sec,xc,yc;
	private Font font;
	private GregorianCalendar calend;
	
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		// forme du paneau de l'affichage de l'horloge analogique 
		xc=getWidth()/2;  // largeur 
		yc=getHeight()/2;  // longueur 
	    int rayon=Math.min(xc,yc)*75/100; // rayon de l'analogique 
	     
	    font= new Font("Times New Roman",0,20);
	    g.setFont(font); // police utilisé pour les chiffres et la taille 
		
		for(int i=1;i<=12;i++) { 
			//Fonctionnement des aiguilles 
		     double angle=i*Math.PI/6.0-Math.PI/2.0;
		     double x=xc+rayon*Math.cos(angle);
		     double y=yc+rayon*Math.sin(angle);
		      g.drawString(" "+i,(int)x,(int)y);
		}
		
		// gestion et affichage de aiguilles
		
		double anglesec=(sec*((Math.PI)/30.0)-(Math.PI/2.0));
		int xsf=xc+(int)(0.7*rayon*Math.cos(anglesec));
		int ysf=yc+(int)(0.7*rayon*Math.sin(anglesec));
		g.setColor(Color.gray);
		g.drawLine(xc,yc,xsf,ysf);
			
		double anglemin=(min*((Math.PI)/30.0)-(Math.PI/2.0));
		int xmf=xc+(int)(0.6*rayon*Math.cos(anglemin));
		int ymf=yc+(int)(0.6*rayon*Math.sin(anglemin));
		g.setColor(Color.green);
		g.drawLine(xc,yc,xmf,ymf);
		
		
		double angleheure=(h*((2*Math.PI)/12.0)-(Math.PI/2.0));
		int xhf=xc+(int)(0.4*rayon*Math.cos(angleheure));
		int yhf=yc+(int)(0.4*rayon*Math.sin(angleheure));
		g.setColor(Color.red);
		g.drawLine(xc,yc,xhf,yhf);
		

	}
	
	
	public void getCalendar(String[] timeSplitted) {
		// assignement des valeurs pour heurs, minutes, secondes..
        Date date = new Date();
    	date.setHours(Integer.parseInt(timeSplitted[0]));	
    	date.setMinutes(Integer.parseInt(timeSplitted[1]));	    
    	date.setSeconds(Integer.parseInt(timeSplitted[2]));	  
        
         // creation d'un nouveau calendrier du calendrier gregorien 
		calend = new GregorianCalendar();
		calend.setTime(date);
		h = calend.get(GregorianCalendar.HOUR_OF_DAY);
		min = calend.get(GregorianCalendar.MINUTE);
		sec = calend.get(GregorianCalendar.SECOND);
	}
	 // incrementation de l'analogique 
	public void increment() {
		sec = sec + 1;
		if(sec > 60) {
			min = min + 1;
			sec = 0;
			if(min > 60) {
				h = h + 1;
				min = 0;
			    if(h > 12) {
					h = 0;
				}
			}
		}
		super.repaint();
	}

}/* fin de classe ClockPanel */