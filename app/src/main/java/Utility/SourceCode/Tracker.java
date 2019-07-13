package Utility.SourceCode;

import java.util.ArrayList;

/**
 * The tracker class is used to store boundaries for areas within a block of string, for example, the regions
 * containing the line count, the regions of the lines intended to be highlighted in a sourcecode, the regions
 * within which a function is defined etc. The boundary information is used when adding formatting to these
 * regions. The length of a tracker indicates the number of start and end marker pairs have been registered to
 * the tracker.
 */

public class Tracker {
    private ArrayList<Integer> starts = new ArrayList<Integer>();
    private ArrayList<Integer> ends = new ArrayList<Integer>();

    private int length = 0;

    /**
     * Registers a new set of region boundaries or 'markers'. Throws an exception if the position marker indicating
     * the start of a region is behind the position marker indicating the end. Each marker pair is associated with
     * an index that indicates the order in which the markers were registered.
     */
    public void addMarkers(int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException("The argument for 'start', " + String.valueOf(start) +
                    ", has a greater value than the argument for 'end', " + String.valueOf(end) + ".");
        }
        starts.add(start);
        ends.add(end);
        length++;
    }

    /**
     * Removes an existing marker for a particular index value if the index is valid. If the index is invalid,
     * throws an Exception.
     */
    public void removeMarkers(int index) {
        validateIndex(index);

        starts.remove(index);
        ends.remove(index);

        length--;
    }

    /** Gets the length of the tracker, or the number of registered markers.
     * @return int indicating length of tracker*/
    public int getLength() {
        return length;
    }

    /** Gets the marker indicating start of a region for a particular index value.
     * @return int indicating start marker*/
    public int getStart(int index) {
        validateIndex(index);
        return starts.get(index);
    }

    /** Gets the marker indicating end of a region for a particular index value.
     * @return int indicating end marker*/
    public int getEnd(int index) {
        validateIndex(index);
        return ends.get(index);
    }

    /** Clears all existing markers and resets length of tracker.*/
    public void clear() {
        starts.clear();
        ends.clear();

        length = 0;
    }

    /** Validates index as a positive value smaller that the length of the tracker.*/
    private void validateIndex(int index) {
        if (index >= this.length || index < 0) {
            throw new IndexOutOfBoundsException("The length of the tracker is " +
                    length + ". " + index + " is not a valid index.");
        }
    }
}