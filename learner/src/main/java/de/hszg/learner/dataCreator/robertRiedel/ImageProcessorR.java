package de.hszg.learner.dataCreator.robertRiedel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * for all stuff done with the images to make a feature vector
 */
public class ImageProcessorR
{
	String				rootPathToSigns				= "f:";
	String				folderNameVorfahrtVonRechts	= "102 - Vorfahrt von rechts";
	String				folderNameVorfahrtGewaehren	= "205 - Vorfahrt gewaehren";
	String				folderNameStop				= "206 - Stop";
	String				folderNameRechtsAbbiegen	= "";
	String				folderNameLinksAbbiegen		= "209 - Fahrtrichtung links";
	String				folderNameVorfahrtsstrasse	= "306 - Vorfahrtsstrße";
	int					widthDivider;
	int					heightDivider;
	int					coreDivider;

	ColorClassifierR	colorClassifier;

	/**
	 * get an image from specified source
	 * 
	 * @param source
	 * @return
	 */
	public ImageProcessorR()
	{
		this(2, 2, 8);
	}

	public ImageProcessorR(int widthDivider, int heightDivider, int coreDivider)
	{
		colorClassifier = new ColorClassifierR();
		this.heightDivider = heightDivider;
		this.widthDivider = widthDivider;
		this.coreDivider = coreDivider;

	}

	public int[] getFeatures(File imageFile) throws IOException
	{
		BufferedImage image = ImageIO.read(imageFile);
		// TODO maybe move sector borders to get more feature diversity
		// sectors
		// 1 2
		// 3 4
		int width = image.getWidth();
		int height = image.getHeight();
		int imgSize = width * height;
		int halfWidth = width / widthDivider;
		int halfHeigth = height / heightDivider;
		int width1Sector1 = 0;
		int width2Sector1 = halfWidth;
		int height1Sector1 = 0;
		int height2Sector1 = halfHeigth;
		int width1Sector2 = halfWidth;
		int width2Sector2 = width;
		int height1Sector2 = 0;
		int height2Sector2 = halfHeigth;
		int width1Sector3 = 0;
		int width2Sector3 = halfWidth;
		int height1Sector3 = halfHeigth;
		int height2Sector3 = height;
		int width1Sector4 = halfWidth;
		int width2Sector4 = width;
		int height1Sector4 = halfHeigth;
		int height2Sector4 = height;
		int width1core = (width / 2) - (width / coreDivider);
		int width2core = (width / 2) + (width / coreDivider);
		int height1core = (height / 2) - (height / coreDivider);
		int height2core = (height / 2) + (height / coreDivider);
		int counterNone = 0;
		int counterRed1 = 0;
		int counterRed2 = 0;
		int counterRed3 = 0;
		int counterRed4 = 0;
		int counterRedCore = 0;
		int counterBlue1 = 0;
		int counterBlue2 = 0;
		int counterBlue3 = 0;
		int counterBlue4 = 0;
		int counterBlueCore = 0;
		int counterBlack1 = 0;
		int counterBlack2 = 0;
		int counterBlack3 = 0;
		int counterBlack4 = 0;
		int counterBlackCore = 0;
		int counterWhite1 = 0;
		int counterWhite2 = 0;
		int counterWhite3 = 0;
		int counterWhite4 = 0;
		int counterWhiteCore = 0;
		int counterYellow1 = 0;
		int counterYellow2 = 0;
		int counterYellow3 = 0;
		int counterYellow4 = 0;
		int counterYellowCore = 0;
		for (int i = height1Sector1; i < height2Sector1; i++)
		{
			for (int j = width1Sector1; j < width2Sector1; j++)
			{
				int pixel = image.getRGB(j, i);
				if (colorClassifier.isBlack(pixel))
				{
					counterBlack1++;
				}
				else if (colorClassifier.isBlue(pixel))
				{
					counterBlue1++;
				}
				else if (colorClassifier.isRed(pixel))
				{
					counterRed1++;
				}
				else if (colorClassifier.isYellow(pixel))
				{
					counterYellow1++;
				}
				else if (colorClassifier.isWhite(pixel))
				{
					counterWhite1++;
				}
				else
				{
					counterNone++;
				}
			}
		}
		for (int i = height1Sector2; i < height2Sector2; i++)
		{
			for (int j = width1Sector2; j < width2Sector2; j++)
			{
				int pixel = image.getRGB(j, i);
				if (colorClassifier.isBlack(pixel))
				{
					counterBlack2++;
				}
				else if (colorClassifier.isBlue(pixel))
				{
					counterBlue2++;
				}
				else if (colorClassifier.isRed(pixel))
				{
					counterRed2++;
				}
				else if (colorClassifier.isYellow(pixel))
				{
					counterYellow2++;
				}
				else if (colorClassifier.isWhite(pixel))
				{
					counterWhite2++;
				}
				else
				{
					counterNone++;
				}
			}
		}
		for (int i = height1Sector3; i < height2Sector3; i++)
		{
			for (int j = width1Sector3; j < width2Sector3; j++)
			{
				int pixel = image.getRGB(j, i);
				if (colorClassifier.isBlack(pixel))
				{
					counterBlack3++;
				}
				else if (colorClassifier.isBlue(pixel))
				{
					counterBlue3++;
				}
				else if (colorClassifier.isRed(pixel))
				{
					counterRed3++;
				}
				else if (colorClassifier.isYellow(pixel))
				{
					counterYellow3++;
				}
				else if (colorClassifier.isWhite(pixel))
				{
					counterWhite3++;
				}
				else
				{
					counterNone++;
				}
			}
		}
		for (int i = height1Sector4; i < height2Sector4; i++)
		{
			for (int j = width1Sector4; j < width2Sector4; j++)
			{
				int pixel = image.getRGB(j, i);
				if (colorClassifier.isBlack(pixel))
				{
					counterBlack4++;
				}
				else if (colorClassifier.isBlue(pixel))
				{
					counterBlue4++;
				}
				else if (colorClassifier.isRed(pixel))
				{
					counterRed4++;
				}
				else if (colorClassifier.isYellow(pixel))
				{
					counterYellow4++;
				}
				else if (colorClassifier.isWhite(pixel))
				{
					counterWhite4++;
				}
				else
				{
					counterNone++;
				}
			}
		}
		for (int i = height1core; i < height2core; i++)
		{
			for (int j = width1core; j < width2core; j++)
			{
				int pixel = image.getRGB(j, i);
				if (colorClassifier.isBlack(pixel))
				{
					counterBlackCore++;
				}
				else if (colorClassifier.isBlue(pixel))
				{
					counterBlueCore++;
				}
				else if (colorClassifier.isRed(pixel))
				{
					counterRedCore++;
				}
				else if (colorClassifier.isYellow(pixel))
				{
					counterYellowCore++;
				}
				else if (colorClassifier.isWhite(pixel))
				{
					counterWhiteCore++;
				}
				else
				{
					counterNone++;
				}
			}
		}
		int gelb = colorClassifier.getColorTrue(counterYellow1 + counterYellow2 + counterYellow3 + counterYellow4 + counterYellowCore, imgSize);
		int blauLinks = counterBlue1 + counterBlue3;
		int blauRechts = counterBlue2 + counterBlue4;
		int blauRelationLinksRechts = getRelation(blauLinks, blauRechts);
		int blauOben = counterBlue1 + counterBlue2;
		int blauUnten = counterBlue3 + counterBlue4;
		int blauRelationObenUnten = getRelation(blauOben, blauUnten);
		int rotOben = counterRed1 + counterRed2;
		int rotUnten = counterRed3 + counterRed4;
		int rotRelationObenUnten = getRelation(rotOben, rotUnten);
		int rotLinks = counterRed1 + counterRed3;
		int rotRechts = counterRed2 + counterRed4;
		int rotRelationLinksRechts = getRelation(rotLinks, rotRechts);
		int symmetrieObenUnten = getRelation(rotOben + blauOben + counterBlack1 + counterBlack2 + counterWhite1 + counterWhite2, rotUnten + blauUnten + counterBlack3 + counterBlack4 + counterWhite3 + counterWhite4);
		int symmetrieLinksRechts = getRelation(rotLinks + blauLinks + counterBlack1 + counterBlack3 + counterWhite1 + counterWhite3, rotRechts + blauRechts + counterBlack2 + counterBlack4 + counterWhite2 + counterWhite4);
		int kernFarbeWeiss = getRelation(counterWhiteCore, (width2core - width1core) * (height2core - height1core));
		int kernFarbeSchwarz = counterBlackCore;
		int[] featureInts =
		{ gelb, blauRelationObenUnten, blauRelationLinksRechts, rotRelationObenUnten, rotRelationLinksRechts, symmetrieObenUnten, symmetrieLinksRechts, kernFarbeWeiss, kernFarbeSchwarz };
		return featureInts;
	}

	private int getRelation(int dividend, int divisor)
	{// TODO right results?
		double result;
		try
		{
			if (dividend < divisor)
			{
				result = ((double) dividend / (double) divisor);
				result = result * 100;
			}
			else
			{
				result = ((double) divisor / (double) dividend);
				result = 100 - (result * 100);
				System.out.println("groesser: " + result + " " + divisor + " " + dividend);
			}
		}
		catch (Exception e)
		{
			result = 0;
			System.out.println(e.getMessage() + " exception raw: " + dividend + " " + divisor);
		}

		return (int) result;
	}

	public BufferedImage getImageFromSource(String source)
	{
		try
		{
			// URL sourceUrl = new URL(source);
			BufferedImage image = ImageIO.read(new File(source));
			return image;
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
			return null;
		}
	}

	public int getPixelFromImage(BufferedImage image, int x, int y)
	{
		return image.getRGB(x, y);
	}

	// private BufferedImage cutOutlinesFromImage(BufferedImage image)
	// {
	// int mainColor = getColors(image);
	// int width = image.getWidth();
	// int height = image.getHeight();
	// int horizontalBorder = 0;
	// int vertikalBorder = 0;
	// for (int i = 0; i < height; i++)
	// {
	// for (int j = 0; j < width; j++)
	// {
	// int pixel = image.getRGB(j, i);
	// if (pixel == mainColor)
	// {
	// if (i > horizontalBorder)
	// {
	// horizontalBorder = i;
	// }
	// if (j > vertikalBorder)
	// {
	// vertikalBorder = j;
	// }
	// }
	// }
	// }
	// // Graphics2D g2d;
	// // g2d.drawImage(image, x, y, x + width, y + height, frameX, frameY, frameX + width, frameY + height, this);
	// // TODO
	// return image;
	// }

	private int getColors(BufferedImage image)
	{
		// TODO rework logic
		// TODO just needed for testing
		// all needed? now just space is used as syso
		int surroundingColor = 0;
		int surroundingColor2 = 0;
		int width = image.getWidth();
		int height = image.getHeight();
		Map<Integer, Integer> colorsFound = new HashMap<Integer, Integer>();
		colorsFound = getColorsPresent(image, width, height, colorsFound);

		int maxAmount = 0;
		maxAmount = getMostFoundColor(colorsFound, maxAmount);
		int minAmount = 0;
		getColorSpace(colorsFound);
		surroundingColor = getKeyOfFoundColor(surroundingColor, colorsFound, maxAmount);
		surroundingColor2 = getKeyOfFoundColor(surroundingColor2, colorsFound, minAmount);
		System.out.println("chose surrounding color1(as int) " + surroundingColor + " with " + maxAmount + " pixel");
		System.out.println("chose surrounding color2(as int) " + surroundingColor2 + " with " + minAmount + " pixel");
		return surroundingColor;
	}

	private int getKeyOfFoundColor(int surroundingColor, Map<Integer, Integer> colorsFound, int maxAmount)
	{
		for (Iterator<Integer> iterator = colorsFound.keySet().iterator(); iterator.hasNext();)
		{
			Integer key = iterator.next();
			if (colorsFound.get(key) == maxAmount)
			{
				surroundingColor = key;
			}
		}
		return surroundingColor;
	}

	private int getMostFoundColor(Map<Integer, Integer> colorsFound, int maxAmount)
	{
		for (Iterator<Integer> iterator = colorsFound.values().iterator(); iterator.hasNext();)
		{
			Integer value = iterator.next();
			if (value > maxAmount)
			{
				maxAmount = value;
			}
		}
		return maxAmount;
	}

	// for testing
	private void testColorCount(BufferedImage image)
	{
		int width = image.getWidth();
		int height = image.getHeight();
		int counterNone = 0;
		int counterRed = 0;
		int counterBlue = 0;
		int counterBlack = 0;
		int counterWhite = 0;
		int counterYellow = 0;
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				int pixel = image.getRGB(j, i);
				if (colorClassifier.isBlack(pixel))
				{
					counterBlack++;
				}
				else if (colorClassifier.isBlue(pixel))
				{
					counterBlue++;
				}
				else if (colorClassifier.isRed(pixel))
				{
					counterRed++;
				}
				else if (colorClassifier.isYellow(pixel))
				{
					counterYellow++;
				}
				else if (colorClassifier.isWhite(pixel))
				{
					counterWhite++;
				}
				else
				{
					counterNone++;
				}
			}
		}
		System.out.println("found red pixel: " + counterRed);
		System.out.println("found blue pixel: " + counterBlue);
		System.out.println("found black pixel: " + counterBlack);
		System.out.println("found yellow pixel: " + counterYellow);
		System.out.println("found white pixel: " + counterWhite);
		System.out.println("found other pixel: " + counterNone);
	}

	// testing
	private void getColorSpace(Map<Integer, Integer> colorsFound)
	{
		int minint = Integer.MIN_VALUE;
		int minred = 0;
		int minblue = 0;
		int mingreen = 0;

		for (Iterator<Integer> iterator = colorsFound.keySet().iterator(); iterator.hasNext();)
		{
			Integer value = iterator.next();
			int red = colorClassifier.getRedFromPixel(value);
			int green = colorClassifier.getGreenFromPixel(value);
			int blue = colorClassifier.getBlueFromPixel(value);
			if (red > minred)
			{
				minred = red;
			}
			if (blue > minblue)
			{
				minblue = blue;
			}
			if (green > mingreen)
			{
				mingreen = green;
			}
			if (value > minint)
			{
				minint = value;
			}
		}
		System.out.println("min int: " + minint);
		System.out.println("min int rgb: " + colorClassifier.getRedFromPixel(minint) + " " + colorClassifier.getGreenFromPixel(minint) + " " + colorClassifier.getBlueFromPixel(minint));

		int maxint = minint;
		int maxred = minred;
		int maxblue = minblue;
		int maxgreen = mingreen;
		for (Iterator<Integer> iterator = colorsFound.keySet().iterator(); iterator.hasNext();)
		{
			Integer value = iterator.next();
			int red = colorClassifier.getRedFromPixel(value);
			int green = colorClassifier.getGreenFromPixel(value);
			int blue = colorClassifier.getBlueFromPixel(value);
			if (red < maxred)
			{
				maxred = red;
			}
			if (blue < maxblue)
			{
				maxblue = blue;
			}
			if (green < maxgreen)
			{
				maxgreen = green;
			}
			if (value < maxint)
			{
				maxint = value;
			}
		}
		System.out.println("max int: " + maxint);
		System.out.println("max int rgb: " + colorClassifier.getRedFromPixel(maxint) + " " + colorClassifier.getGreenFromPixel(maxint) + " " + colorClassifier.getBlueFromPixel(maxint));

		System.out.println("min rgb: " + minred + " " + mingreen + " " + minblue);
		System.out.println("max rgb: " + maxred + " " + maxgreen + " " + maxblue);
	}

	private Map<Integer, Integer> getColorsPresent(BufferedImage image, int width, int height, Map<Integer, Integer> colorsFound)
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

	public static void main(String[] args)
	{
		new ImageProcessorR();
	}

}
