package canvas_behavior;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JLabel;

import components.UMLObjects.BaseUMLObject;
import widgets.Canvas;

public class Select extends MouseAdapter {
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
    public void mousePressed(MouseEvent mouseEvent) {
        if (this.selectedArea != null) {
            this.clearSelectedArea();
        }

        BaseUMLObject component = Canvas.getInstance().getComponentWithin(mouseEvent.getX(), mouseEvent.getY());
        if (component != null) {
            // Single Selection

            this.originalX = (int) component.getMousePosition().getX();
            this.originalY = (int) component.getMousePosition().getY();

            ArrayList<BaseUMLObject> selections = new ArrayList<BaseUMLObject>();
            selections.add(component);

            Canvas.getInstance().clearSelections();
            Canvas.getInstance().setSelections(selections);

            this.singleSelection = true;
        } else {
            this.originalX = mouseEvent.getX();
            this.originalY = mouseEvent.getY();

            Canvas.getInstance().clearSelections();

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
    public void mouseDragged(MouseEvent mouseEvent) {
        if (this.singleSelection) {
            // Single Selection

            Canvas.getInstance().getSelections().iterator().next().moveTo(mouseEvent.getX() - originalX, mouseEvent.getY() - originalY);
        } else {
            int upperLeftX = Math.min(this.originalX, mouseEvent.getX());
            int upperLeftY = Math.min(this.originalY, mouseEvent.getY());
            int width = Math.abs(this.originalX - mouseEvent.getX());
            int height = Math.abs(this.originalY - mouseEvent.getY());

            this.selectedArea.setBounds(upperLeftX, upperLeftY, width, height);

            ArrayList<BaseUMLObject> selections = Canvas.getInstance()
                    .getAllComponentWithin(new Rectangle(upperLeftX, upperLeftY, width, height));

            Canvas.getInstance().clearSelections();
            Canvas.getInstance().setSelections(selections);
        }

        Canvas.getInstance().repaint();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        // System.out.println("SelectMode onReleased");

        if (this.selectedArea != null) {
            if (Canvas.getInstance().getSelections().isEmpty()) {
                this.clearSelectedArea();
            } else {
                // Resize Selection Area

                int upperLeftX = Integer.MAX_VALUE;
                int upperLeftY = Integer.MAX_VALUE;
                int width = 0;
                int height = 0;

                Iterator<BaseUMLObject> iterator = Canvas.getInstance().getSelections().iterator();
                while (iterator.hasNext()) {
                    BaseUMLObject tmp = iterator.next();

                    upperLeftX = Math.min(upperLeftX, tmp.getX());
                    upperLeftY = Math.min(upperLeftY, tmp.getY());
                }

                iterator = Canvas.getInstance().getSelections().iterator();
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
