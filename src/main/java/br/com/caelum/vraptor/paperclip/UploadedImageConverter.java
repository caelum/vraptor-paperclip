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
import br.com.caelum.vraptor.paperclip.resize.Resize;
import br.com.caelum.vraptor.paperclip.resize.ResizeFactory;
import br.com.caelum.vraptor.paperclip.resize.ImageResizer;

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

	@Override
	public UploadedImage convert(String name,
			Class<? extends UploadedImage> type) {
		UploadedFile file = (UploadedFile) request.getAttribute(name);
		
		BufferedImage image = readImage(file);
		
		if (shouldResize()) {
			Resize resize = findResize();
			image = resizer.resize(image, ResizeFactory.build(resize));
		}
		
		return new UploadedImage(image, context);
	}

	private BufferedImage readImage(UploadedFile file) {
		try {
			return ImageIO.read(file.getFile());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private boolean shouldResize() {
		return findResize() != null;
	}

	private Resize findResize() {
		Annotation[][] annotations = method.getMethod().getParameterAnnotations();
		if (annotations.length > 0) {
			for (Annotation[] annotationsOnParam : annotations) {
				for (Annotation annotation : annotationsOnParam) {
					if (annotation instanceof Resize) {
						return (Resize) annotation;
					}
				}
			}
		}
		return null;
	}

}
