package ch.nostromo.cub;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CombinedUnicodeBlocksTests {


    @Test
    public void swissRegistersTests() {
        CombinedUnicodeBlocks testee = new CombinedUnicodeBlocks(
                Character.UnicodeBlock.BASIC_LATIN,
                Character.UnicodeBlock.LATIN_1_SUPPLEMENT,
                Character.UnicodeBlock.LATIN_EXTENDED_A);

        String basicLatin = "B";
        String latinSupplement = "ÀÁÂ";
        String extendedA = "ĆĈĊČ";

        String combined = basicLatin + latinSupplement + extendedA;

        String euro = "€";

        assertTrue(testee.isLegal(basicLatin));
        assertTrue(testee.isLegal(latinSupplement));
        assertTrue(testee.isLegal(extendedA));
        assertTrue(testee.isLegal(combined));

        assertFalse(testee.isLegal(euro));

    }


}
