package br.com.caelum.vraptor.paperclip.resize;

import java.awt.image.BufferedImage;

import org.imgscalr.Scalr;

public class DefaultImageResizer implements ImageResizer {

	@Override
	public BufferedImage resize(BufferedImage image, ImageResize resize) {
		int width = resize.width(image);
		int height = resize.height(image);
		return Scalr.resize(image, width, height);
	}
}
