package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import javax.swing.*;

public class View extends JFrame {
    private JPanel wrapper = new JPanel();
    private JPanel nord;
    private JPanel centru;
    private JPanel sud;
    private JTextField P1;
    private JTextField P2;
    private JComboBox menuOp;
    private JButton calcul;
    private JButton reset;
    private JTextField result;

    public View() {
        this.wrapper.setLayout(new BorderLayout());
        // Set up container Nord
        this.nord = new JPanel() {
            protected void paintComponent(Graphics g) {
                Paint p = new GradientPaint(0.0F, 0.0F, Color.decode("#EE2E31"), (float)this.getWidth(), (float)this.getHeight(), Color.decode("#000000"), true);
                Graphics2D g2d = (Graphics2D)g;
                g2d.setPaint(p);
                g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
            }
        };
        wrapper.add(nord, BorderLayout.NORTH);
        // Adaugare selector operatii
        this.menuOp = new JComboBox();
        this.menuOp.addItem("<default>");
        this.menuOp.addItem("Adunare");
        this.menuOp.addItem("Scădere");
        this.menuOp.addItem("Înmulțire");
        this.menuOp.addItem("Împărțire");
        this.menuOp.addItem("Integrare (a P1)");
        this.menuOp.addItem("Derivare (a P1)");
        this.nord.add(this.menuOp);
        this.calcul = new JButton("Calculeaza");
        this.nord.add(this.calcul);

        // Set up container Centru
        this.centru = new JPanel() {
            protected void paintComponent(Graphics g) {
                Paint p = new GradientPaint(0.0F, 0.0F, Color.decode("#EE2E31"), (float)this.getWidth(), (float)this.getHeight(), Color.decode("#000000"), true);
                Graphics2D g2d = (Graphics2D)g;
                g2d.setPaint(p);
                g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
            }
        };
        wrapper.add(centru, BorderLayout.CENTER);
        centru.setLayout(new FlowLayout());
        centru.setBackground(Color.decode("#be7c4d"));

        // Adaugarea zonelor de introdus a celor 2 polinoame
        P1 = this.addPolynomialArea(this.centru, "P1");
        P2 = this.addPolynomialArea(this.centru, "P2");

        // Set up container Sud
        this.sud = new JPanel() {
            protected void paintComponent(Graphics g) {
                Paint p = new GradientPaint(0.0F, 0.0F, Color.decode("#ef3d3f"), (float)this.getWidth(), (float)this.getHeight(), Color.decode("#000000"), true);
                Graphics2D g2d = (Graphics2D)g;
                g2d.setPaint(p);
                g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
            }
        };
        wrapper.add(sud, BorderLayout.SOUTH);
        sud.setBackground(Color.decode("#ddfcad"));

        // Adaugare buton Reset, textField Result
        reset = new JButton("Reset All");
        result = new JTextField();
        result.setPreferredSize(new Dimension(250, 25));
        result.setEditable(false);
        sud.add(new JLabel("Rezultat: "));
        sud.add(result);
        sud.add(reset);
        sud.setPreferredSize(new Dimension(400, 65));

        // Alte date despre aplicatie: titlu, dimensiune, chestiuni vitale
        this.setTitle("Calculator de polinoame");
        this.setSize(new Dimension(400, 250));
        this.setResizable(false);
        this.setLocationRelativeTo((Component)null);
        this.setDefaultCloseOperation(3);
        this.setContentPane(this.wrapper);
        this.setVisible(true);
    }

    public JTextField addPolynomialArea(Container c, String text) {
        JPanel subpanel = new JPanel();
        JTextField tf = new JTextField();
        subpanel.add(new JLabel(text + ": "));
        subpanel.setOpaque(false);
        tf.setPreferredSize(new Dimension(200, 30));
        subpanel.add(tf);
        c.add(subpanel);
        return tf;
    }

    public JTextField getP1() {
        return P1;
    }

    public JTextField getP2() {
        return P2;
    }

    public JComboBox getMenuOp() {
        return menuOp;
    }

    public String getPolinom(int care){
        // care = 1 sau 2, P1 sau P2;
        switch (care){
            case 1: return getP1().getText();
            case 2: return getP2().getText();
            default: return "";
        }
    }
    public String getOperatie(){
        return (String)getMenuOp().getSelectedItem();
    }

    public void resetP1() {
        P1.setText("resetat");
    }

    public void resetP2() {
        P2.setText("resetat");
    }

    public void setResult(String result) {
        this.result.setText(result);
    }
    public void addCalculeazaListener(Controller.whenCalculeazaPressed asc){
        // asc = ascultator
        calcul.addActionListener(asc);
    }
    public void addResetListener(Controller.whenResetPressed asc){
        reset.addActionListener(asc);
    }
    public void dialogBox(String errMessage){
        JOptionPane.showMessageDialog(this, errMessage);
    }
}
