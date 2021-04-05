package canvas_behavior;

import components.UMLObjects.BaseUMLObject;
import widgets.Canvas;

public class Select implements ICanvasBehavior {
    BaseUMLObject source = null;

    protected int originalX;
    protected int originalY;

    @Override
    public void onPressed(int mousePosX, int mousePosY) {
        System.out.println("SelectMode onPressed");

        BaseUMLObject within = Canvas.getInstance().withinComponent(mousePosX, mousePosY);
        if (within != null) {
            this.setSource(within);
        }
    }

    @Override
    public void onDragged(int mousePosX, int mousePosY) {
        System.out.println("SelectMode onDragged");

        if (this.source != null) {
            this.source.setLocation(mousePosX - originalX, mousePosY - originalY);
            Canvas.getInstance().repaint();
        }
    }

    @Override
    public void onReleased(int mousePosX, int mousePosY) {
        System.out.println("SelectMode onReleased");
    }

    public void setSource(BaseUMLObject source) {
        if(source != null) {
            this.originalX = (int) source.getMousePosition().getX();
            this.originalY = (int) source.getMousePosition().getY();
        }

        this.source = source;
    }

}
