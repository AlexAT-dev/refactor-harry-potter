# Harry Potter Refactored
## Lab 3: Memory optimization

---

## Changes:

- **Use of `BufferedReader` instead of reading the entire file into memory (`Files.readAllBytes`)** to reduce memory consumption.
- **Use of `Pattern SPLIT_PATTERN` to split lines into words** instead of `String.split(" ")`.
- **Removed the `cleanText` method**; text processing now occurs during line-by-line reading.
- **Use of `TreeMap` instead of `HashMap`** for counting word frequencies.
- **Word frequency counting implemented via `merge(word, 1, Integer::sum)`** instead of a manual loop over all words and `distinctWords`.
- **Instead of creating a "word count" list and sorting, `stream().sorted(...).limit(30)` is used** to select the top 30 most frequent words.
- **`toLowerCase(Locale.ROOT)` applied directly during line reading.**
- **Removed the separate step of normalizing spaces (`replaceAll("\\s+", " ")`).**

## Results:
**Before:**<br>
![Before](images/before.jpg)
**After:**<br>
![Before](images/memory.jpg)
