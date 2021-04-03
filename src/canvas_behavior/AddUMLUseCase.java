package canvas_behavior;

import components.UMLObjects.BaseUMLObject;
import components.UMLObjects.UMLUseCase;

import widgets.Canvas;

public class AddUMLUseCase implements ICanvasBehavior {

    @Override
    public void onPressed(int mousePosX, int mousePosY) {
        System.out.println("AddUMLObjectMode onPressed");

        BaseUMLObject label = new UMLUseCase();
        label.setBounds(mousePosX - 50, mousePosY - 50, 120, 80);

        Canvas.addUMLObject(label, label.getZAxisDepth());
    }

    @Override
    public void onReleased(int mousePosX, int mousePosY) {
        System.out.println("AddUMLObjectMode onReleased");
    }
    
}
