package buttons;

import javax.swing.ImageIcon;

import canvas_behavior.AddUMLConnectionLine;


public class AddUMLGeneralizationLineButton extends BaseButton {
    private static AddUMLGeneralizationLineButton instance = null;

    private AddUMLGeneralizationLineButton() {
        super(new AddUMLConnectionLine("Generalization"));
    }

    public static AddUMLGeneralizationLineButton getInstance() {
        if (instance == null) {
            instance = new AddUMLGeneralizationLineButton();
        }

        return instance;
    }

    @Override
    protected void setImage() {
        this.button.setIcon(new ImageIcon("resource/imgs/generation_line.png"));
    }
}