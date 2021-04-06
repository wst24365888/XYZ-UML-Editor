package components.UMLObjects;

import java.awt.*;

import java.awt.geom.*;
import javax.swing.JLabel;

import components.port.Port;

import javax.swing.JComponent;

public class UMLGroup extends BaseUMLObject {
    private static final long serialVersionUID = -599667535411632025L;

    public UMLGroup(JLabel component) {
        super();

        this.width = component.getWidth();
        this.height = component.getHeight();

        this.setOpaque(false);
        this.setBackground(component.getBackground());
    }

    public void addComponent(JComponent component) {
        this.add(component);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setColor(this.getBackground());
        graphics2D.fillRect(this.offset, this.offset, this.width, this.height);
    }
}
