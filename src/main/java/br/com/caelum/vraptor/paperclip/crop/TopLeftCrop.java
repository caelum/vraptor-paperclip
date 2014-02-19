package br.com.caelum.vraptor.paperclip.crop;

public class TopLeftCrop implements CropOperation {

	private int width;
	private int height;

	public TopLeftCrop(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public int topLeftX() {
		return 0;
	}

	@Override
	public int topLeftY() {
		return 0;
	}

	@Override
	public int cropWidth() {
		return width;
	}

	@Override
	public int cropHeight() {
		return height;
	}

}
