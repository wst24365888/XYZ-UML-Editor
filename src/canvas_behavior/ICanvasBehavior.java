package canvas_behavior;

public interface ICanvasBehavior {
    public void onPressed(int mousePosX, int mousePosY);

    public void onDragged(int mousePosX, int mousePosY);

    public void onReleased(int mousePosX, int mousePosY);
}
