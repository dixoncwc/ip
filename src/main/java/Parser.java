public class Parser {

    public String[] parseCommand(String userCommand){
        String[] newCommand = userCommand.split(" ", 2);
        return newCommand;
    }

    public String[] parseDeadlineCommand(String userCommand){
            String[] newDeadlineCommand = userCommand.split("/by", 2);
            return newDeadlineCommand;
    }

    public String[] parseEventCommand(String userCommand){
        String[] newEventCommand = userCommand.split("/at" ,2);
        return newEventCommand;
    }
}
