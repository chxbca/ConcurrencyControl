package javaapplication7;


import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

class MainFrame extends JFrame {
    private HashMap<String, Object> obj = new HashMap<>();

    MainFrame() {
        this.obj.put("root", this);
        this.initView();
        this.initListener();
    }

    private void initView() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 0;
        g.insets = new Insets(10, 10, 10, 10);
        JTextField a = new JTextField();
        Dimension dd = new Dimension(90, 22);
        a.setPreferredSize(dd);
        this.add(a, g);
        this.obj.put("str1", a);
        ++g.gridx;
        JTextField b = new JTextField();
        b.setPreferredSize(dd);
        this.add(b, g);
        this.obj.put("str2", b);
        ++g.gridx;
        JTextField c = new JTextField();
        c.setPreferredSize(dd);
        this.add(c, g);
        this.obj.put("str3", c);
        ++g.gridx;
        JPanel p = new JPanel();
        JButton ok = new JButton("确定");
        JButton clear = new JButton("清除");
        p.add(ok);
        p.add(clear);
        this.obj.put("ok", ok);
        this.obj.put("clear", clear);
        this.add(p, g);
        ++g.gridy;
        g.gridx = 0;
        g.fill = 2;
        g.insets = new Insets(10, 10, 10, 10);
        Font font = new Font("Monospaced", Font.BOLD, 15);
        Dimension dd1 = new Dimension(90, 30);
        JLabel result1 = new JLabel();
        result1.setHorizontalAlignment(0);
        result1.setBorder(BorderFactory.createEtchedBorder());
        result1.setPreferredSize(dd1);
        result1.setFont(font);
        this.add(result1, g);
        this.obj.put("result1", result1);
        ++g.gridx;
        JLabel result2 = new JLabel();
        result2.setHorizontalAlignment(0);
        result2.setBorder(BorderFactory.createEtchedBorder());
        result2.setPreferredSize(dd1);
        result2.setFont(font);
        this.add(result2, g);
        this.obj.put("result2", result2);
        ++g.gridx;
        JLabel result3 = new JLabel();
        result3.setHorizontalAlignment(0);
        result3.setBorder(BorderFactory.createEtchedBorder());
        result3.setPreferredSize(dd1);
        result3.setFont(font);
        this.add(result3, g);
        this.obj.put("result3", result3);
        g.gridx = 0;
        ++g.gridy;
        g.gridwidth = 3;
        g.fill = 2;
        g.insets = new Insets(-5, 10, 10, 10);
        JLabel result = new JLabel();
        result.setHorizontalAlignment(0);
        result.setBorder(BorderFactory.createEtchedBorder());
        result.setPreferredSize(new Dimension(dd.width, 50));
        Font font1 = new Font("Monospaced", Font.BOLD, 30);
        result.setFont(font1);
        this.add(result, g);
        this.obj.put("result", result);
        this.setResizable(false);
        this.setTitle("并发控制实例 - 线程");
        this.setDefaultCloseOperation(3);
        this.pack();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dd2 = this.getSize();
        int x = d.width - dd2.width;
        int y = d.height - dd2.height;
        this.setLocation(x / 2, y / 3);
    }

    private void initListener() {
        JButton ok = (JButton) this.obj.get("ok");
        ok.addActionListener(e -> {
            MainFrame.this.clearResult();
            String str1 = ((JTextField) MainFrame.this.obj.get("str1")).getText().trim();
            String str2 = ((JTextField) MainFrame.this.obj.get("str2")).getText().trim();
            String str3 = ((JTextField) MainFrame.this.obj.get("str3")).getText().trim();
            if (!str1.equals("") && !str2.equals("") && !str3.equals("")) {
                (new ControlThread(MainFrame.this.obj)).start();
            } else {
                JOptionPane.showMessageDialog(null, "输入完整");
            }
        });
        JButton clear = (JButton) this.obj.get("clear");
        clear.addActionListener(e -> {
            MainFrame.this.clearInput();
            MainFrame.this.clearResult();
        });
    }

    private void clearInput() {
        ((JTextField) this.obj.get("str1")).setText("");
        ((JTextField) this.obj.get("str2")).setText("");
        ((JTextField) this.obj.get("str3")).setText("");
    }

    private void clearResult() {
        ((JLabel) this.obj.get("result1")).setText("");
        ((JLabel) this.obj.get("result2")).setText("");
        ((JLabel) this.obj.get("result3")).setText("");
        ((JLabel) this.obj.get("result")).setText("");
    }
}
