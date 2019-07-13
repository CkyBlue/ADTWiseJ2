package Utility.Colors;

public enum Color {

    gray("#808080"),
    light_gray("#D3D3D3"),
    black("#000000"),
    white("#FFFFFF"),
    nickel("#6C756B"),
    pewter_blue("#93ACB5"),
    crimson("#DC143C"),
    firebrick("#B22222"),
    salmon("#FA8072"),
    golden_rod("#DAA520"),
    tomato("#FF6347"),
    indian_red("#CD5C5C"),
    dark_khaki("#BDB76B"),
    sea_green("#2E8B57"),
    medium_aqua_marine("#66CDAA"),
    turquoise("#40E0D0"),
    cadet_blue("#5F9EA0"),
    steel_blue("#4682B4"),
    indigo("#4B0082"),
    slate_blue("#6A5ACD"),
    dark_magenta("#8B008B"),
    medium_orchid("#BA55D3"),
    dark_orchid("#9932CC"),
    thistle("#D8BFD8"),
    plum("#DDA0DD"),
    violet("#EE82EE"),
    light_steel_blue("#B0C4DE"),
    baby_blue_eyes("#96C5F7"),
    fresh_air("#A9D3FF"),
    alice_blue("#F2F4FF"),
    pale_magenta_pink("#FF99C9"),
    electric_blue("#58FCEC"),
    light_cyan("#E1F2FE"),
    wild_blue_yonder("#B2B1CF"),
    light_cornflower_blue("#98D2EB"),
    black_leather_jacket("#303A2B"),
    dark_sea_green("#8DB38B"),
    wintergreen_dream("#56876D"),
    dark_vanilla("#BDBEA9"),
    dark_gunmetal("#0D1F2D"),
    paynes_grey("#546A7B"),
    cadet_grey("#9EA3B0"),
    new_york_pink("#CF8E80"),
    pastel_brown("#916953"),
    pink_lace("#FCDDF2"),
    fuchsia_crayola("#C455A8"),
    dust_storm("#E5CDC8"),
    medium_sky_blue("#65DEF1"),
    bone("#DCE2C8"),
    pink_raspberry("#9C0D38"),
    ruber("#CE5374"),
    purple_navy("#5D5D81"),
    deep_koamaru("#3B3355"),
    pale_brown_egg_blue("#A8DCD1"),
    tyrian_purple("#700548"),
    isabelline("#F7F0F0"),
    purple_taupe("#484349"),
    cyber_grape("#613F75"),
    glaucous("#7D82B8"),
    light_coral("#EF798A"),
    raisin_black("#362023"),
    rick_black("#080705");

    private String hexRGB;

    Color(String hexRGB) {
        if (hexRGB.length() != 7){
            throw new IllegalArgumentException(hexRGB + " is not of the appropriate length. \n" +
                    "A 7 character long '#RRGGBB' value is required");
        }
        Parser.validate_As_Hex_Color_Code(hexRGB);
        this.hexRGB = hexRGB;
    }

    public String getHexARGB() {
        return "#FF" + hexRGB.substring(1).toUpperCase();
    }

    public Values createValues() {
        return new Values(hexRGB);
    }
}

