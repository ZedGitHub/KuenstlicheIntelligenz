package de.hszg.learner.dataCreator.robertRiedel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * for all stuff done with the images to make a feature vector
 */
public class ImageProcessing
{
	String	rootPathToSigns				= "f:\\";
	String	folderNameVorfahrtVonRechts	= "102 - Vorfahrt von rechts";
	String	folderNameVorfahrtGewaehren	= "205 - Vorfahrt gewaehren";
	String	folderNameStop				= "206 - Stop";
	String	folderNameRechtsAbbiegen	= "";
	String	folderNameLinksAbbiegen		= "209 - Fahrtrichtung links";
	String	folderNameVorfahrtsstrasse	= "306 - Vorfahrtsstrße";

	/**
	 * get an image from specified source
	 * 
	 * @param source
	 * @return
	 */
	public BufferedImage getImageFromSource(String source)
	{
		try
		{
			BufferedImage image = ImageIO.read(this.getClass().getResource(source));
			return image;
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
			return null;
		}
	}

	public int getRedFromPixel(int pixel)
	{
		return (pixel >> 16) & 0xff;
	}

	public int getBlueFromPixel(int pixel)
	{
		return (pixel) & 0xff;
	}

	public int getGreenFromPixel(int pixel)
	{
		return (pixel >> 8) & 0xff;
	}

	public int getAlphaFromPixel(int pixel)
	{
		return (pixel >> 24) & 0xff;
	}

	public int getPixelFromImage(BufferedImage image, int x, int y)
	{
		return image.getRGB(x, y);
	}

	private BufferedImage cutOutlinesFromImage(BufferedImage image)
	{
		int mainColor = getSurroundingColor(image);
		int width = image.getWidth();
		int height = image.getHeight();
		int horizontalBorder = 0;
		int vertikalBorder = 0;
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				int pixel = image.getRGB(j, i);
				if (pixel == mainColor)
				{
					if (i > horizontalBorder)
					{
						horizontalBorder = i;
					}
					if (j > vertikalBorder)
					{
						vertikalBorder = j;
					}
				}
			}
		}
		Graphics2D g2d;
		// g2d.drawImage(image, x, y, x + width, y + height, frameX, frameY, frameX + width, frameY + height, this);
		// TODO
		return image;
	}

	/**
	 * get the color with the most occurences in the image
	 * 
	 * @param image
	 * @return
	 */
	private int getSurroundingColor(BufferedImage image)
	{
		int surroundingColor = 0;
		int width = image.getWidth();
		int height = image.getHeight();
		Map colorsFound = new HashMap<Integer, Integer>();
		colorsFound = getColorsPresent(image, width, height, colorsFound);

		int maxAmount = 0;
		maxAmount = getMostFoundColor(colorsFound, maxAmount);
		surroundingColor = getKeyOfMostfoundColor(surroundingColor, colorsFound, maxAmount);
		System.out.println("chose surrounding color(as int) " + surroundingColor + " with " + maxAmount + " pixel");
		return surroundingColor;
	}

	private int getKeyOfMostfoundColor(int surroundingColor, Map colorsFound, int maxAmount)
	{
		for (Iterator iterator = colorsFound.keySet().iterator(); iterator.hasNext();)
		{
			Integer key = (Integer) iterator.next();
			if ((Integer) colorsFound.get(key) == maxAmount)
			{
				surroundingColor = key;
			}
		}
		return surroundingColor;
	}

	private int getMostFoundColor(Map colorsFound, int maxAmount)
	{
		for (Iterator iterator = colorsFound.values().iterator(); iterator.hasNext();)
		{
			Integer value = (Integer) iterator.next();
			if (value > maxAmount)
			{
				maxAmount = value;
			}
		}
		return maxAmount;
	}

	private Map<Integer, Integer> getColorsPresent(BufferedImage image, int width, int height, Map colorsFound)
	{
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				int pixel = image.getRGB(j, i);
				if (colorsFound.containsKey(pixel))
				{
					int newPixelValue = (int) colorsFound.get(pixel) + 1;
					colorsFound.put(pixel, newPixelValue);
				}
				else
				{
					colorsFound.put(pixel, 0);
				}
			}
		}
		System.out.println("found " + colorsFound.size() + " colors");
		return colorsFound;
	}

	public boolean isYellow(int pixel)
	{
		// TODO
		return false;
	}

	public boolean isBlue(int pixel)
	{
		// TODO
		return false;
	}

	public boolean isBlack(int pixel)
	{
		// TODO
		return false;
	}

	public boolean isWhite(int pixel)
	{
		// TODO
		return false;
	}

	public boolean isred(int pixel)
	{
		// TODO
		return false;
	}
}
