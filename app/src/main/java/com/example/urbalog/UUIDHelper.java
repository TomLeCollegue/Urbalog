package com.example.urbalog;

import android.content.Context;
import android.content.SharedPreferences;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

/**
 * Helper class for create or get UUID of the device
 */

public final class UUIDHelper {
    private static String uniqueID = null;
    private static final String PREF_UNIQUE_ID = "PREF_UNIQUE_ID";

    /**
     * Return app UUID if app have UUID on the device
     * @param context App context
     * @return Current device UUID
     */
    public synchronized static String id(Context context) {
        if (uniqueID == null) {
            SharedPreferences sharedPrefs = context.getSharedPreferences(
                    PREF_UNIQUE_ID, Context.MODE_PRIVATE);
            uniqueID = sharedPrefs.getString(PREF_UNIQUE_ID, null);
            if (uniqueID == null) {
                uniqueID = UUID.randomUUID().toString();
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString(PREF_UNIQUE_ID, uniqueID);
                editor.apply();
            }
        }
        return uniqueID;
    }

    static final private String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    static final private Random rng = new SecureRandom();

    /**
     * Return random char
     * @return Random char in ALPHABET
     */
    public static char randomChar(){
        return ALPHABET.charAt(rng.nextInt(ALPHABET.length()));
    }

    /**
     * Used to generate UUID of given length
     * Can handle spacing with given char
     *
     * @param length Length of the UUID
     * @param spacing Number of char between spaces
     * @param spacerChar Spacer character
     * @return UUID key
     */
    public static String randomUUID(int length, int spacing, char spacerChar){
        StringBuilder sb = new StringBuilder();
        int spacer = 0;
        while(length > 0){
            if(spacing != 0) {
                if (spacer == spacing) {
                    sb.append(spacerChar);
                    spacer = 0;
                }
                spacer++;
            }
            length--;
            sb.append(randomChar());
        }
        return sb.toString();
    }
}
