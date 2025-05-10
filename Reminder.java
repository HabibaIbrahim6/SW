import java.time.LocalDate;
import java.time.LocalTime;

public class Reminder {
    private String reminderId;
    private String title;
    private LocalDate date;
    private LocalTime time;

    public Reminder(String reminderId, String title, LocalDate date, LocalTime time) {
        this.reminderId = reminderId;
        this.title = title;
        this.date = date;
        this.time = time;
    }

    public String getReminderId() { return reminderId; }
    public String getTitle() { return title; }
    public LocalDate getDate() { return date; }
    public LocalTime getTime() { return time; }


    public void setReminderId(String reminderId) { this.reminderId = reminderId; }
    public void setTitle(String title) { this.title = title; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setTime(LocalTime time) { this.time = time; }


    public void createReminder() {
        System.out.println("Reminder created: " + title);
    }

    public void updateReminder(String title, LocalDate date, LocalTime time) {
        this.title = title;
        this.date = date;
        this.time = time;
        System.out.println("Reminder updated.");
    }

    public void deleteReminder() {
        System.out.println("Reminder deleted.");
    }
}

