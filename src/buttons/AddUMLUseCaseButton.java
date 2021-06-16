package buttons;

import javax.swing.ImageIcon;

import canvas_behavior.AddUMLObject;

public class AddUMLUseCaseButton extends BaseButton {
    private static AddUMLUseCaseButton instance = null;

    private AddUMLUseCaseButton() {
        super(new AddUMLObject("Use Case"));
    }

    public static AddUMLUseCaseButton getInstance() {
        if (instance == null) {
            instance = new AddUMLUseCaseButton();
        }

        return instance;
    }

    @Override
    protected void setImage() {
        this.button.setIcon(new ImageIcon("resource/imgs/use_case.png"));
    }
}
