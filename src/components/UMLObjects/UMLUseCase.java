package components.UMLObjects;

import java.awt.*;

import java.awt.geom.*;
import javax.swing.JLabel;

import components.port.Port;

public class UMLUseCase extends BaseUMLObject {
    private static final long serialVersionUID = -2442512529591589385L;

    public UMLUseCase() {
        super();

        this.width = 130;
        this.height = 90;
        this.offset = 5;

        this.label = new JLabel("New Use Case");

        this.label.setBounds(2 * this.offset, this.offset, this.width - 4 * this.offset, this.height - 2 * this.offset);
        this.label.setHorizontalAlignment(JLabel.CENTER);

        this.add(label);

        this.ports = new Port(new Point(60 + this.offset, 0 + this.offset),
                new Point(120 + this.offset, 40 + this.offset), new Point(60 + this.offset, 80 + this.offset),
                new Point(0 + this.offset, 40 + this.offset));
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setColor(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Double(this.offset, this.offset, 120, 80));

        graphics2D.setColor(Color.WHITE);
        graphics2D.fill(new Ellipse2D.Double(this.offset + 5, this.offset + 5, 110, 70));

        // Render the text.
        super.paintComponent(graphics);
    }
}
