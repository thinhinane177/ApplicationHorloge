package ClockApp;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

 class DigitalClockPanel extends JPanel {
	 //declaration des variables pour l'horloge numerique 
   
    String stringTime;
    int hour, minute, second;

    String correctionHour = "";
    String correctionMinute = "";
    String correctionSecond = "";
    
    private GregorianCalendar calend;

    public void setStringTime(String xyz) {
        this.stringTime = xyz;
    }
      
    public int findMinimumBetweenTwoNumbers(int a, int b) {
        return (a <= b) ? a : b;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // affichage des heurs:minutes:secondes
        if (hour < 10) {
            this.correctionHour = "0";
        }
        if (hour >= 10) {
            this.correctionHour = "";
        }

        if (minute < 10) {
            this.correctionMinute = "0";
        }
        if (minute >= 10) {
            this.correctionMinute = "";
        }

        if (second < 10) {
            this.correctionSecond = "0";
        }
        if (second >= 10) {
            this.correctionSecond = "";
        }
        setStringTime(correctionHour + hour + ":" + correctionMinute+ minute + ":" + correctionSecond + second);
        g.setColor(Color.BLACK);
        int length = findMinimumBetweenTwoNumbers(this.getWidth(),this.getHeight());
        Font myFont = new Font("Times New Roman ", Font.PLAIN, length / 5);
        g.setFont(myFont);
        g.drawString(stringTime, (int) length/6, length/2);

    }
    
	public void getCalendar(String[] timeSplitted) {
		// j'assigne les valeurs pour heurs, minutes et secondes
        Date date = new Date(); 
    	date.setHours(Integer.parseInt(timeSplitted[0]));	
    	date.setMinutes(Integer.parseInt(timeSplitted[1]));	    
    	date.setSeconds(Integer.parseInt(timeSplitted[2]));	  
        //decalatartion d'un nouveau calendrier gregorien 
  
		calend = new GregorianCalendar();
		calend.setTime(date);
		hour = calend.get(GregorianCalendar.HOUR_OF_DAY);
		minute = calend.get(GregorianCalendar.MINUTE);
		second = calend.get(GregorianCalendar.SECOND);
	}
	//incremenation de l'horloge numerique 

	public void increment() {
		second = second + 1;
		if(second >= 60) {
			minute = minute + 1;
			second = 0;
			if(minute >= 60) {
				minute = 0;
				hour = hour + 1;
				if(hour >= 24) {
					hour = 0;
				}
			}
		}
		super.repaint();
	}
	
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 100); // taille de la fenetre d'affichage de l'horloge 
    }

}