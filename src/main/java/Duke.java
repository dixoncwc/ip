import java.util.Scanner;

public class Duke {

    private static final String boundary = "____________________________________________________________";

    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        boolean endconvo = false;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\nHello from\n" + logo);

        System.out.println(boundary+"\nHello! I'm Duke\n" + "What can I do for you?\n" +boundary);
        System.out.println("Bye. Hope to see you again soon");


        while(!endconvo){
            String txt = scan.nextLine();

            if(txt.equals("bye")){
                endconvo = true;
            }

            else{
                endconvo = false;
            }

            System.out.println(boundary+"\n" + txt + "\n"  +boundary);

        }

        System.out.println(boundary+ "\nBye. Hope to see you again soon\n" +boundary);



    }
}
