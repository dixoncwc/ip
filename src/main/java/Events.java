public class Events extends Task {

    protected String MeetingTime;

    public Events(String description, String MeetingTime) {
        super(description);
        this.MeetingTime = MeetingTime;
    }

    public void setMeetingTime(String MeetingTime) {
        this.MeetingTime = MeetingTime;
    }

    public String getMeetingTime() {
        return MeetingTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + MeetingTime + ")";
    }
}