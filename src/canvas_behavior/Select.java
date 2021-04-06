package canvas_behavior;

import components.UMLObjects.BaseUMLObject;
import widgets.Canvas;

public class Select implements ICanvasBehavior {
    protected int originalX;
    protected int originalY;

    @Override
    public void onPressed(int mousePosX, int mousePosY) {
        System.out.println("SelectMode onPressed");

        // Single Selection
        BaseUMLObject within = Canvas.getInstance().withinComponent(mousePosX, mousePosY);
        if (within != null) {
            this.originalX = (int) within.getMousePosition().getX();
            this.originalY = (int) within.getMousePosition().getY();

            Canvas.clearSelections();
            Canvas.addSelection(within);
        } else {
            Canvas.clearSelections();
        }
    }

    @Override
    public void onDragged(int mousePosX, int mousePosY) {
        System.out.println("SelectMode onDragged");

        // Single Selection
        if (Canvas.getSelections().size() == 1) {
            Canvas.getSelections().iterator().next().setLocation(mousePosX - originalX, mousePosY - originalY);
            Canvas.getInstance().repaint();
        }
    }

    @Override
    public void onReleased(int mousePosX, int mousePosY) {
        System.out.println("SelectMode onReleased");
    }
}
