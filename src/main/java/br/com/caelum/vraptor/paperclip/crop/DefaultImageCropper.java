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
	public UploadedImage crop(UploadedImage upload, CropOperation operation) {
		
		int topLeftX = operation.topLeftX();
		int topLeftY = operation.topLeftY();
		int width = operation.cropWidth();
		int height = operation.cropHeight();
		
		BufferedImage cropped = Scalr.crop(upload.getImage(), topLeftX, topLeftY, width, height);
		
		return new UploadedImage(cropped, context);
	}

}
