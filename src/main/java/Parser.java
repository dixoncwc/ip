/**
 * This class is use to parse the userCommand into split command and return to the main program
 */
public class Parser {
    /**
     * This three method will split the command according to the regex and return into a new Command array
     * @param userCommand
     * @return
     */
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
