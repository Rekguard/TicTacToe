package Shapes;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotated;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glVertex2d;
import Shapes.Interface.myShape;

public class Circle implements myShape {

	private double cRadius = 1.5;
	private double cSegments = 36;
	private double posX, posY;

	public void drawShape() {
		drawCircle();
	}

	public void drawCircle() {

		final double TOTALANGLE = 360.0;
		double split = TOTALANGLE / cSegments; 												// divided up
		double cornerAngle = (180 - split) / 2;
		double pointAngle = split / 2.0;
		double pointAngRad = Math.toRadians(pointAngle);
		double sinInsAngRad = Math.sin(pointAngRad); // SOH formula is used.

		double opposite = (cRadius * sinInsAngRad); // SOH, work out the length
													// of the opposite
		// using the hypotenuse and Point Angle/2
		glPushMatrix();
			glTranslated(posX, posY, 0.0f); // Translate the shape through x and y
										// by the radius
	
			for (int i = 0; i <= (cSegments - 1); i++) {
				// The Radius "Spoke of the Circle"
				glRotated(split, 0.0f, 0.0f, 1.0f);
	
				// The base of the triangle
				glPushMatrix();
					glTranslated(0.0f, cRadius, 0.0f); // Translate to the end of the
					glRotated((90.0 + (90.0 - cornerAngle)), 0.0f, 0.0f, 1.0f);
																		
					glBegin(GL_LINES);
						glVertex2d(0.0, 0.0);
							glVertex2d(0.0, opposite * 2);
					glEnd();
				glPopMatrix();
	
			}
		glPopMatrix();

	}
}
