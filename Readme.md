#  FileEncryptor

A Java program to **encrypt and decrypt any file** (text, image, audio, or binary) using **AES (Advanced Encryption Standard)** encryption.

---

##  Features

- AES encryption/decryption (256-bit key from SHA-256 hash)
- Works with any file type
- Safe and fast encryption using `javax.crypto`
- CLI interface to encrypt/decrypt with a secret key

---

## 📂 How to Use

### 🔧 Requirements

- Java installed (JDK 8+)
- Command-line terminal (Windows CMD / PowerShell / Linux / Mac Terminal)

---

##  Compile and Run

###  Step 1: Compile

```bash
javac FileEncryptor.java

How to run?
java FileEncryptor


example usage: 
=== File Encryptor/Decryptor ===
Enter file path: C:\Users\you\Desktop\myfile.txt
Encrypt or Decrypt (E/D): E
Enter secret key: atishey123
File encrypted successfully: encrypted_file.aes
