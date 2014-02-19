package br.com.caelum.vraptor.paperclip.crop;

import java.awt.image.BufferedImage;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.imgscalr.Scalr;

import br.com.caelum.vraptor.paperclip.UploadedImage;

public class DefaultImageCropper implements ImageCropper {

	@Inject
	private ServletContext context;

	@Override
	public UploadedImage crop(UploadedImage upload, int width, int height) {
		
		double imageWidth = upload.getWidth();
		double imageHeight = upload.getHeight();
		
		double centerX = imageWidth / 2;
		double centerY = imageHeight / 2;
		
		int topLeftX = (int) (centerX - ((double) width) / 2);
		int topLeftY = (int) (centerY - ((double) height) / 2);
		
		BufferedImage cropped = Scalr.crop(upload.getImage(), topLeftX, topLeftY, width, height);
		
		return new UploadedImage(cropped, context);
	}

}
