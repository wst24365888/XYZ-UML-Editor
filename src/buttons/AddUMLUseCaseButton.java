package buttons;

import javax.swing.ImageIcon;

import canvas_behavior.AddUMLUseCase;

public class AddUMLUseCaseButton extends BaseButton {
    public AddUMLUseCaseButton() {
        super(new AddUMLUseCase());
        this.setImage(new ImageIcon("resource/imgs/use_case.png"));
    }

    @Override
    protected void onClick() {
        super.onClick();
    }
}
