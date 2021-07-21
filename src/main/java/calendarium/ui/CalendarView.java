package calendarium.ui;

import calendarium.bl.DataBaseManager;
import calendarium.db.entity.Event;

import javax.swing.*;

import java.awt.*;
import javax.swing.border.EmptyBorder;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CalendarView extends JFrame{
    public static JFrame n;
    private JPanel contentPane;

    private ArrayList<JButton> calendarDays;

    private DataBaseManager dataBaseManager;

    private int year;

    public CalendarView() {
        dataBaseManager=new DataBaseManager();

        calendarDays=new ArrayList<>();

        setBounds(100, 100, 680, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        addLabelsToDaysOfTheMonth();

        LocalDateTime date=LocalDateTime.now();
        year=date.getYear();
        arrangeViewToMonth(date.getMonth().getValue());

        JLabel lblYear=new JLabel("Year: ",SwingConstants.CENTER);
        lblYear.setBounds(10,20,100,20);
        contentPane.add(lblYear);

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
        contentPane.add(txtYear);

        initializeMonthButtons();

        JButton addEvent = new JButton("Add event");
        addEvent.addActionListener(e -> {
            AddEditEventView fr = new AddEditEventView(dataBaseManager);
            fr.setVisible(true);
            fr.setResizable(false);
        });

        addEvent.setBounds(500, 475, 100, 40);
        contentPane.add(addEvent);
    }

    private int startX=130;
    private int startY=200;
    private int endX=500;
    private int endY=400;

    private void clearButtons(){
        for (JButton b: calendarDays) {
            contentPane.remove(b);
        }
        calendarDays=new ArrayList<>();
        repaint();
    }

    private void  arrangeCalendarDays(int dayOfTheWeek,int countOfDays,int month){
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
                int finalIndex = index;
                b.addActionListener(l->{
                    List<Event> eventList=new ArrayList<>();
                    Iterator<Event> eventIterator=dataBaseManager.getEventsContainingDate(ZonedDateTime.of(year,month,finalIndex,0,0,0,0,ZonedDateTime.now().getZone()));
                    eventIterator.forEachRemaining(eventList::add);
                    DayView dayView=new DayView(eventList, dataBaseManager);
                    dayView.setTitle(getTitle()+" - "+ finalIndex);
                    dayView.setVisible(true);
                });
                contentPane.add(b);
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
            contentPane.add(l);
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

    private void arrangeViewToMonth(int month){
        setTitle("Calendarium -- "+year+" - "+LocalDateTime.of(year, month, 1, 12, 0).getMonth());
        arrangeCalendarDays(
                LocalDateTime.of(year, month, 1, 12, 0).getDayOfWeek().ordinal(),
                YearMonth.of(LocalDateTime.now().getYear(), month).lengthOfMonth(),
                month
        );
        fillDaysWithEvents(month);
    }

    private void fillDaysWithEvents(int month){
        Iterator<Event> events=dataBaseManager.getEventsBetween(
                ZonedDateTime.of(year,month,1,12,0,0,0,ZonedDateTime.now().getZone()),
                ZonedDateTime.of(year,month,YearMonth.of(LocalDateTime.now().getYear(), month).lengthOfMonth(),12,0,0,0,ZonedDateTime.now().getZone())
        );
        for (Iterator<Event> it = events; it.hasNext(); ) {
            Event event = it.next();
            int i =event.getStartTime().getMonth().getValue()==month?event.getStartTime().getDayOfMonth():0;
            int n=event.getEndTime().getMonth().getValue()==month?event.getEndTime().getDayOfMonth(): YearMonth.of(LocalDateTime.now().getYear(), month).lengthOfMonth();
            for (;i<=n;i++){
                calendarDays.get(i-1).setBackground(Color.RED);
            }
        }
    }

    private void initializeMonthButtons(){
        JButton JanButton = new JButton("January");
        JanButton.setBounds(10, 50, 100, 40);
        JanButton.addActionListener(e -> {
            arrangeViewToMonth(1);
        });

        contentPane.add(JanButton);

        JButton FebButton = new JButton("February");
        FebButton.setBounds(120, 50, 100, 40);
        FebButton.addActionListener(e -> {
            arrangeViewToMonth(2);
        });
        contentPane.add(FebButton);

        JButton MarchButton = new JButton("March");
        MarchButton.setBounds(230, 50, 100, 40);
        MarchButton.addActionListener(e -> {
            arrangeViewToMonth(3);
        });
        contentPane.add(MarchButton);

        JButton AprilButton = new JButton("April");
        AprilButton.setBounds(340, 50, 100, 40);
        AprilButton.addActionListener(e -> {
            arrangeViewToMonth(4);
        });
        contentPane.add(AprilButton);

        JButton MayButton = new JButton("May");
        MayButton.setBounds(450, 50, 100, 40);
        MayButton.addActionListener(e -> {
            arrangeViewToMonth(5);
        });
        contentPane.add(MayButton);

        JButton JuneButton = new JButton("June");
        JuneButton.setBounds(560, 50, 100, 40);
        JuneButton.addActionListener(e -> {
            arrangeViewToMonth(6);
        });
        contentPane.add(JuneButton);

        JButton JulyButton = new JButton("July");
        JulyButton.setBounds(10, 100, 100, 40);
        JulyButton.addActionListener(e -> {
            arrangeViewToMonth(7);
        });
        contentPane.add(JulyButton);

        JButton AugustButton = new JButton("August");
        AugustButton.setBounds(120, 100, 100, 40);
        AugustButton.addActionListener(e -> {
            arrangeViewToMonth(8);
        });
        contentPane.add(AugustButton);

        JButton SeptemberButton = new JButton("September");
        SeptemberButton.setBounds(230, 100, 100, 40);
        SeptemberButton.addActionListener(e -> {
            arrangeViewToMonth(9);
        });
        contentPane.add(SeptemberButton);

        JButton OctoberButton = new JButton("October");
        OctoberButton.setBounds(340, 100, 100, 40);
        OctoberButton.addActionListener(e -> {
            arrangeViewToMonth(10);
        });
        contentPane.add(OctoberButton);

        JButton NovemberButton = new JButton("November");
        NovemberButton.setBounds(450, 100, 100, 40);
        NovemberButton.addActionListener(e -> {
            arrangeViewToMonth(11);
        });
        contentPane.add(NovemberButton);

        JButton DecemberButton = new JButton("December");
        DecemberButton.setBounds(560, 100, 100, 40);
        DecemberButton.addActionListener(e -> arrangeViewToMonth(12));
        contentPane.add(DecemberButton);
    }



}
