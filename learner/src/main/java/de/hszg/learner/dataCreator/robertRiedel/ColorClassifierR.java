package de.hszg.learner.dataCreator.robertRiedel;

public class ColorClassifierR
{// max and min ints for areas to be the respective area
// int red = -2083802;
// int red2 = -4121325;
// int blue = -10975299;
// int blue2 = -14519910;
// int black = -14673121;
// int black2 = -16312563;
// // int yellow = -3783;
// // int yellow2 = -858263;
// int yellow = -72960;
// int yellow2 = -335865;
// int white = -1;
// int white2 = 0;
// int yellowThreshold = 5;
//
// public boolean isYellow(int pixel)
// {
// return (yellow > pixel && pixel > yellow2);
// }
//
// public boolean isBlue(int pixel)
// {
// return (blue > pixel && pixel > blue2);
// }
//
// public boolean isBlack(int pixel)
// {
// return (black > pixel && pixel > black2);
// }
//
// public boolean isWhite(int pixel)
// {
// return (white > pixel && pixel > white2);
// }
//
// public boolean isRed(int pixel)
// {
// return (red > pixel && pixel > red2);
// }
	int	redR1			= 190;
	int	redR2			= 230;
	int	redG1			= 20;
	int	redG2			= 60;
	int	redB1			= 10;
	int	redB2			= 50;
	int	blueR1			= 40;
	int	blueR2			= 80;
	int	blueG1			= 100;
	int	blueG2			= 140;
	int	blueB1			= 150;
	int	blueB2			= 200;
	int	blackR1			= 0;
	int	blackR2			= 30;
	int	blackG1			= 0;
	int	blackG2			= 30;
	int	blackB1			= 0;
	int	blackB2			= 30;
	int	yellowR1		= 230;
	int	yellowR2		= 255;
	int	yellowG1		= 200;
	int	yellowG2		= 240;
	int	yellowB1		= 0;
	int	yellowB2		= 30;
	int	white			= -50;
	int	white2			= 0;
	int	yellowThreshold	= 5;

	public boolean isYellow(int pixel)
	{
		int red = getRedFromPixel(pixel);
		int green = getGreenFromPixel(pixel);
		int blue = getBlueFromPixel(pixel);
		return ((yellowR1 < red && red < yellowR2) && (yellowG1 < green && green < yellowG2) && (yellowG1 < blue && blue < yellowG2));
	}

	public boolean isBlue(int pixel)
	{
		int red = getRedFromPixel(pixel);
		int green = getGreenFromPixel(pixel);
		int blue = getBlueFromPixel(pixel);
		return ((blueR1 < red && red < blueR2) && (blueG1 < green && green < blueG2) && (blueG1 < blue && blue < blueG2));
	}

	public boolean isBlack(int pixel)
	{
		int red = getRedFromPixel(pixel);
		int green = getGreenFromPixel(pixel);
		int blue = getBlueFromPixel(pixel);
		return ((blackR1 < red && red < blackR2) && (blackG1 < green && green < blackG2) && (blackG1 < blue && blue < blackG2));
	}

	public boolean isWhite(int pixel)
	{
		return (white > pixel && pixel > white2);
	}

	public boolean isRed(int pixel)
	{
		int red = getRedFromPixel(pixel);
		int green = getGreenFromPixel(pixel);
		int blue = getBlueFromPixel(pixel);
		return ((redR1 < red && red < redR2) && (redG1 < green && green < redG2) && (redG1 < blue && blue < redG2));
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

	public int getColorTrue(int i)
	{
		int result = 0;
		if (i > yellowThreshold)
		{
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
