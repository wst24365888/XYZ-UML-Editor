package buttons;

import javax.swing.ImageIcon;

import canvas_behavior.AddUMLObject;

public class AddUMLClassButton extends BaseButton {
    private static AddUMLClassButton instance = null;

    private AddUMLClassButton() {
        super(new AddUMLObject("Class"));
    }

    public static AddUMLClassButton getInstance() {
        if (instance == null) {
            instance = new AddUMLClassButton();
        }

        return instance;
    }

    @Override
    protected void setImage() {
        this.button.setIcon(new ImageIcon("resource/imgs/classes.png"));
    }
}
