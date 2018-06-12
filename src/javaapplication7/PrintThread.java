package javaapplication7;

import java.util.concurrent.CountDownLatch;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

class PrintThread extends Thread {
    private String str;
    private JLabel result;
    private CountDownLatch latch;

    PrintThread(String str, JLabel result, CountDownLatch latch) {
        this.str = str;
        this.result = result;
        this.latch = latch;
    }

    public void run() {
        try {
            long second = (long) (Math.random() * 10000.0D) % 10L;
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            PrintThread.this.result.setText(PrintThread.this.str);
            PrintThread.this.latch.countDown();
        });
    }
}
