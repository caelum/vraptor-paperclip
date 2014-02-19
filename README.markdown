# vraptor-paperclip 

A VRaptor plugin to ease image manipulation.

This plugin currently works only with VRaptor 4.x versions. 

## installing

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
		
## handling a image upload

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

## processing images

The plugin also handle of some simple image processing transformations.

### resizing

You can resize the image before the execution of the method:

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

This will resize the image maitaining its original proportion. You can also specify a ratio
in the annotation, for example, `@Resize(ratio=0.5)` will resize the orginal image to 
the half of its original size.

#### resizing programmatically

You can also use the `ImageResizer` class to resize your images programmatically:

```java
@Controller
public class ProfilePhotoController {

    @Inject private ImageResizer resizer;
    
    @Public
    @Post("/profile/upload")
    public void savePhoto(UploadedImage image) {
        UploadedImage resized = resizer.resize(image, new SimpleResize(100, 100));
        resized.save("/images/upload.png");
    }

}
```
