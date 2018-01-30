package matthieuimbert.bubblepop.model;

import java.util.ArrayList;
import java.util.List;

import matthieuimbert.bubblepop.observer.Observer;

public abstract class AbstractModel {
	protected final int GAME_END_NBGREEN = 10;
	
	protected List<Bubble> bubbles;
	protected int lastIndex;
	protected int nbGreen;
	protected long score;
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	
	public abstract void init();
	
	public abstract void reset();
	
	public abstract void generateBubbles();
	
	public abstract boolean removeBubbleFromId(int id);
	
	public abstract Bubble getBubbleFromId(int id);
	
	public abstract int firstGreenBubbleIndex();
	
	public abstract void updateScore(long points);
	
	public List<Bubble> getBubbles(){
		return this.bubbles;
	}
	
	  //Implémentation du pattern observer
	public void addObserver(Observer obs) {
	    this.listObserver.add(obs);
	  }
	
	public void notifyObserver(List<Bubble> bubbles) {
	    for(Observer obs : listObserver)
	      obs.update(bubbles);
	  }
	
	public void notifyObserver(long score) {
		for(Observer obs : listObserver)
		      obs.update(score);
	}
	
	public void removeObserver() {
	    listObserver = new ArrayList<Observer>();
	  }
	
}
