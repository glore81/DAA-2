public class Tests {

    public static void main(String[] args) {
        System.out.println("Running Correctness Tests...\n");

        testDynamicArray();
        testLinkedList();
        testMinHeap();

        System.out.println("All tests passed successfully!");
    }

    private static void assertEquals(Object expected, Object actual, String message){
        if(!expected.equals(actual)){
            throw new AssertionError(message + "| Expected: "+ expected +"| Actual: "+ actual);
        }
    }
    private static void assertTrue(boolean condition, String message){
        if(!condition){
            throw new AssertionError(message);
        }
    }

    private static void testDynamicArray() {
        System.out.println("Testing Dynamic Array... ");
        DynamicArray arr = new DynamicArray();

        assertEquals(0, arr.size(), "Array should be empty initially");

        arr.add(32);
        assertEquals(1, arr.size(), "Size should be 1 after addition");
        assertEquals(32, arr.get(0), "Element at index 0 should be 32");

        arr.add(10);
        arr.add(32);
        arr.add(5);

        assertEquals(4, arr.size(), "Size should be 4");
        assertTrue(arr.contains(32), "Array should contain 32");
        assertTrue(arr.contains(5), "Array should contain 5");

        arr.add(0,99);
        assertEquals(99, arr.get(0), "Element at index 0 should be 99");

        arr.add(arr.size(), 100);
        assertEquals(100, arr.get(arr.size()-1), "Last element should be 100");

        arr.remove(0);
        assertEquals(32, arr.get(0), "Element at index 0 should shift to 32");

        try {
            arr.get(-1);
            throw new AssertionError("Should throw IndexOutOfBoundsException for negative index");
        } catch (IndexOutOfBoundsException expected) {}

        try {
            arr.get(arr.size());
            throw new AssertionError("Should throw IndexOutOfBoundsException for index == size");
        } catch (IndexOutOfBoundsException expected) {}

        System.out.println("Dynamic array test passed!");
        System.out.println();
    }

    private static void testLinkedList(){
        System.out.println("Testing LinkedList... ");
        LinkedList list = new LinkedList();

        assertEquals(0, list.size(), "List should be empty initially");

        list.add(10);
        assertEquals(1, list.size(), "Size should be 1");
        assertEquals(10, list.get(0), "Element at index 0 should be 10");

        list.add(20);
        list.add(10);
        list.add(30);
        assertEquals(4, list.size(), "Size should be 4");

        list.add(0, 5);
        assertEquals(5, list.get(0), "Head should be 5");

        list.remove(0);
        assertEquals(10, list.get(0), "Head should be 10");

        list.remove(list.size()-1);
        assertEquals(10, list.get(list.size()-1), "Tail should be 10");

        try {
            list.get(-1);
            throw new AssertionError("Should throw IndexOutOfBoundsException for negative index");
        } catch (IndexOutOfBoundsException expected) {}

        try {
            list.remove(100);
            throw new AssertionError("Should throw IndexOutOfBoundsException for out of bounds index");
        } catch (IndexOutOfBoundsException expected) {}

        System.out.println("Linked list test passed");
    }

    private static void testMinHeap() {
        System.out.print("Testing MinHeap... ");
        MinHeap heap = new MinHeap();

        assertEquals(0, heap.size(), "Heap should be empty initially");

        heap.insert(15);
        assertEquals(15, heap.peekMin(), "Peek should return 15");

        heap.insert(5);
        heap.insert(20);
        heap.insert(5);
        heap.insert(1);

        assertEquals(1, heap.peekMin(), "Peek should return absolute minimum 1");

        int[] expectedOrder = {1, 5, 5, 15, 20};
        for( int expected : expectedOrder){
            assertEquals(expected, heap.extractMin(), "Extracted element should preserve non-decreasing order");
        }

        assertEquals(0, heap.size(), "Heap should be empty after extracting all elements");

        try {
            heap.extractMin();
            throw new AssertionError("Should throw IllegalStateException on empty heap extraction");
        } catch (IllegalStateException expected) {}

        System.out.println("Min Heap test passed");
    }

}
