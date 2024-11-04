package fiap.fintech.grandfinale.domain.utils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class EnumUtils {

    // Generic method to get an enum from its integer value
    public static <E extends Enum<E>> E fromValue(Class<E> enumClass, int value) {
        try {
            // Iterate through all enum constants
            for (E enumConstant : enumClass.getEnumConstants()) {
                // Use reflection to invoke getValue()
                Method method = enumClass.getMethod("getValue");
                int enumValue = (int) method.invoke(enumConstant);
                if (enumValue == value) {
                    return enumConstant;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to get enum value", e);
        }
        throw new IllegalArgumentException("Invalid value: " + value + " for enum " + enumClass.getSimpleName());
    }

    // Generic method to get all enum values as a list
    public static <E extends Enum<E>> List<E> getAllValues(Class<E> enumClass) {
        return Arrays.asList(enumClass.getEnumConstants());
    }
}