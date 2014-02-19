package br.com.caelum.vraptor.paperclip.resize;

import java.awt.image.BufferedImage;

public class RatioResize implements ImageResize {
	
	private double ratio;

	public RatioResize(double ratio) {
		this.ratio = ratio;
	}

	@Override
	public int width(BufferedImage image) {
		int originalWidth = image.getWidth();
		return (int) (originalWidth * ratio);
	}

	@Override
	public int height(BufferedImage image) {
		int originalHeight = image.getHeight();
		return (int) (originalHeight * ratio);
	}

}
