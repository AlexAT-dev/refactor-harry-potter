# Harry Potter Refactored

---

## Changes:

1. **Collections Instead of Arrays**
   - Replaced arrays and string concatenation with `List`, `Set`, and `Map` for words and frequencies.

2. **Text Cleaning**
   - Centralized in `cleanText()` method.
   - Cleans text to keep letters, spaces, and now supports **apostrophes** (before `it's` cleaned into `it s`, etc).
   - Normalizes spaces and trims text.

3. **Removed Code Duplication**
   - Main method calls `cleanText()` instead of repeating cleaning logic.

4. **Stream Usage for Sorting and Mapping**
   - Sorting and mapping is now handled via streams for cleaner and more readable code.

5. **Word Counting Logic**
   - Uses `Map<String, Integer>` to count frequencies.
   - Fixes the issue with `distinctString.contains()`, which could produce incorrect matches (e.g., `"he"` inside `"the"`).

6. **Safe Top-N Output**
   - Prints top 30 most frequent words safely without risk of `IndexOutOfBoundsException` (added `i < wordFreqList.size()`)