package canvas_behavior;

import components.UMLObjects.BaseUMLObject;
import widgets.Canvas;

public class AddUMLGenerationLine implements ICanvasBehavior {
    private BaseUMLObject source = null;
    private BaseUMLObject destination = null;

    @Override
    public void onPressed(int mousePosX, int mousePosY) {
        System.out.println("AddUMLGenerationLine onPressed");

        BaseUMLObject within = Canvas.withinComponent(mousePosX, mousePosY);
        if (within != null) {
            source = within;
        }
    }

    @Override
    public void onReleased(int mousePosX, int mousePosY) {
        System.out.println("AddUMLGenerationLine onReleased");

        BaseUMLObject within = Canvas.withinComponent(mousePosX, mousePosY);
        if (within != null) {
            destination = within;
        }

        if (source != null && destination != null) {
            // TODO: connect the line.
        }

        source = null;
        destination = null;
    }
    
}

