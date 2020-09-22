import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Events extends Task {

    protected String meetingTime;
    public LocalDate meetingDate;

    public Events(String description, String meetingTime) {
        super(description);
        try {
            meetingDate = LocalDate.parse(meetingTime.trim());
            this.meetingTime = meetingDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }catch(DateTimeParseException e){
            this.meetingTime = meetingTime;
        }
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }

    public String getMeetingTime() {
        return meetingTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + meetingTime + ")";
    }

    @Override
    public String[] getTaskDetails(){
        String[] taskTime = new String[1];
        taskTime[0] = this.getMeetingTime();

        return taskTime;
    }
}