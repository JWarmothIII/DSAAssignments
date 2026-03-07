# Short-Answer Problem 1: O(N) Sorting Using a Count-Based Bag

Since every integer in the array is guaranteed to be between 0 and 1000, we can sort the array in linear time by using the bag implementation from Problem 26, the version that stores counts in an array.

In that bag implementation, we use an integer array:

- `count[0]` through `count[1000]`

If a number `n` appears 6 times in the bag, then:

- `count[n] = 6`

This lets us sort by counting how many times each value appears, then writing them back in order.

---

## Algorithm (O(N) Time)

Let the input array be `inputArray` with length `n`.

### Step 1: Count occurrences
Create an integer array `count` of length `1001`, initialized to all zeros.

Then scan the input array once:

- For each `element` in `inputArray`, do `count[element]++`

### Step 2: Rebuild the array in sorted order
Use an index variable `writeIndex = 0`.

Then loop through all possible values from `0` to `1000`:

- For each `value`, while `count[value] > 0`:
    - Set `inputArray[writeIndex] = value`
    - Increment `writeIndex`
    - Decrement `count[value]`

When finished, `inputArray` is sorted in increasing order by count.

---

## Example (Array of Size 8)

### Input array
`inputArray = [7, 3, 7, 0, 2, 3, 1, 2]`

### After counting
The nonzero entries in `count` are:

- `count[0] = 1`
- `count[1] = 1`
- `count[2] = 2`
- `count[3] = 2`
- `count[7] = 2`

### Rebuild in order
Write values back in order from smallest to largest:

- one `0`
- one `1`
- two `2`s
- two `3`s
- two `7`s

### Sorted array
`inputArray = [0, 1, 2, 2, 3, 3, 7, 7]`

---

## Why the Runtime Is O(N)

The algorithm has two main parts:

1. **Counting pass:**  
   Scans the array once → **O(N)**

2. **Rebuild pass:**  
   Loops through values `0` to `1000` (which is a constant size, 1001 values), and writes exactly `n` elements total → **1001 + n**

So total runtime is:

- **n + 1001 + n = 2n + 1001**

Since `1001` is a constant, this simplifies to:

- **O(N)**

So the sorting algorithm is linear time.

---

## Pseudocode

```java
int[] count = new int[1001];

for (int element : inputArray) {
    count[element]++;
}

int writeIndex = 0;

for (int value = 0; value <= 1000; value++) {
    while (count[value] > 0) {
        inputArray[writeIndex] = value;
        writeIndex++;
        count[value]--;
    }
}