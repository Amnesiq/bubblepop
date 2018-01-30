package matthieuimbert.bubblepop.observer;

import java.util.List;
import matthieuimbert.bubblepop.model.Bubble;

public interface Observable {
	  public void addObserver(Observer obs);
	  public void removeObserver();
	  public void notifyObserver(List<Bubble> bubbles);
	  public void notifyObserver(long score);
	}
