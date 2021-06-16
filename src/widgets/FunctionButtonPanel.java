package widgets;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import editor.Editor;
import enum_types.UMLConnectionLine;
import enum_types.UMLObject;
import buttons.Button;
import canvas_behavior.AddUMLConnectionLine;
import canvas_behavior.AddUMLObject;
import canvas_behavior.Select;

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
        functionButtons.add((new Button(new Select(), new ImageIcon("resource/imgs/select.png")).getButton()));
        functionButtons.add((new Button(new AddUMLConnectionLine(UMLConnectionLine.Association), new ImageIcon("resource/imgs/association_line.png")).getButton()));
        functionButtons.add((new Button(new AddUMLConnectionLine(UMLConnectionLine.Composition), new ImageIcon("resource/imgs/composition_line.png")).getButton()));
        functionButtons.add((new Button(new AddUMLConnectionLine(UMLConnectionLine.Generalization), new ImageIcon("resource/imgs/generalization_line.png")).getButton()));
        functionButtons.add((new Button(new AddUMLObject(UMLObject.Class), new ImageIcon("resource/imgs/class.png")).getButton()));
        functionButtons.add((new Button(new AddUMLObject(UMLObject.UseCase), new ImageIcon("resource/imgs/use_case.png"))).getButton());
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
