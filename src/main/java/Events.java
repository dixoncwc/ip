public class Events extends Task {
    /**
     * This class handles all the event details and meetingTime
     */
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
        return "[E]" + super.toString() + " (at:" + MeetingTime + ")";
    }

    @Override
    public String[] getTaskDetails(){
        String[] taskTime = new String[1];
        taskTime[0] = this.getMeetingTime();

        return taskTime;
    }
}