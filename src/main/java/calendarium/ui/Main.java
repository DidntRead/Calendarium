package calendarium.ui;

import net.bytebuddy.dynamic.scaffold.TypeInitializer;

import javax.swing.*;

import java.awt.*;
import java.awt.EventQueue;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame{
    public static JFrame n;
    private JPanel f;

    public static int xDate [] = new int[31];
    public static int yDate [] = new int[31];

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main frame = new Main();
                    frame.setVisible(true);
                    frame.setResizable(false);
                    frame.setTitle("Calendarium");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public Main() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 682, 600);
        f = new JPanel();
        f.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(f);
        f.setLayout(null);

        JButton JanButton = new JButton("January");
        JanButton.setBounds(10, 30, 100, 45);
        f.add(JanButton);

        JButton FebButton = new JButton("February");
        FebButton.setBounds(120, 30, 100, 45);
        f.add(FebButton);

        JButton MarchButton = new JButton("March");
        MarchButton.setBounds(230, 30, 100, 45);
        f.add(MarchButton);

        JButton AprilButton = new JButton("April");
        AprilButton.setBounds(340, 30, 100, 45);
        f.add(AprilButton);

        JButton MayButton = new JButton("May");
        MayButton.setBounds(450, 30, 100, 45);
        f.add(MayButton);


        JButton JuneButton = new JButton("June");
        JuneButton.setBounds(560, 30, 100, 45);
        f.add(JuneButton);


        JButton JulyButton = new JButton("July");
        JulyButton.setBounds(10, 90, 100, 45);
        f.add(JulyButton);

        JButton AugustButton = new JButton("August");
        AugustButton.setBounds(120, 90, 100, 45);
        f.add(AugustButton);

        JButton SeptemberButton = new JButton("September");
        SeptemberButton.setBounds(230, 90, 100, 45);
        f.add(SeptemberButton);

        JButton OctoberButton = new JButton("October");
        OctoberButton.setBounds(340, 90, 100, 45);
        f.add(OctoberButton);

        JButton NovemberButton = new JButton("November");
        NovemberButton.setBounds(450, 90, 100, 45);
        f.add(NovemberButton);

        JButton DecemberButton = new JButton("December");
        DecemberButton.setBounds(560, 90, 100, 45);
        f.add(DecemberButton);




    }
 //   public void paint(Graphics g) {
//paint(g);

        /*g.drawLine(70, 200, 600,200);

        g.drawLine(70, 250, 600,250);

        g.drawLine(70, 300, 600,300);

        g.drawLine(70, 350, 600,350);

        g.drawLine(70, 400, 600,400);

        g.drawLine(70, 450, 600,450);

        g.drawLine(70, 500, 600,500);

        g.drawLine(70, 550, 600,550);*/
/*for(int i=0;i<40){
        g.drawRect();
    }
*/
   // }


    }
