package de.hszg.learner.dataCreator.robertRiedel;

public class ColorClassifierR
{
	int	redR1					= 190;
	int	redR2					= 230;
	int	redG1					= 20;
	int	redG2					= 80;
	int	redB1					= 10;
	int	redB2					= 60;
	int	blueR1					= 30;
	int	blueR2					= 90;
	int	blueG1					= 100;
	int	blueG2					= 140;
	int	blueB1					= 140;
	int	blueB2					= 210;
	int	blackR1					= 0;
	int	blackR2					= 30;
	int	blackG1					= 0;
	int	blackG2					= 30;
	int	blackB1					= 0;
	int	blackB2					= 30;
	int	yellowR1				= 230;
	int	yellowR2				= 255;
	int	yellowG1				= 200;
	int	yellowG2				= 250;
	int	yellowB1				= 0;
	int	yellowB2				= 160;
	int	white					= -50;
	int	white2					= 0;
	int	yellowThresholdPercent	= 10;

	public boolean isYellow(int pixel)
	{
		int red = getRedFromPixel(pixel);
		int green = getGreenFromPixel(pixel);
		int blue = getBlueFromPixel(pixel);
		return ((yellowR1 < red && red < yellowR2) && (yellowG1 < green && green < yellowG2) && (yellowB1 < blue && blue < yellowB2));
	}

	public boolean isBlue(int pixel)
	{
		int red = getRedFromPixel(pixel);
		int green = getGreenFromPixel(pixel);
		int blue = getBlueFromPixel(pixel);
		return ((blueR1 < red && red < blueR2) && (blueG1 < green && green < blueG2) && (blueB1 < blue && blue < blueB2));
	}

	public boolean isBlack(int pixel)
	{
		int red = getRedFromPixel(pixel);
		int green = getGreenFromPixel(pixel);
		int blue = getBlueFromPixel(pixel);
		return ((blackR1 < red && red < blackR2) && (blackG1 < green && green < blackG2) && (blackB1 < blue && blue < blackB2));
	}

	public boolean isWhite(int pixel)
	{
		return (white < pixel && pixel < white2);
	}

	public boolean isRed(int pixel)
	{
		int red = getRedFromPixel(pixel);
		int green = getGreenFromPixel(pixel);
		int blue = getBlueFromPixel(pixel);
		return ((redR1 < red && red < redR2) && (redG1 < green && green < redG2) && (redB1 < blue && blue < redB2));
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

	public int getColorTrue(int i, int imgSize)
	{
		int result = 0;
		if (i > imgSize * yellowThresholdPercent / 100)
		{
			result = 1;
			System.out.println(("colorTrue int: " + i + " result: " + result));
			return result;
		}
		else
		{
			System.out.println(("colorTrue int: " + i + " result: " + result));
			return result;
		}
	}
}
