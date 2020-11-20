package interfaces;

import gui.GUISimulator;
import gui.GraphicalElement;

public interface Drawable {
	public static final int RESOLUTION = 800;
	public static final int OFFSET_HAUT = 15;
	public static final int OFFSET_GAUCHE = 15;
	GraphicalElement draw(GUISimulator gui, int n);
}
