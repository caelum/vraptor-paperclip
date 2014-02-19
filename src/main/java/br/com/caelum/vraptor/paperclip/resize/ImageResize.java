package br.com.caelum.vraptor.paperclip.resize;

import br.com.caelum.vraptor.paperclip.UploadedImage;

public interface ImageResize {

	int width(UploadedImage image);

	int height(UploadedImage image);

}
