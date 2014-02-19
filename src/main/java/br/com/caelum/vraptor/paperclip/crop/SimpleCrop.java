package br.com.caelum.vraptor.paperclip.crop;

public class SimpleCrop implements CropOperation {
	
	private int cropHeight;
	private int topLeftX;
	private int topLeftY;
	private int cropWidth;

	public SimpleCrop(int topLeftX, int topLeftY, int cropWidth, int cropHeight) {
		this.topLeftX = topLeftX;
		this.topLeftY = topLeftY;
		this.cropWidth = cropWidth;
		this.cropHeight = cropHeight;
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
