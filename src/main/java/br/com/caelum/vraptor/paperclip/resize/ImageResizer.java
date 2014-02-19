package br.com.caelum.vraptor.paperclip.resize;

import br.com.caelum.vraptor.paperclip.UploadedImage;

public interface ImageResizer {

	public abstract UploadedImage resize(UploadedImage image, ImageResize resize);

}