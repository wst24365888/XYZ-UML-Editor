package buttons;

import javax.swing.ImageIcon;

import canvas_behavior.AddUMLCompositionLine;
import widgets.Canvas;

public class AddUMLCompositionLineButton extends BaseButton {
    public AddUMLCompositionLineButton() {
        super(new AddUMLCompositionLine());
        this.setImage(new ImageIcon("resource/imgs/composition_line.png"));
    }

    @Override
    protected void onClick() {
        super.onClick();
        Canvas.getInstance().setDraggableToAll(false);
    }
}
