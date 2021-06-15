package canvas_behavior;

import java.awt.*;

import components.UMLConnectionLines.UMLGenerationLine;
import components.UMLObjects.BaseUMLObject;
import widgets.Canvas;

public class AddUMLGenerationLine implements ICanvasBehavior {
    private static AddUMLGenerationLine instance = null;

    private BaseUMLObject source = null;
    private BaseUMLObject destination = null;

    private Point originalPoint = null;

    private AddUMLGenerationLine() {
        // System.out.println("AddUMLGenerationLine created");
    };

    public static AddUMLGenerationLine getInstance() {
        if (instance == null) {
            instance = new AddUMLGenerationLine();
        }

        return instance;
    }

    @Override
    public void onPressed(int mousePosX, int mousePosY) {
        // System.out.println("AddUMLGenerationLine onPressed");

        BaseUMLObject component;

        component = Canvas.getInstance().getComponentWithin(mousePosX, mousePosY);

        if (component != null && component.isConnectable()) {
            this.source = component;
            this.originalPoint = new Point(mousePosX, mousePosY);
        }

        Canvas.getInstance().repaint();
    }

    @Override
    public void onDragged(int mousePosX, int mousePosY) {
        // System.out.println("AddUMLGenerationLine onDragged");

        if (this.source != null) {
            Canvas.getInstance().setDrawingLine(
                    new UMLGenerationLine(this.source, this.originalPoint, new Point(mousePosX, mousePosY)));
        }

        Canvas.getInstance().repaint();
    }

    @Override
    public void onReleased(int mousePosX, int mousePosY) {
        // System.out.println("AddUMLGenerationLine onReleased");

        BaseUMLObject component;

        component = Canvas.getInstance().getComponentWithin(mousePosX, mousePosY);

        if (component != null && component.isConnectable()) {
            this.destination = component;
        }

        if (this.source != null && this.destination != null) {
            Canvas.getInstance().setDrawingLine(null);

            if (this.source != this.destination) {
                Canvas.getInstance().addUMLConntection(new UMLGenerationLine(this.source, this.originalPoint,
                        this.destination, new Point(mousePosX, mousePosY)));
            }
        } else if (this.source != null && this.destination == null) {
            Canvas.getInstance().setDrawingLine(null);
        }

        this.source = null;
        this.destination = null;

        Canvas.getInstance().repaint();
    }
}
