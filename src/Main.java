import java.io.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        try {

            String path = "src/input.json";
            String info = new String(Files.readAllBytes(Paths.get(path)));


            JSONObject jsonObject = new JSONObject(info);
            String name = jsonObject.optString("first_name", "").toLowerCase().replaceAll("\\s+", "");
            String roll = jsonObject.optString("roll_number", "").toLowerCase().replaceAll("\\s+", "");


            String practice = name + roll;
            String create = generateMD5(practice);


            String output= "output.txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
                writer.write(create);
            }

            System.out.println("Done ");

        } catch (Exception e) {
            System.err.println(" Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //
    private static String generateMD5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] result = md.digest();
        StringBuilder newString = new StringBuilder();

            for(byte b:result){
                newString.append(String.format("%02x",b &0xff));
            }
        return newString.toString();
    }
}
