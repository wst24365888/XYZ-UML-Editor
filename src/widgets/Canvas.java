package widgets;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLayeredPane;

import mode.*;

public class Canvas {
    private static Canvas instance = null;

    private JLayeredPane layeredPane = new JLayeredPane();
    private ICanvasBehavior canvasBehavior;

    private Canvas() {
        this.layeredPane.setLayout(null);
        this.layeredPane.setOpaque(true);
        
        this.layeredPane.setBackground(Color.WHITE);
        this.layeredPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                if (canvasBehavior == null) {
                    return;
                }

                canvasBehavior.onPressed(mouseEvent.getX(), mouseEvent.getY());
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                if (canvasBehavior == null) {
                    return;
                }

                canvasBehavior.onReleased(mouseEvent.getX(), mouseEvent.getY());
            }
        });
    }

    public static Canvas getInstance() {
        if (instance == null) {
            instance = new Canvas();
        }

        return instance;
    }

    public void setBounds(int x, int y, int width, int height) {
        this.layeredPane.setBounds(x, y, width, height);
    }

    public JLayeredPane getCanvas() {
        return this.layeredPane;
    }

    public void setCanvasBehavior(ICanvasBehavior canvasBehavior) {
        this.canvasBehavior = canvasBehavior;
    }
}
