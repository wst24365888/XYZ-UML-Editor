package widgets;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLayeredPane;

import canvas_behavior.*;
import components.UMLObjects.BaseUMLObject;

public class Canvas extends JLayeredPane {
    private static final long serialVersionUID = 1985691147832327662L;

    private static Canvas instance = null;

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
        this.setLayout(null);
        this.setOpaque(true);
        
        this.setBackground(Color.WHITE);
        this.addMouseListener(mouseAdapter);
        this.addMouseMotionListener(mouseAdapter);
    }

    public static Canvas getInstance() {
        if (instance == null) {
            instance = new Canvas();
        }

        return instance;
    }

    public static void setCanvasBehavior(ICanvasBehavior iCanvasBehavior) {
        canvasBehavior = iCanvasBehavior;
    }

    public void addUMLObject(Component component, int zAxisHeight) {
        this.add(component);
        this.setLayer(component, zAxisHeight);
    }

    public void setDraggableToAll(boolean isDraggable) {
        Component[] components = this.getComponents();

        for (Component component : components) {
            if (component instanceof BaseUMLObject) {
                ((BaseUMLObject) component).setDraggable(isDraggable);
            }
        }
    }

    public BaseUMLObject withinComponent(int x, int y) {
        Component[] components = this.getComponents();
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
