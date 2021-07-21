package calendarium.ui;

import calendarium.bl.DataBaseManager;
import calendarium.db.entity.Event;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.ZoneId;
import javax.swing.JFrame;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyledDocument;

import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;

public class AddEditEventView extends JFrame {
    private final JPanel contentPane;
    private final DataBaseManager manager;

    public AddEditEventView(DataBaseManager manager) {
        this(manager, new Event(), false);
    }

    public AddEditEventView(DataBaseManager manager, Event event) {
        this(manager, event, true);
    }

    private AddEditEventView(DataBaseManager manager, Event event, boolean updating) {
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
        JTextField eventName = new JTextField(event.getName());
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
        startDatePicker.setDate(event.getStartTime().toLocalDate());
        startDatePicker.setBounds(133, 232, 180, 33);
        contentPane.add(startDatePicker);

        JLabel DayTextEnd = new JLabel("End date & time");
        DayTextEnd.setBounds(26, 275, 180, 26);
        contentPane.add(DayTextEnd);

        DatePickerSettings settings2 = new DatePickerSettings();
        settings2.setAllowEmptyDates(false);
        DatePicker endDatePicker = new DatePicker(settings2);
        endDatePicker.setDate(event.getEndTime().toLocalDate());
        endDatePicker.setBounds(133, 269, 180, 34);
        contentPane.add(endDatePicker);

        JTextPane eventDesc = new JTextPane();
        eventDesc.setText(event.getDescription());
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
        startTimePicker.setTime(event.getStartTime().toLocalTime());
        startTimePicker.setBounds(324, 234, 105, 31);
        contentPane.add(startTimePicker);

        TimePickerSettings timePickerSettings2 = new TimePickerSettings();
        timePickerSettings2.setAllowEmptyTimes(false);
        TimePicker endTimePicker = new TimePicker(timePickerSettings2);
        endTimePicker.setTime(event.getEndTime().toLocalTime());
        endTimePicker.setBounds(324, 272, 105, 31);
        contentPane.add(endTimePicker);

        JCheckBox checkNotification = new JCheckBox("Notification", event.isEventNotification());
        checkNotification.setBounds(456, 241, 123, 18);
        contentPane.add(checkNotification);

        JButton btnCreate = new JButton(updating ? "Update event" : "Create event");
        btnCreate.setBounds(451, 272, 109, 32);
        contentPane.add(btnCreate);
        btnCreate.addActionListener(e -> {
            event.setName(eventName.getText());
            event.setDescription(eventDesc.getText());
            event.setStartTime(startDatePicker.getDate().atTime(startTimePicker.getTime()).atZone(ZoneId.systemDefault()));
            event.setEndTime(endDatePicker.getDate().atTime(endTimePicker.getTime()).atZone(ZoneId.systemDefault()));
            event.setEventNotification(checkNotification.isSelected());
            if(updating) {
                manager.updateEvent(event);
            } else {
                manager.addEvent(event);
            }
            setVisible(false);
            dispose();
        });
    }
}