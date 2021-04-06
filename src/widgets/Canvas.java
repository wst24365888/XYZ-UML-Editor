package widgets;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JLayeredPane;

import canvas_behavior.*;
import components.UMLConnectionLines.BaseUMLConnectionLine;
import components.UMLObjects.BaseUMLObject;

public class Canvas extends JLayeredPane {
    private static final long serialVersionUID = 1985691147832327662L;

    private static Canvas instance = null;

    private static ICanvasBehavior canvasBehavior;
    private static Set<BaseUMLObject> selections = new HashSet<BaseUMLObject>();

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

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Component[] components = this.getComponents();

        // Draw the line.
        for (Component component : components) {
            if (component instanceof BaseUMLObject) {
                BaseUMLObject baseUMLObject = (BaseUMLObject) component;
                BaseUMLConnectionLine drawing = baseUMLObject.getDrawing();
                Map<BaseUMLObject, BaseUMLConnectionLine> connections = baseUMLObject.getConntections();
                
                if (drawing != null) {
                    drawing.drawArrowLine(graphics, baseUMLObject.getPort(drawing.getMousePoint()), drawing.getMousePoint());
                }

                if (connections.size() != 0) {
                    for (Map.Entry<BaseUMLObject, BaseUMLConnectionLine> connectionEntry : connections.entrySet()) {
                        connectionEntry.getValue().drawArrowLine(graphics, baseUMLObject.getPort(connectionEntry.getKey().getCenterLocation()), connectionEntry.getKey().getPort(baseUMLObject.getCenterLocation()));
                    }
                }
            }
        }

        // Then cover the arrow on it.
        for (Component component : components) {
            if (component instanceof BaseUMLObject) {
                BaseUMLObject baseUMLObject = (BaseUMLObject) component;
                BaseUMLConnectionLine drawing = baseUMLObject.getDrawing();
                Map<BaseUMLObject, BaseUMLConnectionLine> connections = baseUMLObject.getConntections();
                
                if (drawing != null) {
                    drawing.drawArrow(graphics, baseUMLObject.getPort(drawing.getMousePoint()), drawing.getMousePoint());
                }

                if (connections.size() != 0) {
                    for (Map.Entry<BaseUMLObject, BaseUMLConnectionLine> connectionEntry : connections.entrySet()) {
                        connectionEntry.getValue().drawArrow(graphics, baseUMLObject.getPort(connectionEntry.getKey().getCenterLocation()), connectionEntry.getKey().getPort(baseUMLObject.getCenterLocation()));
                    }
                }
            }
        }
    }

    public static Canvas getInstance() {
        if (instance == null) {
            instance = new Canvas();
        }

        return instance;
    }

    public static void setCanvasBehavior(ICanvasBehavior iCanvasBehavior) {
        clearSelections();
        canvasBehavior = iCanvasBehavior;
    }

    public void addUMLObject(Component component, int zAxisHeight) {
        this.add(component);
        this.setLayer(component, zAxisHeight);
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

    public static void addSelection(BaseUMLObject selection) {
        selection.setPortVisible(true);
        selections.add(selection);
    }

    public static void clearSelections() {
        for (BaseUMLObject selection : selections) {
            selection.setPortVisible(false);
        }
        selections.clear();
    }

    public static Set<BaseUMLObject> getSelections() {
        return selections;
    }
}
