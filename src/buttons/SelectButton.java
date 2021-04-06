package buttons;

import javax.swing.ImageIcon;

import canvas_behavior.Select;

public class SelectButton extends BaseButton {
    private static SelectButton instance = null;

    private SelectButton() {
        super(Select.getInstance());
    }

    public static SelectButton getInstance() {
        if (instance == null) {
            instance = new SelectButton();
        }

        return instance;
    }

    @Override
    protected void setImage() {
        this.button.setIcon(new ImageIcon("resource/imgs/select.png"));
    }
}
