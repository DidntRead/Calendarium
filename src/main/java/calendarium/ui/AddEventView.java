package calendarium.ui;

import calendarium.bl.DataBaseManager;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.ZonedDateTime;
import javax.swing.JFrame;

public class AddEventView extends JFrame{
    public static JFrame n;
    private JPanel contentPane;
    public static String name;
    public static String description;
    public static ZonedDateTime startTime;
    public static ZonedDateTime endTime;

    private JTextField AddNameHere;
    private DataBaseManager dataBaseManager;

    public AddEventView(DataBaseManager dataBaseManager) {
        this.dataBaseManager=dataBaseManager;
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

        DatePickerSettings settings = new DatePickerSettings();
        settings.setAllowEmptyDates(false);
        DatePicker datePicker = new DatePicker(settings);
        datePicker.setBounds(55, 40, 160, 45);
        contentPane.add(datePicker);

        JLabel DayTextEnd = new JLabel("Event end:");
        DayTextEnd.setBounds(490, 15, 180, 26);
        contentPane.add(DayTextEnd);

        DatePickerSettings settings2 = new DatePickerSettings();
        settings2.setAllowEmptyDates(false);
        DatePicker datePicker2 = new DatePicker(settings2);
        datePicker2.setBounds(465, 40, 160, 45);
        contentPane.add(datePicker2);

        JTextPane AddInfoHere = new JTextPane();
        //        f.add(AddInfoHere);
        JScrollPane scrollPane = new JScrollPane(AddInfoHere);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(50, 120, 550, 240);
        contentPane.add(scrollPane);

    }

}