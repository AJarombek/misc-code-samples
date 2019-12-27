import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * @author Andrew Jarombek
 * Date: 7/19/2016
 * Send an email posed as Taylor Swift to the email chain
 */
public class TSwiftSend {

    // takes a file and returns the bytes in the file in an array
    public static byte[] readBinaryFile(String file) {
        Path path = Paths.get(file);
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            System.out.println("ERROR: File Could not be Read.");
            System.exit(0);
        }
        return null;
    }

    public static void main(String[] args) {

        // Command line inputs
        String toEmailAddress = "abjaro13@stlawu.edu";
        String file = "";
        try {
            file = args[0];
        } catch (ArrayIndexOutOfBoundsException ex) {
            // if no file or email address was specified in the command line, exit program
            System.out.println("ERROR: Email Address or File not Specified.");
            System.exit(0);
        }

        // convert file into a byte array
        byte[] bytes = readBinaryFile(file);

        // Encode the File in Base64
        String base64encodedFile = Base64.getMimeEncoder().encodeToString(bytes);

        // Connect to the stlawu SMTP server
        try {

            String fromEmailAddress = "taylorswift@taylorswift.com";
            Socket socket = new Socket("smtp.stlawu.edu", 25);

            DataOutputStream op = new DataOutputStream(socket.getOutputStream());
            DataInputStream inp = new DataInputStream(socket.getInputStream());
            BufferedReader d = new BufferedReader(new InputStreamReader(inp));

            // SMTP and MIME commands
            op.writeBytes("HELO stlawu.edu\r\n");
            op.writeBytes("MAIL FROM: Taylor Swift <"+ fromEmailAddress +">\r\n");
            op.writeBytes("RCPT TO: <" + toEmailAddress + ">\r\n");
            op.writeBytes("DATA\r\n");
            op.writeBytes("Subject: Email\r\n");
            op.writeBytes("Date: 27 Feb 2016 2245 EDT\r\n");
            op.writeBytes("From: <"+fromEmailAddress+">\r\n");
            op.writeBytes("To: <"+toEmailAddress+">\r\n");
            op.writeBytes("MIME-Version: 1.0");
            op.writeBytes("Content-Type: multipart/mixed; boundary=\"boundary 1\"\r\n");
            op.writeBytes("\r\n");
            op.writeBytes("--boundary 1\r\n");
            op.writeBytes("Content-Type: text/plain\r\n");
            op.writeBytes("\r\n");
            op.writeBytes("Oh hey big boy");
            op.writeBytes("\r\n");
            op.writeBytes("--boundary 1\r\n");
            op.writeBytes("Content-Type: application/octet-stream\r\n");
            op.writeBytes("Content-Disposition: attachment; filename="+file+";\r\n");
            op.writeBytes("Content-Transfer-Encoding: Base64\r\n");
            op.writeBytes("\r\n");
            // The attached file encoded in Base64
            op.writeBytes(base64encodedFile+"\r\n");
            op.writeBytes("--boundary 1--\r\n");
            op.writeBytes("\r\n.\r\n");
            op.writeBytes("QUIT\r\n");

            op.flush();
            inp.close();
            op.close();
            socket.close();

        } catch (IOException ex) {
            System.out.println("ERROR: Something Went Wrong.");
            ex.printStackTrace(System.out);
            System.exit(0);
        }
    }
}
