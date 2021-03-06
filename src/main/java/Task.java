public abstract class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone(){
        this.isDone = true;
    }


    @Override
    public String toString() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "] " + description;
    }

    /**
     * This abstract method is use in event and deadline to get individual task details such as time and description
     * @return
     */
    public abstract String[] getTaskDetails();

}