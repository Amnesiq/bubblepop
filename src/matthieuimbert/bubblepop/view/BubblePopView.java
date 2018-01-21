package matthieuimbert.bubblepop.view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import matthieuimbert.bubblepop.controler.AbstractControler;
import matthieuimbert.bubblepop.model.Bubble;
import matthieuimbert.bubblepop.observer.Observer;

public class BubblePopView implements Observer {

	private final int WINDOW_WIDTH = 450; //largeur
	private final int WINDOW_HEIGHT = 300; //hauteur
	private final int BUTTON_WIDTH = 50; //largeur
	private final int BUTTON_HEIGHT = 50; //hauteur
	
    private Shell shell;
    private Display display;
    private ResourceManager resManager;
    
    private AbstractControler controler;
    
    private ButtonSelectionAdapter buttonSa = new ButtonSelectionAdapter();
    
    public BubblePopView(AbstractControler controler) {
    	this.controler=controler;
		display = new Display();
		shell = new Shell(display,SWT.SHELL_TRIM & (~SWT.RESIZE));
        resManager = new LocalResourceManager(JFaceResources.getResources(), shell);
		shell.setText("BubblePop");
		FormLayout layout= new FormLayout ();
        shell.setLayout(layout);
        Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        shell.setBounds((screen.width-WINDOW_WIDTH)/2,(screen.height-WINDOW_HEIGHT)/2, WINDOW_WIDTH , WINDOW_HEIGHT);
    }
	
	private void refreshWindow(List<Bubble> bubbles) {
		for(Control kid : shell.getChildren()){
			kid.dispose();
		}
        generateButtons(bubbles);
        shell.layout();
	}
	
	private void generateButtons(List<Bubble> bubbles) {
		List<FormData> fdList = new ArrayList<FormData>();
		for(Bubble b : bubbles) {
	        Button button =  new Button(shell, SWT.PUSH);
			button.addSelectionListener(buttonSa);
			// Placement et non chevauchement des boutons
			FormData formData = new FormData(BUTTON_WIDTH,BUTTON_HEIGHT);
			do {
		        double left = (((Math.random()*WINDOW_WIDTH)-BUTTON_WIDTH)/WINDOW_WIDTH)*100.0;
		        if(left<0)
		        	left=0;
		        double top = (((Math.random()*WINDOW_HEIGHT)-BUTTON_HEIGHT)/WINDOW_HEIGHT)*100.0;
		        if(top<0)
		        	top=0;
		        formData.top = new FormAttachment((int)top);
		        formData.left = new FormAttachment((int)left);
			}while(isOverlapping(fdList, formData));
	        fdList.add(formData);
			// Style du bouton
			RGB rgb = new RGB(0,0,0);
			if(b.getCouleur().equalsIgnoreCase("Verte")) {
				rgb.red=34;
				rgb.green=139;
				rgb.blue=34;
			}
			else if(b.getCouleur().equalsIgnoreCase("Rouge"))
			{
				rgb.red=205;
				rgb.green=38;
				rgb.blue=38;
			}
	        Color color = resManager.createColor(rgb);
	        // Creation et configuration du bouton
	        button.setBackground(color);
	        button.setText(Integer.toString(b.getNumero()));
	        button.setLayoutData(formData);
	        
		}
	}
	
	private boolean isOverlapping(List<FormData> list,FormData element) {
		boolean result = false;
			for(FormData fd : list) {
				int fdY = (fd.top.numerator*WINDOW_HEIGHT)/fd.top.denominator;
				int elementY = (element.top.numerator*WINDOW_HEIGHT)/element.top.denominator;
				int fdX = (fd.left.numerator*WINDOW_WIDTH)/fd.left.denominator;
				int elementX = (element.left.numerator*WINDOW_WIDTH)/element.left.denominator;
				double absX = Math.abs(fdX-elementX);
				double absY = Math.abs(fdY-elementY);
				if((absY<=BUTTON_HEIGHT)&&(absX<=BUTTON_WIDTH))
					return true;	
			}
		return result;
	}
	
	private void init() {
		controler.init();
	}
	
	private void defeat() {
		shell.dispose();
	}
	
	public void run() {
		init();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
        resManager.dispose();
	}
	
	@Override
	public void update(List<Bubble>bubbles) {
		if(bubbles==null)
			defeat();
		else
			refreshWindow(bubbles);
	}
	
	class ButtonSelectionAdapter extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent e) {
			Object o = e.getSource();
			if (o instanceof Button) {
				Button b = (Button) o;
				controler.addChoice(b.getText());
			}
		}
	}

}
