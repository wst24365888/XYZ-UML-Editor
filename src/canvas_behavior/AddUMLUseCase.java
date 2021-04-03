package canvas_behavior;

import java.awt.*;
import javax.swing.JLabel;

import components.UMLObjects.BaseUMLObject;
import components.UMLObjects.UMLUseCase;

import javax.swing.BorderFactory;

import widgets.Canvas;

public class AddUMLUseCase implements ICanvasBehavior {

    @Override
    public void onPressed(int mousePosX, int mousePosY) {
        System.out.println("AddUMLObjectMode onPressed");

        BaseUMLObject classObj = new UMLUseCase();
        JLabel label = classObj;

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
