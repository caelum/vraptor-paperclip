package br.com.caelum.vraptor.paperclip.resize;

import java.awt.image.BufferedImage;

public interface ImageResizer {

	public abstract BufferedImage resize(BufferedImage image, ImageResize resize);

}