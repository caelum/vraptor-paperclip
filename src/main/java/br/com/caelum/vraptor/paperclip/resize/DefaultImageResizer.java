package br.com.caelum.vraptor.paperclip.resize;

import java.awt.image.BufferedImage;

import javax.inject.Inject;

import org.imgscalr.Scalr;

import br.com.caelum.vraptor.amazonS3.FileStorage;
import br.com.caelum.vraptor.paperclip.UploadedImage;

public class DefaultImageResizer implements ImageResizer {
	
	@Inject
	private FileStorage storage;

	@Override
	public UploadedImage resize(UploadedImage image, ImageResize resize) {
		int width = resize.width(image);
		int height = resize.height(image);
		BufferedImage newImage = Scalr.resize(image.getImage(), width, height);
		return new UploadedImage(newImage, storage);
	}
}
