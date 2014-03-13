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
import br.com.caelum.vraptor.observer.upload.UploadedFile;

public class UploadedImage {
	
	private final BufferedImage image;

	private FileStorage storage;

	private UploadedFile uploadedFile;

	private long timestamp;

	public UploadedImage(BufferedImage image, FileStorage storage, UploadedFile uploadedFile) {
		this.image = image;
		this.storage = storage;
		this.uploadedFile = uploadedFile;
		this.timestamp = System.nanoTime();
	}

	public URL save(String path) {
		String filename = FilenameUtils.getName(path);
		InputStream is = imageToInputStream(path);
		return storage.store(is, path, contentTypeOf(filename));
	}
	
	public URL saveWithTimestamp(String dir){
		return save(dir+"/"+timestampedName());
	}

	private String contentTypeOf(String localPath) {
		return "image/" + getExtension(localPath);
	}
	
	public String timestampedName(){
		String fileName = FilenameUtils.getBaseName(uploadedFile.getFileName());
		String extension = getExtension(uploadedFile.getFileName());
		return fileName + "_" + timestamp + "." + extension;
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

	public UploadedImage recreate(BufferedImage cropped) {
		return new UploadedImage(cropped, storage, uploadedFile);
	}
	
}
