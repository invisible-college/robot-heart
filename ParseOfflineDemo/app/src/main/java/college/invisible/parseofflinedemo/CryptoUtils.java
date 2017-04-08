package college.invisible.parseofflinedemo;

import java.util.Random;

/**
 * Created by ppham on 4/8/17.
 */

public class CryptoUtils {

    public final static int LOCAL_ID_LENGTH = 10;

    public static String getRandomString(int length) {
        if (length == 0) {
            throw new Error("Zero-length randomString is useless.");
        }
        final String characters = new String(
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                "abcdefghijklmnopqrstuvwxyz" +
                "0123456789");
        StringBuilder objectId = new StringBuilder();
        final byte[] arrayBytes = new byte[length];
        Random random = new Random();

        random.nextBytes(arrayBytes);
        for (int i = 0; i < length; ++i) {
            int index = arrayBytes[i] % characters.length();
            index = (index < 0) ? index + characters.length() : index;
            objectId.append(characters.charAt(index));
        }
        return objectId.toString();
    }
}
