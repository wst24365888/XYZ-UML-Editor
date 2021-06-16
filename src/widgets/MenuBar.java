package widgets;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

import javax.swing.*;

import canvas_behavior.Select;
import components.UMLObjects.BaseUMLObject;
import components.UMLObjects.UMLGroup;
import enum_types.MenuFunction;

public class MenuBar {
    private static MenuBar instance = null;

    private static JMenuBar menuBar = new JMenuBar();

    private static JMenu fileMenu = new JMenu("File");
    private static JMenu editMenu = new JMenu("Edit");

    private static JMenuItem changeObjectName = new JMenuItem("Change Object Name");
    private static JMenuItem groupObjects = new JMenuItem("Group Objects");
    private static JMenuItem unGroupObjects = new JMenuItem("UnGroup Objects");

    private MenuBar() {
        changeObjectName.setPreferredSize(new Dimension(150, 30));
        changeObjectName.setEnabled(false);
        changeObjectName.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                changeName();
            }
        });

        groupObjects.setPreferredSize(new Dimension(150, 30));
        groupObjects.setEnabled(false);
        groupObjects.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                group();
            }
        });

        unGroupObjects.setPreferredSize(new Dimension(150, 30));
        unGroupObjects.setEnabled(false);
        unGroupObjects.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                ungroup();
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

    public static void setFunctionEnabled(MenuFunction functionType, boolean enable) {
        switch(functionType) {
            case ChangeName:
                changeObjectName.setEnabled(enable);
                break;
            case Group:
                groupObjects.setEnabled(enable);
                break;
            case Ungroup:
                unGroupObjects.setEnabled(enable);
                break;
            default:
                return;
        }
    }

    private static void changeName() {
        if (!changeObjectName.isEnabled()) {
            return;
        }

        String name = JOptionPane.showInputDialog("Please enter new object name: ");

        if (name != null) {
            Canvas.getInstance().getSelections().iterator().next().getLabel().setText(name);
        }
    }

    private static void group() {
        if (!groupObjects.isEnabled()) {
            return;
        }

        UMLGroup groupObject = new UMLGroup(Select.getInstance().getSelectedArea());

        Iterator<BaseUMLObject> iterator = Canvas.getInstance().getSelections().iterator();
        while (iterator.hasNext()) {
            BaseUMLObject tmp = iterator.next();

            tmp.setLocation(
                    (int) (Canvas.getRelativeLocation(tmp.getLocationOnScreen()).getX()
                            - Select.getInstance().getSelectedArea().getX()) + 5,
                    (int) (Canvas.getRelativeLocation(tmp.getLocationOnScreen()).getY()
                            - Select.getInstance().getSelectedArea().getY()) + 5);

            groupObject.addComponent(tmp);

            Canvas.getInstance().removeBaseUMLObject(tmp);
        }

        groupObject.setBounds((int) Select.getInstance().getSelectedArea().getBounds().getX() - 5,
                (int) Select.getInstance().getSelectedArea().getBounds().getY() - 5,
                (int) Select.getInstance().getSelectedArea().getBounds().getWidth() + 10,
                (int) Select.getInstance().getSelectedArea().getBounds().getHeight() + 10);

        Canvas.getInstance().addBaseUMLObject(groupObject, -1);

        // selectedArea will be destroy
        Select.getInstance().clearSelectedArea();

        Canvas.getInstance().repaint();
    }

    private static void ungroup() {
        if (!unGroupObjects.isEnabled()) {
            return;
        }

        BaseUMLObject groupObject = Canvas.getInstance().getSelections().iterator().next();

        for (BaseUMLObject child : groupObject.getUMLComponents()) {
            child.setLocation((int) Canvas.getRelativeLocation(child.getLocationOnScreen()).getX(),
                    (int) Canvas.getRelativeLocation(child.getLocationOnScreen()).getY());

            Canvas.getInstance().addBaseUMLObject(child, child.getZAxisHeight());
        }

        Canvas.getInstance().removeBaseUMLObject(groupObject);
        Canvas.getInstance().repaint();
    }
}
