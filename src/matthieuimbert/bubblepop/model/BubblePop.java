package matthieuimbert.bubblepop.model;

public class BubblePop extends AbstractModel {
	
	@Override
	public void init() {
		lastIndex=1;
		generateBubbles();	
	}
	
	@Override
	public void reset() {
		bubbles.clear();
		choices.clear();
		greenBubblesId.clear();
	}

	@Override
	public void generateBubbles() {
		reset();
		int nbBubble = 3;
		int i=0;
		for(i=lastIndex;i<lastIndex+nbBubble;i++) {
			if(i==lastIndex) {
				bubbles.add(new Bubble(i,"Verte"));
				greenBubblesId.add(Integer.toString(i));
			}
			else {
				double rdm = Math.random();
				String color = null;
				if(rdm>0.33) {
					color = "Verte";
					greenBubblesId.add(Integer.toString(i));
				}
				else
					color = "Rouge";
				bubbles.add(new Bubble(i,color));
			}
		}
		lastIndex=i;
		notifyObserver(bubbles);
	}

	@Override
	public void addChoice(String choice) {
		choices.add(choice);
		matchingChoices();
	}
	
	@Override
	public void matchingChoices() {
		if(greenBubblesId.size()==choices.size())
		{
			for(int i =0;i<choices.size();i++) {
				if(!greenBubblesId.get(i).equalsIgnoreCase(choices.get(i)))
					notifyObserver(null);
			}
			generateBubbles();
		}
	}
}
