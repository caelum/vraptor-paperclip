package br.com.caelum.vraptor.paperclip;

import static org.apache.commons.io.FilenameUtils.getExtension;
import static org.apache.commons.io.FilenameUtils.getPath;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;

public class UploadedImage {
	
	private final ServletContext context;
	
	private final BufferedImage image;

	public UploadedImage(BufferedImage image, ServletContext context) {
		this.image = image;
		this.context = context;
	}

	public void save(String localPath) {
		String path = getPath(localPath);
		String filename = FilenameUtils.getName(localPath);
		String realPath = context.getRealPath(path);
		File file = new File(realPath, filename);
		write(file);
	}

	private void write(File file) {
		String extension = getExtension(file.getName());
		try {
			ImageIO.write(image, extension, new FileOutputStream(file));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public int getHeight() {
		return getImage().getHeight();
	}

	public int getWidth() {
		return getImage().getWidth();
	}

}
