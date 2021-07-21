package calendarium.ui;

import javax.swing.*;

import java.awt.EventQueue;
import javax.swing.border.EmptyBorder;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;

public class CalendarView extends JFrame{
    public static JFrame n;
    private JPanel contantPane;

    private ArrayList<JButton> calendarDays;

    private int year;

    public CalendarView() {
        calendarDays=new ArrayList<>();

        setBounds(100, 100, 680, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contantPane = new JPanel();
        contantPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contantPane);
        contantPane.setLayout(null);
        addLabelsToDaysOfTheMonth();

        LocalDateTime date=LocalDateTime.now();
        year=date.getYear();
        arrangeViewToMonth(date.getMonth().ordinal());

        JLabel lblYear=new JLabel("Year: ",SwingConstants.CENTER);
        lblYear.setBounds(10,20,100,20);
        contantPane.add(lblYear);

        JTextField txtYear=new JTextField(year+"");
        txtYear.setBounds(100,20,100,20);
        txtYear.addActionListener(a->{
             try {
                year = Integer.parseInt(txtYear.getText());
                LocalDateTime.of(year, 1, 1, 12, 0);
                arrangeViewToMonth(1);
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Invalid year!");
                txtYear.setText(year+"");
            }

        });
        contantPane.add(txtYear);

        initializeMonthButtons();

        JButton addEvent = new JButton("Add event");
        addEvent.addActionListener(e -> {
            AddEventView fr = new AddEventView();
            fr.setVisible(true);
            fr.setResizable(false);
            fr.setTitle("Create event");
        });

        addEvent.setBounds(500, 475, 100, 40);
        contantPane.add(addEvent);
    }

    private int startX=130;
    private int startY=200;
    private int endX=500;
    private int endY=400;

    private void clearButtons(){
        for (JButton b: calendarDays) {
            contantPane.remove(b);
        }
        calendarDays=new ArrayList<>();
        repaint();
    }

    private void  arrangeCalendarDays(int dayOfTheWeek,int countOfDays){
        clearButtons();
        int width=(endX-startX-10)/7;
        int height=(endY-startY-10)/7;
        int plusX=width+10;
        int plusY=height+10;
        int index=0;
        for (int i = 1; i < 7; i++) {
            for (int j = i==1?dayOfTheWeek:0; j < 7; j++) {
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

    private void addLabelsToDaysOfTheMonth(){
        int width=(endX-startX-10)/7;
        int height=(endY-startY-10)/7;
        int plusX=width+10;
        int plusY=height+10;
        JLabel[] labels=new JLabel[7];
        for (int j=0;j<7;j++){
            JLabel l=new JLabel("",SwingConstants.CENTER);
            l.setBounds(startX+j*plusX,startY,width,height);
            contantPane.add(l);
            labels[j]=l;
        }
        labels[0].setText("M");
        labels[1].setText("T");
        labels[2].setText("W");
        labels[3].setText("T");
        labels[4].setText("F");
        labels[5].setText("S");
        labels[6].setText("S");

    }

    private void initializeMonthButtons(){
        JButton JanButton = new JButton("January");
        JanButton.setBounds(10, 50, 100, 40);
        JanButton.addActionListener(e -> {
            arrangeViewToMonth(1);
        });

        contantPane.add(JanButton);

        JButton FebButton = new JButton("February");
        FebButton.setBounds(120, 50, 100, 40);
        FebButton.addActionListener(e -> {
            arrangeViewToMonth(2);
        });
        contantPane.add(FebButton);

        JButton MarchButton = new JButton("March");
        MarchButton.setBounds(230, 50, 100, 40);
        MarchButton.addActionListener(e -> {
            arrangeViewToMonth(3);
        });
        contantPane.add(MarchButton);

        JButton AprilButton = new JButton("April");
        AprilButton.setBounds(340, 50, 100, 40);
        AprilButton.addActionListener(e -> {
            arrangeViewToMonth(4);
        });
        contantPane.add(AprilButton);

        JButton MayButton = new JButton("May");
        MayButton.setBounds(450, 50, 100, 40);
        MayButton.addActionListener(e -> {
            arrangeViewToMonth(5);
        });
        contantPane.add(MayButton);

        JButton JuneButton = new JButton("June");
        JuneButton.setBounds(560, 50, 100, 40);
        JuneButton.addActionListener(e -> {
            arrangeViewToMonth(6);
        });
        contantPane.add(JuneButton);

        JButton JulyButton = new JButton("July");
        JulyButton.setBounds(10, 100, 100, 40);
        JulyButton.addActionListener(e -> {
            arrangeViewToMonth(7);
        });
        contantPane.add(JulyButton);

        JButton AugustButton = new JButton("August");
        AugustButton.setBounds(120, 100, 100, 40);
        AugustButton.addActionListener(e -> {
            arrangeViewToMonth(8);
        });
        contantPane.add(AugustButton);

        JButton SeptemberButton = new JButton("September");
        SeptemberButton.setBounds(230, 100, 100, 40);
        SeptemberButton.addActionListener(e -> {
            arrangeViewToMonth(9);
        });
        contantPane.add(SeptemberButton);

        JButton OctoberButton = new JButton("October");
        OctoberButton.setBounds(340, 100, 100, 40);
        OctoberButton.addActionListener(e -> {
            arrangeViewToMonth(10);
        });
        contantPane.add(OctoberButton);

        JButton NovemberButton = new JButton("November");
        NovemberButton.setBounds(450, 100, 100, 40);
        NovemberButton.addActionListener(e -> {
            arrangeViewToMonth(11);
        });
        contantPane.add(NovemberButton);

        JButton DecemberButton = new JButton("December");
        DecemberButton.setBounds(560, 100, 100, 40);
        DecemberButton.addActionListener(e -> arrangeViewToMonth(12));
        contantPane.add(DecemberButton);
    }

    private void arrangeViewToMonth(int month){
        setTitle("Calendarium -- "+year+" - "+LocalDateTime.of(year, month, 1, 12, 0).getMonth());
        arrangeCalendarDays(
                LocalDateTime.of(year, month, 1, 12, 0).getDayOfWeek().ordinal(),
                YearMonth.of(LocalDateTime.now().getYear(), month).lengthOfMonth()
        );
    }

}
