package gvprojects.mm.shared;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Each element in the Topping enum is associated with an image for the topping
 * and a solid color to render the background of a recipe/order card
 * 
 * @author "Hans Dulimarta <dulimarh@cis.gvsu.edu>"
 * 
 */
public enum Topping {

	/* TODO: -------------------------------
	 * Customize the topping images that you would like to use in the game Use a
	 * question mark image for the ANY topping If you wish to change the solid
	 * color associated with topping image, change the RGB values for each
	 * topping declaration. These values must be in the range 0-255 The
	 * single-letter code following the image name must be unique The images
	 * directory should be created as a sibling of the src directory
	 */

	/* TODO: Comment/Uncomment you choice of toppings */
	/* Each topping is associated with an image and a solid color */
	BLACK_OLIVE("images/black_olive.png", 'B', 156, 68, 123), GREEN_PEPPER(
			"images/green_pepper.png", 'G', 108, 151, 65), HAM(
			"images/ham.png", 'H', 251, 192, 192), CHEESE("images/cheese.png",
			'C', 237, 212, 77),
	// ONION ("images/onion.png", 233, 177, 137),
	SAUSAGE("images/sausage.png", 'S', 238, 85, 64),
	// TOMATO ("images/tomato.png", 224, 10, 37),

	/* MUST INCLUDE Topping.ANY for wildcard in the game */
	ANY("images/any.png", '?', 0, 0, 0), ;

	private BufferedImage theImage;
	private Color pat;
	private char code;

	private Topping(String imageName, char code, int r, int g, int b) {
		File f = new File(imageName);
		try {
			theImage = ImageIO.read(f);
		} catch (IOException e) {
			System.err
					.println("Image file \"" + imageName + "\" was not found");
		}
		this.code = code;
		pat = new Color(r, g, b);
	}

	public BufferedImage getImage() {
		return theImage;
	}

	/*
	 * The pattern color is used for coloring the background of order/recipe
	 * cards. This is also the color associated with a player
	 */
	public Color getPatternColor() {
		return pat;
	}

	public char getCode() {
		return code;
	}
}
