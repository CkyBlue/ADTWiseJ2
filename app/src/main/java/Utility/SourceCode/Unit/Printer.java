package Utility.SourceCode.Unit;

import java.util.ArrayList;

import Utility.SourceCode.FormattingKey;
import Utility.Bases.SuperPrinter;

public abstract class Printer extends SuperPrinter <Content, Feed> {
    private final ArrayList<FormattingKey> formattingKeys = new ArrayList<>();

    public Boolean maskedOut(int start, int end) {
        if (getFeed() == null || getContent().getActiveMask() == null) {
            return false;
        }

        for (int i = 0; i < getContent().getActiveMask().getLength(); i++) {
            if (start >= getContent().getActiveMask().getStart(i) &&
                    end <= getContent().getActiveMask().getEnd(i)) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<FormattingKey> getFormattingKeys() {
        return formattingKeys;
    }

    public abstract void notifyOfFormattingChange(FormattingKey key);

    public abstract void notifyOfScopeChange();

    public abstract void notifyOfMaskChange();

    public abstract void buildFormatting(FormattingKey key);

    public abstract void removeFormatting(FormattingKey key);

    public void removeAllFormatting() {
        for (FormattingKey key : getFormattingKeys()) {
            removeFormatting(key);
        }
    }

    public void buildAllFormatting() {
        for (FormattingKey key : getFormattingKeys()) {
            buildFormatting(key);
        }
    }
}
