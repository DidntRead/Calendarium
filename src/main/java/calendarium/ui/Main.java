package calendarium.ui;

import calendarium.ui.tray.TrayListener;
import calendarium.ui.tray.TrayUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Main {
    private static CalendarView frame;

    public static void main(String[] args) {
        WindowListener windowListener = new WindowListener() {
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
        EventQueue.invokeLater(() -> {
            try {
                frame = new CalendarView();
                frame.addWindowListener(windowListener);
                frame.setVisible(true);
                frame.setResizable(false);
                if (SystemTray.isSupported()) {
                    TrayUtils td = new TrayUtils(new TrayListener() {
                        @Override
                        public void open() {
                            if(frame == null) {
                                frame = new CalendarView();
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
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
