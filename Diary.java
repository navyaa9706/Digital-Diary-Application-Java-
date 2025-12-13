import java.io.*;
import java.util.*;

public class Diary {
    private ArrayList<DiaryEntry> entries;
    private final String FILE_NAME;
    
    public Diary(String username) {
        this.FILE_NAME = username + "_diary.txt"; 
        entries = new ArrayList<>();
        loadFromFile();
    }

    private void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                entries.add(DiaryEntry.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println("\t\t\t\tNo existing diary found. Starting fresh for this user!");
        }
    }

    private void saveToFile() {
        entries.removeIf(e -> e == null);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (DiaryEntry e : entries) {
                bw.write(e.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("\t\t\t\tError saving entries!");
        }
    }

    public void addEntry(DiaryEntry entry) {
        if (entry != null) {
            entries.add(entry);
        }
        saveToFile();
        System.out.println("\t\t\t\tEntry added successfully!");
    }

    public void viewEntries() {
        if (entries.isEmpty()) {
            System.out.println("\t\t\t\tNo diary entries yet!");
            return;
        }
        for (DiaryEntry e : entries) {
            if(e!=null){
                e.displayEntry();
            }
        }
    }

    public void searchByKeyword(String keyword) {
        boolean found = false;
        for (DiaryEntry e : entries) {
            if (e.getTitle().toLowerCase().contains(keyword.toLowerCase()) || e.getContent().toLowerCase().contains(keyword.toLowerCase())) {
                e.displayEntry();
                found = true;
            }
        }
        if (!found)
            System.out.println("\t\t\t\tNo matching entries found.");
    }

    public void deleteEntry(int id) {
        Iterator<DiaryEntry> it = entries.iterator();
        while (it.hasNext()) {
            DiaryEntry e = it.next();
            if (e.getId() == id) {
                it.remove();
                saveToFile();
                System.out.println("\t\t\t\t Entry deleted successfully!");
                return;
            }
        }
        System.out.println("\t\t\t\tEntry not found!");
    }

    public List<DiaryEntry> getEntries() {
    return new ArrayList<>(entries); // for ViewEntriesFrame
}

    public List<DiaryEntry> searchEntries(String keyword) {
        List<DiaryEntry> result = new ArrayList<>();
        for (DiaryEntry e : entries) {
            if (e.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                e.getContent().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(e);
            }
        }
        return result;
    }
}
