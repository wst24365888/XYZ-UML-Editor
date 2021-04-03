package canvas_behavior;

import components.UMLObjects.BaseUMLObject;
import components.UMLObjects.UMLClass;

import widgets.Canvas;

public class AddUMLClass implements ICanvasBehavior {

    @Override
    public void onPressed(int mousePosX, int mousePosY) {
        System.out.println("AddUMLClass onPressed");

        BaseUMLObject baseUMLObject = new UMLClass();
        baseUMLObject.setBounds(mousePosX - 65, mousePosY - 75, 130, 150);

        Canvas.addUMLObject(baseUMLObject, baseUMLObject.getZAxisHeight());
    }

    @Override
    public void onReleased(int mousePosX, int mousePosY) {
        System.out.println("AddUMLClass onReleased");
    }
    
}
