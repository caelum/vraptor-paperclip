package br.com.caelum.vraptor.paperclip;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.amazonS3.FileStorage;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.converter.Converter;
import br.com.caelum.vraptor.http.Parameter;
import br.com.caelum.vraptor.http.ParameterNameProvider;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.caelum.vraptor.paperclip.crop.Crop;
import br.com.caelum.vraptor.paperclip.crop.CropOperation;
import br.com.caelum.vraptor.paperclip.crop.CropType;
import br.com.caelum.vraptor.paperclip.crop.ImageCropper;
import br.com.caelum.vraptor.paperclip.resize.ImageResizer;
import br.com.caelum.vraptor.paperclip.resize.Resize;
import br.com.caelum.vraptor.paperclip.resize.ResizeFactory;

@Convert(UploadedImage.class)
public class UploadedImageConverter implements Converter<UploadedImage> {
	
	@Inject
	private HttpServletRequest request;
	
	@Inject
	private ControllerMethod method;
	
	@Inject
	private ImageResizer resizer;
	
	@Inject
	private ImageCropper cropper;
	
	@Inject
	private ParameterNameProvider provider;
	
	@Inject
	private FileStorage storage;

	@Override
	public UploadedImage convert(String name,
			Class<? extends UploadedImage> type) {
		
		UploadedFile file = (UploadedFile) request.getAttribute(name);
		if (file == null) {
			return null;
		}
		BufferedImage image = readImage(file);
		Parameter parameter = findParameter(name);
		
		UploadedImage upload = new UploadedImage(image, storage,file);
		
		if (parameter.isAnnotationPresent(Resize.class)) {
			Resize resize = parameter.getAnnotation(Resize.class);
			upload = resizer.resize(upload, ResizeFactory.build(resize));
		}
		
		if (parameter.isAnnotationPresent(Crop.class)) {
			Crop crop = parameter.getAnnotation(Crop.class);
			CropType cropType = crop.type();
			int width = crop.width();
			int height = crop.height();
			CropOperation cropOperation = cropType.buildOperation(upload, width, height);
			upload = cropper.crop(upload, cropOperation);
		}
		
		return upload;
	}

	private Parameter findParameter(String name) {
		Parameter[] parameters = provider.parametersFor(this.method.getMethod());
		for (Parameter parameter : parameters) {
			if (parameter.getName().equals(name)) {
				return parameter;
			}
		}
		throw new IllegalStateException("Could not find parameter named \"" + name + "\"");
	}


	private BufferedImage readImage(UploadedFile file) {
		try {
			return ImageIO.read(file.getFile());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
