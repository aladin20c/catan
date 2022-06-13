package platform.states;

import platform.DisplayJframe;
import platform.GameSettings;
import platform.entities.Player;
import platform.observers.LargestArmyObserver;
import platform.observers.LongestRoadObserver;
import platform.observers.Vpobserver;
import platform.states.mainstates.PlaceRoadState;
import platform.states.mainstates.PlaceSettelmentState;
import platform.states.mainstates.PlayerInterfaceState;

import java.awt.*;
import java.awt.event.KeyEvent;


public class MenuState extends GameState {


	private String[] optionsMenu;
	private int selected;
	
	public MenuState(GameStateManager gsm,GameSettings settings) {
		super(gsm,settings);
		this.optionsMenu = new String[] {"Start", "Options", "Quit"};
		selected=0;
		System.out.println("[GameStates][MenuState]: Creating menu...");
	}
	public void reinit(){selected=0;}


	@Override
	public void render(Graphics graphics) {
		graphics.setColor(new Color(51, 231, 255));
		graphics.fillRect(0, 0, DisplayJframe.WIDTH, DisplayJframe.HEIGHT);
		
		graphics.setFont(new Font("Arail", Font.PLAIN, 42));
		
		for(int i=0;i<optionsMenu.length;i++) {
			if(selected == i) graphics.setColor(Color.GREEN);
			else graphics.setColor(Color.WHITE);
			
			graphics.drawString(optionsMenu[i], DisplayJframe.WIDTH/2 -200, 100 +i*120);
		}
	}



	@Override
	public void keyPressed(int key) {
		if(key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}else if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			if(selected > 0) selected--;
		}
		else if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			if(selected < optionsMenu.length-1) selected++;
		}
		else if(key == KeyEvent.VK_ENTER || key == KeyEvent.VK_E) {
			if(selected == 0) {
				//initialising settings and observers
				settings.init();

				Vpobserver ob_1=new Vpobserver(settings,gsm);
				LongestRoadObserver ob_2=new LongestRoadObserver(settings,gsm);
				LargestArmyObserver ob_3=new LargestArmyObserver(settings,gsm);
				for(Player p: settings.getPlayers()){
					p.vpobserver=ob_1;
					p.longestroadobserver=ob_2;
					p.largestarmyobserver=ob_3;
				}

				gsm.addState(new PlayerInterfaceState(gsm, settings, settings.getPlayers()[0]));
				int n=settings.getPlayers().length;
				for(int i=0;i<n;i++) {
					gsm.addState(new PlaceRoadState(gsm, settings, settings.getPlayers()[i]));
					gsm.addState(new PlaceSettelmentState(gsm, settings, settings.getPlayers()[i],true));
				}
				for(int i=n-1;i>=0;i--) {
					gsm.addState(new PlaceRoadState(gsm, settings, settings.getPlayers()[i]));
					gsm.addState(new PlaceSettelmentState(gsm, settings, settings.getPlayers()[i]));
				}
				this.gsm.applyStrategy();

			} else if(selected == 1) {
				gsm.addState(new OptionsState(gsm,settings));
			} else if(selected == 2) {
				System.exit(0);
			}
		}
	}

	@Override
	public void keyReleased(int key) {}
}
