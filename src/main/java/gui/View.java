package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {

    private JPanel contentPane;
    private JTextField inputPoly;
    private JTextField inputPoly2;
    private JTextField outputPolynomial;
    private JButton btnAdd;
    private JButton btnSubtract;
    private JButton btnMultiply;
    private JButton btnDerivate;
    private JButton btnDivision;
    private JButton btnIntegrate;

    public View() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 740, 455);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(224, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        inputPoly = new JTextField();
        inputPoly.setFont(new Font("Calibri", Font.PLAIN, 18));
        inputPoly.setBounds(59, 63, 415, 37);
        contentPane.add(inputPoly);
        inputPoly.setColumns(10);

        JLabel lblInputPolynomial = new JLabel("Input Polynomial: ");
        lblInputPolynomial.setFont(new Font("Calibri", Font.PLAIN, 22));
        lblInputPolynomial.setBounds(59, 13, 196, 37);
        contentPane.add(lblInputPolynomial);

        btnAdd = new JButton("Add");
        btnAdd.setForeground(new Color(0, 0, 128));
        btnAdd.setBackground(new Color(176, 196, 222));
        btnAdd.setFont(new Font("Calibri", Font.PLAIN, 18));
        btnAdd.setBounds(59, 113, 70, 37);
        contentPane.add(btnAdd);

        btnSubtract = new JButton("Subtract");
        btnSubtract.setForeground(new Color(0, 0, 128));
        btnSubtract.setBackground(new Color(176, 196, 222));
        btnSubtract.setFont(new Font("Calibri", Font.PLAIN, 18));
        btnSubtract.setBounds(141, 113, 114, 37);
        contentPane.add(btnSubtract);

        btnDivision = new JButton("Division");
        btnDivision.setForeground(new Color(0, 0, 128));
        btnDivision.setBackground(new Color(176, 196, 222));
        btnDivision.setFont(new Font("Calibri", Font.PLAIN, 18));
        btnDivision.setBounds(265, 113, 93, 37);
        contentPane.add(btnDivision);

        inputPoly2 = new JTextField();
        inputPoly2.setFont(new Font("Calibri", Font.PLAIN, 18));
        inputPoly2.setBounds(59, 163, 415, 37);
        contentPane.add(inputPoly2);
        inputPoly2.setColumns(10);

        // THE OUTPUT
        outputPolynomial = new JTextField();
        outputPolynomial.setFont(new Font("Calibri", Font.PLAIN, 20));
        outputPolynomial.setEditable(false);
        outputPolynomial.setBounds(59, 290, 415, 37);
        contentPane.add(outputPolynomial);
        outputPolynomial.setColumns(10);

        JLabel lblOutput = new JLabel("Output: ");
        lblOutput.setFont(new Font("Calibri", Font.PLAIN, 22));
        lblOutput.setBounds(59, 240, 104, 37);
        contentPane.add(lblOutput);

        JLabel lblOtherOperations = new JLabel("Other Operations: ");
        lblOtherOperations.setFont(new Font("Calibri", Font.PLAIN, 22));
        lblOtherOperations.setBounds(514, 13, 196, 37);
        contentPane.add(lblOtherOperations);

        btnDerivate = new JButton("Derivate");
        btnDerivate.setForeground(new Color(0, 0, 128));
        btnDerivate.setBackground(new Color(176, 196, 222));
        btnDerivate.setFont(new Font("Calibri", Font.PLAIN, 18));
        btnDerivate.setBounds(524, 63, 168, 37);
        contentPane.add(btnDerivate);

        btnIntegrate = new JButton("Integrate");
        btnIntegrate.setBackground(new Color(176, 196, 222));
        btnIntegrate.setForeground(new Color(0, 0, 128));
        btnIntegrate.setFont(new Font("Calibri", Font.PLAIN, 18));
        btnIntegrate.setBounds(524, 113, 168, 37);
        contentPane.add(btnIntegrate);

        btnMultiply = new JButton("Multiply");
        btnMultiply.setForeground(new Color(0, 0, 128));
        btnMultiply.setBackground(new Color(176, 196, 222));
        btnMultiply.setFont(new Font("Calibri", Font.PLAIN, 18));
        btnMultiply.setBounds(370, 113, 104, 37);
        contentPane.add(btnMultiply);
    }

    public void setCalcSolution(String solution) {
        outputPolynomial.setText(solution);
    }

    public void addSumListener(ActionListener listenForAddButton) {
        btnAdd.addActionListener(listenForAddButton);
    }

    public void addSubtractListener(ActionListener listenForSubButton) {
        btnSubtract.addActionListener(listenForSubButton);
    }

    public void addMultiplyListener(ActionListener listenForMultiplyButton) {
        btnMultiply.addActionListener(listenForMultiplyButton);
    }

    public void addDivisionListener(ActionListener listenForDivisionButton) {
        btnDivision.addActionListener(listenForDivisionButton);
    }

    public void addDeriveListener(ActionListener listenForMultiplyButton) {
        btnDerivate.addActionListener(listenForMultiplyButton);
    }

    public void addIntegrateListener(ActionListener listenForMultiplyButton) {
        btnIntegrate.addActionListener(listenForMultiplyButton);
    }

    public String getFirstPoly() {
        return inputPoly.getText();
    }

    public String getSecondPoly() {
        return inputPoly2.getText();
    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

}