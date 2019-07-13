package Utility.Colors.Chrome.Cascade;

import Utility.Colors.Chrome.Chrome;
import Utility.Colors.Color;
import Utility.Colors.Values;

class StreamContent extends Cascade {
    StreamContent(Color[] colors, float[] tints, Values defaultLightText, Values defaultDarkText, Values defaultBgColor, int cycleStart, int cycleEnd) {
        super(colors, tints, defaultLightText, defaultDarkText, defaultBgColor, cycleStart, cycleEnd);
    }

    @Override
    protected Values cycle(String content, int position) {
        return cyclicParse(content);
    }
}

class StreamIndex extends Cascade {
    StreamIndex(Color[] colors, float[] tints, Values defaultLightText, Values defaultDarkText, Values defaultBgColor, int cycleStart, int cycleEnd) {
        super(colors, tints, defaultLightText, defaultDarkText, defaultBgColor, cycleStart, cycleEnd);
    }

    @Override
    protected Values cycle(String content, int position) {
        return cyclicParse(position);
    }
}

class Defaults {
    final static Values defaultLightText = Color.alice_blue.createValues();
    final static Values defaultDarkText = Color.black_leather_jacket.createValues();

    final static Values defaultBgColorLight = Color.alice_blue.createValues();
    final static Values defaultBgColorDark = Color.black_leather_jacket.createValues();
}

public enum Cascades {
    Crimson_CA(new Color[]{Color.crimson, Color.indian_red, Color.pink_raspberry, Color.ruber},
            new float[]{0.1f, -0.15f, 0.3f}, true),

    AquaGreens_CA(new Color[]{Color.dark_sea_green, Color.sea_green, Color.wintergreen_dream},
            new float[]{0.2f, -0.15f, 0f}, true),

    DarkPurple_CA(new Color[]{Color.raisin_black},
            new float[]{0.6f, 0.2f, 0f, 0.15f, 0.4f}, true),

    AllInPlains_CA(Color.values(),
            new float[]{0}, true),

    Stripes(new Color[]{Color.isabelline, Color.light_gray},
            new float[]{0}, false);

    private final Chrome chrome;

    Cascades(Chrome chrome){
        this.chrome = chrome;
    }

    Cascades(Color[] colors,
             float[] tints,
             boolean streamContent) {

        if (streamContent){
            this.chrome = new StreamContent(colors,
                    tints,
                    Defaults.defaultLightText,
                    Defaults.defaultDarkText,
                    Defaults.defaultBgColorDark,
                    0,
                    256);

        } else {
            this.chrome = new StreamIndex(colors,
                    tints,
                    Defaults.defaultLightText,
                    Defaults.defaultDarkText,
                    Defaults.defaultBgColorLight,
                    0,
                    -1);
        }
    }

    public Chrome getChrome() {
        return chrome;
    }
}
