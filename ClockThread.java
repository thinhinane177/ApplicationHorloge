package ClockApp;

class ClockThread extends Thread {
	// declaration des variables 
	private AnalogiqueClockPanel horlogeAnalogique;
	private DigitalClockPanel horlogeDigital;
	private Boolean isHorlogeAnalogique;
	

	ClockThread(AnalogiqueClockPanel analogiquePanel) {
		// l'analogique s'affichera en premier donc true
	this.horlogeAnalogique = analogiquePanel;
		this.isHorlogeAnalogique = true;
	}
	
	public ClockThread(DigitalClockPanel digitalClock) {
		// lorsque c'est horloge numerique qui va s'afficher, analogique ne s'affichera plus => false
		this.horlogeDigital = digitalClock;
		isHorlogeAnalogique = false;
	}

	public void run() {
		//  boucle des deux horloges ( fonctionnement) 
		while(true) {
			if (isHorlogeAnalogique) {
				this.horlogeAnalogique.increment();
			} else {
				this.horlogeDigital.increment();
			}
			try {
				sleep(1000); // temps d'affichage de la deuxieme horloge 
			}
			catch(InterruptedException e) {
			}
		}
	}

}