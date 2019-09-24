package Utility.Themes;

import Utility.Colors.Cascade.StreamContent;
import Utility.Colors.Cascade.StreamIndex;
import Utility.Colors.Chrome.Content;
import Utility.Colors.Color;

interface Streamers {
    public Content getChrome();
}

public class Cascades {
    public enum ContentStreamers implements Streamers {
        Crimson(new Color[]{Color.crimson, Color.indian_red, Color.pink_raspberry, Color.ruber},
                new float[]{0.1f, -0.15f, 0.3f}),

        AquaGreens(new Color[]{Color.dark_sea_green, Color.sea_green, Color.wintergreen_dream, Color.medium_aqua_marine},
                new float[]{0.25f, -0.2f, 0.05f}),

        DarkPurple(new Color[]{Color.raisin_black},
                new float[]{0.6f, 0.2f, 0f, 0.15f, 0.4f}),

        AllInPlains(Color.values(),
                new float[]{0}),
        ;

        private Content chrome;

        ContentStreamers(Color[] colors,
                         float[] tints) {
            chrome = new StreamContent(colors,
                    tints,
                    Defaults.defaultLightText(),
                    Defaults.defaultDarkText(),
                    Defaults.defaultBgColorDark(),
                    0,
                    -1);
        }

        @Override
        public Content getChrome() {
            return chrome;
        }
    }

    public enum IndexStreamers implements Streamers {
        Stripes(new Color[]{Color.isabelline, Color.light_gray},
                new float[]{0}),
        MonoChromeGradient(new Color[]{Color.black_leather_jacket, Color.light_gray, Color.isabelline},
                new float[]{0});

        private Content chrome;

        IndexStreamers(Color[] colors,
                       float[] tints) {
            chrome = new StreamIndex(colors,
                    tints,
                    Defaults.defaultLightText(),
                    Defaults.defaultDarkText(),
                    Defaults.defaultBgColorDark(),
                    0,
                    -1);
        }

        @Override
        public Content getChrome() {
            return chrome;
        }
    }
}
