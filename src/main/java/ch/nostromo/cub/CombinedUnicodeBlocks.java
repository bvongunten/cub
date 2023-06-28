package ch.nostromo.cub;

/**
 * Simple class to handle combined UnicodeBlocks in Java and provide methods to validate characters or strings.
 */
public class CombinedUnicodeBlocks {

    // Character code ranges for given blocks
    int[][] ranges = new int[0][2];

    /**
     * Constructor with list of unicode blocks
     *
     * @param unicodeBlocks
     */
    public CombinedUnicodeBlocks(Character.UnicodeBlock... unicodeBlocks) {

        // Add unicode block ranges to known ranges, merge if possible
        for (Character.UnicodeBlock unicodeBlock : unicodeBlocks) {
            int blockStart = getBlockStart(unicodeBlock);
            int blockEnd = getBlockEnd(unicodeBlock);

            boolean merged = false;
            for (int[] range : ranges) {
                if (blockStart == range[1] + 1) {
                    // add to the end ..
                    range[1] = blockEnd;
                    merged = true;
                } else if (blockEnd == range[0] - 1) {
                    // Insert to the front
                    range[0] = blockStart;
                    merged = true;
                }
            }

            // If range has not been merged, add to ranges array
            if (!merged) {
                int[][] modifiedRanges = new int[ranges.length + 1][2];
                System.arraycopy(ranges, 0, modifiedRanges, 0, ranges.length);

                modifiedRanges[modifiedRanges.length - 1][0] = blockStart;
                modifiedRanges[modifiedRanges.length - 1][1] = blockEnd;

                ranges = modifiedRanges;
            }
        }
    }

    /**
     * Get block start (int) of a given unicode block
     *
     * @param block
     * @return
     */
    private int getBlockStart(Character.UnicodeBlock block) {
        for (int i = 0; i < UnicodeRanges.blocks.length; i++) {
            if (UnicodeRanges.blocks[i] != null && UnicodeRanges.blocks[i].equals(block)) {
                return UnicodeRanges.blockStarts[i];
            }
        }
        throw new IllegalArgumentException("Unknown unicode block: " + block);
    }

    /**
     * Get block end (int) of a given unicode block. If
     *
     * @param block
     * @return
     */
    private int getBlockEnd(Character.UnicodeBlock block) {
        for (int i = 0; i < UnicodeRanges.blocks.length; i++) {
            if (UnicodeRanges.blocks[i] != null && UnicodeRanges.blocks[i].equals(block)) {
                if (i == UnicodeRanges.blocks.length - 1) {
                    return 0x10FFFF;
                } else {
                    return UnicodeRanges.blockStarts[i + 1] - 1;
                }
            }
        }
        throw new IllegalArgumentException("Unknown unicode block: " + block);
    }


    /**
     * Returns true if given character is in known char code range
     *
     * @param charCode
     * @return
     */
    public boolean isLegal(int charCode) {
        for (int[] range : ranges) {
            if (range[0] <= charCode && range[1] >= charCode) {
                return true;
            }
        }

        return false;
    }


    /**
     * Returns true if given string is in known char code range. If the string is null or blank (white spaces only) the string is legal
     *
     * @param str
     * @return
     */
    public boolean isLegal(String str) {
        if (str == null || str.isBlank()) {
            return true;
        }

        for (char c : str.toCharArray()) {
            if (!isLegal(c)) {
                return false;
            }
        }

        return true;
    }


}
