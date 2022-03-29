package bobert.utils;

import java.lang.reflect.Field;

public class ReflectionUtils {

    public static <FieldT, InstanceT> void setPrivateField(InstanceT instance, Class<? super InstanceT> clz, String fieldName, FieldT newValue) {
        try {
            Field field = clz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(instance, newValue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public static <FieldT, InstanceT> FieldT getPrivateField(InstanceT instance, Class<? super InstanceT> clz, String fieldName) {
        try {
            Field field = clz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (FieldT) field.get(instance);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
