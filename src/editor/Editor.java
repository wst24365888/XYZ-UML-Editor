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
    private FunctionButtonPanel functionButtonPanel = FunctionButtonPanel.getInstance();
    private Canvas canvas = Canvas.getInstance();

    public Editor(String title) {
        frame.setLayout(null);

        frame.setTitle(title);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        functionButtonPanel.setBounds(0, 0, BUTTON_PANEL_WIDTH, WINDOW_HEIGHT);
        frame.add(functionButtonPanel.getPanel());

        canvas.setBounds(BUTTON_PANEL_WIDTH, 0, WINDOW_WIDTH - BUTTON_PANEL_WIDTH, WINDOW_HEIGHT);
        frame.add(canvas.getCanvas());

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                int width = componentEvent.getComponent().getWidth();
                int height = componentEvent.getComponent().getHeight();

                functionButtonPanel.setBounds(0, 0, BUTTON_PANEL_WIDTH, height);
                canvas.setBounds(BUTTON_PANEL_WIDTH, 0, width - BUTTON_PANEL_WIDTH, height);
            }
        });
    }

    public void start() {
        frame.setVisible(true);
    }
}
