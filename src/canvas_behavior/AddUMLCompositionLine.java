package canvas_behavior;

import java.awt.*;
import components.UMLConnectionLines.UMLCompositionLine;
import components.UMLObjects.BaseUMLObject;
import widgets.Canvas;

public class AddUMLCompositionLine implements ICanvasBehavior {
    private static AddUMLCompositionLine instance = null;

    private BaseUMLObject source = null;
    private BaseUMLObject destination = null;

    private AddUMLCompositionLine() {
        System.out.println("AddUMLCompositionLine created");
    };

    public static AddUMLCompositionLine getInstance() {
        if (instance == null) {
            instance = new AddUMLCompositionLine();
        }

        return instance;
    }

    @Override
    public void onPressed(int mousePosX, int mousePosY) {
        System.out.println("AddUMLCompositionLine onPressed");

        BaseUMLObject component = Canvas.getInstance().getPressedComponent(mousePosX, mousePosY);
        if (component != null) {
            this.source = component;
        }
    }

    @Override
    public void onDragged(int mousePosX, int mousePosY) {
        System.out.println("AddUMLCompositionLine onDragged");

        if (this.source != null) {
            source.setDrawingUMLConnectionLine(new UMLCompositionLine(new Point(mousePosX, mousePosY)));
        }
    }

    @Override
    public void onReleased(int mousePosX, int mousePosY) {
        System.out.println("AddUMLCompositionLine onReleased");

        BaseUMLObject component = Canvas.getInstance().getPressedComponent(mousePosX, mousePosY);
        if (component != null) {
            this.destination = component;
        }

        if (this.source != null && this.destination != null) {
            source.setDrawingUMLConnectionLine(null);

            if(this.source != this.destination) {
                source.addConntection(destination, new UMLCompositionLine());
            }
        } else if (this.source != null && this.destination == null) {
            source.setDrawingUMLConnectionLine(null);
        }

        this.source = null;
        this.destination = null;
    }
    
}



