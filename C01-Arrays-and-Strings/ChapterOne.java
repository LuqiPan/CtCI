import java.util.Arrays;
import java.io.*;

public class ChapterOne {
    //1.1 string has all unique characters
    public static boolean hasUniqueChar(String str){
        //determine the length upfront
        if (str.length() > 128) return false;

        //record the appearance of each char
        boolean[] present = new boolean[128];

        for (int i = 0; i < str.length(); i++){
            int val = str.charAt(i);
            if (present[val]) return false;
            present[val] = true;
        }

        return true;
    }

    //-------------------------------------------------------

    public static boolean hasUniqueChar2(String str){
        //bit manipulation
        int checker = 0;

        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a';

            //pay attention to $, << and |
            if ((checker & (1 << val)) > 0) {
                return false;
            }

            checker |= (1 << val);
        }
        
        return true;
    }

    //=======================================================

    //1.3 decide if one is a permutation of the other

    public static String sort(String original) {
        //sort the string
        //utilize Arrays.sort method
        char[] chars = original.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static boolean isPerm(String a, String b) {
        //decide if it's permutation
        //compare the length upfront
        if (a.length() != b.length()) return false;

        return sort(a).equals(sort(b));
    }

    //-------------------------------------------------------

    public static boolean isPerm2(String a, String b) {
        //count appearance of each char
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

    //=======================================================
    //1.4 Replace space with "%20"

    public static char[] replaceSpace(char[] chars) {
        int length = chars.length;
        //two pointer
        int i = 0;
        int pos = 0;

        //copy the char array over;
        String string = new String(chars);
        while (i < length) {
            if (string.charAt(pos) == ' '){
                chars[i++] = '%';
                chars[i++] = '2';
                chars[i++] = '0';
            }
            else {
                chars[i++] = string.charAt(pos);
            }
            pos++;
        }

        return chars;
    }

    //-------------------------------------------------------

    public static void replaceSpace2(char[] chars, int length) {
        //no need to get the count for spaces
        //int spaces = (newL - length) / 2;

        //get the array length
        int newL = chars.length;

        for (int i = length - 1; i >= 0; i--) {
            if (chars[i] == ' ') {
                chars[newL - 1] = '0';
                chars[newL - 2] = '2';
                chars[newL - 3] = '%';
                newL = newL - 3;
            } else {
                chars[--newL] = chars[i];
            }
        }
    }
                
    //=======================================================

    public static void main(String[] args) {
        char[] str = "Mr J S    ".toCharArray();
        ChapterOne.replaceSpace2(str, 6);
        System.out.println(new String(str));
    }

}
