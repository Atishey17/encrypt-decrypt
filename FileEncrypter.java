import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Scanner;

public class FileEncryptor {

    private static SecretKeySpec getKey(String myKey) throws Exception {
        byte[] key = myKey.getBytes("UTF-8");
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16); // AES key length is 16 bytes
        return new SecretKeySpec(key, "AES");
    }

    public static void encrypt(String key, File inputFile, File outputFile) throws Exception {
        SecretKeySpec secretKey = getKey(key);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        FileInputStream fis = new FileInputStream(inputFile);
        FileOutputStream fos = new FileOutputStream(outputFile);
        CipherOutputStream cos = new CipherOutputStream(fos, cipher);

        byte[] buffer = new byte[1024];
        int read;
        while ((read = fis.read(buffer)) != -1) {
            cos.write(buffer, 0, read);
        }

        fis.close();
        cos.close();
    }

    public static void decrypt(String key, File inputFile, File outputFile) throws Exception {
        SecretKeySpec secretKey = getKey(key);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        FileInputStream fis = new FileInputStream(inputFile);
        CipherInputStream cis = new CipherInputStream(fis, cipher);
        FileOutputStream fos = new FileOutputStream(outputFile);

        byte[] buffer = new byte[1024];
        int read;
        while ((read = cis.read(buffer)) != -1) {
            fos.write(buffer, 0, read);
        }

        cis.close();
        fos.close();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== File Encryptor/Decryptor ===");
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();

        System.out.print("Encrypt or Decrypt (E/D): ");
        String mode = scanner.nextLine().trim().toUpperCase();

        System.out.print("Enter secret key: ");
        String secretKey = scanner.nextLine();

        try {
            File inputFile = new File(filePath);

            if (mode.equals("E")) {
                File encryptedFile = new File("encrypted_file.aes");
                encrypt(secretKey, inputFile, encryptedFile);
                System.out.println("✅ File encrypted successfully: " + encryptedFile.getAbsolutePath());
            } else if (mode.equals("D")) {
                File decryptedFile = new File("decrypted_file");
                decrypt(secretKey, inputFile, decryptedFile);
                System.out.println("✅ File decrypted successfully: " + decryptedFile.getAbsolutePath());
            } else {
                System.out.println("❌ Invalid option. Please choose E or D.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}