package buttons;

import javax.swing.ImageIcon;

import canvas_behavior.Select;
import widgets.Canvas;

public class SelectButton extends BaseButton {
    public SelectButton() {
        super(new Select());
        this.setImage(new ImageIcon("resource/imgs/select.png"));
    }

    @Override
    protected void onClick() {
        super.onClick();
        Canvas.setDraggableToAll(true);
    }
}
