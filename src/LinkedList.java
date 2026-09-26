public class LinkedList {
    private static class Node {
        int data;
        Node next;
        Node prev;

        Node(int data){
            this.data = data;
        }
    }
    private Node head;
    private Node tail;
    private int size;

    public long accessesCount = 0;
    public long movementsCount = 0;
    public long comparisonsCount = 0;

    public LinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(int x){
        Node newNode = new Node(x);

        if(size == 0){
            head = newNode;
            tail = newNode;
        }

        else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        accessesCount++;
    }

    public void add(int idx, int x){
        checkIndexForAdd(idx);

        if(idx == size){
            add(x);
            return;
        }

        Node newNode = new Node(x);
        if(idx == 0){
            newNode.next = head;
            if(head != null){
                head.prev = newNode;
            }
            head = newNode;
        } else {
            Node current = getNode(idx);
            Node previous = current.prev;

            previous.next = newNode;
            newNode.prev = previous;
            newNode.next = current;
            current.prev = newNode;
        }
        size++;
        accessesCount++;
    }

    public int remove(int idx) {
        checkIndex(idx);
        Node nodeToRemove = getNode(idx);
        int removedElement = nodeToRemove.data;
        if(size == 1){
            head = null;
            tail = null;
        }
        else if(idx == 0){
            head = nodeToRemove.next;
            head.prev = null;
        }
        else if(idx == size-1){
            tail = nodeToRemove.prev;
            tail.next = null;
        }
        else {
            Node previous = nodeToRemove.prev;
            Node nextNode = nodeToRemove.next;
            previous.next = nextNode;
            nextNode.prev = previous;
        }
        size--;
        accessesCount++;
        return removedElement;
    }

    public int get(int idx){
        checkIndex(idx);
        Node current = getNode(idx);
        return current.data;
    }

    public boolean contains(int x){
        Node current = head;
        while (current != null){
            comparisonsCount++;
            accessesCount++;
            if (current.data == x){
                return true;
            }
            current = current.next;
            accessesCount++;
        }
        return false;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private Node getNode(int idx){
        Node current;
        if(idx < size/2){
            current = head;
            for(int i = 0; i < idx; i++){
                current = current.next;
                accessesCount++;
            }
        }
        else {
            current = tail;
            for (int i = size-1; i > idx; i--) {
                current = current.prev;
                accessesCount++;
            }
        }
        accessesCount++;
        return current;
    }
    private void checkIndex(int idx){
        if(idx < 0 || idx >= size){
            throw new IndexOutOfBoundsException("Index: "+idx+", Size: "+size);
        }
    }

    private void checkIndexForAdd(int idx){
        if(idx < 0 || idx > size){
            throw new IndexOutOfBoundsException("Index: "+idx+", Size: "+size);
        }
    }

    public void resetMetrics(){
        comparisonsCount = 0;
        accessesCount = 0;
        movementsCount = 0;
    }
}
