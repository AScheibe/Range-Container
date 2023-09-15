/**
 * Represents a range of integers with inclusive boundaries, implementing
 * the Comparable interface for easy ordering in collections.
 */
public class Range implements Comparable<Range> {
    private int start; // start of range
    private int end; // end of range

    /**
     * Constructs a new Range object with specified start and end values.
     *
     * @param start The starting value of the range.
     * @param end   The ending value of the range.
     */
    public Range(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Sets the starting value of the range.
     *
     * @param newStart The new starting value.
     */
    public void setStart(int newStart) {
        this.start = newStart;
    }

    /**
     * Sets the ending value of the range.
     *
     * @param newEnd The new ending value.
     */
    public void setEnd(int newEnd) {
        this.end = newEnd;
    }

    /**
     * Retrieves the starting value of the range.
     *
     * @return The starting value.
     */
    public int getStart() {
        return this.start;
    }

    /**
     * Retrieves the ending value of the range.
     *
     * @return The ending value.
     */
    public int getEnd() {
        return this.end;
    }

    /**
     * Checks if the given integer falls within this range.
     *
     * @param num The integer to check.
     * @return True if the integer is within the range, false otherwise.
     */
    public boolean contains(int num) {
        return (num >= this.start && num <= this.end);
    }

    /**
     * Overriding equals() method to compare given range to another range for
     * equality.
     *
     * @param obj The object (range) to compare to.
     * @return True if both ranges have the same start and end, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Range)) {
            return false;
        }

        Range range = (Range) obj;
        return this.end == range.end && this.start == range.start;
    }

    /**
     * Overriding compateTo() to compare given range to another range to
     * establish an order.
     * 
     * This is essential for storing the Range objects in ordered collections.
     *
     * @param range The range to compare to.
     * @return -1 if this range ends before the other starts, 1 if it starts after
     *         the other ends,
     *         0 if they overlap or are adjacent.
     */
    @Override
    public int compareTo(Range range) {
        if (end < range.start) {
            return -1;
        } else if (start > range.end) {
            return 1;
        }

        return 0;
    }

    /**
     * Overriding toString() to access a string representation of the range.
     *
     * @return A string in the format "[start - end]".
     */
    @Override
    public String toString() {
        return "[" + this.start + " - " + this.end + "]";
    }
}