package widgets;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

import javax.swing.*;

import canvas_behavior.Select;
import components.UMLObjects.BaseUMLObject;
import components.UMLObjects.UMLGroup;

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
        UMLGroup selectedArea = new UMLGroup(Select.getInstance().getSelectedArea());

        Iterator<BaseUMLObject> iterator = Canvas.getSelections().iterator();
        while (iterator.hasNext()) {
            BaseUMLObject tmp = iterator.next();

            tmp.setLocation(
                    (int) (Canvas.getRelativeLocation(tmp.getLocationOnScreen()).getX() - Select.getInstance().getSelectedArea().getX()),
                    (int) (Canvas.getRelativeLocation(tmp.getLocationOnScreen()).getY() - Select.getInstance().getSelectedArea().getY()));

            System.out.println(tmp.getLocation());

            selectedArea.addComponent(tmp);

            Canvas.getInstance().remove(tmp);
        }
        selectedArea.setBounds(Select.getInstance().getSelectedArea().getBounds());
        Canvas.getInstance().addSelectedArea(selectedArea);
        Select.getInstance().clearSelectedArea();

        Canvas.getInstance().repaint();
    }
}
