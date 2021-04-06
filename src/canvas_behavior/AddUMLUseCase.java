package canvas_behavior;

import components.UMLObjects.BaseUMLObject;
import components.UMLObjects.UMLUseCase;

import widgets.Canvas;

public class AddUMLUseCase implements ICanvasBehavior {
    private static AddUMLUseCase instance = null;

    private AddUMLUseCase() {
        System.out.println("AddUMLUseCase created");
    };

    public static AddUMLUseCase getInstance() {
        if (instance == null) {
            instance = new AddUMLUseCase();
        }

        return instance;
    }

    @Override
    public void onPressed(int mousePosX, int mousePosY) {
        System.out.println("AddUMLUseCase onPressed");

        BaseUMLObject baseUMLObject = new UMLUseCase();
        baseUMLObject.setBounds(mousePosX - 65, mousePosY - 45, 130, 90);

        Canvas.getInstance().addUMLObject(baseUMLObject, baseUMLObject.getZAxisHeight());
    }

    @Override
    public void onDragged(int mousePosX, int mousePosY) {
        System.out.println("AddUMLUseCase onDragged");
    }

    @Override
    public void onReleased(int mousePosX, int mousePosY) {
        System.out.println("AddUMLUseCase onReleased");
    }
    
}
