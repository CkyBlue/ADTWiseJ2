package Utility.Data;

public class Utilities {
    public static Object getTypeDefault(Type type) {
        Object initialValue = null;

        switch (type) {
            case INTEGER: {
                initialValue = 0;
                break;
            }
            case STRING: {
                initialValue = "";
                break;
            }
            case BOOLEAN: {
                initialValue = false;
                break;
            }
            case FLOAT: {
                initialValue = 0.0f;
                break;
            }
        }

        return initialValue;
    }

    public static void validateAsNonNullData(Object value) {
        if (value == null) {
            throw new IllegalStateException("The values stored in Data objects are meant to be maintained as non-null, " +
                    "including in the case of Strings.");

        }
    }
}
