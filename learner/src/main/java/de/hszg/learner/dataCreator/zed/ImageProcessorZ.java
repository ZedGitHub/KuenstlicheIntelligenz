package de.hszg.learner.dataCreator.zed;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.hszg.learner.featureVector.zed.FeatureVectorZ;

/**
 * Creates features from an image file for the creation of a {@link FeatureVectorZ}
 * @author z
 *
 */
public class ImageProcessorZ {

	private ColorClassifierZ colorClassifier;
	
	public ImageProcessorZ() {
		this.colorClassifier = new ColorClassifierZ();
	}

	/**
	 * Creates features for {@link FeatureVectorZ} from a file.
	 * @param inputImage
	 * @return array of features. for the meaning of the values see: {@link FeatureVectorZ} 
	 * @throws IOException
	 */
	public int[] createFeatures(File inputImage) throws IOException {
		int[] result = new int[6];
		BufferedImage img = ImageIO.read(inputImage);
		int height = img.getHeight();
		int width = img.getWidth();

		RangeChecker rangeChecker = new RangeChecker(height, width);
		
		int countYellow = 0;
		int countBlueLeft = 0;
		int countBlueRight = 0;
		int countBlackMiddle = 0;
		int countRedTop = 0;
		int countRedBottom = 0;

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Color c = new Color(img.getRGB(x, y));
				Colors color = colorClassifier.getColorName(c);
				switch (color) {
				case YELLOW:{
					if(rangeChecker.importantBlack(x,y))
					countYellow++;
				}
				case BLACK:
					if (rangeChecker.importantBlack(x, y))
						countBlackMiddle++;
				case BLUE: {
					if (rangeChecker.importantBlueLeft(x, y))
						countBlueLeft++;
					if (rangeChecker.importantBlueRight(x, y))
						countBlueRight++;
				}
				case RED: {
					if (rangeChecker.importantRedTop(x, y))
						countRedTop++;
					if (rangeChecker.importantRedBottom(x, y))
						countRedBottom++;
				}
				case UNIMPORTANT:
					break;
				default:
					break;
				}
			}
		}
//		System.out.println("yellow count " + countYellow);
//		System.out.println("black count " + countBlackMiddle);
//		System.out.println("blueLeft count " + countBlueLeft);
//		System.out.println("blueRight count " + countBlueRight);
//		System.out.println("redTop count " + countRedTop);
//		System.out.println("redBottom count " + countRedBottom);
//		
//		System.out.println("yellow % " + getPercentage(rangeChecker.getPixelCountYellowArea(), countYellow));
//		System.out.println("black % " + getPercentage(rangeChecker.getPixelCountBlackArea(), countBlackMiddle));
//		System.out.println("blueLeft % " + getPercentage(rangeChecker.getPixelCountBlueLeftArea(), countBlueLeft));
//		System.out.println("blueRight % " + getPercentage(rangeChecker.getPixelCountBlueRightArea(), countBlueRight));
//		System.out.println("redTop % " + getPercentage(rangeChecker.getPixelCountRedTopArea(), countRedTop));
//		System.out.println("redBottom % " + getPercentage(rangeChecker.getPixelCountRedBottomArea(), countRedBottom));
		
		result[0] = (getPercentage(rangeChecker.getPixelCountYellowArea(), countYellow));
		result[1] = (getPercentage(rangeChecker.getPixelCountBlueLeftArea(), countBlueLeft));
		result[2] = (getPercentage(rangeChecker.getPixelCountBlueRightArea(), countBlueRight));
		result[3] = (getPercentage(rangeChecker.getPixelCountBlackArea(), countBlackMiddle));
		result[4] = (getPercentage(rangeChecker.getPixelCountRedTopArea(), countRedTop));
		result[5] = (getPercentage(rangeChecker.getPixelCountRedBottomArea(), countRedBottom));
		img.flush();
		return result;
	}

	private int getPercentage(int numberOfPixelsArea, int numberOfPixelsColor){
		return Math.round(((float)numberOfPixelsColor / (float)numberOfPixelsArea)*100);
	}
}
