package Manager;

import java.awt.Graphics2D;

import GameState.AdminState;
import GameState.BossState;
import GameState.ChestState;
import GameState.CraftingBookState;
import GameState.DialogState;
import GameState.GameOverState;
import GameState.GameState;
import GameState.HouseState;
import GameState.InGameMenueState;
import GameState.IntroState;
import GameState.IntroState2;
import GameState.InventoryState;
import GameState.LoadState;
import GameState.MenuState;
import GameState.MerchantState;
import GameState.OptionState;
import GameState.OutroState;
import GameState.OvenState;
import GameState.PauseState;
import GameState.PlayState;
import GameState.PlayerStatState;
import GameState.PlayerStatState2;
import GameState.PuzzleState;
import GameState.TutorialState;
import GameState.TutorialState2;

/**
 * @author lumpy Class to manage the different GameStates. Switching GameStates.
 */
public class GameStateManager {

	private GameState[] gameStates;
	public static int currentState;
	private static int prevState;
	public static final int NUM_STATES = 23;

	public static final int PAUSE = 0;
	public static final int PLAY = 1;
	public static final int GAMEOVER = 2;
	public static final int INTRO = 3;
	public static final int MENU = 5;
	public static final int INVENTORY = 4;
	public static final int STATUS = 6;
	public static final int INGAMEMENUE = 7;
	public static final int LOADGAME = 8;
	public static final int OPTIONSMENU = 9;
	public static final int BOSS = 10;
	public static final int DIALOG = 11;
	public static final int HOUSE = 12;
	public static final int OUTRO = 13;
	public static final int PUZZLE = 14;
	public static final int CHEST = 15;
	public static final int OVEN = 16;
	public static final int MERCHANT = 17;
	public static final int ADMIN = 18;
	public static final int TUTORIAL = 19;
	public static final int TUTORIAL2 = 20;
	public static final int CRAFTINGBOOK = 21;
	public static final int STATUS2 = 22;

	// public PlayState tadsf = new PlayState(null);
	// public MenuState takdsf = new MenuState(null);
	public GameStateManager() {
		gameStates = new GameState[NUM_STATES];
		//setState(MENU);
		setState(MENU);

	}

	public void setState(int state) {
		prevState = currentState;
		currentState = state;
		/**
		 * Selecting a GameState
		 */
		switch (state) {
		case PAUSE:
			gameStates[state] = new PauseState(this);
			gameStates[state].init();
			return;

		case PLAY:
			if (gameStates[state] == null) {
				gameStates[state] = new PlayState(this);
				gameStates[state].init();
			}
			break;

		case INTRO:
			gameStates[state] = new IntroState2(this);
			gameStates[state].init();
			break;

		case INVENTORY:
			gameStates[state] = new InventoryState(this);
			gameStates[state].init();
			break;

		case GAMEOVER:
			gameStates[state] = new GameOverState(this);
			gameStates[state].init();
			break;

		case STATUS:
			gameStates[state] = new PlayerStatState(this);
			gameStates[state].init();
			break;

		case STATUS2:
			gameStates[state] = new PlayerStatState2(this);
			gameStates[state].init();
			break;
			
		case INGAMEMENUE:
			gameStates[state] = new InGameMenueState(this);
			gameStates[state].init();
			break;

		case MENU:
			gameStates[state] = new MenuState(this);
			gameStates[state].init();
			break;

		case LOADGAME:
			gameStates[state] = new LoadState(this);
			gameStates[state].init();
			break;

		case OPTIONSMENU:
			gameStates[state] = new OptionState(this);
			gameStates[state].init();
			break;

		case BOSS:
			gameStates[state] = new BossState(this);
			gameStates[state].init();
			break;

		case DIALOG:
			gameStates[state] = new DialogState(this);
			gameStates[state].init();
			break;

		case HOUSE:
			if (gameStates[state] == null) {
				gameStates[state] = new HouseState(this);
				gameStates[state].init();
			} else
				HouseState.initentered();
			break;

		case OUTRO:
			gameStates[state] = new OutroState(this);
			gameStates[state].init();
			break;

		case PUZZLE:
			if (gameStates[state] == null) {
				gameStates[state] = new PuzzleState(this);
				gameStates[state].init();
			} else
				PuzzleState.initentered();
			break;

		case CHEST:
			gameStates[state] = new ChestState(this);
			gameStates[state].init();
			break;

		case OVEN:
			gameStates[state] = new OvenState(this);
			gameStates[state].init();
			break;

		case MERCHANT:
			gameStates[state] = new MerchantState(this);
			gameStates[state].init();
			break;

		case ADMIN:
			gameStates[state] = new AdminState(this);
			gameStates[state].init();
			break;

		case TUTORIAL:
			gameStates[state] = new TutorialState(this);
			gameStates[state].init();
			break;

		case TUTORIAL2:
			gameStates[state] = new TutorialState2(this);
			gameStates[state].init();
			break;
		
		case CRAFTINGBOOK:
			gameStates[state] = new CraftingBookState(this);
			gameStates[state].init();
			break;
		}

		// unloadState(currentState);
	}

	/**
	 * Method to unload your State
	 */
	public void unloadState(int state) {
		gameStates[state] = null;
	}

	public void update(InputHandler input) {
		gameStates[currentState].update(input);
		input.update();
	}

	public void draw(Graphics2D g) {
		gameStates[currentState].draw(g);
	}

	public static int getPrevState() {
		return prevState;
	}

	public int getCurrentState() {
		return currentState;
	}
}
