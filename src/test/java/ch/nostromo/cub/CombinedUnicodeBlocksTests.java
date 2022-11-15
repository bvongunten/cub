package ch.nostromo.cub;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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


}
