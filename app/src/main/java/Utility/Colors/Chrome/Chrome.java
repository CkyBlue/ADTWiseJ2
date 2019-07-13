package Utility.Colors.Chrome;

public abstract class Chrome {
    /*TODO Make a sub-class that can handle syntax highlighting requirements*/
    /*TODO Test updating adapters, deep-adapters, data*/

    /*Returns a Component object which carries textColor, BGColor & Opacity information based on text content and position*/

    public abstract Components fetchComponents(String content, int position);
}