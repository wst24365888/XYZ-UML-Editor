package editor;

import java.awt.event.*;
import javax.swing.*;

import widgets.*;

public class Editor {
    public static final int BUTTON_PADDING = 12;
    public static final int BUTTON_SIDE_LENGTH = 100;

    private static final int APP_BAR_HEIGHT = 40;
    private static final int VISIBLE_BUTTON_AMOUNT = 6;
    private static final int WINDOW_WIDTH = 1280;
    private static final int WINDOW_HEIGHT = APP_BAR_HEIGHT + BUTTON_PADDING + (BUTTON_SIDE_LENGTH + BUTTON_PADDING) * VISIBLE_BUTTON_AMOUNT;
    private static final int BUTTON_PANEL_WIDTH = BUTTON_SIDE_LENGTH + BUTTON_PADDING * 2;

    private JFrame frame = new JFrame();

    public Editor(String title) {
        FunctionButtonPanel.getInstance();
        Canvas.getInstance();

        frame.setLayout(null);

        frame.setTitle(title);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        FunctionButtonPanel.setBounds(0, 0, BUTTON_PANEL_WIDTH, WINDOW_HEIGHT);
        frame.add(FunctionButtonPanel.getPanel());

        Canvas.setBounds(BUTTON_PANEL_WIDTH, 0, WINDOW_WIDTH - BUTTON_PANEL_WIDTH, WINDOW_HEIGHT);
        frame.add(Canvas.getCanvas());

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                int width = componentEvent.getComponent().getWidth();
                int height = componentEvent.getComponent().getHeight();

                FunctionButtonPanel.setBounds(0, 0, BUTTON_PANEL_WIDTH, height);
                Canvas.setBounds(BUTTON_PANEL_WIDTH, 0, width - BUTTON_PANEL_WIDTH, height);
            }
        });
    }

    public void start() {
        frame.setVisible(true);
    }
}
