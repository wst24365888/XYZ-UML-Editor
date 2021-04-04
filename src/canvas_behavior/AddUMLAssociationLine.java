package canvas_behavior;

public class AddUMLAssociationLine implements ICanvasBehavior {

    @Override
    public void onPressed(int mousePosX, int mousePosY) {
        System.out.println("AddUMLAssociationLine onPressed");
    }

    @Override
    public void onDragged(int mousePosX, int mousePosY) {
        System.out.println("AddUMLAssociationLine onDragged");
    }

    @Override
    public void onReleased(int mousePosX, int mousePosY) {
        System.out.println("AddUMLAssociationLine onReleased");
    }
    
}
