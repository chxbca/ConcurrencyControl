package javaapplication7;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

class ControlThread extends Thread {
    private Map<String, Object> obj;

    ControlThread(Map<String, Object> obj) {
        this.obj = obj;
    }

    public void run() {
        final JButton ok = (JButton) this.obj.get("ok");
        final JButton clear = (JButton) this.obj.get("clear");
        ok.setEnabled(false);
        clear.setEnabled(false);
        ((JFrame) this.obj.get("root")).requestFocus();
        String str1 = ((JTextField) this.obj.get("str1")).getText().trim();
        String str2 = ((JTextField) this.obj.get("str2")).getText().trim();
        String str3 = ((JTextField) this.obj.get("str3")).getText().trim();
        CountDownLatch latch = new CountDownLatch(3);
        final JLabel result1 = (JLabel) this.obj.get("result1");
        final JLabel result2 = (JLabel) this.obj.get("result2");
        final JLabel result3 = (JLabel) this.obj.get("result3");
        (new PrintThread(str1, result1, latch)).start();
        (new PrintThread(str2, result2, latch)).start();
        (new PrintThread(str3, result3, latch)).start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final JLabel result = (JLabel) this.obj.get("result");
        SwingUtilities.invokeLater(() -> {
            result.setText(result1.getText() + " " + result2.getText() + " " + result3.getText());
            ok.setEnabled(true);
            clear.setEnabled(true);
        });
    }
}

