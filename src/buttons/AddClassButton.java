package buttons;

import mode.AddClassMode;

public class AddClassButton  extends BaseButton {
    public AddClassButton() {
        super();
        this.canvasBehavior = new AddClassMode();
    }
}
