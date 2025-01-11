package com.harel.presentation.spring.syntax.reflection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

@Configuration
@Import({})
public class ClassToReflect {

    @Autowired
    private String iAmAutowired;

    public static void main(String[] args) {
        Class<ClassToReflect> classToReflect = ClassToReflect.class;

        Annotation[] annotations = classToReflect.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof Configuration) {
                System.out.println("found configuration annotation");
            }
            if (annotation instanceof Import) {
                System.out.println("found import annotation");
            }
        }

        Field[] declaredFields = classToReflect.getDeclaredFields();
        Annotation[] annotationsOnField = declaredFields[0].getAnnotations();
        System.out.println("field:'" + declaredFields[0].getName() + "'; field type:" +declaredFields[0].getType() +
            "; annotation on field:" + annotationsOnField[0].toString());

    }

}
