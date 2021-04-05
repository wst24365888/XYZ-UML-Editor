package canvas_behavior;

import java.awt.*;
import components.UMLConnectionLines.UMLAssociationLine;
import components.UMLObjects.BaseUMLObject;
import widgets.Canvas;

public class AddUMLAssociationLine implements ICanvasBehavior {
    private BaseUMLObject source = null;
    private BaseUMLObject destination = null;

    @Override
    public void onPressed(int mousePosX, int mousePosY) {
        System.out.println("AddUMLAssociationLine onPressed");

        BaseUMLObject within = Canvas.getInstance().withinComponent(mousePosX, mousePosY);
        if (within != null) {
            this.source = within;
        }
    }

    @Override
    public void onDragged(int mousePosX, int mousePosY) {
        System.out.println("AddUMLAssociationLine onDragged");

        if (this.source != null) {
            source.setDrawingUMLConnectionLine(new UMLAssociationLine(new Point(mousePosX, mousePosY)));
        }
    }

    @Override
    public void onReleased(int mousePosX, int mousePosY) {
        System.out.println("AddUMLAssociationLine onReleased");

        BaseUMLObject within = Canvas.getInstance().withinComponent(mousePosX, mousePosY);
        if (within != null) {
            this.destination = within;
        }

        if (this.source != null && this.destination != null) {
            source.setDrawingUMLConnectionLine(null);

            if(this.source != this.destination) {
                source.addConntection(destination, new UMLAssociationLine());
            }
        } else if (this.source != null && this.destination == null) {
            source.setDrawingUMLConnectionLine(null);
        }

        this.source = null;
        this.destination = null;
    }
    
}


