package ru.sber.annotations;

import ru.sber.enums.CacheTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache {
    CacheTypeEnum cacheType() default CacheTypeEnum.IN_MEMORY;

    String fileNamePrefix() default "";

    boolean zip() default false;

    Class<?>[] identityBy() default {};

    int listList() default 0;
}

