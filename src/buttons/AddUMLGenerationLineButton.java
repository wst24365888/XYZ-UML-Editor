package buttons;

import javax.swing.ImageIcon;

import canvas_behavior.AddUMLGenerationLine;

public class AddUMLGenerationLineButton extends BaseButton {
    public AddUMLGenerationLineButton() {
        super(new AddUMLGenerationLine());
        this.setImage(new ImageIcon("resource/imgs/generation_line.png"));
    }
}