package buttons;

import javax.swing.ImageIcon;

import canvas_behavior.AddUMLCompositionLine;

public class AddUMLCompositionLineButton extends BaseButton {
    public AddUMLCompositionLineButton() {
        super(new AddUMLCompositionLine());
        this.setImage(new ImageIcon("resource/imgs/composition_line.png"));
    }
}
