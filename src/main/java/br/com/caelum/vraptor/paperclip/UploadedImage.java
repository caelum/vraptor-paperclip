package br.com.caelum.vraptor.paperclip;

import static org.apache.commons.io.FilenameUtils.getExtension;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;

import br.com.caelum.vraptor.amazonS3.FileStorage;

public class UploadedImage {
	
	private final BufferedImage image;

	private FileStorage storage;

	public UploadedImage(BufferedImage image, FileStorage storage) {
		this.image = image;
		this.storage = storage;
	}

	public URL save(String path) {
		String filename = FilenameUtils.getName(path);
		InputStream is = imageToInputStream(path);
		return storage.store(is, path, contentTypeOf(filename));
	}

	private String contentTypeOf(String localPath) {
		return "image/" + getExtension(localPath);
	}

	private InputStream imageToInputStream(String localPath) {
		String extension = getExtension(localPath);
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(image, extension, os);
			return new ByteArrayInputStream(os.toByteArray());
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
