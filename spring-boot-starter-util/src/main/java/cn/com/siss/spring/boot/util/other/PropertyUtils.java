package cn.com.siss.spring.boot.util.other;

import lombok.extern.slf4j.Slf4j;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class PropertyUtils {

    public static String getBeanName(String methodName)
    {
        // Assume the method starts with either get or is.
        return Introspector.decapitalize(methodName.substring(methodName.startsWith("is") ? 2 : 3));
    }


    /**
     * get all annotations by the name of the field
     *
     * @param name The name of the field
     * @return <code>Annotation[]</code> if the method is optional
     */
    // get annotated field, then validate it later with corresponding implementation.
    public static Annotation[] getFieldAnnotations(Class<?> clazz, String name) {
        try {
            Field f = clazz.getDeclaredField(name);
            Annotation[] annotations = f.getDeclaredAnnotations();
            return annotations;
        } catch (NoSuchFieldException e) {
            Class<?> superClazz = clazz.getSuperclass();
            if (!superClazz.getName().equals("java.lang.Object")) {
                return getFieldAnnotations(superClazz, name);
            }
            log.trace("No such field {}: {}", name, e.getMessage());
        } catch (NullPointerException e) {
            log.trace("Null Exception {}: {}", name, e.getMessage());
        }
        return null;
    }

    /**
     * get the specific annotation by the name of the field
     *
     * @param name The name of the field
     * @param clazz The class of the annotation
     * @return <code>Annotation</code> if the method is optional
     */
    // get annotated field, then validate it later with corresponding implementation.
    public static Annotation getAnnotation(Object object, String name, Class<? extends Annotation> clazz) {
        try {
            Field f = object.getClass().getDeclaredField(name);
            return f.getAnnotation(clazz);
        } catch (NoSuchFieldException e) {
            log.debug("{}", e);
        } catch (NullPointerException e) {
            log.trace("Null Exception {}: {}", name, e.getMessage());
        }
        return null;
    }


    public static void invokeSetter(Object obj, String propertyName, Object propertyValue){
      /* propertyValue is Object because value can be an Object, Integer, String, etc... */
        try {
            /**
             * Get object of PropertyDescriptor using variable name and class
             * Note: To use PropertyDescriptor on any field/variable, the field must have both `Setter` and `Getter` method.
             */
            PropertyDescriptor objPropertyDescriptor = new PropertyDescriptor(propertyName, obj.getClass());
            // Set field/variable value using getWriteMethod()
            Method setter = objPropertyDescriptor.getWriteMethod();
            setter.setAccessible(true);
            setter.invoke(obj, propertyValue);
        } catch (IllegalAccessException |
                IllegalArgumentException |
                InvocationTargetException |
                IntrospectionException e
                ) {
            // Java 8: Multiple exception in one catch. Use Different catch block for lower version.
            log.debug("{}", e);
        }
    }

    public static Object invokeGetter(Object obj, String propertyName){
        try {
            /**
             * Get object of PropertyDescriptor using property name and class
             * Note: To use PropertyDescriptor on any field/variable, the field must have both `Setter` and `Getter` method.
             */
            PropertyDescriptor objPropertyDescriptor = new PropertyDescriptor(propertyName, obj.getClass());
            /**
             * Get field/variable value using getReadMethod()
             * propertyValue is Object because value can be an Object, Integer, String, etc...
             */
            Method getter = objPropertyDescriptor.getReadMethod();
            getter.setAccessible(true);
            return getter.invoke(obj);

        } catch (IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | IntrospectionException e) {
            /* Java 8: Multiple exception in one catch. Use Different catch block for lower version. */
            log.debug("{}", e);
        }
        return null;
    }
}
