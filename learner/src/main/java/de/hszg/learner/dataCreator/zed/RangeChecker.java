package de.hszg.learner.dataCreator.zed;

/**
 * Holds the areas in which the {@link ImageProcessorZ} looks for pixels of certain colors
 * @author z
 *
 */
public class RangeChecker {
	
	private int height;
	private int width;
	
	private int blackMinX;
	private int blackMaxX;
	private int blackMinY;
	private int blackMaxY;

	private int blueLeftMinX;
	private int blueLeftMaxX;
	private int blueLeftMinY;
	private int blueLeftMaxY;

	private int blueRightMinX;
	private int blueRightMaxX;
	private int blueRightMinY;
	private int blueRightMaxY;

	private int redBottomMinX;
	private int redBottomMaxX;
	private int redBottomMinY;
	private int redBottomMaxY;

	private int redTopMinX;
	private int redTopMaxX;
	private int redTopMinY;
	private int redTopMaxY;

	/**
	 * Creates the percentile ranges according to the given picture size
	 * @param height
	 * @param width
	 */
	public RangeChecker(int height, int width) {
		this.height = height;
		this.width = width;
		initBorders();
	}
	
	private void initBorders() {
		this.blackMaxX = (int) Math.round(width * 0.6);
		this.blackMinX = (int) Math.round(width * 0.4);
		this.blackMaxY = (int) Math.round(height * 0.6);
		this.blackMinY = (int) Math.round(height * 0.4);

		this.blueLeftMaxX = (int) Math.round(width * 0.5);
		this.blueLeftMinX = (int) Math.round(width * 0);
		this.blueLeftMaxY = (int) Math.round(height * 0.5);
		this.blueLeftMinY = (int) Math.round(height * 0);

		this.blueRightMaxX = (int) Math.round(width * 1);
		this.blueRightMinX = (int) Math.round(width * 0.5);
		this.blueRightMaxY = (int) Math.round(height * 0.5);
		this.blueRightMinY = (int) Math.round(height * 0);

		this.redBottomMaxX = (int) Math.round(width * 1);
		this.redBottomMinX = (int) Math.round(width * 0);
		this.redBottomMaxY = (int) Math.round(height * 1);
		this.redBottomMinY = (int) Math.round(height * 0.5);

		this.redTopMaxX = (int) Math.round(width * 1);
		this.redTopMinX = (int) Math.round(width * 0);
		this.redTopMaxY = (int) Math.round(height * 0.5);
		this.redTopMinY = (int) Math.round(height * 0);
	}

	/**
	 * Checks if given pixel is in an important area
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean importantRedBottom(int x, int y) {
		return (x < redBottomMaxX && x > redBottomMinX && y < redBottomMaxY && y > redBottomMinY);
	}
	
	/**
	 * Checks if given pixel is in an important area
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean importantRedTop(int x, int y) {
		return (x < redTopMaxX && x > redTopMinX && y < redTopMaxY && y > redTopMinY);
	}

	/**
	 * Checks if given pixel is in an important area
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean importantBlueRight(int x, int y) {
		return (x < blueRightMaxX && x > blueRightMinX && y < blueRightMaxY && y > blueRightMinY);
	}

	/**
	 * Checks if given pixel is in an important area
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean importantBlueLeft(int x, int y) {
		return (x < blueLeftMaxX && x > blueLeftMinX && y < blueLeftMaxY && y > blueLeftMinY);
	}

	/**
	 * Checks if given pixel is in an important area
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean importantBlack(int x, int y) {
		return (x < blackMaxX && x > blackMinX && y < blackMaxY && y > blackMinY);
	}
	
	public int getPixelCountBlackArea(){
		return (blackMaxX-blackMinX) * (blackMaxY - blackMinY);
	}
	
	public int getPixelCountYellowArea(){
		return getPixelCountBlackArea();
	}
	
	public int getPixelCountBlueLeftArea(){
		return (blueLeftMaxX-blueLeftMinX)*(blueLeftMaxY-blueLeftMinY);
	}
	
	public int getPixelCountBlueRightArea(){
		return (blueRightMaxX-blueRightMinX)*(blueRightMaxY-blueRightMinY);
	}
	
	public int getPixelCountRedTopArea(){
		return (redTopMaxX-redTopMinX)*(redTopMaxY-redTopMinY);
	}
	
	public int getPixelCountRedBottomArea(){
		return (redBottomMaxX-redBottomMinX)*(redBottomMaxY-redBottomMinY);
	}


}
