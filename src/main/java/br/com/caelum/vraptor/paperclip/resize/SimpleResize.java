package br.com.caelum.vraptor.paperclip.resize;

import br.com.caelum.vraptor.paperclip.UploadedImage;

public class SimpleResize implements ImageResize {
	private int height;
	private int width;

	public SimpleResize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public int width(UploadedImage image) {
		return width;
	}

	@Override
	public int height(UploadedImage image) {
		return height;
	}
}