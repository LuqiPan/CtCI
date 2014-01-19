import java.util.Arrays;
import java.io.*;

public class Three {
    public static String sort(String original) {
        char[] chars = original.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static boolean isPerm(String a, String b) {
        if (a.length() != b.length()) return false;

        return sort(a).equals(sort(b));
    }

    public static boolean isPerm2(String a, String b) {
        if (a.length() != b.length()) return false;

        int[] counts = new int[128];

        for (int i = 0; i < a.length(); i++)
            counts[a.charAt(i)]++;

        for (int i = 0; i < b.length(); i++) {
            int val = b.charAt(i);
            if ( --counts[val] < 0 ) return false;
        }

        return true;
    }
}
