package calendarium.ui;

import calendarium.bl.DataBaseManager;
import calendarium.db.entity.Event;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DayView extends JFrame {
    private JPanel contentPane;
    private int width=680;
    private int height=600;

    public DayView(List<Event> events, DataBaseManager manager){

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, width, height);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {
                Main.trayListener.open();
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
        });

        int startX=30;
        int startY=30;

        if(events==null||events.size()==0){
            JLabel label=new JLabel("There is no events this day.",SwingConstants.CENTER);
            label.setBounds(startX,startY,width-(startX*2),height-(startY*8));
            label.setFont(new Font(Font.SERIF,Font.PLAIN,20));
            contentPane.add(label);
        }

        int deleteWidth=50;
        int width=this.width-(startX*2)-deleteWidth;
        int height=events.size()*30<this.height-(startY*2)?30:this.height-(startY*2)/events.size();

        int i=0;

        for (Event event:events) {
            JButton b=new JButton(event.getName()+": "+
                    DateTimeFormatter.ofPattern("yyyy/MM/dd - hh:mm").format(event.getStartTime()) +
                    (event.getStartTime().equals(event.getEndTime()) ? "" : " - " +
                    DateTimeFormatter.ofPattern("yyyy/MM/dd - hh:mm").format(event.getEndTime())));
            b.setBounds(startX,startY+height*i,width-2,height-2 );
            b.addActionListener(l->{
                AddEditEventView fr = new AddEditEventView(manager, event);
                fr.setVisible(true);
                fr.setResizable(false);
            });
            b.setBackground(new Color(255,100,100));
            contentPane.add(b);
            JButton delete =new JButton("X");
            delete.setFont(new Font(Font.SERIF,Font.BOLD,12));
            delete.setBounds(startX+width,startY+height*i,deleteWidth,height);
            delete.addActionListener(l->{
                try {
                    manager.delete(event);
                    JOptionPane.showMessageDialog(null,"Successfully deleted.");
                    this.setVisible(false);
                    this.dispose();
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }

            });
            delete.setBackground(Color.RED);
            contentPane.add(delete);
            i++;
        }


    }
}
