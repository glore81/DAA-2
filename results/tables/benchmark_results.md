### Workload 1 - Random Access
| n | DS | Avg Time (ns) | Accesses | Theoretical |
|---|---|---|---|---|
| 100 | Array | 151 380 | 10 000 | O(1) | 
| 100 | List | 534 600 | 254 356 | O(n) | 
| 1000 | Array | 22 120 | 10 000 | O(1) | 
| 1000 | List | 2 135 060 | 2 496 753 | O(n) | 
| 10000 | Array | 7 040 | 10 000 | O(1) | 
| 10000 | List | 25 037 260 | 25 017 283 | O(n) | 
| 100000 | Array | 36 260 | 10 000 | O(1) | 
| 100000 | List | 305 552 940 | 250 897 702 | O(n) | 

### Workload 2 - Search
| n | DS | Avg Time (ns) | Comparisons | Theoretical |
|---|---|---|---|---|
| 100 | Array | 180 400 | 78 224 | O(n) |
| 100 | List | 190 880 | 78 224 | O(n) |
| 1000 | Array | 638 020 | 792 324 | O(n) |
| 1000 | List | 1 293 380 | 792 324 | O(n) |
| 10000 | Array | 2 460 800 | 7 864 260 | O(n) |
| 10000 | List | 16 811 780 | 7 864 260 | O(n) |
| 100000 | Array | 22 847 400 | 78 564 492 | O(n) |
| 100000 | List | 140 313 040 | 78 564 492 | O(n) |

### Workload 3 - Insertion and Removal
| n | DS | Operation | Avg Time (ns) | Movements | Accesses | Theoretical |
|---|---|---|---|---|---|---|
| 100 | Array | Insert at 0 | 1 332 420 | 600 620 | 1 202 240 | O(n) / O(1) | 
| 100 | Array | Remove at 0 | 29 500 | 4 950 | 10 000 | O(n) / O(1) | 
| 100 | Array | Insert Mid | 576 020 | 301 120 | 603 240 | O(n) |
| 100 | Array | Remove Mid | 17 360 | 2 450 | 5 000 | O(n) |
| 100 | List | Insert at 0 | 45 300 | 0 | 1 000 | O(n) / O(1) | 
| 100 | List | Remove at 0 | 5 120 | 0 | 200 | O(n) / O(1) | 
| 100 | List | Insert Mid | 332 280 | 0 | 301 000 | O(n) |
| 100 | List | Remove Mid | 6 500 | 0 | 2 650 | O(n) |
| 1000 | Array | Insert at 0 | 127 220 | 1 500 780 | 3 002 560 | O(n) / O(1) | 
| 1000 | Array | Remove at 0 | 387 000 | 499 500 | 1 000 000 | O(n) / O(1) | 
| 1000 | Array | Insert Mid | 78 840 | 751 280 | 1 503 560 | O(n) |
| 1000 | Array | Remove Mid | 162 340 | 249 500 | 500 000 | O(n) |
| 1000 | List | Insert at 0 | 12 580 | 0 | 1 000 | O(n) / O(1) | 
| 1000 | List | Remove at 0 | 15 580 | 0 | 2 000 | O(n) / O(1) | 
| 1000 | List | Insert Mid | 772 660 | 0 | 751 000 | O(n) |
| 1000 | List | Remove Mid | 246 520 | 0 | 251 500 | O(n) |
| 10000 | Array | Insert at 0 | 264 180 | 10 509 740 | 21 020 480 | O(n) / O(1) | 
| 10000 | Array | Remove at 0 | 388 900 | 9 499 500 | 19 000 000 | O(n) / O(1) | 
| 10000 | Array | Insert Mid | 199 560 | 5 260 240 | 10 521 480 | O(n) |
| 10000 | Array | Remove Mid | 235 600 | 4 749 500 | 9 500 000 | O(n) |
| 10000 | List | Insert at 0 | 5 580 | 0 | 1 000 | O(n) / O(1) | 
| 10000 | List | Remove at 0 | 6 800 | 0 | 2 000 | O(n) / O(1) | 
| 10000 | List | Insert Mid | 5 859 280 | 0 | 5 251 000 | O(n) |
| 10000 | List | Remove Mid | 5 627 200 | 0 | 4 751 500 | O(n) |
| 100000 | Array | Insert at 0 | 4 931 420 | 100 499 500 | 201 000 000 | O(n) / O(1) | 
| 100000 | Array | Remove at 0 | 4 969 000 | 99 499 500 | 199 000 000 | O(n) / O(1) | 
| 100000 | Array | Insert Mid | 2 448 160 | 50 250 000 | 100 501 000 | O(n) |
| 100000 | Array | Remove Mid | 2 474 500 | 49 749 500 | 99 500 000 | O(n) |
| 100000 | List | Insert at 0 | 4 340 | 0 | 1 000 | O(n) / O(1) | 
| 100000 | List | Remove at 0 | 5 380 | 0 | 2 000 | O(n) / O(1) | 
| 100000 | List | Insert Mid | 78 709 380 | 0 | 50 251 000 | O(n) |
| 100000 | List | Remove Mid | 74 766 500 | 0 | 49 751 500 | O(n) |

### Workload 4 - Priority Processing (Min-Heap)
| n | Operation | Avg Time (ns) | Comparisons | Theoretical |
|---|---|---|---|---|
| 100 | Insert (x100) | 67 480 | 210 | O(n log n) |
| 100 | Extract (x100)| 77 760 | 853 | O(n log n)|
| 1000 | Insert (x1000) | 32 480 | 2 219 | O(n log n) |
| 1000 | Extract (x1000)| 94 300 | 14 980 | O(n log n)|
| 10000 | Insert (x10000) | 266 020 | 22 770 | O(n log n) |
| 10000 | Extract (x10000)| 989 620 | 216 668 | O(n log n)|
| 100000 | Insert (x100000) | 1 248 060 | 228 219 | O(n log n) |
| 100000 | Extract (x100000)| 7 493 760 | 2 831 909 | O(n log n)|