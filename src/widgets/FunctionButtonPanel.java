package widgets;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

import editor.Editor;
import mode.AddUMLClassMode;
import mode.AddUMLUseCaseMode;
import mode.SelectMode;

public class FunctionButtonPanel {
    private static FunctionButtonPanel instance = null;

    private static JPanel panel = new JPanel();
    private static ArrayList<JButton> functionButtons = new ArrayList<JButton>();

    private FunctionButtonPanel() {
        panel.setLayout(null);
        panel.setBackground(Color.GRAY);

        loadButtons();

        for (JButton functionButton : functionButtons) {
            functionButton.setBounds(Editor.BUTTON_PADDING,
                    Editor.BUTTON_PADDING + (Editor.BUTTON_SIDE_LENGTH + Editor.BUTTON_PADDING)
                            * functionButtons.indexOf(functionButton),
                    Editor.BUTTON_SIDE_LENGTH, Editor.BUTTON_SIDE_LENGTH);
            panel.add(functionButton);
        }
    }

    public static FunctionButtonPanel getInstance() {
        if (instance == null) {
            instance = new FunctionButtonPanel();
        }

        return instance;
    }

    private static void loadButtons() {
        functionButtons.add((new ModeButton(new SelectMode())).getButton());
        functionButtons.add((new ModeButton(new AddUMLClassMode())).getButton());
        functionButtons.add((new ModeButton(new AddUMLUseCaseMode())).getButton());
    }

    public static void setBounds(int x, int y, int width, int height) {
        panel.setBounds(x, y, width, height);
    }

    public static JPanel getPanel() {
        return panel;
    }

    public static void resetPanel(int selectedIndex) {
        for (JButton functionButton : functionButtons) {
            functionButton.setBackground(Color.WHITE);
        }

        functionButtons.get(selectedIndex).setBackground(Color.DARK_GRAY);
    }
}
