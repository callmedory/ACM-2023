package lab5;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener 
{
    private JPanel panel1;

    private JLabel x1Add1 = new JLabel(" X1 + ");
    private JLabel x2Add1 = new JLabel(" X2 + ");
    private JLabel x3Equ1 = new JLabel(" X3 = ");

    private JLabel x1Add2 = new JLabel(" X1 + ");
    private JLabel x2Add2 = new JLabel(" X2 + ");
    private JLabel x3Equ2 = new JLabel(" X3 = ");

    private JLabel x1Add3 = new JLabel(" X1 + ");
    private JLabel x2Add3 = new JLabel(" X2 + ");
    private JLabel x3Equ3 = new JLabel(" X3 = ");

    private JLabel x1Res = new JLabel("X1 =  ");
    private JLabel x2Res = new JLabel("X2 =  ");
    private JLabel x3Res = new JLabel("X3 =  ");

    private JTextField x11 = new JTextField();
    private JTextField x12 = new JTextField();
    private JTextField x13 = new JTextField();
    private JTextField x21 = new JTextField();
    private JTextField x22 = new JTextField();
    private JTextField x23 = new JTextField();
    private JTextField x31 = new JTextField();
    private JTextField x32 = new JTextField();
    private JTextField x33 = new JTextField();
    private JTextField res1 = new JTextField();
    private JTextField res2 = new JTextField();
    private JTextField res3 = new JTextField();

    private JButton solve = new JButton("Розв'язати");

    public Main()
    {
        super("Лабораторна робота №5");
        setSize(320,380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel1 = new JPanel();
        panel1.setLayout(null);

        x11.setBounds(10,20,38,40);
        x1Add1.setBounds(50,20,38,40);
        x12.setBounds(90,20,38,40);
        x2Add1.setBounds(130,20,38,40);
        x13.setBounds(170,20,38,40);
        x3Equ1.setBounds(210,20,38,40);
        res1.setBounds(250,20,38,40);

        x21.setBounds(10,66,38,40);
        x1Add2.setBounds(50,66,38,40);
        x22.setBounds(90,66,38,40);
        x2Add2.setBounds(130,66,38,40);
        x23.setBounds(170,66,38,40);
        x3Equ2.setBounds(210,66,38,40);
        res2.setBounds(250,66,38,40);

        x31.setBounds(10,112,38,40);
        x1Add3.setBounds(50,112,38,40);
        x32.setBounds(90,112,38,40);
        x2Add3.setBounds(130,112,38,40);
        x33.setBounds(170,112,38,40);
        x3Equ3.setBounds(210,112,38,40);
        res3.setBounds(250,112,38,40);

        solve.setBounds(110,170,100,40);
        solve.addActionListener(this);

        x1Res.setBounds(10,220,200, 30);
        x2Res.setBounds(10,254,200, 30);
        x3Res.setBounds(10,286,200, 30);

        panel1.add(x11);
        panel1.add(x1Add1);
        panel1.add(x12);
        panel1.add(x2Add1);
        panel1.add(x13);
        panel1.add(x3Equ1);
        panel1.add(res1);

        panel1.add(x21);
        panel1.add(x1Add2);
        panel1.add(x22);
        panel1.add(x2Add2);
        panel1.add(x23);
        panel1.add(x3Equ2);
        panel1.add(res2);

        panel1.add(x31);
        panel1.add(x1Add3);
        panel1.add(x32);
        panel1.add(x2Add3);
        panel1.add(x33);
        panel1.add(x3Equ3);
        panel1.add(res3);

        panel1.add(solve);

        panel1.add(x1Res);
        panel1.add(x2Res);
        panel1.add(x3Res);

        setContentPane(panel1);
    }

    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == solve) 
        {
            double[][] matrixC = new double[3][3];
            double[][] matrixRes = new double[3][1];

            matrixC[0][0] = Double.valueOf(x11.getText());
            matrixC[0][1] = Double.valueOf(x12.getText());
            matrixC[0][2] = Double.valueOf(x13.getText());
            matrixC[1][0] = Double.valueOf(x21.getText());
            matrixC[1][1] = Double.valueOf(x22.getText());
            matrixC[1][2] = Double.valueOf(x23.getText());
            matrixC[2][0] = Double.valueOf(x31.getText());
            matrixC[2][1] = Double.valueOf(x32.getText());
            matrixC[2][2] = Double.valueOf(x33.getText());

            matrixRes[0][0] = Double.valueOf(res1.getText());
            matrixRes[1][0] = Double.valueOf(res2.getText());
            matrixRes[2][0] = Double.valueOf(res3.getText());

            double accuracy = 0.000001;

            double[][] matrixCT = new double[matrixC.length][matrixC[0].length];
            double[][] matrixCAM = new double[matrixC.length][matrixCT[0].length];
            double[][] matrixResAM = new double[matrixRes.length][1];
            double[] xNext = new double[matrixRes.length];
            double[] xPresent = new double[matrixRes.length];

            matrixCT = transpose(matrixC);
            matrixCAM = multiply(matrixCT,matrixC);
            matrixResAM = multiply(matrixCT,matrixRes);

            for (int i = 0; i < matrixC.length; i++) 
            {
                matrixC[i][i] = matrixResAM[i][0] / matrixCAM[i][i];
                for (int j = 0; j < matrixC[0].length; j++) 
                {
                    if (i != j) 
                    {
                        matrixC[i][j] = -1 * matrixCAM[i][j] / matrixCAM[i][i];
                    }
                }
            }
            for (int i = 0; i < xNext.length; i++) 
            {
                xNext[i] = xPresent[i] = 0;
            }
            while (true) 
            {
                boolean f = false;

                xNext[0] = matrixC[0][0] + matrixC[0][1] * xPresent[1] + matrixC[0][2] * xPresent[2];
                xNext[1] = matrixC[1][1] + matrixC[1][0] * xNext[0] + matrixC[1][2] * xPresent[2];
                xNext[2] = matrixC[2][2] + matrixC[2][0] * xNext[0] + matrixC[2][1] * xNext[1];
                for (int i = 0; i < xPresent.length; i++) 
                {
                    if (Math.abs(xPresent[i] - xNext[i]) < accuracy) f = true;
                    else 
                    {
                        f = false;
                        break;
                    }
                }
                if (f) break;
                xPresent[0] = xNext[0];
                xPresent[1] = xNext[1];
                xPresent[2] = xNext[2];
            }

            x1Res.setText(String.format("X1 = %.2f", xNext[0]));
            x2Res.setText(String.format("X2 = %.2f", xNext[1]));
            x3Res.setText(String.format("X3 = %.2f", xNext[2]));
        }
    }

    public static double[][] transpose(double[][] matrixC) 
    {
        double[][] matrixCT = new double[matrixC.length][matrixC[0].length];
        for (int i = 0; i < matrixC.length; i++) 
        {
            for (int j = 0; j < matrixC[0].length; j++) 
            {
                matrixCT[i][j] = matrixC[j][i];
            }
        }
        return matrixCT;
    }

    public static double[][] multiply(double[][] matrix1, double[][] matrix2)
    {
        double[][] matrixRes = new double[matrix1.length][matrix2[0].length];
        if (matrix1[0].length != matrix2.length) throw new IllegalArgumentException("Помилка");
        for (int i = 0; i < matrix1.length; i++) 
        {
            for (int j = 0; j < matrix2[0].length; j++) 
            {
                for (int k = 0; k < matrix1[0].length; k++) 
                {
                    matrixRes[i][j] += matrix1[i][k]*matrix2[k][j];
                }
            }
        }
        return matrixRes;
    }

    public static void main(String[] args) 
    {
        Main de = new Main();
        de.setVisible(true);
    }
}
