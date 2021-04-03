package canvas_behavior;

public class AddUMLGenerationLine implements ICanvasBehavior {

    @Override
    public void onPressed(int mousePosX, int mousePosY) {
        System.out.println("AddUMLGenerationLine onPressed");
    }

    @Override
    public void onReleased(int mousePosX, int mousePosY) {
        System.out.println("AddUMLGenerationLine onReleased");
    }
    
}

