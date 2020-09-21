package com.jeffrey.context.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Jeffrey on 2019-08-30
 * customized valid script
 */
@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(ScriptValid.List.class)
public @interface ScriptValid {
    String script();

    @Target({TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        ScriptValid[] value();
    }
}
