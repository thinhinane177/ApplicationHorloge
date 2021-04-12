	package ClockApp;
	
	
import java.applet.Applet;
import java.awt.*;
import javax.swing.event.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.lang.Math.*;
	
public class ClockApp implements MouseListener{ // declare les attributs 
	private static JFrame fenetre;
	private GregorianCalendar calend;
	private int h,min,sec;
	private static AnalogiqueClockPanel analogiquePanel;
	private static DigitalClockPanel digitalClock;
	private static Boolean isAnalogique = true;
	private static ClockThread clockThreadDigital;
	private static ClockThread clockThreadAnalog;
	private static String[] timeSplitted;
	private static Boolean firstTime = true;
	
	
	// demander à l'utilisateur de saisir l'heur 
	
	public static void getUserEntry() {
	    String localHours;
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("Indiquez l'heur :HH:MM:SS");
	    localHours = scanner.next();
	    timeSplitted = localHours.split(":");
	    
	    //condition d'affichage
	    
        Boolean hoursCond = Integer.parseInt(timeSplitted[0]) >= 0 && Integer.parseInt(timeSplitted[0]) <=23;
        Boolean minutesCond = (Integer.parseInt(timeSplitted[0]) == 23 && Integer.parseInt(timeSplitted[1])==59) ||
        		(Integer.parseInt(timeSplitted[1]) >= 0 && Integer.parseInt(timeSplitted[1]) <= 59);
        Boolean secondsCond = (Integer.parseInt(timeSplitted[1]) == 59 && Integer.parseInt(timeSplitted[2]) == 59) ||
        		(Integer.parseInt(timeSplitted[2]) >= 0 && Integer.parseInt(timeSplitted[2]) <= 59);
        if(timeSplitted.length != 3 || !hoursCond || !minutesCond || !secondsCond) {
        	System.out.println("L'heure que vous avez saisie ne correspond pas au format demandé ! veuillez réessayer ");
        	displayWindow();
        }
	}
	// la forme de la fenetre d'affichage 

	public static void main (String args[]) {
		displayWindow();
	}
	
	public static void displayWindow() {
		getUserEntry(); 
		fenetre = new JFrame("JClock");
		fenetre.addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		fenetre.setSize(300,300);  // taille de la fenetre      
		fenetre.addMouseListener(new MouseAdapter() {
		     @Override
		     public void mousePressed(MouseEvent e) {
		 		isAnalogique = !isAnalogique;
				fenetre.getContentPane().removeAll();
				openClock(isAnalogique);
		     }
		  });
		if (firstTime) {
			openClock(isAnalogique);
			firstTime = false;
		}
	}
	    // affichage des deux horloge avec boolean 
	
	public static void openClock(Boolean isAnalog) {
		
		if (isAnalog) { //affichage de l'horloge Analogique 
			if (analogiquePanel == null) {
				System.out.println("Horloge Analogique");
		        analogiquePanel = new AnalogiqueClockPanel();
				analogiquePanel.getCalendar(timeSplitted);
				analogiquePanel.h = Integer.parseInt(timeSplitted[0]);
				analogiquePanel.min = Integer.parseInt(timeSplitted[1]);
				analogiquePanel.sec = Integer.parseInt(timeSplitted[2]);
			}
				fenetre.getContentPane().add(analogiquePanel);
			if (clockThreadAnalog == null) {
				clockThreadAnalog = new ClockThread(analogiquePanel);
				clockThreadAnalog.start();
			}
		} else { // si non affichage de l'horloge numerique 
			
			if (digitalClock == null) {
				System.out.println("Horloge Numerique");
		        digitalClock = new DigitalClockPanel();
		        digitalClock.getCalendar(timeSplitted);
		        digitalClock.hour = Integer.parseInt(timeSplitted[0]);
		        digitalClock.minute = Integer.parseInt(timeSplitted[1]);
		        digitalClock.second = Integer.parseInt(timeSplitted[2]);
			}
	        fenetre.getContentPane().add(digitalClock);
	        if (clockThreadDigital == null) {
				clockThreadDigital = new ClockThread(digitalClock);
				clockThreadDigital.start();
	        }

		}
		fenetre.setVisible(true);

	}
	  
	

	// ça concerne la souris 

	@Override
	public void mouseClicked(MouseEvent e) {
		
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


} //fin


