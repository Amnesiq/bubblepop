package matthieuimbert.bubblepop.observer;

import java.util.List;
import matthieuimbert.bubblepop.model.Bubble;

public interface Observer {
	  public void update(List<Bubble> bubbles);
}