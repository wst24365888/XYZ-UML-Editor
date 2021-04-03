package canvas_behavior;

import components.UMLObjects.BaseUMLObject;
import components.UMLObjects.UMLClass;

import widgets.Canvas;

public class AddUMLClass implements ICanvasBehavior {

    @Override
    public void onPressed(int mousePosX, int mousePosY) {
        System.out.println("AddUMLObjectMode onPressed");

        BaseUMLObject label = new UMLClass();
        label.setBounds(mousePosX - 50, mousePosY - 50, 100, 100);

        Canvas.addUMLObject(label, label.getZAxisDepth());
    }

    @Override
    public void onReleased(int mousePosX, int mousePosY) {
        System.out.println("AddUMLObjectMode onReleased");
    }
    
}
