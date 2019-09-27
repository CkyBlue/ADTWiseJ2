package Utility.SourceCode.Unit;

import com.example.ckyblue.adtwisei4.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import Utility.Bases.SuperContent;
import Utility.Key;
import Utility.SourceCode.FormattingKey;
import Utility.SourceCode.Parser;
import Utility.SourceCode.Tracker;
import Utility.Utilities;

public class Content extends SuperContent<Feed> {
    public enum HighLightingLevel implements Key {
        Primary(FormattingKey.Highlighting_A), // Preview highlighting
        Secondary(FormattingKey.Highlighting_B), // Execution-highlighting
        Tertiary(FormattingKey.Highlighting_C); // Misc-highlighting

        private FormattingKey formattingKey;

        HighLightingLevel(FormattingKey formattingKey) {
            this.formattingKey = formattingKey;
        }

        public FormattingKey getFormattingKey() {
            return formattingKey;
        }
    }

    private String contentText;
    private int scopeCount = 0;

    private Tracker activeMask;
    private final Tracker lineBoundaries = new Tracker();

    private final HashMap<String, Tracker> maskTrackers = new HashMap<>();
    private final HashMap<FormattingKey, Tracker> formattingTrackers = new HashMap<>();
    private final Set<FormattingKey> maskedKeys = new HashSet<FormattingKey>();

    public int getLineCount() {
        return this.lineBoundaries.getLength();
    }

    public Tracker getLineBoundaries() {
        return this.lineBoundaries;
    }

    public int getScopeCount() {
        return this.scopeCount;
    }

    public void setScopeCount(int scopeCount) {
        this.scopeCount = scopeCount;

        if (getFeed() != null) {
            getFeed().scopeChanged();
        }
        this.alertComposingGroupOfChangeToConstituent();
    }

    public void registerMask(String key, Tracker mask) {
        this.maskTrackers.put(key, mask);
    }

    public void setMaskedKeys(Set<FormattingKey> maskedKeys) {
        this.maskedKeys.clear();

        if (maskedKeys != null) {
            this.maskedKeys.addAll(maskedKeys);
        }

        if (getFeed() != null) {
            getFeed().maskChanged();
        }
        this.alertComposingGroupOfChangeToConstituent();
    }

    public Set<FormattingKey> getMaskedKeys() {
        return this.maskedKeys;
    }

    public void setActiveMask(String key) {
        this.activeMask = this.maskTrackers.get(key);

        if (getFeed() != null) {
            getFeed().maskChanged();
        }
        this.alertComposingGroupOfChangeToConstituent();
    }

    public Tracker getActiveMask() {
        return this.activeMask;
    }

    public void buildFormatting(Parser parser) {
        HashMap<FormattingKey, Tracker> trackersMap = parser.parseString(getText());
        boolean feedExists = (getFeed() != null);

        for (FormattingKey key : trackersMap.keySet()) {
            this.formattingTrackers.put(key, trackersMap.get(key));

            if (feedExists) {
                getFeed().formattingChanged(key);
            }
        }
    }

    public Tracker getFormatting(FormattingKey key) {
        return this.formattingTrackers.get(key);
    }

    public void highlight(int[] lineNumbers) {
        highlight(lineNumbers, HighLightingLevel.Primary);
    }

    public void highlight(int[] lineNumbers, HighLightingLevel levelKey) {
        Tracker highlighting = new Tracker();

        if (lineNumbers != null) {
            int start, end;

            for (int lineNum : lineNumbers) {
                if (lineNum > 0 && lineNum <= getLineCount()) {
                    start = getLineBoundaries().getStart(lineNum - 1);
                    end = getLineBoundaries().getEnd(lineNum - 1);

                    highlighting.addMarkers(start, end);
                }
            }
        }

        FormattingKey formattingKey = levelKey.getFormattingKey();
        Logger.log("Highlighter", "highlight() called with formattingKey = [" + formattingKey + "]");

        this.formattingTrackers.put(formattingKey, highlighting);

        if (getFeed() != null) {
            getFeed().formattingChanged(formattingKey);
        }
        this.alertComposingGroupOfChangeToConstituent();
    }

    public void setText(String content) {
        /*Adding line count and padding*/

        String[] lines;

        if (content == null) {
            lines = new String[0];

        } else {
            lines = content.split("\\r?\\n");

        }

        Tracker lineCounts;
        lineCounts = new Tracker();

        this.lineBoundaries.clear();
        this.maskedKeys.clear();
        this.formattingTrackers.clear();

        /*Gets length of the longest single line*/
        int maxLineLength = 0;
        for (String line : lines) {
            if (line.length() > maxLineLength) {
                maxLineLength = line.length();
            }
        }

        int noOfLines = lines.length;
        int max_digits_in_lineCount = String.valueOf(noOfLines).length();

        StringBuilder newContent = new StringBuilder();

        int count = 0, start;
        String lineCountStr;

        /*Builds content text*/
        for (String line : lines) {
            start = newContent.length();
            lineCounts.addMarkers(start, start + max_digits_in_lineCount);

            count++;
            lineCountStr = Utilities.padLeft(String.valueOf(count), max_digits_in_lineCount, ' ');

            newContent.append(lineCountStr);
            newContent.append(" ");

            newContent.append(line);
            /*Pads each line to the length of the longest line from the right*/
            for (int x = line.length(); x < maxLineLength; x++) {
                newContent.append(" ");
            }

            this.lineBoundaries.addMarkers(start + max_digits_in_lineCount + 1, newContent.length());

            if (count < noOfLines) {
                newContent.append("\n");
            }
        }

        this.contentText = newContent.toString();

        this.formattingTrackers.put(FormattingKey.Base, this.lineBoundaries);
        this.formattingTrackers.put(FormattingKey.Line_Count, lineCounts);

        this.maskedKeys.clear();
        this.activeMask = null;
        this.scopeCount = 0;

        if (getFeed() != null) {
            getFeed().feedRebuilt();
        }
        this.alertComposingGroupOfChangeToConstituent();
    }

    public String getText() {
        return this.contentText;
    }
}
