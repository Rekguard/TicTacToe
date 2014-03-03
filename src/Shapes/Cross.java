package Shapes;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;
import Shapes.Interface.myShape;

public class Cross implements myShape {
	
	public void drawShape(){
		
		float size = 1.5f;
		
		glBegin(GL_LINES);
			glVertex2f(size, -size);
				glVertex2f(-size, size);
			glVertex2f(size, size);
				glVertex2f(-size, -size);
		glEnd();
	}

}
