package canvas_behavior;

import java.awt.event.*;

import components.UMLObjects.BaseUMLObject;
import components.UMLObjects.UMLClass;
import components.UMLObjects.UMLUseCase;
import enum_types.UMLObject;
import widgets.Canvas;

public class AddUMLObject extends MouseAdapter {
    UMLObject type;

    public AddUMLObject(UMLObject type) {
        this.type = type;
    }

    private BaseUMLObject createUMLObject() {
        switch (this.type) {
            case Class:
                return new UMLClass();
            case UseCase:
                return new UMLUseCase();
            default:
                return null;
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        BaseUMLObject baseUMLObject = this.createUMLObject();

        baseUMLObject.setBounds(mouseEvent.getX() - 65, mouseEvent.getY() - 45, baseUMLObject.getWidth(), baseUMLObject.getHeight());

        Canvas.getInstance().addBaseUMLObject(baseUMLObject, baseUMLObject.getZAxisHeight());

        Canvas.getInstance().repaint();
    }
}
