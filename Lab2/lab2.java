package lab2;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main extends JPanel
{
    public static boolean isInt( String input )
    {
        try
        {
            Integer.parseInt(input);
            return true;
        }
        catch( Exception e)
        {
            return false;
        }
    }

    private static int[] sortMerge(int[] arr) 
    {

        int len = arr.length;
        if (len < 2) return arr;
        int middle = len / 2;
        return merge(sortMerge(Arrays.copyOfRange(arr, 0, middle)),
                sortMerge(Arrays.copyOfRange(arr, middle, len)));
    }

    private static int[] merge(int[] arr_1, int[] arr_2) 
    {
        int len_1 = arr_1.length, len_2 = arr_2.length;
        int a = 0, b = 0, len = len_1 + len_2;
        int[] result = new int[len];
        for (int i = 0; i < len; i++) 
        {
            if (b < len_2 && a < len_1) 
            {
                if (arr_1[a] > arr_2[b]) result[i] = arr_2[b++];
                else result[i] = arr_1[a++];
            } 
            else if (b < len_2) 
            {
                result[i] = arr_2[b++];
            } 
            else 
            {
                result[i] = arr_1[a++];
            }
        }
        return result;
    }
    public static final int FRAME_WIDTH = 700;
    public static final int FRAME_HEIGHT = 600;
    static int[] x_arr = {1, 6, 36, 216, 1296, 7776, 46656, 279936, 1679616, 10077696};
    static double millis[] = new double[10];
    static double[] y = new double[10];

    public void paint (Graphics g) 
    {
        Graphics2D g2 = (Graphics2D) g;

        g2.draw(new Line2D.Double(69, 100, 69, 450));
        g2.draw(new Line2D.Double(69, 450, 680, 450));
        g2.draw(new Line2D.Double(65, 105, 69, 100));
        g2.draw(new Line2D.Double(69, 100, 73, 105));
        g2.draw(new Line2D.Double(676, 446, 680, 450));
        g2.draw(new Line2D.Double(676, 454, 680, 450));
        g2.drawString("Час виконання,ns", 20, 100);
        g2.drawString("Розмір вхідного масиву,n", 670, 465);

        g2.setFont(new Font("Times New Roman", Font.PLAIN,10));

        for (int i = 0; i < 9; i++) 
        {
            g2.draw(new Line2D.Double(70 + Math.log10(x_arr[i]) / Math.log10(1.03), 600 - Math.log10(millis[i]) / Math.log10(1.05),
                    70 + Math.log10(x_arr[i+1]) / Math.log10(1.03), 600 - Math.log10(millis[i+1]) / Math.log10(1.05)));
            g2.draw(new Ellipse2D.Double(70 + Math.log10(x_arr[i]) / Math.log10(1.03) - 1.5,
                    600 - Math.log10(millis[i]) / Math.log10(1.05) - 1.5, 3, 3));
            g2.drawString(String.format("%15.0f", millis[i]),
                    5, (float)(600 - Math.log10(millis[i]) / Math.log10(1.05)));
            g2.drawString(String.format("%d",x_arr[i]),
                    (float)(70 + Math.log10(x_arr[i]) / Math.log10(1.03)), 465);
        }
        
        g2.drawString(String.format("%15.0f",millis[9]),
                5, (float)(600 - Math.log10(millis[9]) / Math.log10(1.05)));
        g2.drawString(String.format("%d",x_arr[9]),
                (float)(70 + Math.log10(x_arr[9]) / Math.log10(1.03)), 465);
        g2.draw(new Ellipse2D.Double(70 + Math.log10(x_arr[9]) / Math.log10(1.03) - 1.5,
                600 - Math.log10(millis[9]) / Math.log10(1.05) - 1.5, 3, 3));
        g2.setColor(Color.blue);
        
        for (int i = 0; i < 9; i++) 
        {
            g2.draw(new Line2D.Double(70 + Math.log10(x_arr[i]) / Math.log10(1.03), 600 - Math.log10(y[i]) / Math.log10(1.05),
                    70 + Math.log10(x_arr[i+1]) / Math.log10(1.03), 600 - Math.log10(y[i + 1]) / Math.log10(1.05)));
            g2.draw(new Ellipse2D.Double(70 + Math.log10(x_arr[i])/Math.log10(1.03) - 1.5,
                    600 - Math.log10(y[i]) / Math.log10(1.05) - 1.5, 3, 3));
        }
        
        g2.draw(new Ellipse2D.Double(70 + Math.log10(x_arr[9]) / Math.log10(1.03) - 1.5,
                600 - Math.log10(y[9]) / Math.log10(1.05) - 1.5, 3, 3));

    }

    public static void main(String[] args) 
    {
        int[] arr1 = new int[1];
        int[] arr2 = new int[5];
        int[] arr3 = new int[25];
        int[] arr4 = new int[125];
        int[] arr5 = new int[625];
        int[] arr6 = new int[3125];
        int[] arr7 = new int[15625];
        int[] arr8 = new int[78125];
        int[] arr9 = new int[390625];
        int[] arr10 = new int[1953125];
        for (int i = 0; i < 1 ; i++)  arr1[i] = (int)(Math.random()*1953125);
        for (int i = 0; i < 5 ; i++)   arr2[i] = (int)(Math.random()*1953125);
        for (int i = 0; i < 25 ; i++)   arr3[i] = (int)(Math.random()*1953125);
        for (int i = 0; i < 125 ; i++)    arr4[i] = (int)(Math.random()*1953125);
        for (int i = 0; i < 625 ; i++)   arr5[i] = (int)(Math.random()*1953125);
        for (int i = 0; i < 3125 ; i++)   arr6[i] = (int)(Math.random()*1953125);
        for (int i = 0; i < 15625 ; i++)   arr7[i] = (int)(Math.random()*1953125);
        for (int i = 0; i < 78125 ; i++)    arr8[i] = (int)(Math.random()*1953125);
        for (int i = 0; i < 390125 ; i++)   arr9[i] = (int)(Math.random()*1953125);
        for (int i = 0; i < 1953125 ; i++)  arr10[i] = (int)(Math.random()*1953125);


        for (int i = 0; i < 10; i++) millis[i] = System.nanoTime();

        int[] arr_sort1 = sortMerge(arr1);
        millis[0] = System.nanoTime() - millis[0];
        int[] arr_sort2 = sortMerge(arr2);
        millis[1] = System.nanoTime() - millis[1];
        int[] arr_sort3 = sortMerge(arr3);
        millis[2] = System.nanoTime() - millis[2];
        int[] arr_sort4 = sortMerge(arr4);
        millis[3] = System.nanoTime() - millis[3];
        int[] arr_sort5 = sortMerge(arr5);
        millis[4] = System.nanoTime() - millis[4];
        int[] arr_sort6 = sortMerge(arr6);
        millis[5] = System.nanoTime() - millis[5];
        int[] arr_sort7 = sortMerge(arr7);
        millis[6] = System.nanoTime() - millis[6];
        int[] arr_sort8 = sortMerge(arr8);
        millis[7] = System.nanoTime() - millis[7];
        int[] arr_sort9 = sortMerge(arr9);
        millis[8] = System.nanoTime() - millis[8];
        int[] arr_sort10 = sortMerge(arr10);
        millis[9] = System.nanoTime() - millis[9];

        for (int i = 0; i < 10; i++) 
        {
            y[i] = x_arr[i] * Math.log10(x_arr[i] + 6) * millis[i] / x_arr[i];
        }


        JFrame frame = new JFrame();
        frame.setTitle("Лабораторна робота №2");
        frame.setSize(900, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTabbedPane tabby = new JTabbedPane();
        JPanel panel1 = new JPanel();
        GridLayout g1 = new GridLayout(5, 2);
        panel1.setBackground(Color.white);
        panel1.setLayout(g1);
        JLabel label = new JLabel("Алгоритм сортування Боуза-Нельсона");
        JLabel label1 = new JLabel("Введіть масив для сортування через кому та без пробілів:");
        final JTextField field1 = new JTextField();
        final JTextField field_res = new JTextField();
        JLabel label00 = new JLabel(" ");
        JButton file = new JButton("Обрати файл");
        file.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent ae) 
            {
                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                fileChooser.setDialogTitle("Обрати папку");
                fileChooser.showSaveDialog(null);
                try
                {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
                    String line = bufferedReader.readLine();
                    field1.setText(line);

                }
                catch (FileNotFoundException e)
                {
                    System.out.println("Файл не знайдено.");
                }
                catch (IOException e){
                    System.out.println("Помилка при читанні.");
                }
            }
        });
        
        JLabel label_res = new JLabel("Результат сортування: ");
        JButton sort = new JButton("Відсортувати масив");
        JButton graphic = new JButton("Показати графіки");
        sort.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent ae) 
            {
                String str;
                if (field1.getText().isEmpty() ) 
                {
                    field1.setText("Помилка при введенні даних.");
                }
                else 
                {
	                str = field1.getText();
	                String str_pars[] = str.split(",");
	                boolean f = true;
	                for (int i = 0; i < str_pars.length; i++) 
	                {
	                	if (!isInt(str_pars[i]))
	                	{
	                		field1.setText("Помилка при введенні даних.");
	                		f = false;
	                		break;
	                	}
	                }
	                if (f)
	                {
	                	int array_int[] = new int[str_pars.length];
	                	for (int i = 0; i < str_pars.length; i++) 
	                	{
	                		array_int[i] = Integer.valueOf(str_pars[i]);
	                	}
	                	int arr_sorted[] = sortMerge(array_int);
	                	field_res.setText(Arrays.toString(arr_sorted));
	                }
                }
            }
        });
        
        panel1.add(label);
        panel1.add(file);
        panel1.add(label1);
        panel1.add(field1);
        panel1.add(sort);
        panel1.add(label00);
        panel1.add(label_res);
        panel1.add(field_res);
        panel1.add(graphic);
        frame.add(panel1);

        graphic.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent ae) 
            {
                JFrame frame1 = new JFrame();
                frame1.setTitle("Графіки");
                frame1.setSize(FRAME_WIDTH,FRAME_HEIGHT);
                frame1.getContentPane().add(new Main());
                frame1.setVisible(true);
            }
        });

        frame.setVisible(true);
    }
}
