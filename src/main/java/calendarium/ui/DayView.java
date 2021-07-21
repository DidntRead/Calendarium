package calendarium.ui;

import calendarium.db.entity.Event;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DayView extends JFrame {
    private JPanel contentPane;
    private int width=680;
    private int height=600;

    public DayView(List<Event> events){

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, width, height);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        if(events==null)return;
        //List<JButton> eventButtons=new ArrayList<>();
        int startX=30;
        int startY=30;
        int width=this.width-(startX*2);
        int height=events.size()*30<this.height-(startY*2)?30:this.height-(startY*2)/events.size();

        int i=0;
        for (Event event:events) {
            JButton b=new JButton(event.getName()+": "+
                    DateTimeFormatter.ofPattern("yyyy/MM/dd - hh:mm").format(event.getStartTime())+" - "+
                    DateTimeFormatter.ofPattern("yyyy/MM/dd - hh:mm").format(event.getEndTime()));
            b.setBounds(startX,startY+height*i++,width,height-2 );
            b.addActionListener(l->{
                //Todo: connect it with delete/edit event view
            });
            b.setBackground(new Color(255,100,100));
            contentPane.add(b);
        }


    }
}
