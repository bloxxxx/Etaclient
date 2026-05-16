package io.github.bloxxxx.etaclient.feature.trait.packetHandler;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HandlePacket {
    boolean alwaysOn() default false;
}
