package matthieuimbert.bubblepop.test;

import matthieuimbert.bubblepop.controler.AbstractControler;
import matthieuimbert.bubblepop.controler.BubblePopControler;
import matthieuimbert.bubblepop.model.AbstractModel;
import matthieuimbert.bubblepop.model.BubblePop;
import matthieuimbert.bubblepop.view.BubblePopView;

public class Test {

	public static void main(String[] args) {
		AbstractModel model = new BubblePop();
		AbstractControler controler = new BubblePopControler(model);
		BubblePopView view = new BubblePopView(controler);
		model.addObserver(view);
		view.run();

	}

}
