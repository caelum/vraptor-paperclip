package br.com.caelum.vraptor.paperclip;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import br.com.caelum.vraptor.observer.upload.UploadedFile;

public class UploadedImage {
	
	private ServletContext context;
	
	private UploadedFile file;

	private BufferedImage image;

	public UploadedImage(UploadedFile file, BufferedImage image, ServletContext context) {
		this.file = file;
		this.image = image;
		this.context = context;
	}

	public void save(String localPath, String fileName) {
		String extension = FilenameUtils.getExtension(fileName);
		String path = context.getRealPath(localPath);
		File file = new File(path, fileName);
		write(extension, file);
	}

	private void write(String extension, File file) {
		try {
			ImageIO.write(image, extension, new FileOutputStream(file));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
