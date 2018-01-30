package matthieuimbert.bubblepop.controler;

import matthieuimbert.bubblepop.model.AbstractModel;

public class BubblePopControler extends AbstractControler {
	
	public BubblePopControler(AbstractModel model) {
		super(model);
	}
	
	@Override
	public boolean isChoiceGood() {
		if(choice.equalsIgnoreCase(Integer.toString(goodChoice)))
			return true;
		return false;
	}
	
	@Override
	public void control() {
		if(isChoiceGood()) {
			model.removeBubbleFromId(goodChoice);
			model.updateScore(acquiredPoints);
			goodChoice=model.firstGreenBubbleIndex();
			if(goodChoice==-1) {
				model.generateBubbles();
				goodChoice=model.firstGreenBubbleIndex();
			}
		}
		else
			model.updateScore(-50);
	}
}
