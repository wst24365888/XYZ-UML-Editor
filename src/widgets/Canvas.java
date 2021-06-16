package widgets;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.swing.JLayeredPane;

import canvas_behavior.*;
import components.UMLConnectionLines.BaseUMLConnectionLine;
import components.UMLObjects.BaseUMLObject;
import editor.Editor;
import enum_types.MenuFunction;

public class Canvas extends JLayeredPane {
    private static final long serialVersionUID = 1985691147832327662L;

    private static Canvas instance = null;

    private MouseAdapter canvasBehavior;
    private ArrayList<BaseUMLObject> selections = new ArrayList<BaseUMLObject>();

    private ArrayList<BaseUMLObject> allBaseUMLObject = new ArrayList<BaseUMLObject>();

    private BaseUMLConnectionLine drawing = null;
    private ArrayList<BaseUMLConnectionLine> connections = new ArrayList<BaseUMLConnectionLine>();

    private Canvas() {
        this.setLayout(null);
        this.setOpaque(true);
        this.setDoubleBuffered(true);

        this.setBackground(Color.WHITE);
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
        MenuBar.setFunctionEnabled(MenuFunction.ChangeName, selections.size() == 1 && selections.iterator().next().isNameChangable());
        MenuBar.setFunctionEnabled(MenuFunction.Group, selections.size() > 1);
        MenuBar.setFunctionEnabled(MenuFunction.Ungroup, selections.size() == 1 && selections.iterator().next().getUMLComponents() != null);
    }

    public void setCanvasBehavior(MouseAdapter canvasBehavior) {
        clearSelections();
        Select.getInstance().clearSelectedArea();

        this.removeMouseListener(this.canvasBehavior);
        this.removeMouseMotionListener(this.canvasBehavior);

        this.canvasBehavior = canvasBehavior;
        
        this.addMouseListener(this.canvasBehavior);
        this.addMouseMotionListener(this.canvasBehavior);
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

    public void addUMLConntectionLine(BaseUMLConnectionLine newConnection) {
        connections.add(newConnection);
        this.repaint();
    }
}
