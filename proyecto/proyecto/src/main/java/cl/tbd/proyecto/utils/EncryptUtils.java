package cl.tbd.proyecto.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {

    public static String hashPassword(String passwordPlainText) {
        try {
            // Crear instancia de MessageDigest para el algoritmo SHA-256
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            // Pasar la contraseña en texto plano al digest para generar el hash
            byte[] hashedBytes = messageDigest.digest(passwordPlainText.getBytes());

            // Convertir el arreglo de bytes en una cadena hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // En un caso de uso real, manejaría esta excepción de manera diferente,
            // pero para este ejemplo, simplemente relanzaremos la excepción como RuntimeException.
            throw new RuntimeException("Error al aplicar el algoritmo de hash a la contraseña", e);
        }
    }

    public static boolean comparePassword(String passDB, String pass){
        return passDB.equals( EncryptUtils.hashPassword(pass));
    };

    // Puedes agregar aquí otros métodos relacionados con la criptografía si es necesario
}
