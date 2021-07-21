package calendarium.ui;

import calendarium.bl.DataBaseManager;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import javax.swing.JFrame;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;

public class AddEventView extends JFrame {
    private final JPanel contentPane;
    private final DataBaseManager manager;

    public AddEventView(DataBaseManager manager) {
        this.manager = manager;

        setTitle("Calendarium - create event");
        setBounds(100, 100, 601, 352);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblEventName = new JLabel("Event name");
        lblEventName.setBounds(26, 6, 109, 26);
        contentPane.add(lblEventName);

        Font font1 = new Font("SansSerif", Font.BOLD, 24);
        JTextField eventName = new JTextField();
        eventName.setBounds(26, 31, 534, 40);
        contentPane.add(eventName);
        eventName.setFont(font1);
        eventName.setColumns(10);

        JLabel DayText = new JLabel("Start date & time");
        DayText.setBounds(26, 237, 180, 26);
        contentPane.add(DayText);

        DatePickerSettings settings = new DatePickerSettings();
        settings.setAllowEmptyDates(false);
        DatePicker startDatePicker = new DatePicker(settings);
        startDatePicker.setBounds(133, 232, 180, 33);
        contentPane.add(startDatePicker);

        JLabel DayTextEnd = new JLabel("End date & time");
        DayTextEnd.setBounds(26, 275, 180, 26);
        contentPane.add(DayTextEnd);

        DatePickerSettings settings2 = new DatePickerSettings();
        settings2.setAllowEmptyDates(false);
        DatePicker endDatePicker = new DatePicker(settings2);
        endDatePicker.setBounds(133, 269, 180, 34);
        contentPane.add(endDatePicker);

        JTextPane eventDesc = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(eventDesc);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(26, 100, 534, 126);
        contentPane.add(scrollPane);

        JLabel lblEventDesc = new JLabel("Event description");
        lblEventDesc.setBounds(26, 82, 126, 14);
        contentPane.add(lblEventDesc);

        TimePickerSettings timePickerSettings = new TimePickerSettings();
        timePickerSettings.setAllowEmptyTimes(false);
        TimePicker startTimePicker = new TimePicker(timePickerSettings);
        startTimePicker.setBounds(324, 234, 105, 31);
        contentPane.add(startTimePicker);

        TimePickerSettings timePickerSettings2 = new TimePickerSettings();
        timePickerSettings2.setAllowEmptyTimes(false);
        TimePicker endTimePicker = new TimePicker(timePickerSettings2);
        endTimePicker.setBounds(324, 272, 105, 31);
        contentPane.add(endTimePicker);

        JCheckBox checkNotification = new JCheckBox("Notification");
        checkNotification.setBounds(456, 241, 123, 18);
        contentPane.add(checkNotification);

        JButton btnCreate = new JButton("Create event");
        btnCreate.setBounds(451, 272, 109, 32);
        contentPane.add(btnCreate);
        btnCreate.addActionListener(e -> {
            setVisible(false);
            dispose();
            manager.addEvent(eventName.getText(), eventDesc.getText(), startDatePicker.getDate().atTime(startTimePicker.getTime()).atZone(ZoneId.systemDefault()), endDatePicker.getDate().atTime(endTimePicker.getTime()).atZone(ZoneId.systemDefault()), checkNotification.isSelected());
        });
    }
}