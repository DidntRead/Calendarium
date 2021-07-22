package calendarium.ui;

import calendarium.bl.DataBaseManager;
import calendarium.db.entity.Event;
import calendarium.ui.tray.TrayListener;
import calendarium.ui.tray.TrayUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    private static CalendarView frame;
    private static DataBaseManager dataBaseManager;
    private static TrayUtils td;
    private static java.util.Timer timer;

    public static void main(String[] args) {
        dataBaseManager = new DataBaseManager();

        timer = new Timer();

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(() -> {
            try {
               trayListener.open();
                if (SystemTray.isSupported()) {
                    td = new TrayUtils(trayListener);
                    handleNotifications(td, dataBaseManager);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void handleNotifications(TrayUtils td, DataBaseManager dataBaseManager) {
        Event ev;
        timer.cancel();
        timer = new Timer();
        if((ev = dataBaseManager.getNextEventWithNotification()) != null) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    td.displayNotification(ev.getName(), ev.getDescription());
                    handleNotifications(td, dataBaseManager);
                }
            }, Date.from(ev.getStartTime().toInstant()));
        }
    }

    public static TrayListener trayListener=new TrayListener() {
        @Override
        public void open() {
            handleNotifications(td, dataBaseManager);
            if(frame == null) {
                frame = new CalendarView(dataBaseManager);
                frame.setVisible(true);
                frame.setResizable(false);
                frame.addWindowListener(windowListener);
            } else {
                int sta = frame.getExtendedState() & ~JFrame.ICONIFIED & JFrame.NORMAL;
                frame.setExtendedState(sta);
                frame.setAlwaysOnTop(true);
                frame.toFront();
                frame.requestFocus();
                frame.setAlwaysOnTop(false);
                frame.requestFocus();
                frame.repaint();
            }
        }

        @Override
        public void exit() {
            frame.setVisible(false);
            frame.dispose();
            frame = null;
        }
    };

    private static WindowListener windowListener = new WindowListener() {
        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {

        }

        @Override
        public void windowClosed(WindowEvent e) {
            frame = null;
        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    };

}
