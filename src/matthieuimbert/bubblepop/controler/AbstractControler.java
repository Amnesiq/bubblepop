package matthieuimbert.bubblepop.controler;

import matthieuimbert.bubblepop.model.AbstractModel;

public abstract class AbstractControler {
	protected AbstractModel model;
	protected String choice;
	protected int goodChoice;
	protected long acquiredPoints;
	
	public AbstractControler(AbstractModel model) {
		this.model=model;
	}
	
	public void init() {
		this.model.init();
		goodChoice=model.firstGreenBubbleIndex();
	}
	
	public void reset() {
		this.model.reset();
	}
	
	public void setAcquiredPoints(long points) {
		this.acquiredPoints=points;
	}
	
	public void setChoice(String choice) {
		this.choice=choice;
		control();
	}
	
	public abstract void control();
	
	public abstract boolean isChoiceGood();
}
