package Controls;

import org.lwjgl.input.Mouse;

public class MouseControl {
	
	public void input(int screenHeight){
		int mouseX = Mouse.getX();
			/* OpenGL Y-Axis is always inverted! */
		int mouseY = (screenHeight-1)- Mouse.getY();
		
		try{
			if (Mouse.isButtonDown(0)) {
				System.out.println("Position: X = " + mouseX + " Y = "+ mouseY);
			}
			Thread.sleep(100);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}
