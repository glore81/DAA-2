import java.util.Random;

public class Benchmark {
    private static final int[] N_VALUES = {100, 1000, 10000, 100000};
    private static final int RUNS = 5;
    private static final long SEED = 42;

    public static void main(String[] args){
        System.out.println("Starting Benchmark Suite...\n");

        runWorkLoad1();
        runWorkLoad2();
        runWorkLoad3();
        runWorkLoad4();

    }

    private static void runWorkLoad1(){
        System.out.println("### Workload 1 - Random Access");
        System.out.println("| n | DS | Avg Time (ns) | Accesses | Theoretical |");
        System.out.println("|---|---|---|---|---|");

        int m = 10000;
        Random rand = new Random(SEED);

        for(int n : N_VALUES) {
            long totalTimeArray = 0, totalAccessesArray = 0;
            long totalTimeList = 0, totalAccessesList = 0;

            for(int r  = 0; r < RUNS; r++){

                DynamicArray array = new DynamicArray();
                LinkedList list = new LinkedList();
                for (int i = 0; i < n; i++){
                    int val = rand.nextInt();
                    array.add(val);
                    list.add(val);
                }

                int[] indicesToGet = new int[m];
                for (int i = 0; i < m; i++){
                    indicesToGet[i] = rand.nextInt(n);
                }

                array.resetMetrics();
                long start = System.nanoTime();
                for (int idx : indicesToGet){
                    array.get(idx);
                }

                totalTimeArray += (System.nanoTime() - start);
                totalAccessesArray += array.accessesCount;

                list.resetMetrics();
                start = System.nanoTime();
                for(int idx : indicesToGet){
                    list.get(idx);
                }
                totalTimeList += (System.nanoTime() - start);
                totalAccessesList += list.accessesCount;
            }

            System.out.printf("| %d | Array | %,d | %,d | O(1) | \n", n , (totalTimeArray / RUNS), (totalAccessesArray / RUNS));
            System.out.printf("| %d | List | %,d | %,d | O(n) | \n", n, (totalTimeList / RUNS), (totalAccessesList / RUNS));
        }
        System.out.println();
    }

    private static void runWorkLoad2() {
        System.out.println("### Workload 2 - Search");
        System.out.println("| n | DS | Avg Time (ns) | Comparisons | Theoretical |");
        System.out.println("|---|---|---|---|---|");

        int m = 1000;
        Random rand = new Random(SEED);

        for(int n : N_VALUES) {
            long totalTimeArray = 0, totalCompArray = 0;
            long totalTimeList = 0, totalCompList = 0;

            for (int r = 0; r < RUNS; r++){
                DynamicArray array = new DynamicArray();
                LinkedList list = new LinkedList();
                for (int i = 0; i < n; i++){
                    int val = rand.nextInt(n * 2);
                    array.add(val);
                    list.add(val);
                }

                int[] valuesToSearch = new int[m];
                for(int i = 0;i < m; i++){
                    valuesToSearch[i] = rand.nextInt(n * 2);
                }

                array.resetMetrics();
                long start = System.nanoTime();
                for(int val : valuesToSearch) {
                    array.contains(val);
                }
                totalTimeArray += (System.nanoTime() - start);
                totalCompArray += array.comparisonsCount;

                list.resetMetrics();
                start = System.nanoTime();
                for(int val : valuesToSearch){
                    list.contains(val);
                }
                totalTimeList += (System.nanoTime() - start);
                totalCompList += list.comparisonsCount;
            }

            System.out.printf("| %d | Array | %,d | %,d | O(n) |\n", n, (totalTimeArray / RUNS), (totalCompArray / RUNS));
            System.out.printf("| %d | List | %,d | %,d | O(n) |\n", n, (totalTimeList / RUNS), (totalCompList / RUNS));
        }
        System.out.println();
    }

    private static void runWorkLoad3() {
        System.out.println("### Workload 3 - Insertion and Removal");
        System.out.println("| n | DS | Operation | Avg Time (ns) | Movements | Accesses | Theoretical |");
        System.out.println("|---|---|---|---|---|---|---|");

        int m = 1000;
        Random rand = new Random(SEED);

        for (int n : N_VALUES) {
            for (String ds : new String[]{"Array", "List"}) {
                long timeInsert0 = 0, moveInsert0 = 0, accessInsert0 = 0;
                long timeRemove0 = 0, moveRemove0 = 0, accessRemove0 = 0;
                long timeInsertMid = 0, moveInsertMid = 0, accessInsertMid = 0;
                long timeRemoveMid = 0, moveRemoveMid = 0, accessRemoveMid = 0;

                for(int r = 0; r < RUNS; r++){
                    int[] newVals = new int[m];
                    for(int i = 0; i < m; i++){
                        newVals[i] = rand.nextInt();
                    }



                    // Insert at 0
                    DynamicArray arr = new DynamicArray();
                    LinkedList list = new LinkedList();
                    for(int i = 0; i < n; i++){
                        if(ds.equals("Array")) arr.add(i);
                        else list.add(i);
                    }

                    if(ds.equals("Array")) arr.resetMetrics();
                    else list.resetMetrics();

                    long start = System.nanoTime();
                    for(int val : newVals){
                        if(ds.equals("Array")) arr.add(0, val);
                        else list.add(0, val);
                    }
                    timeInsert0 += (System.nanoTime() - start);
                    moveInsert0 += ds.equals("Array") ? arr.movementsCount : list.movementsCount;
                    accessInsert0 += ds.equals("Array") ? arr.accessesCount : list.accessesCount;



                    // Remove at 0
                    arr = new DynamicArray();
                    list = new LinkedList();
                    for(int i = 0; i < n; i++){
                        if(ds.equals("Array")) arr.add(i);
                        else list.add(i);
                    }

                    if(ds.equals("Array")) arr.resetMetrics();
                    else list.resetMetrics();

                    start = System.nanoTime();
                    for(int i = 0; i < m; i++){
                        if(ds.equals("Array")) arr.remove(0);
                        else list.remove(0);
                    }
                    timeRemove0 += (System.nanoTime() - start);
                    moveRemove0 += ds.equals("Array") ? arr.movementsCount : list.movementsCount;
                    accessRemove0 += ds.equals("Array") ? arr.accessesCount : list.accessesCount;



                    // Insert Mid
                    arr = new DynamicArray();
                    list = new LinkedList();
                    for(int i = 0; i < n; i++){
                        if(ds.equals("Array")) arr.add(i);
                        else list.add(i);
                    }

                    if(ds.equals("Array")) arr.resetMetrics();
                    else list.resetMetrics();

                    start = System.nanoTime();
                    for(int val : newVals){
                        int mid = ds.equals("Array") ? arr.size()/2 : list.size()/2;
                        if(ds.equals("Array")) {
                            arr.add(mid, val);
                        }
                        else {
                            list.add(mid, val);
                        }
                    }
                    timeInsertMid += (System.nanoTime() - start);
                    moveInsertMid += ds.equals("Array") ? arr.movementsCount : list.movementsCount;
                    accessInsertMid += ds.equals("Array") ? arr.accessesCount : list.accessesCount;



                    // Remove Mid
                    arr = new DynamicArray();
                    list = new LinkedList();
                    for(int i = 0; i < n; i++){
                        if(ds.equals("Array")) arr.add(i);
                        else list.add(i);
                    }

                    if(ds.equals("Array")) {
                        arr.resetMetrics();
                    }
                    else {
                        list.resetMetrics();
                    }
                    start = System.nanoTime();
                    for(int i = 0; i < m; i++){
                        int mid = ds.equals("Array") ? arr.size()/2 : list.size()/2;
                        if(ds.equals("Array")){
                            arr.remove(mid);
                        }
                        else{
                            list.remove(mid);
                        }
                    }
                    timeRemoveMid += (System.nanoTime() - start);
                    moveRemoveMid += ds.equals("Array") ? arr.movementsCount : list.movementsCount;
                    accessRemoveMid += ds.equals("Array") ? arr.accessesCount : list.accessesCount;
                }

                System.out.printf("| %d | %s | Insert at 0 | %,d | %,d | %,d | O(n) / O(1) | \n", n, ds, (timeInsert0 / RUNS), (moveInsert0 / RUNS), (accessInsert0 / RUNS));
                System.out.printf("| %d | %s | Remove at 0 | %,d | %,d | %,d | O(n) / O(1) | \n", n , ds, (timeRemove0 / RUNS), (moveRemove0 / RUNS), (accessRemove0 / RUNS));
                System.out.printf("| %d | %s | Insert Mid | %,d | %,d | %,d | O(n) |\n", n, ds, (timeInsertMid / RUNS), (moveInsertMid / RUNS), (accessInsertMid / RUNS));
                System.out.printf("| %d | %s | Remove Mid | %,d | %,d | %,d | O(n) |\n", n , ds, (timeRemoveMid / RUNS), (moveRemoveMid / RUNS), (accessRemoveMid / RUNS));
            }
        }
        System.out.println();
    }

    private static void runWorkLoad4() {
        System.out.println("### Workload 4 - Priority Processing (Min-Heap)");
        System.out.println("| n | Operation | Avg Time (ns) | Comparisons | Theoretical |");
        System.out.println("|---|---|---|---|---|");

        Random rand = new Random(SEED);

        for(int n : N_VALUES){
            long totalTimeInsert = 0, totalCompInsert = 0;
            long totalTimeExtract = 0, totalCompExtract = 0;

            for(int r = 0; r < RUNS; r++) {
                MinHeap heap = new MinHeap();
                int[] dataToInsert = new int[n];
                for (int i = 0; i < n; i++){
                    dataToInsert[i] = rand.nextInt();
                }
                heap.resetMetrics();;
                long start = System.nanoTime();
                for(int val : dataToInsert) {
                    heap.insert(val);
                }
                totalTimeInsert += (System.nanoTime() - start);
                totalCompInsert += heap.comparisonsCount;

                heap.resetMetrics();
                start = System.nanoTime();
                int prev = Integer.MIN_VALUE;
                for(int i = 0; i < n; i++){
                    int current = heap.extractMin();
                    if(current < prev) {
                        throw new IllegalStateException("Heap property violated during extract");
                    }
                    prev = current;
                }
                totalTimeExtract += (System.nanoTime() - start);
                totalCompExtract += heap.comparisonsCount;
            }
            System.out.printf("| %d | Insert (x%d) | %,d | %,d | O(n log n) |\n", n, n, (totalTimeInsert / RUNS), (totalCompInsert / RUNS));
            System.out.printf("| %d | Extract (x%d)| %,d | %,d | O(n log n)|\n", n, n, (totalTimeExtract / RUNS), (totalCompExtract / RUNS));
        }
        System.out.println();
    }
}
