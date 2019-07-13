package Utility.Colors.Chrome;

import Utility.Colors.Color;
import Utility.Colors.Values;

public class Plain extends Chrome {
    private static Plain single_instance = null;

    private static final Components components;

    static {
        Values values = Color.light_gray.createValues();
        values.setTintFactor(0.6f);
        values.setAlpha(60);

        components = new Components(Color.black_leather_jacket.getHexARGB(),
            values.getHexARGB(),
            1);
    }

    private Plain() {
    }

    public static Plain getInstance(){
        if (single_instance == null){
            single_instance = new Plain();
        }
        return single_instance;
    }

    @Override
    public Components fetchComponents(String content, int position) {
        return components;
    }
}
