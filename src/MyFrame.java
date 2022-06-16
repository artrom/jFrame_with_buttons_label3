import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

public class MyFrame extends JFrame {
    public static int countRed = 0;
    public static int countIndigo = 0;
    public static String str = "";
    private String text = "Рисунок 1";
    MyFrame(int x, int y){
        super();
        setTitle("Окно с кнопками и меткой");
        setBounds(x, y, 400, 400);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        Font f = new Font(Font.DIALOG, Font.BOLD|Font.ITALIC, 13);
        JPanel p = new JPanel();
        p.setBounds(10, 50, 370, 200);
        p.setBackground(Color.LIGHT_GRAY);
        p.setLayout(new BorderLayout());

        JLabel metka1 =new JLabel(text);
        metka1.setForeground(Color.BLUE);
        metka1.setHorizontalAlignment(JLabel.LEFT);
        metka1.setFont(f);
        metka1.setBounds(10,10,370,50);
        add(metka1);

        JLabel metka2 =new JLabel("Рисунок 2");
        metka2.setForeground(Color.BLUE);
        metka2.setHorizontalAlignment(JLabel.RIGHT);
        metka2.setFont(f);
        metka2.setBounds(10,10,370,50);
        add(metka2);

        JLabel l = new JLabel();
        l.setHorizontalAlignment(JLabel.LEFT);

        JLabel r = new JLabel();
        r.setHorizontalAlignment(JLabel.RIGHT);
        r.setForeground(Color.BLUE);
        r.setFont(f);

        add(p);

        p.add(r, BorderLayout.EAST);
        p.add(l, BorderLayout.WEST);

        //Подсчет красных пикселей в рисунке 1
        JButton A = new JButton("Загрузить рис 1");
        A.setBounds(30,260,150,30);
        A.setFont(f);
        A.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showOpenDialog(fileChooser);
                if(option == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();

                    ImageIcon icon = new ImageIcon(String.valueOf(file));
                    Image image = icon.getImage();
                    Image newimg = image.getScaledInstance(170, 170,  java.awt.Image.SCALE_SMOOTH);
                    icon = new ImageIcon(newimg);
                    l.setIcon(icon);

                    try {
                        System.out.println("Рисунок 1:");

                        BufferedImage image1 = ImageIO.read(file);

                        int width = image1.getWidth();
                        int height = image1.getHeight();

                        System.out.println("Image width: " + width + " Image height: " + height);
                        int j = width*height;
                        int[] pixels = new int[j];
                        System.out.println("Всего пикселей: " + j);

                        // Retrieve pixel info and store in 'pixels' variable
                        PixelGrabber pgb = new PixelGrabber(image1, 0, 0, width, height, pixels, 0, width);
                        pgb.grabPixels();

                        //int countIndigo = 0;
                        //int countRed = 0;

                        for (int i = 0; i < width*height; i++) {
                            Color color = new Color(pixels[i]);
                            //System.out.println(color);

                            if (color.equals(new Color(237, 27, 36))){
                                countRed = countRed + 1;
                                //System.out.println(countRed);
                            }

                            /*if (color.equals(new Color(63, 71, 204))){
                                countIndigo = countIndigo + 1;
                            }*/
                        }

                        System.out.println("Красный: " + countRed + " пикселей");
                        System.out.println("Индиго: " + countIndigo + " пикселей");

                        //Всего пикселей - красные, Руда - индиго пиксели, доля руды = индиго/красный
                        if (countRed > 0 & countIndigo > 0){
                            double ruda = countIndigo/countRed;
                            System.out.println("Отношение руды и породы: " + ruda);
                        }

                    } catch (IOException | InterruptedException ioException) {
                        ioException.printStackTrace();
                    }

                }else{
                    l.setText("Open command canceled");
                }
            }
        });
        add(A);

        //Подсчет индиго пикселей в рисунке 2
        JButton B = new JButton("Загрузить рис 2");
        B.setFont(f);
        B.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showOpenDialog(fileChooser);
                if(option == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();

                    ImageIcon icon = new ImageIcon(String.valueOf(file));
                    Image image = icon.getImage();
                    Image newimg = image.getScaledInstance(170, 170,  java.awt.Image.SCALE_SMOOTH);
                    icon = new ImageIcon(newimg);
                    r.setIcon(icon);

                    try {
                        System.out.println("Рисунок 2:");

                        BufferedImage image1 = ImageIO.read(file);

                        int width = image1.getWidth();
                        int height = image1.getHeight();

                        System.out.println("Image width: " + width + " Image height: " + height);
                        int j = width*height;
                        int[] pixels = new int[j];

                        System.out.println("Всего пикселей: " + j);

                        // Retrieve pixel info and store in 'pixels' variable
                        PixelGrabber pgb = new PixelGrabber(image, 0, 0, width, height, pixels, 0, width);
                        pgb.grabPixels();

                        //int countIndigo = 0;
                        //int countRed = 0;

                        for (int i = 0; i < width*height; i++) {
                            Color color = new Color(pixels[i]);
                            //System.out.println(color);

                            /*if (color.equals(new Color(237, 27, 36))){
                                countRed = countRed + 1;
                            }*/

                            if (color.equals(new Color(63, 71, 204))){
                                countIndigo = countIndigo + 1;
                            }
                        }

                        System.out.println("Красный: " + countRed + " пикселей");
                        System.out.println("Индиго: " + countIndigo + " пикселей");

                        //Всего пикселей - красные, Руда - индиго пиксели, доля руды = индиго/красный
                        if (countRed > 0 & countIndigo > 0){
                            double ruda = countIndigo/countRed;
                            System.out.println("Отношение руды и породы: " + ruda);
                        }
                    } catch (IOException | InterruptedException ioException) {
                        ioException.printStackTrace();
                    }

                }else{
                    r.setText("Open command canceled");
                }
            }
        });
        B.setBounds(200, 260, 150,30);
        add(B);
        //setVisible(true);

        JButton C = new JButton("Отношение");
        C.setFont(f);
        C.setBounds(200,300,150,30);
        add(C);
        setVisible(true);

        C.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {

                        if (countIndigo > 0) {
                            double result = (double) countRed/countIndigo;
                            System.out.println(result);
                            str = "Отношение красный/индиго: " + result;
                        }
                        showMessageDialog(null, str);
                    }
                }
        );
    }

}
class Demo{
    public static void main(String[] args) {
        new MyFrame(400, 300);
    }
}