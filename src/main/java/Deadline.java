import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    /**
     * This class handles all the deadline details and deadlineDate
     */
    protected String by;
    public LocalDate deadlineDate;

    public Deadline(String description, String by) {
        super(description);
        try {
            deadlineDate = LocalDate.parse(by.trim());
            this.by = deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }catch(DateTimeParseException e){
            this.by = by;
        }
    }

    public void setBy(String by){
        this.by = by;
    }

    public String getBy(){
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String[] getTaskDetails(){
        String[] taskTime = new String[1];
        taskTime[0] = this.getBy();

        return taskTime;
    }
}
