/**
 * This class provides test methods to validate the functionality of the
 * Container class. It includes tests for insertion, removal, and containment of
 * numbers.
 * 
 */
public class ContainerTest {

    /**
     * The main entry point for executing the test cases.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        ContainerTest test = new ContainerTest();

        test.testContains();
        test.testInsert();
        test.testRemove();

        System.out.println("\nAll tests passed!\n");
    }

    /**
     * Tests the insertion method of the Container class. It checks if numbers are
     * correctly added and can be retrieved after
     * insertion.
     */
    public void testInsert() {
        Container container = new Container();

        // Test 1: Single Number Insertion
        container.insert(5);
        assert container.contains(5);
        assert container.getContents().equals("[5 - 5]\n");

        // Test 2: Consecutive Number Insertion (ascending)
        for (int i = 6; i <= 10; i++) {
            container.insert(i);
            assert container.contains(i);
        }

        assert container.getContents().equals("[5 - 10]\n");

        // Test 3: Consecutive Number Insertion (descending)
        for (int i = 4; i >= 0; i--) {
            container.insert(i);
            assert container.contains(i);
        }

        assert container.getContents().equals("[0 - 10]\n");

        // Test 4: Non-Consecutive Number Insertion
        container.insert(20);
        container.insert(30);
        assert container.contains(20) && !container.contains(21);
        assert container.contains(30) && !container.contains(29);
        assert container.getContents().equals("[0 - 10]\n[20 - 20]\n[30 - 30]\n");

        // Test 5: Inserting Numbers Inside Existing Range
        container.insert(7);
        assert container.contains(7);

        container.insert(8);
        assert container.contains(8);

        // Contents should remain the same as before
        assert container.getContents().equals("[0 - 10]\n[20 - 20]\n[30 - 30]\n");

        // Test 6: Inserting Numbers to Expand Existing Ranges
        container.insert(11);
        assert container.contains(11);

        container.insert(19);
        assert container.contains(19) && !container.contains(18);
        assert container.getContents().equals("[0 - 11]\n[19 - 20]\n[30 - 30]\n");

        // Test 7: Inserting Number to Merge Ranges
        container.insert(22);
        container.insert(21);
        assert container.contains(21) && container.contains(20) && container.contains(22);
        assert container.getContents().equals("[0 - 11]\n[19 - 22]\n[30 - 30]\n");
    }

    /**
     * Tests the removal method of the Container class. It checks if numbers are
     * correctly removed and are no longer available after removal.
     */
    public void testRemove() {
        Container container = new Container();

        // 1. Removing the only number in a range.
        container.insert(5);
        container.remove(5);
        assert !container.contains(5);

        // 2. Removing a number from the beginning, middle, and end of a range.
        for (int i = 1; i <= 10; i++) {
            container.insert(i);
        }
        container.remove(1);
        assert !container.contains(1);

        container.remove(5);
        String suspectedContents = "[2 - 4]\n" + "[6 - 10]\n";
        assert container.getContents().equals(suspectedContents);
        assert !container.contains(5);

        container.remove(10);
        assert !container.contains(10);

        // 3. Removing a number that isn't in the container.
        container.remove(50);

        // 4. Inserting and removing multiple consecutive numbers.
        for (int i = 20; i <= 30; i++) {
            container.insert(i);
        }
        for (int i = 20; i <= 30; i++) {
            container.remove(i);
            assert !container.contains(i);
        }

        // 5. Removing a number from a large range.
        for (int i = 40; i <= 100; i++) {
            container.insert(i);
        }

        container.remove(70);
        assert !container.contains(70);

        container = new Container();

        container.insert(40);
        container.insert(41);
        container.insert(43);
        container.insert(44);
        container.remove(42); // This shouldn't affect the ranges since 42 is not inserted.

        suspectedContents = "[40 - 41]\n" + "[43 - 44]\n";
        assert container.getContents().equals(suspectedContents);

        container.insert(42); // This should merge the two ranges.
        suspectedContents = "[40 - 44]\n";
        assert container.getContents().equals(suspectedContents);

        // 7. Removing numbers from the beginning and end of a large range.
        for (int i = 50; i <= 80; i++) {
            container.insert(i);
        }

        container.remove(50);
        container.remove(80);
        suspectedContents = "[40 - 44]\n" + "[51 - 79]\n";
        assert container.getContents().equals(suspectedContents);

        // 8. Removing all numbers to empty the container.
        for (int i = 1; i <= 100; i++) {
            container.remove(i);
        }

        suspectedContents = "";
        assert container.getContents().equals(suspectedContents);
    }

    /**
     * Tests the contains method of the Container class. It checks if the class can
     * correctly identify if a number is contained within its ranges or not.
     */
    public void testContains() {
        Container container = new Container();
        container.insert(5);
        container.insert(6);

        assert container.contains(5);
        assert container.contains(6);
        assert !container.contains(4);
    }
}
