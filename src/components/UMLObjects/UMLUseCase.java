package components.UMLObjects;

import java.awt.*;

import java.awt.geom.*;
import javax.swing.JLabel;

public class UMLUseCase extends BaseUMLObject {
    private static final long serialVersionUID = -2442512529591589385L;

    public UMLUseCase() {
        super();

        JLabel label = new JLabel("New Use Case");

        label.setBounds(5, 5, 120, 80);
        label.setHorizontalAlignment(JLabel.CENTER);

        this.add(label);

        this.ports.add(new Point(60, 0));
        this.ports.add(new Point(0, 40));
        this.ports.add(new Point(120, 40));
        this.ports.add(new Point(60, 80));

        this.setPortVisible(true);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setColor(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Double(5, 5, 120, 80));

        graphics2D.setColor(Color.WHITE);
        graphics2D.fill(new Ellipse2D.Double(10, 10, 110, 70));

        // Render the text.
        super.paintComponent(graphics);
    }
}
