package buttons;

import javax.swing.ImageIcon;

import canvas_behavior.AddUMLCompositionLine;

public class AddUMLCompositionLineButton extends BaseButton {
    private static AddUMLCompositionLineButton instance = null;

    private AddUMLCompositionLineButton() {
        super(AddUMLCompositionLine.getInstance());
    }

    public static AddUMLCompositionLineButton getInstance() {
        if (instance == null) {
            instance = new AddUMLCompositionLineButton();
        }

        return instance;
    }

    @Override
    protected void setImage() {
        this.button.setIcon(new ImageIcon("resource/imgs/composition_line.png"));
    }
}
