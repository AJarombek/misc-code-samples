package concurrency;

/**
 * Comparing Java's threading functionality to languages I'm learning.
 * @author Andrew Jarombek
 * @since 10/4/2019
 */

class Threading {
    static void executeBasicThread() {
        var thread = new Thread(() -> print('1'));
        assert !thread.isAlive();
        thread.start();

        print('0');
    }

    private static void print(char c) {
        for (int i = 0; i < 50; i++)
        {
            System.out.print(c);
        }
    }

    static void executeThread() {
        var thread = new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                assert false;
            }
        });

        thread.start();
        assert thread.isAlive();

        try {
            thread.join();
            assert !thread.isAlive();
        } catch (InterruptedException e) {
            assert false;
        }
    }
}
