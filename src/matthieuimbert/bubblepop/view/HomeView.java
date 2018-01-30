package matthieuimbert.bubblepop.view;

import java.awt.Dimension;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import matthieuimbert.bubblepop.controler.AbstractControler;
import matthieuimbert.bubblepop.controler.BubblePopControler;
import matthieuimbert.bubblepop.model.AbstractModel;
import matthieuimbert.bubblepop.model.BubblePop;

public class HomeView {
	
	private final int WINDOW_WIDTH = 450; //largeur
	private final int WINDOW_HEIGHT = 300; //hauteur
	private final int PLAY_BUTTON_WIDTH = 100; //largeur
	private final int PLAY_BUTTON_HEIGHT = 50; //hauteur
	
    private Shell shell;
    private Display display;
    
    private BubblePopView gameView;
	
    public HomeView(){
    	display = new Display();
		shell = new Shell(display,SWT.SHELL_TRIM & (~SWT.RESIZE));
		shell.setText("BubblePop");
		FormLayout layout= new FormLayout ();
        shell.setLayout(layout);
        Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        shell.setBounds((screen.width-WINDOW_WIDTH)/2,(screen.height-WINDOW_HEIGHT)/2, WINDOW_WIDTH , WINDOW_HEIGHT);
    }
    
    private void init() {
    	Label label = new Label(shell, SWT.HORIZONTAL);
    	label.setText("Faites exploser les bulles vertes dans l'ordre croissant\n "
    			+ "le plus rapidement possible pour augmenter votre score.\n "
    			+ "Attention si vous vous trompez ...\n\n"
    			+ "Cliquez sur \"Jouer\" pour démarrer une partie");
    	
    	Button playButton =  new Button(shell, SWT.PUSH);
    	playButton.setText("Jouer");
    	playButton.addSelectionListener(new PlayButtonSelectionAdapter());
        FormData formData = new FormData(PLAY_BUTTON_WIDTH,PLAY_BUTTON_HEIGHT);
    	formData.top = new FormAttachment(75,-PLAY_BUTTON_HEIGHT/2);
        formData.left = new FormAttachment(50,-PLAY_BUTTON_WIDTH/2);
        playButton.setLayoutData(formData);
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
	}
	
	private void runGame(){
		AbstractModel model = new BubblePop();
		AbstractControler controler = new BubblePopControler(model);
		gameView = new BubblePopView(controler);
		model.addObserver(gameView);
		gameView.run();
	}
	
	class PlayButtonSelectionAdapter extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent e) {
				shell.dispose();
				display.dispose();
				runGame();
			}
		}
	

}
