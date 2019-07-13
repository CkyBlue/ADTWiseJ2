package Utility.Colors.Chrome;

import Utility.Colors.Color;

public class PlainHeader extends Chrome {
    private static PlainHeader single_instance = null;

    private static final Components components = new Components(Color.isabelline.getHexARGB(),
            Color.black_leather_jacket.getHexARGB(),
            1);

    private PlainHeader() {
    }

    public static PlainHeader getInstance() {
        if (single_instance == null) {
            single_instance = new PlainHeader();
        }
        return single_instance;
    }

    @Override
    public Components fetchComponents(String content, int position) {
        return components;
    }
}
