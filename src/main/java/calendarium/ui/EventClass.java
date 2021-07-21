package calendarium.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.ZonedDateTime;
import javax.swing.JFrame;

public class EventClass extends JFrame{
    public static JFrame n;
    private JPanel contentPane;
    public static String name;
    public static String description;
    public static ZonedDateTime startTime;
    public static ZonedDateTime endTime;

    private JTextField AddNameHere;

        public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        EventClass fr = new EventClass();
                        fr.setVisible(true);
                        fr.setResizable(false);
                        fr.setTitle("[Create event]");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    public EventClass() {
        setBounds(100, 100, 660, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);



        JLabel lblNewLabel = new JLabel("Add name of the event:");
        lblNewLabel.setBounds(250, 15, 180, 26);
        contentPane.add(lblNewLabel);

        Font font1 = new Font("SansSerif", Font.BOLD, 24);
        AddNameHere = new JTextField();
        AddNameHere.setBounds(250, 40, 140, 40);
        contentPane.add(AddNameHere);
        AddNameHere.setFont(font1);
        AddNameHere.setColumns(10);


        JLabel DayText = new JLabel("Event start:");
        DayText.setBounds(80, 15, 180, 26);
        contentPane.add(DayText);

        String[] dayStart = { "1", "2", "3", "5", "6","7", "8", "9", "10", "11","12", "13", "14", "15", "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        JComboBox DayStartList = new JComboBox(dayStart);
        DayStartList.setBounds(55, 40, 120, 45);
        contentPane.add(DayStartList);
        DayStartList.setFont(font1);
        DayStartList.setVisible(true);

        JLabel DayTextEnd = new JLabel("Event end:");
        DayTextEnd.setBounds(490, 15, 180, 26);
        contentPane.add(DayTextEnd);

        String[] dayEnd = { "1", "2", "3", "5", "6","7", "8", "9", "10", "11","12", "13", "14", "15", "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        JComboBox DayEndList = new JComboBox(dayEnd);
        DayEndList.setBounds(465, 40, 120, 45);
        contentPane.add(DayEndList);
        DayEndList.setFont(font1);
        DayEndList.setVisible(true);

        JTextPane AddInfoHere = new JTextPane();
        //        f.add(AddInfoHere);
        JScrollPane scrollPane = new JScrollPane(AddInfoHere);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(50, 120, 550, 240);
        contentPane.add(scrollPane);

        }

}
