package br.com.caelum.vraptor.paperclip.crop;

import java.awt.image.BufferedImage;

import org.imgscalr.Scalr;

import br.com.caelum.vraptor.paperclip.UploadedImage;

public class DefaultImageCropper implements ImageCropper {

	@Override
	public UploadedImage crop(UploadedImage upload, CropOperation operation) {
		
		int topLeftX = operation.topLeftX();
		int topLeftY = operation.topLeftY();
		int width = operation.cropWidth();
		int height = operation.cropHeight();
		
		BufferedImage cropped = Scalr.crop(upload.getImage(), topLeftX, topLeftY, width, height);
		
		return upload.recreate(cropped);
	}

}
