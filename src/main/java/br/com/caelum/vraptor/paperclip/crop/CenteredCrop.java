package br.com.caelum.vraptor.paperclip.crop;

import br.com.caelum.vraptor.paperclip.UploadedImage;

public class CenteredCrop implements CropOperation {
	
	private int topLeftY;
	private int topLeftX;
	private int cropHeight;
	private int cropWidth;

	public CenteredCrop(UploadedImage image, int width, int height) {
		this.cropWidth = width;
		this.cropHeight = height;
		double imageWidth = image.getWidth();
		double imageHeight = image.getHeight();
		
		double centerX = imageWidth / 2;
		double centerY = imageHeight / 2;
		
		topLeftX = (int) (centerX - ((double) width) / 2);
		topLeftY = (int) (centerY - ((double) height) / 2);
	}

	@Override
	public int topLeftX() {
		return topLeftX;
	}

	@Override
	public int topLeftY() {
		return topLeftY;
	}

	@Override
	public int cropWidth() {
		return cropWidth;
	}

	@Override
	public int cropHeight() {
		return cropHeight;
	}
}
