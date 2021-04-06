package components.UMLObjects;

import java.awt.*;

import javax.swing.JLabel;

import components.port.Port;

public class UMLClass extends BaseUMLObject {
    private static final long serialVersionUID = 8488292894462555825L;

    public UMLClass() {
        super();

        this.width = 130;
        this.height = 150;
        this.offset = 5;

        this.label = new JLabel("New Class");

        this.label.setBounds(2 * this.offset, this.offset, this.width - 4 * this.offset, 50);
        this.label.setHorizontalAlignment(JLabel.CENTER);

        this.add(label);

        this.ports = new Port(new Point(60 + this.offset, 0 + this.offset),
                new Point(120 + this.offset, 70 + this.offset), new Point(60 + this.offset, 140 + this.offset),
                new Point(0 + this.offset, 70 + this.offset));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(this.offset, this.offset, 120, 140);

        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(this.offset + 5, this.offset + 5, 110, 40);
        graphics2D.fillRect(this.offset + 5, this.offset + 50, 110, 40);
        graphics2D.fillRect(this.offset + 5, this.offset + 95, 110, 40);

        // Render the text.
        super.paintComponent(graphics);
    }
}
