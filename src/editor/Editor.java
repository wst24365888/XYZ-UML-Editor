package editor;

import java.awt.event.*;
import javax.swing.*;

import widgets.*;

public class Editor {
    public static final int BUTTON_PADDING = 10;
    public static final int BUTTON_SIDE_LENGTH = 90;

    public static final int APP_BAR_HEIGHT = 40;
    public static final int MENU_BAR_HEIGHT = 30;

    private static final int VISIBLE_BUTTON_AMOUNT = 6;
    private static final int WINDOW_WIDTH = 1280;
    private static final int WINDOW_HEIGHT = APP_BAR_HEIGHT + MENU_BAR_HEIGHT + BUTTON_PADDING
            + (BUTTON_SIDE_LENGTH + BUTTON_PADDING) * VISIBLE_BUTTON_AMOUNT;

    public static final int BUTTON_PANEL_WIDTH = BUTTON_SIDE_LENGTH + BUTTON_PADDING * 2;

    private JFrame frame = new JFrame();

    public Editor(String title) {
        MenuBar.getInstance();
        FunctionButtonPanel.getInstance();
        Canvas canvas = Canvas.getInstance();

        frame.setLayout(null);

        frame.setTitle(title);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setJMenuBar(MenuBar.getMenuBar());

        FunctionButtonPanel.setBounds(0, 0, BUTTON_PANEL_WIDTH, WINDOW_HEIGHT);
        frame.add(FunctionButtonPanel.getPanel());

        canvas.setBounds(BUTTON_PANEL_WIDTH, 0, WINDOW_WIDTH - BUTTON_PANEL_WIDTH, WINDOW_HEIGHT);
        frame.add(canvas);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                int width = componentEvent.getComponent().getWidth();
                int height = componentEvent.getComponent().getHeight();

                FunctionButtonPanel.setBounds(0, 0, BUTTON_PANEL_WIDTH, height);
                canvas.setBounds(BUTTON_PANEL_WIDTH, 0, width - BUTTON_PANEL_WIDTH, height);
            }
        });
    }

    public void start() {
        frame.setVisible(true);
    }
}
