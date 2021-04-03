package canvas_behavior;

public class AddUMLCompositionLine implements ICanvasBehavior {

    @Override
    public void onPressed(int mousePosX, int mousePosY) {
        System.out.println("AddUMLAssociationLine onPressed");
    }

    @Override
    public void onReleased(int mousePosX, int mousePosY) {
        System.out.println("AddUMLAssociationLine onReleased");
    }
    
}

