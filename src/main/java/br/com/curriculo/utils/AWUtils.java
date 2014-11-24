package br.com.curriculo.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author Leonardo Cesar Borges
 * 
 */
public class AWUtils {

    private static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    /**
     * Criptograma uma String para MD5
     * 
     * @param valor
     * @return
     * @throws Exception
     */
    public static String MD5(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] md5hash = new byte[32];
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        md5hash = md.digest();
        return convertToHex(md5hash);
    }

    public static String findEmailUsuarioByIdUsuario(Long idUsuario) throws ClassNotFoundException, SQLException {
        String retorno = "";
        Connection con = null;
        PreparedStatement pstmt = null;
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(AWConstants.BD_URL, AWConstants.BD_USERNAME, AWConstants.BD_PASSWORD);

        pstmt = con.prepareStatement("SELECT U.EMAIL FROM USUARIO U WHERE U.ID_USUARIO = ?");
        pstmt.setLong(1, idUsuario);

        ResultSet rs = pstmt.executeQuery();
        if (rs != null) {
            while (rs.next()) {
                retorno = rs.getString("EMAIL");
            }
        }
        pstmt.close();
        con.close();

        return retorno;

    }

    public static String capitalizeString(String value) {
        String retorno = "";
        String[] split = value.toLowerCase().split(" ");
        for (String string : split) {
            retorno = retorno + " " + string.replaceFirst(string.substring(0, 1), string.substring(0, 1).toUpperCase());
        }
        return retorno.trim();
    }

    public static String getSHA(String string) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(string.getBytes("UTF-8"));
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            stringBuilder.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuilder.toString();
    }

   
}
