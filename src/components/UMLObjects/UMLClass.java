package components.UMLObjects;

import java.awt.*;

import javax.swing.JLabel;

public class UMLClass extends BaseUMLObject {
    private static final long serialVersionUID = 8488292894462555825L;

    public UMLClass() {
        super();

        JLabel label = new JLabel("New Class");
        
        label.setBounds(0, 0, 120, 50);
        label.setHorizontalAlignment(JLabel.CENTER);

        this.add(label);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(0, 0, 120, 155);

        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(5, 5, 110, 40);
        graphics2D.fillRect(5, 50, 110, 40);
        graphics2D.fillRect(5, 95, 110, 40);

        // Render the text.
        super.paintComponent(graphics);
    }
}
