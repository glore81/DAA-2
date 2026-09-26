public class DynamicArray {
        private int[] arr;
        private int size;
        private static final int DEFAULT_CAPACITY = 10;

        public long accessesCount = 0;
        public long movementsCount = 0;
        public long comparisonsCount = 0;

        public DynamicArray() {
            this.arr = new int[DEFAULT_CAPACITY];
            this.size = 0;
        }

        public DynamicArray(int capacity){
             if (capacity < 0){
                 throw new IllegalArgumentException("Capacity cannot be negative");
             }
             this.arr = new int[capacity];
             this.size = 0;
        }

        public void add(int x){
            ensureCapacity(size + 1);
            arr[size] = x;
            size++;
            accessesCount++;
        }

        public void add(int idx, int x){
            checkIndexForAdd(idx);
            ensureCapacity(size+1);

            for (int i = size; i > idx; i--){
                arr[i] = arr[i-1];
                movementsCount++;
                accessesCount += 2;
            }
            arr[idx] = x;
            size++;
            accessesCount++;
        }

        public int remove(int idx){
            checkIndex(idx);
            int removedElement = arr[idx];
            accessesCount++;

            for(int i = idx; i < size-1; i++){
                arr[i] = arr[i+1];
                movementsCount++;
                accessesCount += 2;
            }

            size--;
            return removedElement;
        }

        public int get(int idx){
            checkIndex(idx);
            accessesCount++;
            return arr[idx];
        }

        public boolean contains(int x){
            for(int i = 0; i < size; i++){
                comparisonsCount++;
                accessesCount++;
                if(arr[i] == x){
                    return true;
                }
            }
            return false;
        }

        private void ensureCapacity(int capacity){
            if (capacity > arr.length){
                int newCapacity = Math.max(arr.length * 2, DEFAULT_CAPACITY);
                int[] newArray = new int[newCapacity];

                for(int i = 0; i < size; i++){
                    newArray[i] = arr[i];
                    movementsCount++;
                    accessesCount += 2;
                }
                arr = newArray;
            }
        }

        public int size(){
            return size;
        }

        public boolean isEmpty(){
            return size == 0;
        }

        private void checkIndex(int idx){
            if(idx < 0 || idx >= size){
                throw new IndexOutOfBoundsException("Index: "+ idx + ", Size: " + size);
            }
        }

        private void checkIndexForAdd(int idx){
            if(idx < 0 || idx > size){
                throw new IndexOutOfBoundsException("Index: "+ idx + ", Size: " + size);
            }
        }

        public void resetMetrics() {
            this.accessesCount = 0;
            this.movementsCount = 0;
            this.comparisonsCount = 0;
        }
}
