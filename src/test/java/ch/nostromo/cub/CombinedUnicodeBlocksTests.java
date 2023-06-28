package ch.nostromo.cub;

import org.junit.Test;

import static java.lang.Character.UnicodeBlock.SUPPLEMENTARY_PRIVATE_USE_AREA_B;
import static org.junit.Assert.*;

public class CombinedUnicodeBlocksTests {

    CombinedUnicodeBlocks testee = new CombinedUnicodeBlocks(
            Character.UnicodeBlock.BASIC_LATIN,
            Character.UnicodeBlock.LATIN_1_SUPPLEMENT,
            Character.UnicodeBlock.LATIN_EXTENDED_A);


    @Test
    public void swissRegistersTests() {
        String basicLatin = "BBB";
        String latin1Supplement = "ÀÁÂ";
        String latinExtendedA = "ĆĈĊČ";

        String combined = basicLatin + latin1Supplement + latinExtendedA;

        assertTrue(testee.isLegal(basicLatin));
        assertTrue(testee.isLegal(latin1Supplement));
        assertTrue(testee.isLegal(latinExtendedA));

        assertTrue(testee.isLegal(combined));
    }

    @Test
    public void illegalCharacterTest() {
        String illegalCharacter = "Do not use €";

        assertFalse(testee.isLegal(illegalCharacter));
    }

    @Test
    public void testRangesMerge() {
        assertEquals(1, testee.ranges.length);
        assertEquals(0, testee.ranges[0][0]);
        assertEquals(383, testee.ranges[0][1]);
    }

    @Test
    public void testRangesMergeToEnd() {
        CombinedUnicodeBlocks testee = new CombinedUnicodeBlocks(
                Character.UnicodeBlock.BASIC_LATIN,
                Character.UnicodeBlock.LATIN_1_SUPPLEMENT,
                Character.UnicodeBlock.LATIN_EXTENDED_A,
                Character.UnicodeBlock.SUPPLEMENTARY_PRIVATE_USE_AREA_B);



        assertEquals(2, testee.ranges.length);
        assertEquals(0, testee.ranges[0][0]);
        assertEquals(0x17F, testee.ranges[0][1]);

        assertEquals(0x100000, testee.ranges[1][0]);
        assertEquals(0x10FFFF, testee.ranges[1][1]);
    }


}
