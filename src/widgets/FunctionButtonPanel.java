package widgets;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

import editor.Editor;
import buttons.*;

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
        functionButtons.add(SelectButton.getInstance().getButton());
        functionButtons.add(AddUMLAssociationLineButton.getInstance().getButton());
        functionButtons.add(AddUMLGeneralizationLineButton.getInstance().getButton());
        functionButtons.add(AddUMLCompositionLineButton.getInstance().getButton());
        functionButtons.add(AddUMLClassButton.getInstance().getButton());
        functionButtons.add(AddUMLUseCaseButton.getInstance().getButton());
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
