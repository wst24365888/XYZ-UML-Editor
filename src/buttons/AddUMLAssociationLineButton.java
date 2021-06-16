package buttons;

import javax.swing.ImageIcon;

import canvas_behavior.AddUMLConnectionLine;

public class AddUMLAssociationLineButton extends BaseButton {
    private static AddUMLAssociationLineButton instance = null;

    private AddUMLAssociationLineButton() {
        super(new AddUMLConnectionLine("Association"));
    }

    public static AddUMLAssociationLineButton getInstance() {
        if (instance == null) {
            instance = new AddUMLAssociationLineButton();
        }

        return instance;
    }

    @Override
    protected void setImage() {
        this.button.setIcon(new ImageIcon("resource/imgs/association_line.png"));
    }
}
