package ch.nostromo.cub;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple class to handle combined UnicodeBlocks in Java and provide methods to validate characters or strings.
 */
public class CombinedUnicodeBlocks {

    Set<Character.UnicodeBlock> combinedBlocks;

    public CombinedUnicodeBlocks(Character.UnicodeBlock... unicodeBlocks) {
        combinedBlocks = new HashSet<>(Arrays.asList(unicodeBlocks));
    }

    public boolean isLegal(char c) {
        return combinedBlocks.contains(Character.UnicodeBlock.of(c));
    }


    public boolean isLegal(String str) {
        if (str == null) {
            return true;
        }

        CharacterIterator it = new StringCharacterIterator(str);
        while (it.current() != CharacterIterator.DONE) {
            if (!isLegal(it.current())) {
                return false;
            }
            it.next();
        }
        return true;
    }

}
