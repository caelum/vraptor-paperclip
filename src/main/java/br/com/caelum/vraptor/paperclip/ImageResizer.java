package br.com.caelum.vraptor.paperclip;

import java.awt.image.BufferedImage;

import org.imgscalr.Scalr;

public class ImageResizer {

	public BufferedImage resize(BufferedImage image, Resize resize) {
		int width = resize.width();
		int height = resize.height();
		
		if (width == 0 || height == 0) {
			double ratio = resize.ratio();
			int originalWidth = image.getWidth();
			int originalHeight = image.getHeight();
			width = (int) (originalWidth * ratio);
			height = (int) (originalHeight * ratio);
		}
		
		return Scalr.resize(image, width, height);
	}
}
