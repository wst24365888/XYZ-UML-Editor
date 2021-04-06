package buttons;

import javax.swing.ImageIcon;

import canvas_behavior.Select;

public class SelectButton extends BaseButton {
    public SelectButton() {
        super(new Select());
        this.setImage(new ImageIcon("resource/imgs/select.png"));
    }
}
