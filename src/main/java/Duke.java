import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static final String boundary = "____________________________________________________________";
    public static String commandTxt;
    public static String[] Message;
    public static Task newTask;
    public static int taskNo = 0;
    public static ArrayList<Task> itemList = new ArrayList<>();
    public static Task task;
    public static int index = 1;

    public static void main(String[] args) throws DukeException {

        Scanner scan = new Scanner(System.in);
        boolean EndConvo = false;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\nHello from\n" + logo);

        System.out.println(boundary+"\nHello! I'm Duke\n" + "What can I do for you?\n" +boundary);


        while(!EndConvo){
            if(!scan.hasNextLine()){
                return;
            } else{
                try {
                    commandTxt = scan.nextLine();
                    Message = commandTxt.split(" ", 2);

                    if (commandTxt.equals("bye")) {
                        EndConvo = true;
                    } else if (commandTxt.equals("list")) {
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
                        Help();
                    } else if(Message[0].equals("delete")){
                        deleteTask();
                    } else {
                        throw new DukeException();
                    }
                }catch(DukeException e){
                    System.out.println("Oops!!! Sorry but I don't know what that means");
                }
            }
        }

        System.out.println(boundary+ "\nBye. Hope to see you again soon\n" +boundary);
    }

    public static void displayList(){
        System.out.println(boundary);

        for (int i = 0; i < taskNo; i++) {
            System.out.println(String.format("%d", index) + "." + itemList.get(i));
            index++;
        }
        System.out.println(boundary);
        index = 1;
    }
    public static void done(){
        int doneTaskNo = Integer.parseInt(Message[1]);
        task = itemList.get(doneTaskNo - 1);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("\t[" + task);
        System.out.println(boundary);
    }
    public static void deleteTask() {
        int deleteTaskNo = Integer.parseInt(Message[1]);
        task = itemList.get(deleteTaskNo - 1);
        itemList.remove(task);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + (taskNo - 1) + " task in the list\n" + boundary);
        taskNo--;
    }
    public static void addTodo(){
        try {
            newTask = new ToDo(Message[1]);
            itemList.add(newTask);
            System.out.println(boundary + "\nGot it. I've added this task: " + System.lineSeparator()
                    + "  " + itemList.get(taskNo));
            System.out.println("Now you have " + (taskNo + 1) + " task in the list\n" + boundary);
            taskNo++;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops!!! The description of todo cannot be empty");
        }
    }
    public static void addDeadline(){
        try {
            String[] DeadlineMessage = Message[1].split("/by ", 2);
            newTask = new Deadline(DeadlineMessage[0], DeadlineMessage[1]);
            itemList.add(newTask);
            System.out.println(boundary + "\nGot it. I've added this task: " + System.lineSeparator()
                    + "  " + itemList.get(taskNo));
            System.out.println("Now you have " + (taskNo + 1) + " task in the list\n" + boundary);
            taskNo++;
        }catch(IndexOutOfBoundsException e){
            System.out.println("Oops!!! Please enter a description and deadline");
        }
    }

    public static void addEvent(){
        try {
            String[] EventMessage = Message[1].split("/at", 2);
            newTask = new Events(EventMessage[0], EventMessage[1]);
            itemList.add(newTask);
            System.out.println(boundary + "\nGot it. I've added this task: " + System.lineSeparator()
                    + "  " + itemList.get(taskNo));
            System.out.println("Now you have " + (taskNo + 1) + " task in the list\n" + boundary);
            taskNo++;
        }catch(IndexOutOfBoundsException e){
            System.out.println("Oops!!! Please enter a description and event date");
        }
    }
    public static void Help(){
        System.out.println("Format for Done: done <taskno>");
        System.out.println("Format for List of task: list");
        System.out.println("Format for EndConvo: bye");
        System.out.println("Format for ToDo: todo <task>");
        System.out.println("Format for Deadline: deadline <task> + /by <date>");
        System.out.println("Format for Event: event <task> + /at <datetime>\n" + boundary);
        System.out.println("Format for Delete: delete <taskno>");
    }
}
