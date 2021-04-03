package components.UMLObjects;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class UMLUseCase extends BaseUMLObject {
    private static final long serialVersionUID = -2442512529591589385L;

    public UMLUseCase() {
        super();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setColor(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Double(0, 0, 120, 80));

        graphics2D.setColor(Color.WHITE);
        graphics2D.fill(new Ellipse2D.Double(5, 5, 110, 70));
    }
}
