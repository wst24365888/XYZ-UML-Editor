package canvas_behavior;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;

import components.UMLObjects.BaseUMLObject;
import widgets.Canvas;

public class Select implements ICanvasBehavior {
    protected boolean singleSelection = false;

    protected int originalX;
    protected int originalY;

    protected JLabel selectedArea;

    @Override
    public void onPressed(int mousePosX, int mousePosY) {
        System.out.println("SelectMode onPressed");

        BaseUMLObject component = Canvas.getInstance().getPressedComponent(mousePosX, mousePosY);
        if (component != null) {
            // Single Selection

            this.originalX = (int) component.getMousePosition().getX();
            this.originalY = (int) component.getMousePosition().getY();

            Canvas.clearSelections();
            Canvas.addSelection(component);

            this.singleSelection = true;
        } else {
            this.originalX = mousePosX;
            this.originalY = mousePosY;

            Canvas.clearSelections();

            Random random = new Random();

            float r = random.nextFloat();
            float g = random.nextFloat();
            float b = random.nextFloat();

            Color randomColor = new Color(r, g, b);
            randomColor.brighter();

            this.selectedArea = new JLabel();
            this.selectedArea.setOpaque(true);
            this.selectedArea.setBackground(randomColor);
            this.selectedArea.setBounds(this.originalX, this.originalY, 0, 0);

            Canvas.getInstance().addUMLObject(selectedArea, -1);
            Canvas.getInstance().repaint();
        }
    }

    @Override
    public void onDragged(int mousePosX, int mousePosY) {
        System.out.println("SelectMode onDragged");

        if (this.singleSelection) {
            // Single Selection

            Canvas.getSelections().iterator().next().setLocation(mousePosX - originalX, mousePosY - originalY);
            Canvas.getInstance().repaint();
        } else {
            int upperLeftX = Math.min(this.originalX, mousePosX);
            int upperLeftY = Math.min(this.originalY, mousePosY);
            int width = Math.abs(this.originalX - mousePosX);
            int height = Math.abs(this.originalY - mousePosY);

            this.selectedArea.setBounds(upperLeftX, upperLeftY, width, height);

            ArrayList<BaseUMLObject> selections = Canvas.getInstance().getWithinComponent(new Rectangle(upperLeftX, upperLeftY, width, height));
            for (BaseUMLObject selection : selections) {
                Canvas.addSelection(selection);
            }

            Canvas.getInstance().repaint();
        }
    }

    @Override
    public void onReleased(int mousePosX, int mousePosY) {
        System.out.println("SelectMode onReleased");

        if (this.selectedArea != null) {
            Canvas.getInstance().remove(this.selectedArea);
            Canvas.getInstance().repaint();
        }

        this.singleSelection = false;
        this.selectedArea = null;
    }
}
