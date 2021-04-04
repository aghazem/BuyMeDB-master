import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// 'Buyme' site are checking if the e-mail exists in their system. Therefore this test
// needs a new and unused e-mail for each new register.
// This class will take care for this matter.
// The last used e-mail is saved in a file called 'curemail.txt'.
// If you get this file with the rest of the project files, you're good to go.
// If not, change the 'curName' from Stam100@gm...." to "Stam1000" (1000 instead of 100)
   public class GenerateEmail {
    private static String curEmail;
    private static File email;
    private static String curName="hazem123@gmail.com";
    //***********************************************
    // Check if the file exists. If not, create one.
    //***********************************************
    public static void chkfile() throws IOException {
        email = new File("curemail.txt");
        if (!email.exists()) {
            try (FileWriter writer = new FileWriter(email)) {
                writer.write(curName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //***********************************************
    // increasing the e-mail number
    //***********************************************
    public static String incEmail(String curEmail){

        String num = curEmail.substring(4,8);
        int tmpNum = Integer.valueOf(num);
        tmpNum++;
        curEmail = null;
        num = String.valueOf(tmpNum);
        String newEmail = "Stam";
        newEmail = newEmail.concat(num);
        newEmail = newEmail.concat("@gmail.com");
        return(newEmail);
    }
    //***********************************************
    // Update the file with the new e-mail in use
    //***********************************************
    public static void updFile(String newEmail) throws IOException {
        FileWriter fw = null;
        fw = new FileWriter(email);
        fw.write(newEmail);
        fw.close();
    }
    //******************************************************************************
    // Return the next available e-mail name, and save it (in a file) for next time
    //******************************************************************************
    public static String getEmail() throws IOException {
        chkfile();
        Scanner line = new Scanner(email);
        String curEmail = line.next();
        String newEmail = incEmail(curEmail);
        updFile(newEmail);
        return (newEmail);
    }
}
