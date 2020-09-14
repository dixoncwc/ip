public class ToDo extends Task {

    protected boolean isDone;

    public ToDo(String description) {
        super(description);
        isDone = false;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    @Override
    public String[] getTaskDetails() {
        return new String[0];
    }
}