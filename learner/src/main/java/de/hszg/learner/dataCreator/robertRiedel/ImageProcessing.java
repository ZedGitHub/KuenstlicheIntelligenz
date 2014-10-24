package de.hszg.learner.dataCreator.robertRiedel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;

import de.hszg.learner.Concept;
import de.hszg.learner.featureVector.robertRiedel.FeatureVectorR;

/**
 * for all stuff done with the images to make a feature vector
 */
public class ImageProcessing
{
	String	rootPathToSigns				= "f:";
	String	folderNameVorfahrtVonRechts	= "102 - Vorfahrt von rechts";
	String	folderNameVorfahrtGewaehren	= "205 - Vorfahrt gewaehren";
	String	folderNameStop				= "206 - Stop";
	String	folderNameRechtsAbbiegen	= "";
	String	folderNameLinksAbbiegen		= "209 - Fahrtrichtung links";
	String	folderNameVorfahrtsstrasse	= "306 - Vorfahrtsstrße";
	// max and min ints for areas to be the respective area
	int		red							= -2083802;
	int		red2						= -4121325;
	int		blue						= -10975299;
	int		blue2						= -14519910;
	int		black						= -14673121;
	int		black2						= -16312563;
	int		yellow						= -3783;
	int		yellow2						= -858263;
	int		white						= -1;
	int		white2						= 0;

	/**
	 * get an image from specified source
	 * 
	 * @param source
	 * @return
	 */
	public ImageProcessing()
	{
		// TODO automated colorscanning from testbmps?
		String filename = "X0Y0.jpg";
		// String filename = "X0Y0.jpg";
		String source = rootPathToSigns + "\\" + folderNameVorfahrtGewaehren + "\\0\\3500\\" + filename;
		// String source = "C:\\Studium\\Künstliche Intelligenz\\farbTestWeiss.bmp";
		BufferedImage image = getImageFromSource(source);
		getSurroundingColor(image);
		String testSource = "C:\\Studium\\Künstliche Intelligenz\\farbTestRot.bmp";
		BufferedImage testImage = getImageFromSource(testSource);
		int[] features = getFeatures(image);
		Concept d = Concept.Stop;
		FeatureVectorR TestFv = new FeatureVectorR(features, d);
		System.out.println(TestFv);
		System.out.println("done");
	}

	private int[] getFeatures(BufferedImage image)
	{ // sectors
		// 1 2
		// 3 4
		int width = image.getWidth();
		int height = image.getHeight();
		int halfWidth = width / 2;
		int halfHeigth = height / 2;
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
		int width1core = 0;
		int width2core = 0;
		int height1core = 0;
		int height2core = 0;
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
				if (isBlack(pixel))
				{
					counterBlack1++;
				}
				else if (isBlue(pixel))
				{
					counterBlue1++;
				}
				else if (isRed(pixel))
				{
					counterRed1++;
				}
				else if (isYellow(pixel))
				{
					counterYellow1++;
				}
				else if (isWhite(pixel))
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
				if (isBlack(pixel))
				{
					counterBlack2++;
				}
				else if (isBlue(pixel))
				{
					counterBlue2++;
				}
				else if (isRed(pixel))
				{
					counterRed2++;
				}
				else if (isYellow(pixel))
				{
					counterYellow2++;
				}
				else if (isWhite(pixel))
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
				if (isBlack(pixel))
				{
					counterBlack3++;
				}
				else if (isBlue(pixel))
				{
					counterBlue3++;
				}
				else if (isRed(pixel))
				{
					counterRed3++;
				}
				else if (isYellow(pixel))
				{
					counterYellow3++;
				}
				else if (isWhite(pixel))
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
				if (isBlack(pixel))
				{
					counterBlack4++;
				}
				else if (isBlue(pixel))
				{
					counterBlue4++;
				}
				else if (isRed(pixel))
				{
					counterRed4++;
				}
				else if (isYellow(pixel))
				{
					counterYellow4++;
				}
				else if (isWhite(pixel))
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
				if (isBlack(pixel))
				{
					counterBlackCore++;
				}
				else if (isBlue(pixel))
				{
					counterBlueCore++;
				}
				else if (isRed(pixel))
				{
					counterRedCore++;
				}
				else if (isYellow(pixel))
				{
					counterYellowCore++;
				}
				else if (isWhite(pixel))
				{
					counterWhiteCore++;
				}
				else
				{
					counterNone++;
				}
			}
		}
		int gelb = getColorTrue(counterYellow1 + counterYellow2 + counterYellow3 + counterYellow4 + counterYellowCore);
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
		int kernFarbeWeiss = counterWhiteCore;
		int kernFarbeSchwarz = counterBlackCore;
		System.out.println("raw values: " + gelb + " " + blauRelationObenUnten + " " + blauRelationLinksRechts + " " + rotRelationObenUnten + " " + rotRelationLinksRechts + " " + symmetrieObenUnten + " " + symmetrieLinksRechts + " " + kernFarbeWeiss + " " + kernFarbeSchwarz);
		int[] featureInts =
		{ gelb, blauRelationObenUnten, blauRelationLinksRechts, rotRelationObenUnten, rotRelationLinksRechts, symmetrieObenUnten, symmetrieLinksRechts, kernFarbeWeiss, kernFarbeSchwarz };
		System.out.println("featureints " + featureInts[0] + " " + featureInts[1] + " " + featureInts[2] + " " + featureInts[3] + " " + featureInts[4] + " ");
		return featureInts;
	}

	private int getColorTrue(int i)
	{
		if (i > 0)
		{
			return 1;
		}
		else
			return 0;
	}

	private int getRelation(int dividend, int divisor)
	{// TODO
		float result;
		System.out.println("raw: " + dividend + " " + divisor + " before");
		try
		{
			 result = (divisor/dividend  );
//			result = (dividend / divisor);
			System.out.println("raw: " + dividend + " " + divisor + " " + result);
		}
		catch (Exception e)
		{
			result = 0;
			System.out.println(e.getMessage() + " raw: " + dividend + " " + divisor);
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
		// Graphics2D g2d;
		// g2d.drawImage(image, x, y, x + width, y + height, frameX, frameY, frameX + width, frameY + height, this);
		// TODO
		return image;
	}

	private int getSurroundingColor(BufferedImage image)
	{
		// TODO rework logic
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
				if (isBlack(pixel))
				{
					counterBlack++;
				}
				else if (isBlue(pixel))
				{
					counterBlue++;
				}
				else if (isRed(pixel))
				{
					counterRed++;
				}
				else if (isYellow(pixel))
				{
					counterYellow++;
				}
				else if (isWhite(pixel))
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
		for (Iterator<Integer> iterator = colorsFound.keySet().iterator(); iterator.hasNext();)
		{
			Integer value = iterator.next();
			if (value > minint)
			{
				minint = value;
			}
		}
		System.out.println("min int: " + minint);
		int maxint = minint;
		for (Iterator<Integer> iterator = colorsFound.keySet().iterator(); iterator.hasNext();)
		{
			Integer value = iterator.next();
			if (value < maxint)
			{
				maxint = value;
			}
		}
		System.out.println("max int: " + maxint);
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

	public boolean isYellow(int pixel)
	{
		return (yellow > pixel && pixel > yellow2);
	}

	public boolean isBlue(int pixel)
	{
		return (blue > pixel && pixel > blue2);
	}

	public boolean isBlack(int pixel)
	{
		return (black > pixel && pixel > black2);
	}

	public boolean isWhite(int pixel)
	{
		return (white > pixel && pixel > white2);
	}

	public boolean isRed(int pixel)
	{
		return (red > pixel && pixel > red2);
	}
}
