package Utility.Colors.Cascade;

import Utility.Colors.Color;
import Utility.Colors.Values;

public class StreamIndex extends Cascade {
    public StreamIndex(Color[] colors,
                       float[] tints,
                       Values defaultLightText,
                       Values defaultDarkText,
                       Values defaultBgColor,
                       int cycleStart,
                       int cycleEnd) {
        super(colors, tints, defaultLightText, defaultDarkText, defaultBgColor, cycleStart, cycleEnd);
    }

    @Override
    protected Values cycle(String content, int position) {
        return cyclicParse(position);
    }
}
