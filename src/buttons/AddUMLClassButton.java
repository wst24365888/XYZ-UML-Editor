package buttons;

import javax.swing.ImageIcon;

import canvas_behavior.AddUMLClass;
import widgets.Canvas;

public class AddUMLClassButton extends BaseButton {
    public AddUMLClassButton() {
        super(new AddUMLClass());
        this.setImage(new ImageIcon("resource/imgs/classes.png"));
    }

    @Override
    protected void onClick() {
        super.onClick();
        Canvas.setDraggableToAll(false);
    }
}
