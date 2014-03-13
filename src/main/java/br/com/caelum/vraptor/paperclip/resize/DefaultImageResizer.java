package br.com.caelum.vraptor.paperclip.resize;

import java.awt.image.BufferedImage;

import org.imgscalr.Scalr;

import br.com.caelum.vraptor.paperclip.UploadedImage;

public class DefaultImageResizer implements ImageResizer {
	
	@Override
	public UploadedImage resize(UploadedImage image, ImageResize resize) {
		int width = resize.width(image);
		int height = resize.height(image);
		BufferedImage newImage = Scalr.resize(image.getImage(), width, height);
		return image.recreate(newImage);
	}
}
