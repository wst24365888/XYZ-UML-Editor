package components.UMLObjects;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JLabel;

import components.port.Port;

public class UMLGroup extends BaseUMLObject {
    private static final long serialVersionUID = -599667535411632025L;

    private ArrayList<BaseUMLObject> components = new ArrayList<BaseUMLObject>();

    public UMLGroup(JLabel component) {
        super();

        this.offset = 5;
        this.width = component.getWidth() + this.offset * 2;
        this.height = component.getHeight() + this.offset * 2;

        this.setOpaque(false);
        this.setBackground(component.getBackground());

        this.ports = new Port(new Point(0 + this.offset, 0 + this.offset),
                new Point(this.width - this.offset, 0 + this.offset),
                new Point(this.width - this.offset, this.height - this.offset),
                new Point(0 + this.offset, this.height - this.offset));
    }

    public void addComponent(BaseUMLObject component) {
        this.add(component);
        components.add(component);
    }

    public ArrayList<BaseUMLObject> getUMLComponents() {
        return this.components;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setColor(this.getBackground());
        graphics2D.fillRect(this.offset, this.offset, this.width - this.offset * 2, this.height - this.offset * 2);
    }
}
