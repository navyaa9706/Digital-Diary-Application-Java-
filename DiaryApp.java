import java.awt.*;
import java.time.LocalDate;
import java.util.Scanner;
import javax.swing.*;

public class DiaryApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\t\t\t\t\t\t==== Welcome to Digital Diary ====\n");

        String username = null;
        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.println("\t\t\t\t\t\t1. Login");
            System.out.println("\t\t\t\t\t\t2. Register");
            System.out.println("\t\t\t\t\t\t3. Exit");
            System.out.print("\n\t\t\t\t\t\tChoose option: ");
            int choice = Integer.parseInt(sc.nextLine());

            if (choice == 1) {
                System.out.print("\n\t\t\t\t\t\tEnter username: ");
                username = sc.nextLine();
                System.out.print("\t\t\t\t\t\tEnter password: ");
                String password = sc.nextLine();
                loggedIn = UserAuth.login(username, password);
            } else if (choice == 2) {
                System.out.print("\t\t\t\t\t\tEnter username: ");
                username = sc.nextLine();
                System.out.print("\t\t\t\t\t\tEnter password: ");
                String password = sc.nextLine();
                loggedIn = UserAuth.register(username, password);
            } else if ( choice == 3) {
                System.out.println("\t\t\t\t\tKeep journaling, stay inspired, and see you next time!");
                System.out.println("\t\t\t\t\tExitting...");
                return;
            }
            else {
                System.out.println("\t\t\t\t\t\tInvalid option!");
            }
        }

        Diary diary = new Diary(username);
        System.out.println("\n\t\t\t\t\t\t\t** HELLO "+username.toUpperCase()+" **");
        while (true) {
            System.out.println("\n\t\t\t\t\t\t==== " + username.toUpperCase() + "'s DIGITAL DIARY ====");
            System.out.println("\n\t\t\t\tSo, what's on your mind today..?");
            System.out.println("\t\t\t\t\t1. Add Entry");
            System.out.println("\n\t\t\t\tDown the memory lane... :)");
            System.out.println("\t\t\t\t\t2. View Entries");
            System.out.println("\n\t\t\t\tRecalling some important day maybe?");
            System.out.println("\t\t\t\t\t3. Search by Keyword");
            System.out.println("\n\t\t\t\tIt's never a bad idea to get rid of sad stuff... go delete that entry :D");
            System.out.println("\t\t\t\t\t4. Delete Entry");
            System.out.println("\n\t\t\t\tOnce you feel better...");
            System.out.println("\t\t\t\t\t5. Logout");
            System.out.print("\n\t\t\t\t\tChoose an option (1-5): ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> openAddEntryWindow(diary);
                case 2 -> diary.viewEntries();
                case 3 -> {
                    System.out.print("\t\t\t\t\t\tEnter keyword: ");
                    diary.searchByKeyword(sc.nextLine());
                }
                case 4 -> {
                    System.out.print("\t\t\t\t\t\tEnter entry ID to delete: ");
                    diary.deleteEntry(Integer.parseInt(sc.nextLine()));
                }
                case 5 -> {
                    System.out.println("\t\t\t\t\t\tLogging out...");
                    main(args);
                    return;
                }
                default -> System.out.println("\t\t\t\t\t\tInvalid choice!");
            }
        }
    }

    public static void openAddEntryWindow(Diary diary) {
        JFrame frame = new JFrame("New Diary Entry");
        frame.setSize(1915, 1080);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setIconImage(new ImageIcon("diary_icon").getImage());

        frame.requestFocus();
        frame.toFront();
        frame.setAlwaysOnTop(true);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setFont(new Font("Constantia", Font.BOLD, 20));
        JTextField titleField = new JTextField();
        titleField.setPreferredSize(new Dimension(400,35));
        titlePanel.add(titleLabel);
        titlePanel.add(titleField);

        JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel contentLabel = new JLabel("Content:");
        contentLabel.setFont(new Font("Constantia", Font.PLAIN, 20));
        JTextArea contentArea = new JTextArea();
        contentArea.setPreferredSize(new Dimension(1450,850));
        JScrollPane scrollPane = new JScrollPane(contentArea);
        contentPanel.add(contentLabel);

        JPanel contentAreaPanel = new JPanel();
        contentAreaPanel.setLayout(new BoxLayout(contentAreaPanel, BoxLayout.Y_AXIS));
        contentAreaPanel.add(scrollPane);

        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save Entry");
        saveButton.setPreferredSize(new Dimension(80, 40));
        buttonPanel.add(saveButton);
        saveButton.setBackground(new Color(60, 179, 113));
        saveButton.setForeground(Color.WHITE);

        panel.add(titlePanel);
        panel.add(contentPanel);
        panel.add(contentAreaPanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(buttonPanel);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
        frame.toFront();

        saveButton.addActionListener(e -> {
            String title = titleField.getText().trim();
            String content = contentArea.getText().trim();
            if (!title.isEmpty() && !content.isEmpty()) {
                int id = (int)(Math.random() * 10000);
                DiaryEntry entry = new DiaryEntry(id, LocalDate.now(), title, content);
                diary.addEntry(entry);
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Please fill in both title and content.");
            }
        });
    }
}