public class One {
    public static boolean hasUniqueChar(String str){
        if (str.length() > 128) return false;

        boolean[] present = new boolean[128];

        for (int i = 0; i < str.length(); i++){
            int val = str.charAt(i);
            if (present[val]) return false;
            present[val] = true;
        }

        return true;
    }

    public static boolean hasUniqueChar2(String str){
        int checker = 0;

        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a';

            if ((checker & (1 << val)) > 0) {
                return false;
            }

            checker |= (1 << val);
        }
        
        return true;
    }
}
