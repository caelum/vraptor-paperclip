package br.com.caelum.vraptor.paperclip.crop;

import br.com.caelum.vraptor.paperclip.UploadedImage;

public enum CropType {
	
	CENTERED {
		@Override
		public CropOperation buildOperation(UploadedImage upload, int width,
				int height) {
			return new CenteredCrop(upload, width, height);
		}
	},
	TOP_LEFT {
		@Override
		public CropOperation buildOperation(UploadedImage upload, int width,
				int height) {
			return new TopLeftCrop(width, height);
		}
		
	};

	public abstract CropOperation buildOperation(UploadedImage upload, int width,
			int height);

}
