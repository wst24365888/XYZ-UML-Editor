package canvas_behavior;

import java.awt.Color;
import java.util.Random;

import javax.swing.JLabel;

import components.UMLObjects.BaseUMLObject;
import widgets.Canvas;

public class Select implements ICanvasBehavior {
    protected int originalX;
    protected int originalY;

    protected JLabel selectedArea;

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

        // Single Selection
        if (Canvas.getSelections().size() == 1) {
            Canvas.getSelections().iterator().next().setLocation(mousePosX - originalX, mousePosY - originalY);
            Canvas.getInstance().repaint();
        } else {
            this.selectedArea.setBounds(Math.min(this.originalX, mousePosX), Math.min(this.originalY, mousePosY), Math.abs(this.originalX - mousePosX), Math.abs(this.originalY - mousePosY));

            Canvas.getInstance().repaint();
        }
    }

    @Override
    public void onReleased(int mousePosX, int mousePosY) {
        System.out.println("SelectMode onReleased");

        Canvas.getInstance().remove(this.selectedArea);
        Canvas.getInstance().repaint();
    }
}
