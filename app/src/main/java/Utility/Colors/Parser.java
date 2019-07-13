package Utility.Colors;

import static java.lang.Long.parseLong;

public class Parser {
    public static void validate_As_Color_Val(int colorVal) {
        if (colorVal > 255 || colorVal < 0) {
            throw new IllegalArgumentException(String.valueOf(colorVal) + " is not a valid value. All values must lie between 0-255.");
        }
    }

    public static void validate_As_Rgb_Values(int red, int green, int blue) {
        int[] colors = {red, green, blue};

        for (int color : colors) {
            validate_As_Color_Val(color);
        }
    }

    public static void validate_As_Hex_Val(String hexVal) {
        /*Validates whether a '...' string is a valid hexadecimal value or not.
         * Doesn't accept hex-values with leading '#'*/
        if (!is_Valid_Hex_Val(hexVal)) {
            throw new IllegalArgumentException(hexVal + " is not a valid hexadecimal value.");
        }
    }

    public static void validate_As_Hex_Color_Code(String hexColorCode) {
        if (hexColorCode.length() == 0) {

            throw new IllegalArgumentException("The hex color code string cannot be empty.");
        } else if (!hexColorCode.substring(0, 1).equals("#")) {

            throw new IllegalArgumentException("The hex color code string must begin with a '#'.");
        } else {

            validate_As_Hex_Val(hexColorCode.substring(1));
        }
    }

    public static int[] parse_Hex_As_8_Bit_Color(String hex) {
        /*Groups hex characters into groups of 2 starting from the beginning so hex strings with odd number
         * of digits will be treated as having a trailing 0. Each group will be parsed into an integer and
         * an array of those integers will be returned.*/

        if (hex.length() % 2 != 0) {
            hex += "0";
        }

        int noOfValues = hex.length() / 2;
        int[] values = new int[noOfValues];
        String hexSeq;

        try {

            for (int j = 0; j < noOfValues; j++) {
                hexSeq = hex.substring(j * 2, j * 2 + 2);
                values[j] = Integer.parseInt(hexSeq, 16);

            }
            return values;
        } catch (Exception e) {

            throw new IllegalArgumentException(hex + " is not a valid hexadecimal value.");
        }
    }

    public static String hex(int value) {
        return Integer.toHexString(value).toUpperCase();
    }

    public static Boolean is_Valid_Hex_Val(String hexVal) {
        /*Tests whether a '...' string is a valid hexadecimal value or not.
         * Doesn't accept hex-values with leading '#'*/

        try {
            parseLong(hexVal, 16);
            return true;

        } catch (Exception e) {
            return false;

        }
    }

    public static int[] parse_Rgb_Int(int rgb) {
        int[] rgbArray = new int[3];

        rgbArray[0] = (rgb & 0xff0000) >> 16;
        rgbArray[1] = (rgb & 0xff00) >> 8;
        rgbArray[2] = (rgb & 0xff);

        return rgbArray;
    }
}
