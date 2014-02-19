package br.com.caelum.vraptor.paperclip.resize;

public class ResizeFactory {

	public static ImageResize build(Resize resize) {
		int width = resize.width();
		int height = resize.height();
		if (width == 0 && height == 0) {
			return new RatioResize(resize.ratio());
		}
		return new SimpleResize(width, height);
	}

}
