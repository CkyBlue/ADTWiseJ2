package Utility.RecursionStack;

public abstract class Controller {
    private Data data;

    public enum Alteration {
        pushed_to_atack,
        popped_from_stack
    }

    public abstract void notifyOfChangeToData(Alteration alterationType);

    public void setData(Data data) {
        if (this.data != null) {
            this.data.setController(null);
        }

        if (data != null) {
            data.setController(this);
        }

        this.data = data;


    }

    public Data getData() {
        return data;
    }
}
