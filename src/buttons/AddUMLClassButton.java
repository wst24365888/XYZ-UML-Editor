package buttons;

import javax.swing.ImageIcon;

import canvas_behavior.AddUMLClass;

public class AddUMLClassButton extends BaseButton {
    public AddUMLClassButton() {
        super(new AddUMLClass());
        this.setImage(new ImageIcon("resource/imgs/classes.png"));
    }

    @Override
    protected void onClick() {
        super.onClick();
    }
}
