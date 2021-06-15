package widgets;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLayeredPane;

import canvas_behavior.*;
import components.UMLConnectionLines.BaseUMLConnectionLine;
import components.UMLObjects.BaseUMLObject;
import components.UMLObjects.UMLClass;
import components.UMLObjects.UMLGroup;
import components.UMLObjects.UMLUseCase;
import editor.Editor;

public class Canvas extends JLayeredPane {
    private static final long serialVersionUID = 1985691147832327662L;

    private static Canvas instance = null;

    private static ICanvasBehavior canvasBehavior;
    private ArrayList<BaseUMLObject> selections = new ArrayList<BaseUMLObject>();

    private ArrayList<BaseUMLObject> allBaseUMLObject = new ArrayList<BaseUMLObject>();

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
        this.setDoubleBuffered(true);

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

        setMenuEnable();

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

    private void setMenuEnable() {
        MenuBar.setChangeObjectNameEnable(selections.size() == 1 && (selections.iterator().next() instanceof UMLClass
                || selections.iterator().next() instanceof UMLUseCase));
        MenuBar.setGroupObjectsEnable(selections.size() > 1);
        MenuBar.setUnGroupObjectsEnable(selections.size() == 1 && selections.iterator().next() instanceof UMLGroup);
    }

    public void setCanvasBehavior(ICanvasBehavior iCanvasBehavior) {
        clearSelections();
        Select.getInstance().clearSelectedArea();

        canvasBehavior = iCanvasBehavior;
    }

    public void addSelectedArea(Component component) {
        this.add(component);
        this.setLayer(component, -1);
    }

    public void addBaseUMLObject(BaseUMLObject component, int zAxisHeight) {
        this.add(component);
        this.setLayer(component, zAxisHeight);
        this.allBaseUMLObject.add(component);
    }

    public void removeBaseUMLObject(BaseUMLObject component) {
        this.remove(component);
        this.allBaseUMLObject.remove(component);
    }

    public BaseUMLObject getComponentWithin(int x, int y) {
        BaseUMLObject result = null;

        for (BaseUMLObject component : this.allBaseUMLObject) {
            if (component.getBounds().contains(x, y)) {
                if (result == null || component.getZAxisHeight() > result.getZAxisHeight()) {
                    result = component;
                }
            }
        }

        return result;
    }

    public ArrayList<BaseUMLObject> getAllComponentWithin(Rectangle rectangle) {
        ArrayList<BaseUMLObject> results = new ArrayList<BaseUMLObject>();

        for (BaseUMLObject component : this.allBaseUMLObject) {
            if (rectangle.contains(component.getBounds())) {
                results.add(component);
            }
        }

        return results;
    }

    public void setSelections(ArrayList<BaseUMLObject> selections) {
        for (BaseUMLObject selection : selections) {
            selection.setPortVisible(true);    
        }

        this.selections = selections;
        this.repaint();
    }

    public void clearSelections() {
        for (BaseUMLObject selection : this.selections) {
            selection.setPortVisible(false);
        }

        this.selections.clear();
    }

    public ArrayList<BaseUMLObject> getSelections() {
        return selections;
    }

    public static Point getRelativeLocation(Point point) {
        // Window offset: (8, -9)
        return new Point((int) point.getX() - 8 - Editor.BUTTON_PANEL_WIDTH,
                (int) point.getY() + 9 - Editor.APP_BAR_HEIGHT - Editor.MENU_BAR_HEIGHT);
    }

    public void setDrawingLine(BaseUMLConnectionLine drawing) {
        this.drawing = drawing;
        this.repaint();
    }

    public void addUMLConntection(BaseUMLConnectionLine newConnection) {
        connections.add(newConnection);
        this.repaint();
    }
}
