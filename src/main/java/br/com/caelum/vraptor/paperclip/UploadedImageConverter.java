package br.com.caelum.vraptor.paperclip;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.annotation.Annotation;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.converter.Converter;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.caelum.vraptor.paperclip.crop.Crop;
import br.com.caelum.vraptor.paperclip.crop.ImageCropper;
import br.com.caelum.vraptor.paperclip.resize.ImageResizer;
import br.com.caelum.vraptor.paperclip.resize.Resize;
import br.com.caelum.vraptor.paperclip.resize.ResizeFactory;

@Convert(UploadedImage.class)
public class UploadedImageConverter implements Converter<UploadedImage> {
	
	@Inject
	private HttpServletRequest request;
	
	@Inject
	private ServletContext context;
	
	@Inject
	private ControllerMethod method;
	
	@Inject
	private ImageResizer resizer;
	
	@Inject
	private ImageCropper cropper;

	@Override
	public UploadedImage convert(String name,
			Class<? extends UploadedImage> type) {
		UploadedFile file = (UploadedFile) request.getAttribute(name);
		BufferedImage image = readImage(file);
		
		UploadedImage upload = new UploadedImage(image, context);
		
		if (shouldResize()) {
			Resize resize = findAnnotation(Resize.class);
			upload = resizer.resize(upload, ResizeFactory.build(resize));
		}
		
		if (shouldCrop()) {
			Crop crop = findAnnotation(Crop.class);
			int width = crop.width();
			int height = crop.height();
			upload = cropper.crop(upload, width, height);
		}
		
		return upload;
	}

	private boolean shouldCrop() {
		return findAnnotation(Crop.class) != null;
	}

	private BufferedImage readImage(UploadedFile file) {
		try {
			return ImageIO.read(file.getFile());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private boolean shouldResize() {
		return findAnnotation(Resize.class) != null;
	}

	private <T> T findAnnotation(Class<T> annotationClass) {
		Annotation[][] annotations = method.getMethod().getParameterAnnotations();
		if (annotations.length > 0) {
			for (Annotation[] annotationsOnParam : annotations) {
				for (Annotation annotation : annotationsOnParam) {
					if (annotationClass.isAssignableFrom(annotation.getClass())) {
						return annotationClass.cast(annotation);
					}
				}
			}
		}
		return null;
	}

}
