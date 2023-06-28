package ch.nostromo.cub;

public class PerformanceTests {

    private static int RUNS = 10000000;

    public static void main(String...args) {

        long instancesStart = System.currentTimeMillis();
        for (int i = 0; i< RUNS;i++) {
            new CombinedUnicodeBlocks(
                    Character.UnicodeBlock.BASIC_LATIN,
                    Character.UnicodeBlock.LATIN_1_SUPPLEMENT,
                    Character.UnicodeBlock.LATIN_EXTENDED_A);
        }
        long timeInstances = System.currentTimeMillis() - instancesStart;

        CombinedUnicodeBlocks testee = new CombinedUnicodeBlocks(
                Character.UnicodeBlock.BASIC_LATIN,
                Character.UnicodeBlock.LATIN_1_SUPPLEMENT,
                Character.UnicodeBlock.LATIN_EXTENDED_A);

        // 50 bytes string with illegal ending
        String worstFiftyBytes = "                                               €";

        long startFiftyString = System.currentTimeMillis();
        for (int i = 0; i < RUNS; i++) {
           testee.isLegal(worstFiftyBytes);
        }
        long timeFiftyString = System.currentTimeMillis() - startFiftyString;

        System.out.println("time for " + RUNS + " instances: " + timeInstances);
        System.out.println("time for " + RUNS + " worst case tests with 50 bytes: " + timeFiftyString);
    }


}
