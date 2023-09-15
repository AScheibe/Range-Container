import java.util.TreeSet;

/**
 * A class representing a collection of non-overlapping ranges.
 * This container automatically merges or splits ranges as numbers are inserted
 * or removed.
 */
public class Container {
    private TreeSet<Range> ranges; // base data structure to hold all ranges

    /**
     * Initializes a new instance of the Container class.
     */
    public Container() {
        ranges = new TreeSet<Range>();
    }

    /**
     * Inserts a new number into the container. If the number is adjacent to an
     * existing range, the range is extended. If it is inside an existing range,
     * nothing changes.
     *
     * 
     * @param newNum The number to be inserted.
     */
    public void insert(int newNum) {
        // This will ask as a "holder" to be used for comparison or direct
        // insertion if there is no adjacent "ends" in current ranges
        Range range = new Range(newNum, newNum);

        // Add range if empty
        if (ranges.isEmpty()) {
            ranges.add(range);
            return;
        }

        // Obtain next lowest range (or existing range that encapsulates) given a range
        Range lower = ranges.floor(range);

        // Obtain next highest range given range as input
        Range higher = ranges.ceiling(range);

        // If lower contains new num, range already exists
        if (lower != null && lower.contains(newNum)) {
            return;
        }

        // If end of lower range is adjacnet to new num, expand end of range
        if (lower != null && lower.getEnd() == newNum - 1) {
            lower.setEnd(newNum);
            range = null; // no need to add new range
        }

        // If start of higher range is adjacent to new num, expand start of range
        if (higher != null && higher.getStart() == newNum + 1) {
            higher.setStart(newNum);

            // If lower range end is equal to higher range start, merge the two
            if (lower != null && lower.getEnd() == higher.getStart()) {
                int lower_start = lower.getStart();
                int higher_end = higher.getEnd();

                // Better to create a new range to avoid unforseen mem conflicts due to removal.
                // Alternative would be to just expand higher or lower and remove the other but
                // just felt more comfortable doing this way.
                
                range = new Range(lower_start, higher_end); 

                ranges.remove(lower);
                ranges.remove(higher);
            }

        }

        if(range != null){
            ranges.add(range);
        }
    }

    /**
     * Removes a number from the container. If the number is at the boundary of a
     * range, the range is adjusted. If it is inside a range, the range is split
     * into two.
     *
     * @param num The number to be removed.
     */
    public void remove(int num) {
        if (!contains(num)) {
            return;
        }

        Range holdingRange = new Range(num, num); // Used just for comparison

        // We utilize floor as this will capture the range if it is equal to the holding
        // range or it is encapsulated inside an existing range
        Range currentRange = ranges.floor(holdingRange);

        // Handle only one number in range -> if num at start of end of range -> if num
        // in middle of range
        if (currentRange.equals(holdingRange)) {
            ranges.remove(currentRange);
            return;
        } else if (currentRange.getStart() == num) {
            currentRange.setStart(num + 1);
            return;
        } else if (currentRange.getEnd() == num) {
            currentRange.setEnd(num - 1);
            return;
        } else {
            split(num, currentRange);
        }
    }

    /**
     * Splits a range into two parts at the specified number. This is used
     * internally when removing a number from the middle of a range.
     *
     * @param newNum The number at which the range should be split.
     * @param range  The range to be split.
     */
    private void split(int newNum, Range range) {
        int oldStart = range.getStart();
        int oldEnd = range.getEnd();

        ranges.remove(range);
        ranges.add(new Range(oldStart, newNum - 1));
        ranges.add(new Range(newNum + 1, oldEnd));
    }

    /**
     * Checks if the specified number is part of any range in the container.
     *
     * @param num The number to check.
     * @return True if the number is part of a range, false otherwise.
     */
    public boolean contains(int num) {
        Range holdingRange = ranges.floor(new Range(num, num));
        return (holdingRange != null && holdingRange.contains(num));
    }

    /**
     * Provides a string representation of all ranges in the container.
     *
     * @return A string representation of the ranges, each on a new line.
     */
    public String getContents() {
        StringBuilder str = new StringBuilder();

        for (Range r : ranges) {
            str.append(r).append("\n");
        }

        return str.toString();
    }
}
