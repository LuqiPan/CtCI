import java.io.*;

public Class ChapterTwo() {
    //Chapter.2 Linked list

    //2.1 remove duplicates
    public static void removeDups(LinkedListNode n) {
        // Implementation with HashTable
        HashTable table = new HashTable();
        LinkedListNode prev = null;

        while (n != null) {
            if (table.containsKey(n.data)) {
                prev.next = n.next;
            }
            else {
                table.put(n.data, true);
                prev = n;
            }

            n = n.next;
        }
    }

    //-------------------------------------------------------

    public static void deleteDups2(LinkedListNode n) {
        // Another implementation with no data structure
        // Pay attention: null head!!
        if (n == null) return;

        LinkedListNode runner = null;

        while (n != null) {
            runner = n;

            while(runner.next != null) {
                if (runner.next.data == n.data) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            n = n.next;
        }
    }
}
