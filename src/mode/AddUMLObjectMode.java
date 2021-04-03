package mode;

import java.awt.*;
import javax.swing.JLabel;

import components.UMLObjects.UMLClass;

import javax.swing.BorderFactory;

import widgets.Canvas;

public class AddUMLObjectMode implements ICanvasBehavior {

    @Override
    public void onPressed(int mousePosX, int mousePosY) {
        System.out.println("AddUMLObjectMode onPressed");

        UMLClass classObj = new UMLClass();
        JLabel label = classObj.getLabel();

        label.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);
        label.setBounds(mousePosX - 50, mousePosY - 50, 100, 100);

        Canvas.addUMLObject(label, classObj.getZAxisDepth());
    }

    @Override
    public void onReleased(int mousePosX, int mousePosY) {
        System.out.println("AddUMLObjectMode onReleased");
    }
    
}
