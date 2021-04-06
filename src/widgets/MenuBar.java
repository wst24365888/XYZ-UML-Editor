package widgets;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

import javax.swing.*;

import canvas_behavior.Select;
import components.UMLObjects.BaseUMLObject;

public class MenuBar {
    private static MenuBar instance = null;

    private static JMenuBar menuBar = new JMenuBar();

    private MenuBar() {
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        JMenuItem changeObjectName = new JMenuItem("Change Object Name");
        JMenuItem groupObjects = new JMenuItem("Group Objects");
        JMenuItem unGroupObjects = new JMenuItem("UnGroup Objects");

        changeObjectName.setPreferredSize(new Dimension(150, 30));
        groupObjects.setPreferredSize(new Dimension(150, 30));
        unGroupObjects.setPreferredSize(new Dimension(150, 30));

        groupObjects.addMouseListener(new MouseAdapter() {            
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                group();
            }
        });

        editMenu.add(changeObjectName);
        editMenu.add(groupObjects);
        editMenu.add(unGroupObjects);

        menuBar.setPreferredSize(new Dimension(100, 30));
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
    }

    public static MenuBar getInstance() {
        if (instance == null) {
            instance = new MenuBar();
        }

        return instance;
    }

    public static JMenuBar getMenuBar() {
        return menuBar;
    }

    private void group() {
        JLabel selectedArea = Select.getInstance().getSelectedArea();
                
        Iterator<BaseUMLObject> iterator = Canvas.getSelections().iterator();
        while(iterator.hasNext()) {
            BaseUMLObject tmp = iterator.next();
            
            tmp.setLocation(tmp.getX() - selectedArea.getX(), tmp.getY() - selectedArea.getY());
            selectedArea.add(tmp);

            Canvas.getInstance().remove(tmp);
            System.out.println(tmp.getLocationOnScreen());
        }
        Select.getInstance().clearSelectedArea();
        Canvas.getInstance().addUMLObject(selectedArea, -1);

        Canvas.getInstance().repaint();
    }
}
