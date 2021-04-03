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
    
    private JPanel panel = new JPanel();
    private ArrayList<JButton> functionButtons = new ArrayList<JButton>();

    private FunctionButtonPanel() {
        this.panel.setLayout(null);
        this.panel.setBackground(Color.GRAY);

        loadButtons();

        for (JButton functionButton : this.functionButtons) {
            functionButton.setBounds(Editor.BUTTON_PADDING,
                    Editor.BUTTON_PADDING + (Editor.BUTTON_SIDE_LENGTH + Editor.BUTTON_PADDING)
                            * this.functionButtons.indexOf(functionButton),
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

    private void loadButtons() {
        this.functionButtons.add((new ModeButton(new SelectMode())).getButton());
        this.functionButtons.add((new ModeButton(new AddUMLClassMode())).getButton());
        this.functionButtons.add((new ModeButton(new AddUMLUseCaseMode())).getButton());
    }

    public void setBounds(int x, int y, int width, int height) {
        this.panel.setBounds(x, y, width, height);
    }

    public JPanel getPanel() {
        return this.panel;
    }
}
