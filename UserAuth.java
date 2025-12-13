import java.io.*;

public class UserAuth {
    private static final String USERS_FILE = "users.txt";

    public static boolean register(String username, String password) {
        if (userExists(username)) {
            System.out.println("\t\t\t\tUser already exists! Login to continue...\n");
            return false; // GUI will show "Username already exists"
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
            bw.write(username + ":" + password);
            bw.newLine();

            // Create diary file for the user
            File diaryFile = new File(username + "_diary.txt");
            diaryFile.createNewFile();

            return true;
        } catch (IOException e) {
            //e.printStackTrace();
            return false;
        }
    }

    public static boolean login(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            boolean found=false;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2 && parts[0].equals(username)) {
                    if(parts[1].equals(password)){
                        found=true;
                        return true;
                    }else{
                        System.out.println("\t\t\t\tIncorrect password or username!");
                        return false;
                    }
                }
            }
            if(!found){
                System.out.println("\t\t\t\tSpecified user does not exist! Register to create new user\n");
            }
        } catch (IOException e) {
            e.printStackTrace(); // Optional: log error
        }
        return false;
    }

    private static boolean userExists(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length > 0 && parts[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            // File might not exist yet, nvm
        }
        return false;
    }
}
