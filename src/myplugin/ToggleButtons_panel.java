package myplugin;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by 노성훈 on 2017-01-04.
 */
public class ToggleButtons_panel extends JToolBar {


    JToggleButton abstract_button= new JToggleButton("abstract");
    JToggleButton static_button= new JToggleButton("static");
    JToggleButton derived_button= new JToggleButton("derived");
    public ToggleButtons_panel(JList list){
        setFloatable(false);
        add(abstract_button);
        add(static_button);
        add(derived_button);
        abstract_button.addItemListener((ItemEvent ev) -> {
            if(ev.getStateChange()==ItemEvent.SELECTED){
                System.out.println("hello");
            } else if(ev.getStateChange()==ItemEvent.DESELECTED){
                System.out.println("button is not selected");
            }

        });
    }

}
