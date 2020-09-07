import java.util.Scanner;

public class Duke {

    private static final String boundary = "____________________________________________________________";

    public static void main(String[] args) throws DukeException {

        Scanner scan = new Scanner(System.in);
        Task[] item = new Task[100];
        int taskno = 0;
        int index = 1;
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
                    String CommandTxt = scan.nextLine();
                    String[] Message = CommandTxt.split(" ", 2);

                    if (CommandTxt.equals("bye")) {
                        EndConvo = true;
                    } else if (CommandTxt.equals("list")) {
                        System.out.println(boundary + "\n");

                        for (int i = 0; i < taskno; i++) {
                            System.out.println(String.format("%d", index) + "." + item[i]);
                            index++;
                        }
                        System.out.println(boundary);
                        index = 1;
                    } else if (Message[0].equals("done")) {
                        int donetaskno = Integer.parseInt(Message[1]);
                        item[donetaskno - 1].markAsDone();
                        System.out.println("Nice! I've marked this task as done: " + "\n");
                        System.out.println("\t[" + item[donetaskno - 1].getStatusIcon() + "] " + item[donetaskno - 1].description);
                        System.out.println(boundary);
                    } else if (Message[0].equals("todo")) {
                        try {
                            item[taskno] = new ToDo(Message[1]);
                            System.out.println(boundary + "\nGot it. I've added this task: " + System.lineSeparator()
                                    + "  " + item[taskno]);
                            System.out.println("Now you have " + (taskno + 1) + " task in the list\n" + boundary);
                            taskno++;
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Oops!!! The description of todo cannot be empty");
                        }
                    } else if (Message[0].equals("deadline")) {
                        try {
                            String[] DeadlineMessage = Message[1].split("/by ", 2);
                            item[taskno] = new Deadline(DeadlineMessage[0], DeadlineMessage[1]);
                            System.out.println(boundary + "\nGot it. I've added this task: " + System.lineSeparator()
                                    + "  " + item[taskno]);
                            System.out.println("Now you have " + (taskno + 1) + " task in the list\n" + boundary);
                            taskno++;
                        }catch(IndexOutOfBoundsException e){
                            System.out.println("Oops!!! Please enter a description and deadline");
                        }
                    } else if (Message[0].equals("event")) {
                        try {
                            String[] EventMessage = Message[1].split("/at", 2);
                            item[taskno] = new Events(EventMessage[0], EventMessage[1]);
                            System.out.println(boundary + "\nGot it. I've added this task: " + System.lineSeparator()
                                    + "  " + item[taskno]);
                            System.out.println("Now you have " + (taskno + 1) + " task in the list\n" + boundary);
                            taskno++;
                        }catch(IndexOutOfBoundsException e){
                            System.out.println("Oops!!! Please enter a description and event date");
                        }
                    } else if (Message[0].equals("help")) {
                        Help();
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

    public static void Help(){
        System.out.println("Format for Done: done <taskno>");
        System.out.println("Format for List of task: list");
        System.out.println("Format for EndConvo: bye");
        System.out.println("Format for ToDo: todo <task>");
        System.out.println("Format for Deadline: deadline <task> + /by <date>");
        System.out.println("Format for Event: event <task> + /at <datetime>\n" + boundary);
    }
}
