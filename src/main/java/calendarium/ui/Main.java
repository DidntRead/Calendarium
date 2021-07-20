package calendarium.ui;

import javax.swing.*;

import java.awt.EventQueue;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Main extends JFrame{
    public static JFrame n;
    private JPanel contantPane;

    private ArrayList<JButton> calendarDays;

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
        calendarDays=new ArrayList<>();

        setBounds(100, 100, 680, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contantPane = new JPanel();
        contantPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contantPane);
        contantPane.setLayout(null);



        JButton JanButton = new JButton("January");
        JanButton.setBounds(10, 30, 100, 45);
        JanButton.addActionListener(e -> {
            int month=1;
            arrangeCalendarDays(

                    LocalDateTime.of(2021, month, 1, 12, 0).getDayOfWeek().ordinal(),
                    YearMonth.of(LocalDateTime.now().getYear(), month).lengthOfMonth()
            );
        });

        contantPane.add(JanButton);

        JButton FebButton = new JButton("February");
        FebButton.setBounds(120, 30, 100, 45);
        FebButton.addActionListener(e -> {
            int month=2;
            arrangeCalendarDays(

                    LocalDateTime.of(2021, month, 1, 12, 0).getDayOfWeek().ordinal(),
                    YearMonth.of(LocalDateTime.now().getYear(), month).lengthOfMonth()
            );
        });
        contantPane.add(FebButton);

        JButton MarchButton = new JButton("March");
        MarchButton.setBounds(230, 30, 100, 45);
        MarchButton.addActionListener(e -> {
            int month=3;
            arrangeCalendarDays(

                    LocalDateTime.of(2021, month, 1, 12, 0).getDayOfWeek().ordinal(),
                    YearMonth.of(LocalDateTime.now().getYear(), month).lengthOfMonth()
            );
        });
        contantPane.add(MarchButton);

        JButton AprilButton = new JButton("April");
        AprilButton.setBounds(340, 30, 100, 45);
        AprilButton.addActionListener(e -> {
            int month=4;
            arrangeCalendarDays(

                    LocalDateTime.of(2021, month, 1, 12, 0).getDayOfWeek().ordinal(),
                    YearMonth.of(LocalDateTime.now().getYear(), month).lengthOfMonth()
            );
        });
        contantPane.add(AprilButton);

        JButton MayButton = new JButton("May");
        MayButton.setBounds(450, 30, 100, 45);
        MayButton.addActionListener(e -> {
            int month=5;
            arrangeCalendarDays(

                    LocalDateTime.of(2021, month, 1, 12, 0).getDayOfWeek().ordinal(),
                    YearMonth.of(LocalDateTime.now().getYear(), month).lengthOfMonth()
            );
        });
        contantPane.add(MayButton);

        JButton JuneButton = new JButton("June");
        JuneButton.setBounds(560, 30, 100, 45);
        JuneButton.addActionListener(e -> {
            int month=6;
            arrangeCalendarDays(

                    LocalDateTime.of(2021, month, 1, 12, 0).getDayOfWeek().ordinal(),
                    YearMonth.of(LocalDateTime.now().getYear(), month).lengthOfMonth()
            );
        });
        contantPane.add(JuneButton);

        JButton JulyButton = new JButton("July");
        JulyButton.setBounds(10, 90, 100, 45);
        JulyButton.addActionListener(e -> {
            int month=7;
            arrangeCalendarDays(

                    LocalDateTime.of(2021, month, 1, 12, 0).getDayOfWeek().ordinal(),
                    YearMonth.of(LocalDateTime.now().getYear(), month).lengthOfMonth()
            );
        });
        contantPane.add(JulyButton);

        JButton AugustButton = new JButton("August");
        AugustButton.setBounds(120, 90, 100, 45);
        AugustButton.addActionListener(e -> {
            int month=8;
            arrangeCalendarDays(

                    LocalDateTime.of(2021, month, 1, 12, 0).getDayOfWeek().ordinal(),
                    YearMonth.of(LocalDateTime.now().getYear(), month).lengthOfMonth()
            );
        });
        contantPane.add(AugustButton);

        JButton SeptemberButton = new JButton("September");
        SeptemberButton.setBounds(230, 90, 100, 45);
        SeptemberButton.addActionListener(e -> {
            int month=9;
            arrangeCalendarDays(

                    LocalDateTime.of(2021, month, 1, 12, 0).getDayOfWeek().ordinal(),
                    YearMonth.of(LocalDateTime.now().getYear(), month).lengthOfMonth()
            );
        });
        contantPane.add(SeptemberButton);

        JButton OctoberButton = new JButton("October");
        OctoberButton.setBounds(340, 90, 100, 45);
        OctoberButton.addActionListener(e -> {
            int month=10;
            arrangeCalendarDays(

                    LocalDateTime.of(2021, month, 1, 12, 0).getDayOfWeek().ordinal(),
                    YearMonth.of(LocalDateTime.now().getYear(), month).lengthOfMonth()
            );
        });
        contantPane.add(OctoberButton);

        JButton NovemberButton = new JButton("November");
        NovemberButton.setBounds(450, 90, 100, 45);
        NovemberButton.addActionListener(e -> {
            int month=11;
            arrangeCalendarDays(

                    LocalDateTime.of(2021, month, 1, 12, 0).getDayOfWeek().ordinal(),
                    YearMonth.of(LocalDateTime.now().getYear(), month).lengthOfMonth()
            );
        });
        contantPane.add(NovemberButton);

        JButton DecemberButton = new JButton("December");
        DecemberButton.setBounds(560, 90, 100, 45);
        DecemberButton.addActionListener(e -> {
            int month=12;
            arrangeCalendarDays(

                    LocalDateTime.of(2021, month, 1, 12, 0).getDayOfWeek().ordinal(),
                    YearMonth.of(LocalDateTime.now().getYear(), month).lengthOfMonth()
            );
        });
        contantPane.add(DecemberButton);

        JButton SelectDate = new JButton("Add event");
        SelectDate.addActionListener(e -> {
            // TODO Auto-generated method stub
            //Dunno.setVisible(false);
           // new EventClass().setVisible(true);
        });

        SelectDate.setBounds(500, 475, 100, 45);
        contantPane.add(SelectDate);
    }

    private void arrangeCalendarDays(int dayOfTheWeek,int countOfDays){
        for (JButton b:
             calendarDays) {
            contantPane.remove(b);
        }
        calendarDays=new ArrayList<>();
        int startX=30;
        int startY=150;
        int endX=600;
        int endY=450;
        int width=(endX-startX-10)/7;
        int height=(endY-startY-10)/6;
        int plusX=width+10;
        int plusY=height+10;
        int index=0;
        for (int i = 0; i < 6; i++) {
            for (int j = i==0?dayOfTheWeek:0; j < 7; j++) {
                JButton b=new JButton();
                b.setBounds(startX+j*plusX,startY+i*plusY,width,height);
                b.setText(++index+"");
                contantPane.add(b);
                calendarDays.add(b);
                if (index==countOfDays){
                    repaint();
                    return;
                }
            }
        }
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
