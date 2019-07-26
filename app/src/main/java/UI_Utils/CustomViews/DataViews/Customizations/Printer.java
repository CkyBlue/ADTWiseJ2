package UI_Utils.CustomViews.DataViews.Customizations;

import Utility.Bases.SuperPrinter;

public abstract class Printer extends SuperPrinter<Content, Feed> {
    public Utility.Colors.ColorAdapter.Content getColorAdapter(){
        if (getContent() != null){
            return getContent().getColorAdapter();
        }

        return null;
    }
    public UI_Utils.CustomViews.DataViews.ParamsAdapter.Content getParamsAdapter(){
        if (getContent() != null){
            return getContent().getParamsContent();
        }

        return null;
    }
}
