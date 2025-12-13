import java.time.LocalDate;

public class DiaryEntry {
    private int id;
    private LocalDate date;
    private String title;
    private String content;

    public DiaryEntry(int id, LocalDate date, String title, String content) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.content = content;
    }

    public int getId() { return id; }
    public LocalDate getDate() { return date; }
    public String getTitle() { return title; }
    public String getContent() { return content; }

    // Convert entry to a line for file storage
    public String toFileString() {
        return id + "|" + date + "|" + title.replace("|", "/") + "|" + content.replace("|", "/");
    }

    // Create an entry from a line in the file
    public static DiaryEntry fromFileString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length < 4) return null;
        int id = Integer.parseInt(parts[0]);
        LocalDate date = LocalDate.parse(parts[1]);
        return new DiaryEntry(id, date, parts[2], parts[3]);
    }

    // Nicely display entry in console
    public void displayEntry() {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Date: " + date);
        System.out.println("Title: " + title);
        System.out.println("Content: " + content);
        System.out.println("ID: " + id);
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }
}
