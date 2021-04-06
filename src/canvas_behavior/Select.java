package canvas_behavior;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JLabel;

import components.UMLObjects.BaseUMLObject;
import widgets.Canvas;

public class Select implements ICanvasBehavior {
    private static Select instance = null;

    private boolean singleSelection = false;

    private int originalX;
    private int originalY;

    private JLabel selectedArea;

    private Select() {
        // System.out.println("SelectMode created");
    };

    public static Select getInstance() {
        if (instance == null) {
            instance = new Select();
        }

        return instance;
    }

    @Override
    public void onPressed(int mousePosX, int mousePosY) {
        // System.out.println("SelectMode onPressed");

        if (this.selectedArea != null) {
            this.clearSelectedArea();
        }

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

            Color randomColor = new Color(r, g, b, 0.25f);
            randomColor.brighter();

            this.selectedArea = new JLabel();
            this.selectedArea.setOpaque(true);
            this.selectedArea.setBackground(randomColor);
            this.selectedArea.setBounds(this.originalX, this.originalY, 0, 0);

            Canvas.getInstance().addSelectedArea(selectedArea);
        }

        Canvas.getInstance().repaint();
    }

    @Override
    public void onDragged(int mousePosX, int mousePosY) {
        // System.out.println("SelectMode onDragged");

        if (this.singleSelection) {
            // Single Selection

            Canvas.getSelections().iterator().next().moveTo(mousePosX - originalX, mousePosY - originalY);
        } else {
            int upperLeftX = Math.min(this.originalX, mousePosX);
            int upperLeftY = Math.min(this.originalY, mousePosY);
            int width = Math.abs(this.originalX - mousePosX);
            int height = Math.abs(this.originalY - mousePosY);

            this.selectedArea.setBounds(upperLeftX, upperLeftY, width, height);

            ArrayList<BaseUMLObject> selections = Canvas.getInstance()
                    .getWithinComponent(new Rectangle(upperLeftX, upperLeftY, width, height));
            for (BaseUMLObject selection : selections) {
                Canvas.addSelection(selection);
            }
        }

        Canvas.getInstance().repaint();
    }

    @Override
    public void onReleased(int mousePosX, int mousePosY) {
        // System.out.println("SelectMode onReleased");

        if (this.selectedArea != null) {
            if (Canvas.getSelections().isEmpty()) {
                this.clearSelectedArea();
            } else {
                // Resize Selection Area

                int upperLeftX = Integer.MAX_VALUE;
                int upperLeftY = Integer.MAX_VALUE;
                int width = 0;
                int height = 0;

                Iterator<BaseUMLObject> iterator = Canvas.getSelections().iterator();
                while (iterator.hasNext()) {
                    BaseUMLObject tmp = iterator.next();

                    upperLeftX = Math.min(upperLeftX, tmp.getX());
                    upperLeftY = Math.min(upperLeftY, tmp.getY());
                }

                iterator = Canvas.getSelections().iterator();
                while (iterator.hasNext()) {
                    BaseUMLObject tmp = iterator.next();

                    width = Math.max(width, Math.abs((tmp.getX() + tmp.getWidth()) - upperLeftX));
                    height = Math.max(height, Math.abs((tmp.getY() + tmp.getHeight()) - upperLeftY));
                }

                this.selectedArea.setBounds(upperLeftX, upperLeftY, width, height);

                Canvas.getInstance().repaint();
            }
        }

        this.singleSelection = false;
    }

    public void clearSelectedArea() {
        if (this.selectedArea != null) {
            Canvas.getInstance().remove(this.selectedArea);
            Canvas.getInstance().repaint();

            this.selectedArea = null;
        }
    }

    public JLabel getSelectedArea() {
        return this.selectedArea;
    }
}
