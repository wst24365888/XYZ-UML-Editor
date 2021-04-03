package components.UMLObjects;

import java.awt.*;

import javax.swing.JLabel;

public class UMLClass extends BaseUMLObject {
    private static final long serialVersionUID = 8488292894462555825L;

    public UMLClass() {
        super();

        JLabel label = new JLabel("New Class");
        
        label.setBounds(5, 5, 120, 50);
        label.setHorizontalAlignment(JLabel.CENTER);

        this.add(label);

        this.ports.add(new Point(60, 0));
        this.ports.add(new Point(0, 70));
        this.ports.add(new Point(120, 70));
        this.ports.add(new Point(60, 140));

        this.setPortVisible(true);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(5, 5, 120, 140);

        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(10, 10, 110, 40);
        graphics2D.fillRect(10, 55, 110, 40);
        graphics2D.fillRect(10, 100, 110, 40);

        // Render the text.
        super.paintComponent(graphics);
    }
}
