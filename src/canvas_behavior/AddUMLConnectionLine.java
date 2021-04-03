package canvas_behavior;

public class AddUMLConnectionLine implements ICanvasBehavior {

    @Override
    public void onPressed(int mousePosX, int mousePosY) {
        System.out.println("AddClassMode onPressed");
    }

    @Override
    public void onReleased(int mousePosX, int mousePosY) {
        System.out.println("AddClassMode onReleased");
    }
    
}
