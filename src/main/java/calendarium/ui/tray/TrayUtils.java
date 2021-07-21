package calendarium.ui.tray;

import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TrayUtils {
    private TrayIcon trayIcon;

    public TrayUtils(TrayListener listener) throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();

        Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("/icon.png"));

        trayIcon = new TrayIcon(image, "Calendarium");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("A simple calendar app");
        tray.add(trayIcon);
        trayIcon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1)
                    listener.open();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        PopupMenu menu = new PopupMenu();
        MenuItem show = new MenuItem("Show");
        show.addActionListener(e -> {
            listener.open();
        });
        menu.add(show);
        MenuItem exit = new MenuItem("Exit");
        exit.addActionListener(e -> {
            tray.remove(trayIcon);
            System.exit(0);
        });
        menu.add(exit);
        trayIcon.setPopupMenu(menu);
    }

    public void displayNotification(String caption, String text) {
        trayIcon.displayMessage(caption, text, MessageType.INFO);
    }

}
