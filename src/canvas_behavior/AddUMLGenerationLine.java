package canvas_behavior;

import java.awt.*;

import components.UMLConnectionLines.UMLGenerationLine;
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
            this.source = within;
        }
    }

    @Override
    public void onDragged(int mousePosX, int mousePosY) {
        System.out.println("AddUMLGenerationLine onDragged");

        if (this.source != null) {
            source.setDrawingUMLConnectionLine(new UMLGenerationLine(new Point(mousePosX, mousePosY)));
        }
    }

    @Override
    public void onReleased(int mousePosX, int mousePosY) {
        System.out.println("AddUMLGenerationLine onReleased");

        BaseUMLObject within = Canvas.withinComponent(mousePosX, mousePosY);
        if (within != null) {
            this.destination = within;
        }

        if (this.source != null && this.destination != null) {
            // TODO: connect the line.
        }

        this.source = null;
        this.destination = null;
    }
    
}

