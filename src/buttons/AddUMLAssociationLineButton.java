package buttons;

import javax.swing.ImageIcon;

import canvas_behavior.AddUMLAssociationLine;
import widgets.Canvas;

public class AddUMLAssociationLineButton extends BaseButton {
    public AddUMLAssociationLineButton() {
        super(new AddUMLAssociationLine());
        this.setImage(new ImageIcon("resource/imgs/association_line.png"));
    }

    @Override
    protected void onClick() {
        super.onClick();
        Canvas.getInstance().setDraggableToAll(false);
    }
}
