package calendarium.ui;

import calendarium.bl.DataBaseManager;
import calendarium.db.entity.Event;

import javax.swing.*;

import java.awt.*;
import javax.swing.border.EmptyBorder;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.*;
import java.util.List;

public class CalendarView extends JFrame{
    private JPanel contentPane;
    private ArrayList<JButton> calendarDays;
    private JPanel daysPanel;
    private DataBaseManager dataBaseManager;
    private int year;

    public CalendarView(DataBaseManager dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
        calendarDays=new ArrayList<>();

        setBounds(100, 100, 685, 598);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        daysPanel = new JPanel();
        daysPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        daysPanel.setBounds(10, 150, 650, 358);
        daysPanel.setLayout(null);
        contentPane.add(daysPanel);
        addLabelsToDaysOfTheMonth();

        LocalDateTime date=LocalDateTime.now();
        year=date.getYear();
        arrangeViewToMonth(date.getMonth().getValue());

        JLabel lblYear=new JLabel("Year: ",SwingConstants.CENTER);
        lblYear.setBounds(10,18,100,20);
        contentPane.add(lblYear);

        JTextField txtYear=new JTextField(year+"");
        txtYear.setBounds(116, 13, 100, 30);
        txtYear.addActionListener(a->{
             try {
                year = Integer.parseInt(txtYear.getText());
                arrangeViewToMonth(1);
            } catch (Exception e){
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
            this.setVisible(false);
            this.dispose();
        });

        addEvent.setBounds(560, 513, 100, 40);
        contentPane.add(addEvent);

        JButton btnYearMinus = new JButton("<");
        btnYearMinus.setBounds(82, 14, 35, 28);
        btnYearMinus.addActionListener(e -> {
            year--;
            arrangeViewToMonth(1);
            txtYear.setText(String.valueOf(year));
        });
        contentPane.add(btnYearMinus);

        JButton btnYearPlus = new JButton(">");
        btnYearPlus.setBounds(217, 14, 35, 28);
        btnYearPlus.addActionListener(e -> {
            year++;
            arrangeViewToMonth(1);
            txtYear.setText(String.valueOf(year));
        });
        contentPane.add(btnYearPlus);
    }

    private int startX=10;
    private int startY=0;
    private int endX=600;
    private int endY=300;

    private void clearButtons(){
        for (JButton b: calendarDays) {
            daysPanel.remove(b);
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
                    eventList.sort(Comparator.comparing(e -> e.getStartTime().toInstant()));
                    DayView dayView=new DayView(eventList, dataBaseManager);
                    dayView.setTitle(getTitle()+" - "+ finalIndex);
                    dayView.setVisible(true);
                    this.setVisible(false);
                    this.dispose();
                });
                daysPanel.add(b);
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
            daysPanel.add(l);
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
            int i =event.getStartTime().getMonth().getValue()==month?event.getStartTime().getDayOfMonth():1;
            int n=event.getEndTime().getMonth().getValue()==month?event.getEndTime().getDayOfMonth(): YearMonth.of(LocalDateTime.now().getYear(), month).lengthOfMonth();
            for (;i<=n;i++){
                calendarDays.get(i-1).setBackground(Color.RED);
            }
        }
    }

    private void initializeMonthButtons(){
        for(int i = 1; i <= 12; i++) {
            int temp = i;
            JButton button = new JButton(Month.of(i).getDisplayName(TextStyle.FULL, Locale.getDefault()));
            button.setBounds(10 + ((i > 6 ? (i - 7) : (i - 1)) * 110), i > 6 ? 100 : 50, 100, 40);
            button.addActionListener(e -> arrangeViewToMonth(temp));
            contentPane.add(button);
        }
    }
}
