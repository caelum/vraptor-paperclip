package br.com.caelum.vraptor.paperclip.resize;

import java.awt.image.BufferedImage;

public interface ImageResize {

	int width(BufferedImage image);

	int height(BufferedImage image);

}
