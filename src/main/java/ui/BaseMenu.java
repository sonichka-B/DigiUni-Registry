package ui;

public abstract class BaseMenu {
    protected Validation validation=new Validation();
    protected abstract void printTitle();
    protected abstract void printOptions();
    protected abstract int getMaxOption();
    protected abstract void handleChoice(int choice);
    public void showMenu(){
        while(true){
            printTitle();
            printOptions();
            int choice = validation.readInt("Оберіть дію", 0, getMaxOption());
            if (choice == 0) {
                return;
            }
            handleChoice(choice);
        }

    }
}
