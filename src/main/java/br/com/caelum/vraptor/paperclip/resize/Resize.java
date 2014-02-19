package br.com.caelum.vraptor.paperclip.resize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Resize {

	int width() default 0;
	int height() default 0;
	double ratio() default 1.0;
	
}
