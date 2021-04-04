package canvas_behavior;

public class Select implements ICanvasBehavior {

    @Override
    public void onPressed(int mousePosX, int mousePosY) {
        System.out.println("SelectMode onPressed");
    }

    @Override
    public void onDragged(int mousePosX, int mousePosY) {
        System.out.println("SelectMode onDragged");
    }

    @Override
    public void onReleased(int mousePosX, int mousePosY) {
        System.out.println("SelectMode onReleased");
    }

}
