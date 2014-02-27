package Screen;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_NICEST;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PERSPECTIVE_CORRECTION_HINT;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glHint;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import Controls.KeyboardControl;
import Controls.MouseControl;

public class Screen {
	
	MouseControl mc = new MouseControl();
	KeyboardControl kc =  new KeyboardControl();
	
	int screenSizeW;
	int screenSizeH;
	
	public Screen(int screenSizeWidth, int screenSizeHeight){
		screenSizeW = screenSizeWidth;
		screenSizeH = screenSizeHeight;
		screenSetup(screenSizeWidth,screenSizeHeight);
	}
	
	private void screenSetup(int screenWidth, int screenHeight) {
		{
				// Calculate Aspect Ratio
			float aspectRatio = (float) screenWidth/screenHeight;
				// Load Frame
			try {
				Display.setDisplayMode(new DisplayMode(screenWidth,screenHeight));
				Display.setTitle("..Chess..");
				Display.create();
			} catch (LWJGLException e){
				e.printStackTrace();
			}
			
				/* Initialise Screen Properties */
			glEnable(GL_TEXTURE_2D);
			glEnable(GL_BLEND); 
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			gluPerspective(50.0f,aspectRatio, 0.001f, 1000.0f);
			
			glMatrixMode(GL_MODELVIEW);
			glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
			glTranslatef(0.0f,0.0f,-20.0f);
			
				/* Initialise Components */
			init();
			
			
				/* Display */
			while(!Display.isCloseRequested()) {
					/* Render Images */
				glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);	
				
				draw();
				Display.update();
				Display.sync(60);				
			}
				/* Close Display */
			Display.destroy();									
			
		}
	
	}

	/*
	 * 		Init
	 * Initialise everything method.
	 */
	
	private void init(){
		loadAllGameTextures();
	}
	
	/* 
	 * 		Draw
	 * Draw all components
	 */
	private void draw(){
		drawComponents();
	}
	
	
	/*
	 * Load Textures
	 */
	
	private void loadAllGameTextures(){
		glEnable(GL_TEXTURE_2D);
		
		glDisable(GL_TEXTURE_2D);
	}
	
	private void drawComponents(){
			/* Enable Texturing */
		glEnable(GL_TEXTURE_2D);
		
		glDisable(GL_TEXTURE_2D);
	}
	

}
