package widgets;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JLayeredPane;

import canvas_behavior.*;
import components.UMLConnectionLines.BaseUMLConnectionLine;
import components.UMLObjects.BaseUMLObject;
import editor.Editor;

public class Canvas extends JLayeredPane {
    private static final long serialVersionUID = 1985691147832327662L;

    private static Canvas instance = null;

    private static ICanvasBehavior canvasBehavior;
    private static Set<BaseUMLObject> selections = new HashSet<BaseUMLObject>();

    private BaseUMLConnectionLine drawing = null;
    private ArrayList<BaseUMLConnectionLine> connections = new ArrayList<BaseUMLConnectionLine>();

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

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        if (this.drawing != null) {
            drawing.drawArrowLine(graphics);
            drawing.drawArrow(graphics);
        }

        // Draw the line.
        for (BaseUMLConnectionLine connection : connections) {
            connection.drawArrowLine(graphics);
        }

        // Then cover the arrow on it.
        for (BaseUMLConnectionLine connection : connections) {
            connection.drawArrow(graphics);
        }
    }

    public static void setCanvasBehavior(ICanvasBehavior iCanvasBehavior) {
        clearSelections();
        Select.getInstance().clearSelectedArea();

        canvasBehavior = iCanvasBehavior;
    }

    public void addUMLObject(Component component, int zAxisHeight) {
        this.add(component);
        this.setLayer(component, zAxisHeight);
    }

    public BaseUMLObject getPressedComponent(int x, int y) {
        Component[] components = this.getComponents();
        BaseUMLObject result =  null;

        for (Component component : components) {
            if (component instanceof BaseUMLObject) {
                BaseUMLObject tmp = (BaseUMLObject) component;
                if (tmp.getBounds().contains(x, y)) {
                    if (result == null || tmp.getZAxisHeight() > result.getZAxisHeight()) {
                        result = tmp;
                    }
                }
            }
        }

        return result;
    }

    public ArrayList<BaseUMLObject> getWithinComponent(Rectangle rectangle) {
        Component[] components = this.getComponents();
        ArrayList<BaseUMLObject> results = new ArrayList<BaseUMLObject>();

        for (Component component : components) {
            if (component instanceof BaseUMLObject) {
                BaseUMLObject tmp = (BaseUMLObject) component;
                
                if (rectangle.contains(tmp.getBounds())) {
                    results.add(tmp);
                }
            }
        }

        return results;
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

    public static Point getRelativeLocation(Point point) {
        return new Point((int) point.getX() - Editor.BUTTON_PANEL_WIDTH, (int) point.getY() - Editor.APP_BAR_HEIGHT - Editor.MENU_BAR_HEIGHT);
    }

    public void setDrawingLine(BaseUMLConnectionLine drawing) {
        this.drawing = drawing;
        this.repaint();
    }

    public void addConntection(BaseUMLConnectionLine newConnection) {
        int alreadyHasConnection = -1;
        
        for (BaseUMLConnectionLine connection : connections) {
            if (connection.alreadyHasConnection(newConnection.getSource(), newConnection.getDestination())) {
                alreadyHasConnection = connections.indexOf(connection);
            }
        }

        if (alreadyHasConnection == -1) {
            connections.add(newConnection);
        } else {
            connections.set(alreadyHasConnection, newConnection);
        }

        this.repaint();
    }
}
