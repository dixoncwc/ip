import java.util.Scanner;

public class Duke {

    private static final String boundary = "____________________________________________________________";

    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        Task[] item = new Task[100];
        int itemno = 0;
        int index = 1;
        boolean endconvo = false;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\nHello from\n" + logo);

        System.out.println(boundary+"\nHello! I'm Duke\n" + "What can I do for you?\n" +boundary);


        while(!endconvo){
            String txt = scan.nextLine();
            String[] donetxt = txt.split(" ", 3);

            if(txt.equals("bye")){
                endconvo = true;
            }

            else if(txt.equals("list")){
                System.out.println(boundary+"\n");

                for(int i =0; i < itemno; i++){
                    System.out.println(String.format("%d",index) + ".[ " + item[i].getStatusIcon() + "] " + item[i].description);
                    index++;
                }

                System.out.println(boundary);
                index = 1;
            }

            else if(donetxt[0].equals("done")){
                int taskno = Integer.parseInt(donetxt[1]);
                item[taskno - 1].markAsDone();
                System.out.println("Nice! I've marked this task as done: " + "\n");
                System.out.println("\t[" + item[taskno - 1].getStatusIcon() + "] " + item[taskno - 1 ].description);
                System.out.println(boundary);

            }

            else {
                item[itemno] = new Task(txt);
                System.out.println(boundary + "\nadded: " + item[itemno].description + "\n" + boundary);
                itemno++;
            }


        }

        System.out.println(boundary+ "\nBye. Hope to see you again soon\n" +boundary);



    }
}
