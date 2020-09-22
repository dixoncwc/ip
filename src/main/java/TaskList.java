import java.util.ArrayList;

/**
 * This class is a taskList of the class Task
 */
public class TaskList {
    private ArrayList<Task> task = new ArrayList<>();

    /**
     * This method will return the arraylist size
     * @return
     */
    public int getTaskSize(){
        return this.task.size();
    }

    /**
     * This method will add new task into the arraylist
     * @param newTask
     */
    public void addTask(Task newTask){
        this.task.add(newTask);
    }

    /**
     * This method will delete the task based on the taskIndex from the arraylist
     * @param taskIndex
     * @return
     */
    public Task removeTask(int taskIndex){
        return this.task.remove(taskIndex);
    }

    /**
     * This method will return the full task details from the arraylist
     * @param taskIndex
     * @return
     */
    public Task getFullTask(int taskIndex){
        return this.task.get(taskIndex);
    }

    /**
     * This method will return the arraylist of class Task
     * User can use this method to access class Task and get individual details of the task
     * @return
     */
    public ArrayList<Task> getTask() {
        return this.task;
    }

    /**
     * This method is to load all the task from the saved file to the arraylist
     * @param loadedTaskList
     */
    public TaskList(ArrayList<Task> loadedTaskList){
        this.task = loadedTaskList;
    }

    public TaskList(){

    }
}
