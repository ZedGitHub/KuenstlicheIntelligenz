package de.hszg.learner.dataCreator.zed;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Classifies a color given by r,g,b values
 * 
 * @author z
 *
 */
public class ColorClassifierZ {

	public ColorClassifierZ() {
		loadColorConfig();
	}

	/**
	 * Uses a {@link Color}s rgb values to classify it into a {@link Colors}
	 * 
	 * @param c
	 * @return
	 */
	public Colors getColorName(Color c) {
		if (isRed(c)) {
			return Colors.RED;
		}
		if (isYellow(c)) {
			return Colors.YELLOW;
		}
		if (isBlue(c)) {
			return Colors.BLUE;
		}
		if (isBlack(c)) {
			return Colors.BLACK;
		}
		return Colors.UNIMPORTANT;
	}

	private boolean isYellow(Color c) {
		return (almostTheSame(c.getRed(), c.getGreen())
				&& c.getBlue() < c.getRed() && c.getBlue() < c.getGreen()
				&& c.getRed() > 50 && c.getGreen() > 50);
	}

	private boolean isRed(Color c) {
		return ((c.getRed() > 50) && (!almostTheSame(c.getRed(), c.getBlue())) && (!almostTheSame(
				c.getRed(), c.getGreen()) && c.getRed() > c.getBlue() && c
				.getRed() > c.getGreen()));
	}

	private boolean isBlack(Color c) {
		return (almostTheSame(c.getRed(), c.getGreen())
				&& almostTheSame(c.getGreen(), c.getBlue()) && (c.getRed()
				+ c.getGreen() + c.getBlue() < 300));
	}

	private boolean isBlue(Color c) {
		return (c.getBlue() > 20 && c.getRed() < 200 && c.getGreen() < 200 && almostTheSame(
				c.getRed(), c.getGreen()));
	}

	/**
	 * Checks if two integers only differ by a given value
	 * 
	 * @param value1
	 * @param value2
	 * @return
	 */
	private boolean almostTheSame(int value1, int value2) {
		return ((Math.abs(value1 - value2)) < 65);
	}

	private void loadColorConfig() {
		Properties prop = new Properties();
		String propFileName = "colorConfigZ.properties";
		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream(propFileName);
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(prop.getProperty("maxB"));
	}

}
