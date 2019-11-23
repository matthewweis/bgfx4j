package com.bariumhoof;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface Precondition {
    public String value() default "";
}
