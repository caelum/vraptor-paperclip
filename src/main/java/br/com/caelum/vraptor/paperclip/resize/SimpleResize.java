package br.com.caelum.vraptor.paperclip.resize;

import java.awt.image.BufferedImage;

public class SimpleResize implements ImageResize {
	private int height;
	private int width;

	public SimpleResize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public int width(BufferedImage image) {
		return width;
	}

	@Override
	public int height(BufferedImage image) {
		return height;
	}
}