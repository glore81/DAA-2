public class MinHeap {
    private int[] heap;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public long comparisonsCount = 0;
    public long movementsCount = 0;
    public long accessesCount = 0;

    public MinHeap(){
        this.heap = new int[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public void insert(int x){
        ensureCapacity(size + 1);
        heap[size] = x;
        accessesCount++;

        siftUp(size);
        size++;
    }

    public int peekMin(){
        if(size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        accessesCount++;
        return heap[0];
    }

    public int extractMin(){
        if(size == 0){
            throw new IllegalStateException("Heap is empty");
        }

        int min = heap[0];
        accessesCount++;

        heap[0] = heap[size-1];
        accessesCount += 2;
        size--;

        if(size > 0){
            siftDown(0);
        }
        return min;
    }

    private void siftUp(int idx){
        while(idx > 0){
            int parentIdx = (idx - 1)/ 2;
            comparisonsCount++;
            accessesCount += 2;

            if(heap[idx] < heap[parentIdx]){
                swap(idx,parentIdx);
                idx = parentIdx;
            }
            else {
                break;
            }
        }
    }

    private void siftDown(int idx){
        while (idx < size){
            int leftChildIdx = 2 * idx + 1;
            int rightChildIdx = 2 * idx + 2;
            int smallest = idx;

            if(leftChildIdx < size){
                comparisonsCount++;
                accessesCount += 2;
                if(heap[leftChildIdx] < heap[smallest]){
                    smallest = leftChildIdx;
                }
            }

            if(rightChildIdx < size){
                comparisonsCount++;
                accessesCount += 2;
                if(heap[rightChildIdx] < heap[smallest]){
                    smallest = rightChildIdx;
                }
            }

            if(smallest != idx){
                swap(idx, smallest);
                idx = smallest;
            }
            else {
                break;
            }
        }
    }

    private void swap(int i, int j){
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;

        movementsCount += 3;
        accessesCount += 4;
    }

    public void ensureCapacity(int capacity){
        if(capacity > heap.length){
            int newCapacity = Math.max(heap.length * 2,DEFAULT_CAPACITY);
            int[] newHeap = new int[newCapacity];

            for(int i = 0; i < size; i++){
                newHeap[i] = heap[i];
                movementsCount++;
                accessesCount += 2;
            }
            heap = newHeap;
        }
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void resetMetrics(){
        accessesCount = 0;
        movementsCount = 0;
        comparisonsCount = 0;
    }
}
