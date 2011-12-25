package sheep.dic;

import java.lang.annotation.ElementType;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RUNTIME)
public @interface DicItem {

	String value();
	//String value();

    int ext() default 0;
    //扩展属性
}



