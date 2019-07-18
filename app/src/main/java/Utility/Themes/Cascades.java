package Utility.Themes;

import Utility.Colors.Cascade.StreamContent;
import Utility.Colors.Cascade.StreamIndex;
import Utility.Colors.Chrome.Content;
import Utility.Colors.Color;

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

    private final Content chrome;

    Cascades(Content chrome){
        this.chrome = chrome;
    }

    Cascades(Color[] colors,
             float[] tints,
             boolean streamContent) {

        if (streamContent){
            this.chrome = new StreamContent(colors,
                    tints,
                    Defaults.defaultLightText(),
                    Defaults.defaultDarkText(),
                    Defaults.defaultBgColorDark(),
                    0,
                    -1);

        } else {
            this.chrome = new StreamIndex(colors,
                    tints,
                    Defaults.defaultLightText(),
                    Defaults.defaultDarkText(),
                    Defaults.defaultBgColorLight(),
                    0,
                    -1);
        }
    }

    public Content getChrome() {
        return chrome;
    }
}
