import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> task = new ArrayList<>();

    public int getTaskSize(){
        return this.task.size();
    }

    public void addTask(Task newTask){
        this.task.add(newTask);
    }

    public Task removeTask(int taskIndex){
        return this.task.remove(taskIndex);
    }

    public Task getFullTask(int taskIndex){
        return this.task.get(taskIndex);
    }

    public ArrayList<Task> getTask() {
        return this.task;
    }

    public TaskList(ArrayList<Task> loadedTaskList){
        this.task = loadedTaskList;
    }

    public TaskList(){

    }
}
