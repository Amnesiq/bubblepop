package matthieuimbert.bubblepop.model;

import java.util.ArrayList;
import java.util.List;

import matthieuimbert.bubblepop.observer.Observer;

public abstract class AbstractModel {
	protected List<String> choices = new ArrayList<String>();
	protected List<String> greenBubblesId = new ArrayList<String>();
	protected List<Bubble> bubbles = new ArrayList<Bubble>();
	protected int lastIndex;
	protected int score;
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	
	public abstract void init();
	
	public abstract void reset();
	
	public abstract void generateBubbles();
	
	public abstract void addChoice(String choice);
	
	public abstract void matchingChoices();
	
	  //Implémentation du pattern observer
	public void addObserver(Observer obs) {
	    this.listObserver.add(obs);
	  }
	
	public void notifyObserver(List<Bubble> bubbles) {
	    for(Observer obs : listObserver)
	      obs.update(bubbles);
	  }
	
	public void removeObserver() {
	    listObserver = new ArrayList<Observer>();
	  }  
}
