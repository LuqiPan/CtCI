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

    public static String compress(String str) {
        //Use StringBuffer
        StringBuffer sb = new StringBuffer();

        int i = 0;
        while (i < str.length()) {
            char c = str.charAt(i);
            Integer count = 0;

            while (i < str.length() && str.charAt(i) == c) {
                i++;
                count++;
            }
            sb.append(c + count.toString());
        }

        String result = sb.toString();

        //Determine the length at last
        return (result.length() < str.length()) ? result : str;
    }

    //=======================================================
    //1.6 Rotate N by N matrix

    public static void rotateMatrix(int[][] matrix, int n) {
        for (int i = 0; i < n / 2; i++) {
            int cells = n - 2 * i - 1;
            for (int j = i; j < (i + cells); j++) {
                int max = i + cells;
                int tmp = matrix[i][j];

                matrix[i][j] = matrix[j][max];
                matrix[j][max] = matrix[max][max - j + i];
                matrix[max][max - j + i] = matrix[max - j + i][i];
                matrix[max - j + i][i] = tmp;
            }
        }
    }

    public static void test1_6() {
        int[][] m = new int[][]{
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 10},
            {11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20},
            {21, 22, 23, 24, 25}
        };
        int n = 5;
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0 ; j < n ; j++) 
                System.out.format("%02d",m[i][j]);            
            System.out.println();
        }
        ChapterOne.rotateMatrix(m, n);
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0 ; j < n ; j++) 
                System.out.format("%02d",m[i][j]);            
            System.out.println();
        }
    }

    //=======================================================

    public static void main(String[] args) {
        test1_6();
    }

}
