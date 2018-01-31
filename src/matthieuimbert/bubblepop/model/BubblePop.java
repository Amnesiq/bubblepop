package matthieuimbert.bubblepop.model;

import java.util.ArrayList;

public class BubblePop extends AbstractModel {
	
	@Override
	public void init() {
		bubbles = new ArrayList<Bubble>();
		reset();
		generateBubbles();	
	}
	
	@Override
	public void reset() {
		bubbles.clear();
		lastIndex=1;
		score=0;
		nbGreen=0;
		notifyObserver(score);
	}

	@Override
	public void generateBubbles() {
		bubbles.clear();
		int nbBubble = 25;
		int i = lastIndex;
		if(nbGreen<GAME_END_NBGREEN) {
			while(nbGreen<GAME_END_NBGREEN && i<nbBubble+lastIndex ) {
				if(i==lastIndex) {
					nbGreen++;
					bubbles.add(new Bubble((i),"Verte"));
				}
				else {
					double rdm = Math.random();
					String color = null;
					if(rdm>0.33) {
						color = "Verte";
						nbGreen++;
					}
					else
						color = "Rouge";
					bubbles.add(new Bubble(i,color));
				}
				i++;
			}
			lastIndex=i;
			notifyObserver(bubbles);
		}
		else
			notifyObserver(null);
		
	}
	
	@Override
	public Bubble getBubbleFromId(int id) {
		for(Bubble b : bubbles) {
			if(b.getNumero()==id)
				return b;
		}
		return null;
	}
	
	@Override
	public boolean removeBubbleFromId(int id) {
		boolean result =  false;
		Bubble b = getBubbleFromId(id);
		if(b!=null) {
			result = bubbles.remove(b);
			notifyObserver(bubbles);
			return result;
		}
		return result;
	}
	
	@Override
	public int firstGreenBubbleIndex() {
		int result = -1;
		if(bubbles==null)
			return result;
		for(Bubble b : bubbles)
			if(b.getCouleur()=="Verte")
				return b.numero;
		return result;
	}
	
	@Override
	public void updateScore(long points) {
		score+=points;
		notifyObserver(score);
	}
}
