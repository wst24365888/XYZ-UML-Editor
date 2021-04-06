package canvas_behavior;

import java.awt.*;
import components.UMLConnectionLines.UMLAssociationLine;
import components.UMLObjects.BaseUMLObject;
import widgets.Canvas;

public class AddUMLAssociationLine implements ICanvasBehavior {
    private static AddUMLAssociationLine instance = null;

    private BaseUMLObject source = null;
    private BaseUMLObject destination = null;

    private AddUMLAssociationLine() {
        System.out.println("AddUMLAssociationLine created");
    };

    public static AddUMLAssociationLine getInstance() {
        if (instance == null) {
            instance = new AddUMLAssociationLine();
        }

        return instance;
    }

    @Override
    public void onPressed(int mousePosX, int mousePosY) {
        System.out.println("AddUMLAssociationLine onPressed");

        BaseUMLObject component = Canvas.getInstance().getPressedComponent(mousePosX, mousePosY);
        if (component != null) {
            this.source = component;
        }
    }

    @Override
    public void onDragged(int mousePosX, int mousePosY) {
        System.out.println("AddUMLAssociationLine onDragged");

        if (this.source != null) {
            Canvas.getInstance().setDrawingLine(new UMLAssociationLine(this.source, new Point(mousePosX, mousePosY)));
        }
    }

    @Override
    public void onReleased(int mousePosX, int mousePosY) {
        System.out.println("AddUMLAssociationLine onReleased");

        BaseUMLObject component = Canvas.getInstance().getPressedComponent(mousePosX, mousePosY);
        if (component != null) {
            this.destination = component;
        }

        if (this.source != null && this.destination != null) {
            Canvas.getInstance().setDrawingLine(null);

            if(this.source != this.destination) {
                Canvas.getInstance().addConntection(new UMLAssociationLine(this.source, this.destination));
            }
        } else if (this.source != null && this.destination == null) {
            Canvas.getInstance().setDrawingLine(null);
        }

        this.source = null;
        this.destination = null;
    }
    
}


