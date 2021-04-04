package canvas_behavior;

public class AddUMLCompositionLine implements ICanvasBehavior {

    @Override
    public void onPressed(int mousePosX, int mousePosY) {
        System.out.println("AddUMLCompositionLine onPressed");
    }

    @Override
    public void onDragged(int mousePosX, int mousePosY) {
        System.out.println("AddUMLCompositionLine onDragged");
    }

    @Override
    public void onReleased(int mousePosX, int mousePosY) {
        System.out.println("AddUMLCompositionLine onReleased");
    }
    
}

