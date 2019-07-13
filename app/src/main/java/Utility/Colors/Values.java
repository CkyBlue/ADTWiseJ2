package Utility.Colors;

import static Utility.Colors.Parser.hex;
import static Utility.Colors.Parser.is_Valid_Hex_Val;
import static Utility.Colors.Parser.parse_Hex_As_8_Bit_Color;
import static Utility.Colors.Parser.validate_As_Color_Val;
import static Utility.Colors.Parser.validate_As_Rgb_Values;

public class Values {
    private int red, green, blue, alpha;
    private float tintFactor;

    private static final float lightLuminanceThreshold = 0.7f;

    public Values(int red, int green, int blue) {
        setRGB(red, green, blue);
        setAlpha(255);
        setTintFactor(0);
    }

    public Values(String hexRGB) {
        if (!hexRGB.substring(0, 1).equals("#")
                || !(hexRGB.length() == 7)
                || !is_Valid_Hex_Val(hexRGB.substring(1))) {
            throw new IllegalArgumentException("An RGB hexadecimal color code '#RRGGBB' is expected. " + hexRGB + " is not valid.");
        }

        setAlpha(255);
        setTintFactor(0);

        int red, green, blue;
        int[] values = parse_Hex_As_8_Bit_Color(hexRGB.substring(1));

        red = values[0];
        green = values[1];
        blue = values[2];

        setRGB(red, green, blue);
    }

    public String getHexARGB() {
        int[] tintedRgb = applyTint();
        int[] argb = {this.alpha, tintedRgb[0], tintedRgb[1], tintedRgb[2]};
        StringBuilder hexARGB = new StringBuilder();

        hexARGB.append("#");
        String hexCode;

        for (int val : argb) {
            hexCode = hex(val);

            if (hexCode.length() == 1) {
                hexCode = "0" + hexCode;
            }
            hexARGB.append(hexCode);
        }

        return hexARGB.toString();
    }

    public void setRGB(int red, int green, int blue) {
        validate_As_Rgb_Values(red, green, blue);

        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void setAlpha(int alpha) {
        validate_As_Color_Val(alpha);
        this.alpha = alpha;
    }

    public void setTintFactor(float tintFactor) {
        validateAsTintVal(tintFactor);
        this.tintFactor = tintFactor;
    }

    private static void validateAsTintVal(float value) {
        if (value > 1 || value < -1) {
            throw new IllegalArgumentException(value + " is not a valid tint value. \n" +
                    "The value must be between -1 an 1 (0 to 1 for a lightening tint, 0 to -1 for a darkening shade.)");
        }
    }

    private static int[] applyTint(int red, int green, int blue, float tintFactor) {
        validate_As_Rgb_Values(red, green, blue);
        validateAsTintVal(tintFactor);

        int[] rgb = new int[3];

        if (tintFactor > 0) {

            rgb[0] = (int) (red + (255 - red) * tintFactor);
            rgb[1] = (int) (green + (255 - green) * tintFactor);
            rgb[2] = (int) (blue + (255 - blue) * tintFactor);

        } else {

            rgb[0] = (int) (red * (1 + tintFactor));
            rgb[1] = (int) (green * (1 + tintFactor));
            rgb[2] = (int) (blue * (1 + tintFactor));

        }

        return rgb;
    }

    public Boolean isLight() {
        int[] tintedRgb = applyTint();
        float luminance = calcLuminance(tintedRgb[0], tintedRgb[1], tintedRgb[2]);

        return luminance > Values.lightLuminanceThreshold;
    }

    private int[] applyTint() {
        return applyTint(this.red, this.green, this.blue, this.tintFactor);
    }

    private static float calcLuminance(int red, int green, int blue) {
        return (red * 0.299f + green * 0.587f + blue * 0.114f) / 256;
    }


}
