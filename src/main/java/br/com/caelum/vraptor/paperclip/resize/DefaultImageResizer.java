package br.com.caelum.vraptor.paperclip.resize;

import java.awt.image.BufferedImage;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.imgscalr.Scalr;

import br.com.caelum.vraptor.paperclip.UploadedImage;

public class DefaultImageResizer implements ImageResizer {
	
	@Inject
	private ServletContext context;

	@Override
	public UploadedImage resize(UploadedImage image, ImageResize resize) {
		int width = resize.width(image);
		int height = resize.height(image);
		BufferedImage newImage = Scalr.resize(image.getImage(), width, height);
		return new UploadedImage(newImage, context);
	}
}
