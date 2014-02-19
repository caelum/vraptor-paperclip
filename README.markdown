# vraptor-paperclip 

A VRaptor plugin to ease image manipulation.

This plugin currently works only with VRaptor 4.x versions. 

## Installing

Add to your pom:
```xml
<dependency>
	<groupId>br.com.caelum.vraptor</groupId>
	<artifactId>vraptor-paperclip</artifactId>
	<version>4.0.0</version>
	<scope>test</scope>
</dependency>
```
		
Or simply copy all jars to your classpath.
		
## Handling a image upload

To receive a uploaded image, you can receive in your method a instance of UploadedImage:

```java
@Controller
public class ProfilePhotoController {
    
    @Public
    @Post("/profile/upload")
    public void savePhoto(UploadedImage image) {
        image.save("/images/upload.png");
    }

}
```

This controller will save the uploaded image as png inside of the
`/images` directory in your webapp (if you access
`http://localhost:8080/appcontext/images/upload.png` you will see the file,
for example). Note that vraptor-paperclip will take care of converting the uploaded
file to png format.

## Processing images

The plugin also handle some simple image processing transformations.

### Image crop

You can crop images before the execution of a method: 

```java
@Controller
public class ProfilePhotoController {
    
	@Post("/profile/upload")
	public void savePhoto(@Crop(width=100, height=100) UploadedImage image) {
		image.save("/images/cropped.png");
	}
	
}
```

The default behavior is to crop the image in the center. You can specify the 
type of cropping in the annotation. For example, to crop the image in the top 
left: `@Crop(width=100, height=100, type=CropType.TOP_LEFT)`

It's also possible to perform the cropping programmatically, with the `ImageCropper`:

```java
@Controller
public class ProfilePhotoController {
    
	@Inject
	private ImageCropper cropper;
	
    @Public
    @Post("/profile/upload")
    public void savePhoto(UploadedImage image) {
    	UploadedImage cropped = cropper.crop(image, new CenteredCrop(image, 50, 50));
        cropped.save("/images/upload.png");
    }

}
```


### Image resize

Similarlly, you can resize the image before the execution of the method:

```java
@Controller
public class ProfilePhotoController {
    
    @Public
    @Post("/profile/upload")
    public void savePhoto(@Resize(width=100, height=100) UploadedImage image) {
        image.save("/images/upload.png");
    }

}
```

This will resize the image maintaining its original proportion. You can also specify a ratio
in the annotation, for example, `@Resize(ratio=0.5)` will resize the original image to 
the half of its original size.

You can also use the `ImageResizer` class to resize your images programmatically:

```java
@Controller
public class ProfilePhotoController {

    @Inject private ImageResizer resizer;
    
    @Post("/profile/upload")
    public void savePhoto(UploadedImage image) {
        UploadedImage resized = resizer.resize(image, new SimpleResize(100, 100));
        resized.save("/images/upload.png");
    }

}
```
