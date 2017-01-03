package myplugin;

import javax.swing.*;

/**
 * Created by 노성훈 on 2017-01-04.
 */
public class CustomList extends JList {
    private DefaultListModel staticdlm = new DefaultListModel();
    public CustomList(){
        setCellRenderer(new CustomListRenderer());

    }

    public void MakeModel(DefaultListModel dlm){
        staticdlm = dlm;
        setModel(staticdlm);
    }
}
