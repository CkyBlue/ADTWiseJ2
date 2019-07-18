package Utility.Themes;

import Utility.Colors.Chrome.Content;
import Utility.Colors.Chrome.Components;
import Utility.Colors.Color;
import Utility.Colors.Values;

public class Defaults {
    public static Values defaultLightText() {
        return Color.alice_blue.createValues();
    }

    public static Values defaultDarkText() {
        return Color.black_leather_jacket.createValues();
    }

    public static Values defaultBgColorLight() {
        return Color.alice_blue.createValues();
    }

    public static Values defaultBgColorDark() {
        return Color.black_leather_jacket.createValues();
    }

    public final static Content chrome = new Content() {
        private Components components;

        {
            Values values = Color.light_gray.createValues();
            values.setTintFactor(0.6f);
//            values.setAlpha(60);

            components = new Components(Color.black_leather_jacket.getHexARGB(),
                    values.getHexARGB(),
                    1);
        }

        @Override
        public Components fetchComponents(String content, int position) {
            return components;

        }
    };
    public final static Content headerChrome = new Content() {
        private Components components = new Components(Color.isabelline.getHexARGB(),
                Color.black_leather_jacket.getHexARGB(), 1);

        @Override
        public Components fetchComponents(String content, int position) {
            return components;

        }
    };
}
