package components.UMLObjects;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.JLabel;

public class UMLUseCase extends BaseUMLObject {
    private static final long serialVersionUID = -2442512529591589385L;

    public UMLUseCase() {
        super();

        JLabel label = new JLabel("New Use Case");

        label.setBounds(0, 0, 120, 80);
        label.setHorizontalAlignment(JLabel.CENTER);

        this.add(label);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setColor(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Double(0, 0, 120, 80));

        graphics2D.setColor(Color.WHITE);
        graphics2D.fill(new Ellipse2D.Double(5, 5, 110, 70));

        // Render the text.
        super.paintComponent(graphics);
    }
}
