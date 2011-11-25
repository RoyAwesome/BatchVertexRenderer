package org.royawesome.util;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.util.Hashtable;

public class Texture2D extends BufferedImage {

	public Texture2D(ColorModel cm, WritableRaster raster,
			boolean isRasterPremultiplied, Hashtable<?, ?> properties) {
		super(cm, raster, isRasterPremultiplied, properties);
		// TODO Auto-generated constructor stub
	}

	public Texture2D(int width, int height, int imageType, IndexColorModel cm) {
		super(width, height, imageType, cm);
		// TODO Auto-generated constructor stub
	}

	public Texture2D(int width, int height, int imageType) {
		super(width, height, imageType);
		
	}
	
	
	int texturePointer = -1;
	

	
	
	public void assignTexture(){
		
	}
	
}
