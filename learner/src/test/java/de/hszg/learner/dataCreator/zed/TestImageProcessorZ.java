package de.hszg.learner.dataCreator.zed;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class TestImageProcessorZ {
	
	private static ImageProcessorZ processor = new ImageProcessorZ();

	@Test
	public void testCreateFeaturesLinks() throws IOException{
		int[] features = processor.createFeatures(new File(getClass().getResource("/Links.bmp").getPath()));
		assertTrue(features[1]<features[2]);
	}
	
	@Test
	public void testCreateFeaturesRechts() throws IOException{
		int[] features = processor.createFeatures(new File(getClass().getResource("/Rechts.bmp").getPath()));
		assertTrue(features[1]>features[2]);
	}
	
	@Test
	public void testCreateFeaturesStop() throws IOException{
		int[] features = processor.createFeatures(new File(getClass().getResource("/Stop.bmp").getPath()));
		assertTrue(features[4]>25&&features[5]>25);
	}
	
	@Test
	public void testCreateFeaturesVorfahrtGewahren() throws IOException{
		int[] features = processor.createFeatures(new File(getClass().getResource("/VorfahrtGewaehren.bmp").getPath()));
		assertTrue(features[4]>features[5]);
	}
	
	@Test
	public void testCreateFeaturesVorfahrtsstrasse() throws IOException{
		int[] features = processor.createFeatures(new File(getClass().getResource("/Vorfahrtsstrasse.bmp").getPath()));
		assertTrue(features[3]>30);
	}
}
