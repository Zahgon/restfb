/*
 * Copyright (c) 2010-2026 Mark Allen, Norbert Bartels.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.restfb.util;

import static java.lang.String.format;
import static java.util.Collections.synchronizedMap;
import static java.util.Collections.unmodifiableList;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import com.restfb.annotation.OriginalJson;
import com.restfb.exception.FacebookJsonMappingException;

/**
 * A collection of reflection-related utility methods.
 *
 * @author <a href="http://restfb.com">Mark Allen</a>
 * @author Igor Kabiljo
 * @author Scott Hernandez
 * @since 1.6
 */
public final class ReflectionUtils {

    /**
     * In-memory shared cache of reflection data for {@link #findFieldsWithAnnotation(Class, Class)}.
     */
    private static final Map<ClassAnnotationCacheKey, List<?>> FIELDS_WITH_ANNOTATION_CACHE = synchronizedMap(new HashMap<>());

    /**
     * In-memory shared cache of reflection data for {@link #findMethodsWithAnnotation(Class, Class)}.
     */
    private static final Map<ClassAnnotationCacheKey, List<Method>> METHODS_WITH_ANNOTATION_CACHE = synchronizedMap(new HashMap<>());

    /**
     * Prevents instantiation.
     */
    private ReflectionUtils() {
        // prevent instantiation
    }

    public static void setJson(Object cls, String obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static void setFieldData(Field field, Object obj, Object data) {
        try {
            field.setAccessible(true);
            field.set(obj, data);
        } catch (IllegalAccessException e) {
            // do nothing here, the field stays unset and the developer has to handle it
        }
    }

    public static boolean isPrimitive(Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T extends Annotation> List<FieldWithAnnotation<T>> findFieldsWithAnnotation(Class<?> type, Class<T> annotationType) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T extends Annotation> List<Method> findMethodsWithAnnotation(Class<?> type, Class<T> annotationType) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Class<?> getFirstParameterizedTypeArgument(Field field) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Class<?> getSecondParameterizedTypeArgument(Field field) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Type getParameterizedTypeArgument(Type type, int i) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static Class<?> getParameterizedTypeArgument(Field field, int i) {
        Type firstTypeArgument = getParameterizedTypeArgument(field.getGenericType(), i);
        return (firstTypeArgument instanceof Class) ? (Class<?>) firstTypeArgument : null;
    }

    public static List<Method> getAccessors(Class<?> clazz) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String toString(Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static String getMethodName(Method method) {
        String methodName = method.getName();
        int offset = methodName.startsWith("is") ? 2 : 3;
        methodName = methodName.substring(offset, offset + 1).toLowerCase() + methodName.substring(offset + 1);
        return methodName;
    }

    public static int hashCode(Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean equals(Object object1, Object object2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static void makeMethodAccessible(Method method) {
        if (!method.isAccessible()) {
            method.setAccessible(true);
        }
    }

    public static <T> T createInstance(Class<T> type) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static void throwStateException(Method method, Object obj, Exception e) {
        throw new IllegalStateException("Unable to reflectively invoke " + method + Optional.ofNullable(obj).map(o -> " on " + o).orElse(""), e);
    }

    /**
     * A field/annotation pair.
     *
     * @author <a href="http://restfb.com">Mark Allen</a>
     */
    public static class FieldWithAnnotation<T extends Annotation> {

        /**
         * A field.
         */
        private final Field field;

        /**
         * An annotation on the field.
         */
        private final T annotation;

        /**
         * Creates a field/annotation pair.
         *
         * @param field
         *          A field.
         * @param annotation
         *          An annotation on the field.
         */
        public FieldWithAnnotation(Field field, T annotation) {
            this.field = field;
            this.annotation = annotation;
        }

        public Field getField() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public T getAnnotation() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public String toString() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Cache key composed of a class and annotation pair. Used by {@link ReflectionUtils#FIELDS_WITH_ANNOTATION_CACHE}.
     *
     * @author Igor Kabiljo
     */
    private static final class ClassAnnotationCacheKey {

        /**
         * Class component of this cache key.
         */
        private final Class<?> clazz;

        /**
         * Annotation component of this cache key.
         */
        private final Class<? extends Annotation> annotation;

        /**
         * Creates a cache key with the given {@code clazz}/@{code annotation} pair.
         *
         * @param clazz
         *          Class component of this cache key.
         * @param annotation
         *          Annotation component of this cache key.
         */
        private ClassAnnotationCacheKey(Class<?> clazz, Class<? extends Annotation> annotation) {
            this.clazz = clazz;
            this.annotation = annotation;
        }

        @Override
        public int hashCode() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean equals(Object obj) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
