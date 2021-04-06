package buttons;

import javax.swing.ImageIcon;

import canvas_behavior.AddUMLGenerationLine;

public class AddUMLGenerationLineButton extends BaseButton {
    private static AddUMLGenerationLineButton instance = null;

    private AddUMLGenerationLineButton() {
        super(AddUMLGenerationLine.getInstance());
    }

    public static AddUMLGenerationLineButton getInstance() {
        if (instance == null) {
            instance = new AddUMLGenerationLineButton();
        }

        return instance;
    }    

    @Override
    protected void setImage() {
        this.button.setIcon(new ImageIcon("resource/imgs/generation_line.png"));
    }
}