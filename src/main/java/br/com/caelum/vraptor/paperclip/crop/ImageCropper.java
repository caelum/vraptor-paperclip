package br.com.caelum.vraptor.paperclip.crop;

import br.com.caelum.vraptor.paperclip.UploadedImage;

public interface ImageCropper {

	UploadedImage crop(UploadedImage upload, int width, int height);

}
