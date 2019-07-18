package Utility.Colors.Cascade;

import Utility.Colors.Chrome.Content;
import Utility.Colors.Chrome.Components;
import Utility.Colors.Color;
import Utility.Colors.Values;

abstract class Cascade extends Content {
    private Values[] values;
    private float[] tints;
    private Values defaultBgColor, defaultLightText, defaultDarkText;
    private int cycleStart, cycleEnd, length;

    protected Values parseTintAndColor(int index) {
        int color = index / tints.length;
        int tint = index % tints.length;

        Values values = this.values[color];
        values.setTintFactor(this.tints[tint]);

        return values;
    }

    private void setPalette(Color[] colors) {
        values = new Values[colors.length];

        int i = 0;

        for (Color color : colors) {
            values[i] = color.createValues();
            i++;
        }
    }

    protected Values cyclicParse(int num) {
        /*For different numbers between start to end, designates colors from palette*/
        /*unless  end < start, in which case it loops indefinitely*/

        int index = -1;

        if ((num >= cycleStart && num <= cycleEnd)) {
            /*If it lies within range*/

            index = (num - cycleStart) % length;
        } else if ((cycleStart > cycleEnd)) {
            /*If no upper-limit exists*/

            if (num < 0) {

                index = Math.abs(length - Math.abs(num)) % length;
            } else {

                index = num % length;
            }
        }

        if (index >= 0) {
            return parseTintAndColor(index);
        }

        return null;

    }

    protected Values cyclicParse(String num) {
        /*For different numbers between start to end, designates colors from palette*/
        /*unless  end < start, in which case it loops indefinitely*/

        /*Checks against non-integer entities*/
        try {

            int value = Integer.parseInt(num);
            return cyclicParse(value);

        } catch (NumberFormatException e) {

            return null;

        }
    }

    protected abstract Values cycle(String content, int position);

    @Override
    public Components fetchComponents(String content, int position) {
        Values bgValues = cycle(content, position);

        if (bgValues == null) {
            bgValues = defaultBgColor;
        }

        Values textValues;

        if (bgValues.isLight()) {
            textValues = defaultDarkText;

        } else {
            textValues = defaultLightText;

        }

        return new Components(textValues.getHexARGB(), bgValues.getHexARGB(), 1);
    }

    public Cascade(Color[] colors,
                   float[] tints,
                   Values defaultLightText,
                   Values defaultDarkText,
                   Values defaultBgColor,
                   int cycleStart,
                   int cycleEnd) {
        this.defaultBgColor = defaultBgColor;

        this.defaultDarkText = defaultDarkText;
        this.defaultLightText = defaultLightText;

        this.cycleStart = cycleStart;
        this.cycleEnd = cycleEnd;

        this.setPalette(colors);
        this.tints = tints;

        this.length = (this.tints.length * values.length);

        if (this.length == 0) {
            throw new IllegalArgumentException("The number of tints or colors for this Cascade is 0.");
        }

    }
}
