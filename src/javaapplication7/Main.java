package javaapplication7;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.invokeLater(() -> (new MainFrame()).setVisible(true));
        } catch (InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException | ClassNotFoundException var2) {
            JOptionPane.showMessageDialog(null, "不持支该视界风格");
        }

    }
}

