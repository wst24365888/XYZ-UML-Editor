package widgets;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLayeredPane;

import canvas_behavior.*;

public class Canvas {
    private static Canvas instance = null;

    private static JLayeredPane layeredPane = new JLayeredPane();
    private ICanvasBehavior canvasBehavior;

    private Canvas() {
        layeredPane.setLayout(null);
        layeredPane.setOpaque(true);
        
        layeredPane.setBackground(Color.WHITE);
        layeredPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                if (canvasBehavior == null) {
                    return;
                }

                canvasBehavior.onPressed(mouseEvent.getX(), mouseEvent.getY());
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                if (canvasBehavior == null) {
                    return;
                }

                canvasBehavior.onReleased(mouseEvent.getX(), mouseEvent.getY());
            }
        });
    }

    public static Canvas getInstance() {
        if (instance == null) {
            instance = new Canvas();
        }

        return instance;
    }

    public static void setBounds(int x, int y, int width, int height) {
        layeredPane.setBounds(x, y, width, height);
    }

    public static JLayeredPane getCanvas() {
        return layeredPane;
    }

    public void setCanvasBehavior(ICanvasBehavior canvasBehavior) {
        this.canvasBehavior = canvasBehavior;
    }

    public static void addUMLObject(Component component, int zAxisDepth) {
        layeredPane.add(component);
        layeredPane.setLayer(component, zAxisDepth);
    }
}
