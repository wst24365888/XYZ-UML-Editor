package canvas_behavior;

import components.UMLObjects.BaseUMLObject;
import components.UMLObjects.UMLClass;

import widgets.Canvas;

public class AddUMLClass implements ICanvasBehavior {
    private static AddUMLClass instance = null;

    private AddUMLClass() {
        // System.out.println("AddUMLClass created");
    };

    public static AddUMLClass getInstance() {
        if (instance == null) {
            instance = new AddUMLClass();
        }

        return instance;
    }

    @Override
    public void onPressed(int mousePosX, int mousePosY) {
        // System.out.println("AddUMLClass onPressed");

        BaseUMLObject baseUMLObject = new UMLClass();
        baseUMLObject.setBounds(mousePosX - 65, mousePosY - 75, 130, 150);

        Canvas.getInstance().addBaseUMLObject(baseUMLObject, baseUMLObject.getZAxisHeight());

        Canvas.getInstance().repaint();
    }

    @Override
    public void onDragged(int mousePosX, int mousePosY) {
        // System.out.println("AddUMLClass onDragged");
    }

    @Override
    public void onReleased(int mousePosX, int mousePosY) {
        // System.out.println("AddUMLClass onReleased");
    }
}
