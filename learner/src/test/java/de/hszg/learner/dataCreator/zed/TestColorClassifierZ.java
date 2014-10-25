package de.hszg.learner.dataCreator.zed;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestColorClassifierZ {
	
	private static ColorClassifierZ classifier;

	@BeforeClass
	public static void initClassifier() {
		classifier = new ColorClassifierZ();
	}
	
	@Test
	public void testGetColorNameBlack() {
		assertEquals(Colors.BLACK,classifier.getColorName(new Color(13,16,12)));
		assertEquals(Colors.BLACK,classifier.getColorName(new Color(18,20,18)));
		assertEquals(Colors.BLACK,classifier.getColorName(new Color(0,0,0)));
		assertEquals(Colors.BLACK,classifier.getColorName(new Color(69,78,81)));
		assertEquals(Colors.BLACK,classifier.getColorName(new Color(122,134,150)));
	}
	
	@Test
	public void testGetColorNameYellow() {
		assertEquals(Colors.YELLOW,classifier.getColorName(new Color(253,229,17)));
		assertEquals(Colors.YELLOW,classifier.getColorName(new Color(94,67,13)));
		assertEquals(Colors.YELLOW,classifier.getColorName(new Color(194,156,4)));
		assertEquals(Colors.YELLOW,classifier.getColorName(new Color(255,255,208)));
		assertEquals(Colors.YELLOW,classifier.getColorName(new Color(255,255,150)));
	}
	
	@Test
	public void testGetColorNameBlue() {
		assertEquals(Colors.BLUE,classifier.getColorName(new Color(0,26,40)));
		assertEquals(Colors.BLUE,classifier.getColorName(new Color(28,36,93)));
		assertEquals(Colors.BLUE,classifier.getColorName(new Color(134,198,242)));
		assertEquals(Colors.BLUE,classifier.getColorName(new Color(73,132,194)));
	}
	
	@Test
	public void testGetColorNameRed() {
		assertEquals(Colors.RED,classifier.getColorName(new Color(221,54,42)));
		assertEquals(Colors.RED,classifier.getColorName(new Color(232,131,122)));
		assertEquals(Colors.RED,classifier.getColorName(new Color(119,31,25)));
		assertEquals(Colors.RED,classifier.getColorName(new Color(251,189,176)));
		assertEquals(Colors.RED,classifier.getColorName(new Color(54,9,13)));
	}

}
