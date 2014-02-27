package UtilityTools;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class MyTextureloader {
	
	public static void loadTexture(Texture itemName,String fileType, String directoryPath){
		
			/* Texture Loader with directory path from argument */
		try{
			itemName = TextureLoader.getTexture(fileType, 
							ResourceLoader.getResourceAsStream(directoryPath));
		}catch(FileNotFoundException e){
			System.out.println("File:" + directoryPath + " not found, file not found!");
			e.printStackTrace();
		}catch(IOException e){
			System.out.println("Failed to load texture!");
			e.printStackTrace();
		}
	}
}
