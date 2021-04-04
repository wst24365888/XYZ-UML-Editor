package widgets;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLayeredPane;

import canvas_behavior.*;
import components.UMLObjects.BaseUMLObject;

public class Canvas {
    private static Canvas instance = null;

    private static JLayeredPane layeredPane = new JLayeredPane();
    private static ICanvasBehavior canvasBehavior;

    private static MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            if (canvasBehavior == null) {
                return;
            }

            canvasBehavior.onPressed(mouseEvent.getX(), mouseEvent.getY());
        }

        @Override
        public void mouseDragged(MouseEvent mouseEvent) {
            if (canvasBehavior == null) {
                return;
            }

            canvasBehavior.onDragged(mouseEvent.getX(), mouseEvent.getY());
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            if (canvasBehavior == null) {
                return;
            }

            canvasBehavior.onReleased(mouseEvent.getX(), mouseEvent.getY());
        }
    };

    private Canvas() {
        layeredPane.setLayout(null);
        layeredPane.setOpaque(true);
        
        layeredPane.setBackground(Color.WHITE);
        layeredPane.addMouseListener(mouseAdapter);
        layeredPane.addMouseMotionListener(mouseAdapter);
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

    public static void setCanvasBehavior(ICanvasBehavior iCanvasBehavior) {
        canvasBehavior = iCanvasBehavior;
    }

    public static void addUMLObject(Component component, int zAxisHeight) {
        layeredPane.add(component);
        layeredPane.setLayer(component, zAxisHeight);
    }

    public static void setDraggableToAll(boolean isDraggable) {
        Component[] components = layeredPane.getComponents();

        for (Component component : components) {
            if (component instanceof BaseUMLObject) {
                ((BaseUMLObject) component).setDraggable(isDraggable);
            }
        }
    }

    public static BaseUMLObject withinComponent(int x, int y) {
        Component[] components = layeredPane.getComponents();
        BaseUMLObject result =  null;

        for (Component component : components) {
            if (component instanceof BaseUMLObject) {
                BaseUMLObject tmp = (BaseUMLObject) component;
                if ((tmp.getLocation().getX() < x && x < tmp.getLocation().getX() + tmp.getWidth()) &&
                    (tmp.getLocation().getY() < y && y < tmp.getLocation().getY() + tmp.getHeight())) {
                        if (result == null || tmp.getZAxisHeight() > result.getZAxisHeight()) {
                            result = tmp;
                        }
                }
            }
        }

        return result;
    }
}
