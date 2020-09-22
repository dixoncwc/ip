import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String boundary = "____________________________________________________________";
    public String commandTxt;
    public String[] Message;
    public static String newMessage;
    public static Task newTask;
    public int taskNo = 0;
    public static ArrayList<Task> itemList = new ArrayList<>();
    public static Task task;
    public static int index = 1;
    public static boolean endConvo = false;
    public static Parser parser;
    public static TaskList taskList;
    public static Storage storage;

    public void showIntro() throws FileNotFoundException, DukeException {
        Scanner scan = new Scanner(System.in);
        this.parser = new Parser();
        this.storage = new Storage("Duke/DukeTaskList.txt");
        this.taskList = new TaskList(storage.loadFile());

        for(int i=0; i < taskList.getTaskSize(); i++){
            taskNo++;
        }

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\nHello from\n" + logo);

        System.out.println(boundary+"\nHello! I'm Duke\n" + "What can I do for you?\n" +boundary);

        while(!endConvo){
            if(!scan.hasNextLine()){
                return;
            } else{
                try {
                    commandTxt = scan.nextLine();
                    Message = parser.parseCommand(commandTxt);

                    if (Message[0].equals("bye")) {
                       storage.saveFile(taskList);
                    } else if (Message[0].equals("list")) {
                        displayList();
                    } else if (Message[0].equals("done")) {
                        done();
                    } else if (Message[0].equals("todo")) {
                        addTodo();
                    } else if (Message[0].equals("deadline")) {
                        addDeadline();
                    } else if (Message[0].equals("event")) {
                        addEvent();
                    } else if (Message[0].equals("help")) {
                        showHelp();
                    } else if(Message[0].equals("delete")){
                        deleteTask();
                    } else {
                        throw new DukeException();
                    }
                }catch(DukeException | IOException e){
                    System.out.println("Oops!!! Sorry but I don't know what that means");
                }
            }
        }

        System.out.println(boundary+ "\nBye. Hope to see you again soon\n" +boundary);
    }

    /**
     * This method is use to display the whole list of task stored inside the ArrayList when the user
     * input command "list"
     */
    public void displayList(){
        System.out.println(boundary);

        for (int i = 0; i < taskList.getTaskSize(); i++) {
            System.out.println(String.format("%d", index) + "." + taskList.getFullTask(i));
            index++;
        }

        System.out.println(boundary);
        index = 1;
    }

    /**
     * This method is use to mark a task as done according to the index user has input
     */
    public void done(){
        int doneTaskNo = Integer.parseInt(Message[1]);
        task = taskList.getFullTask(doneTaskNo - 1);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("\t[" + task);
        System.out.println(boundary);
    }

    /**
     * This method is use to delete a task according to the index user has input
     */
    public void deleteTask() {
        int deleteTaskNo = (Integer.parseInt(Message[1])) - 1;
        task = taskList.getFullTask(deleteTaskNo);
        taskList.removeTask(deleteTaskNo);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskList.getTaskSize() + " task in the list\n" + boundary);
        taskNo--;
    }

    /**
     * This method is use to add a To-Do into the arraylist
     * User have to enter command todo with the task details
     */
    public void addTodo(){
        try {
            newTask = new ToDo(Message[1]);
            taskList.addTask(newTask);
            System.out.println(boundary + "\nGot it. I've added this task: " + System.lineSeparator()
                   + "  " + taskList.getFullTask(taskNo));
            System.out.println("Now you have " + taskList.getTaskSize() + " task in the list\n" + boundary);
            taskNo++;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops!!! The description of todo cannot be empty");
        }
    }

    /**
     * This method is use to enter a task that has a deadline.
     * User will have to input command deadline + details and datetime by using /by
     */
    public void addDeadline(){
        try {
            newMessage = Message[1];
            String[] DeadlineMessage = parser.parseDeadlineCommand(newMessage);
            newTask = new Deadline(DeadlineMessage[0], DeadlineMessage[1]);
            taskList.addTask(newTask);
            System.out.println(boundary + "\nGot it. I've added this task: " + System.lineSeparator()
                    + "  " + taskList.getFullTask(taskNo));
            System.out.println("Now you have " + taskList.getTaskSize() + " task in the list\n" + boundary);
            taskNo++;
        }catch(IndexOutOfBoundsException e){
            System.out.println("Oops!!! Please enter a description and deadline");
        }
    }

    /**
     * This method is use to enter an event that is happening some other days.
     * User will have to input command event + details and datetime by using /at
     */
    public void addEvent(){
        try {
            newMessage = Message[1];
            String[] EventMessage = parser.parseEventCommand(newMessage);
            newTask = new Events(EventMessage[0], EventMessage[1]);
            taskList.addTask(newTask);
            System.out.println(boundary + "\nGot it. I've added this task: " + System.lineSeparator()
                    + "  " + taskList.getFullTask(taskNo));
            System.out.println("Now you have " + taskList.getTaskSize() + " task in the list\n" + boundary);
            taskNo++;
        }catch(IndexOutOfBoundsException e){
            System.out.println("Oops!!! Please enter a description and event date");
        }
    }

    /**
     * User can input command help to display a help list that shows the individual command and usage
     */
    public static void showHelp(){
        System.out.println("Format for Done: done <taskno>");
        System.out.println("Format for List of task: list");
        System.out.println("Format for EndConvo: bye");
        System.out.println("Format for ToDo: todo <task>");
        System.out.println("Format for Deadline: deadline <task> + /by <date>");
        System.out.println("Format for Event: event <task> + /at <datetime>");
        System.out.println("Format for Delete: delete <taskno>" + "\n" + boundary);
    }

}
