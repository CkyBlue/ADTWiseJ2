package Utility.Colors;

public class Components {
        private String text_color = "";
        private String bg_color = "";
        private int opacity = 1;

        public Components(String text_color, String bg_color, int opacity) {
            this.text_color = text_color;
            this.bg_color = bg_color;
            this.opacity = opacity;
        }

        public String getText_color() {
            return text_color;
        }

        public String getBg_color() {
            return bg_color;
        }

        public int getOpacity() {
            return opacity;
        }
    }