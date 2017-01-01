package myplugin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.SelectionChangeListener;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiUtil;
import com.intellij.ui.components.JBList;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.uiDesigner.core.GridConstraints;
import com.sun.istack.internal.NotNull;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Created by 노성훈 on 2016-12-31.
 */
public class mypluginform implements ToolWindowFactory{
    private JList list1;
    private JList list2;
    private JList list3;
    private JList list4;
    private JPanel myToolWindowContent;
    private JPanel Panel1;
    private JPanel Panel2;
    private JPanel Panel3;
    private JPanel Panel4;
    private ToolWindow myToolWindow;
    private PsiPackage mypsipackage;



    private void init_lists_scroll(JList list,JPanel panel){
        JScrollPane scrollPane = new JBScrollPane(list,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        GridConstraints temp = new GridConstraints();
        temp.setFill(GridConstraints.FILL_VERTICAL|GridConstraints.FILL_HORIZONTAL);
        panel.add(scrollPane);
        scrollPane.setVisible(true);

    }

    private void init_packageslists(Project project) {
        PsiPackage pack = JavaPsiFacade.getInstance(project).findPackage("");
        PsiPackage[] psiPackages = pack.getSubPackages();


        DefaultListModel dlm = new DefaultListModel();

        for (PsiPackage one_package : psiPackages) {
            dlm.addElement(one_package.getName());
        }

        list1.setModel(dlm);

    }

    private void list_selection_eventlistener_set(Project project){
        list1.addListSelectionListener((ListSelectionEvent arg0) -> {
            if(!arg0.getValueIsAdjusting())
                make_class_list(project, list1.getSelectedValue().toString());
        });
    }

    private void make_class_list(Project project,String packageName){
        PsiClass[] classes = JavaPsiFacade.getInstance(project).findPackage(packageName).getClasses();

        DefaultListModel dlm = new DefaultListModel();

        for (PsiClass oneclass : classes) {
            dlm.addElement(oneclass.getName());
        }

        list2.setModel(dlm);
    }


    public void createToolWindowContent(Project project, ToolWindow toolWindow){
        init_lists_scroll(list1,Panel1);
        init_lists_scroll(list2,Panel2);
        init_lists_scroll(list3,Panel3);
        init_lists_scroll(list4,Panel4);
        //init_lists_scroll(list2,myToolWindowContent);
        list_selection_eventlistener_set(project);

        myToolWindow = toolWindow;
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(myToolWindowContent, "", false);
        toolWindow.getContentManager().addContent(content);

        this.init_packageslists(project);



    }
}
