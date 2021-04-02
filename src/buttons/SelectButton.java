package buttons;

import mode.SelectMode;

public class SelectButton extends BaseButton {
    public SelectButton() {
        super();
        this.canvasBehavior = new SelectMode();
    }
}
