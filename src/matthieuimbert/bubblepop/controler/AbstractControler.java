package matthieuimbert.bubblepop.controler;

import matthieuimbert.bubblepop.model.AbstractModel;

public abstract class AbstractControler {
	protected AbstractModel model;
	
	public AbstractControler(AbstractModel model) {
		this.model=model;
	}
	
	public void init() {
		this.model.init();
	}
	
	public void reset() {
		this.model.reset();
	}
	
	public void addChoice(String choice) {
		this.model.addChoice(choice);
	}
	
	public abstract void control();
}
