import java.io.*;

public class ChapterTwo {
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

    //=======================================================
    //2.2 find the k-th to last element

    public static LinkedListNode kthToLast(LinkedListNode n, int k) {
        // Runner implementation
        if(k <= 0 || n == null) return null;
        LinkedListNode runner = n;

        // Let runner be k-1 elements away from the head
        while(--k != 0) {
            if (runner == null) return null;
            runner = runner.next;
        }

        // Move n and runner simultaneously until runner hit the end
        while (runner != null) {
            n = n.next;
            runner = runner.next;
        }
        return n;
    }

    //=======================================================
    //2.3 delete a node given only access to that node

    public static boolean deleteNode(LinkedListNode n) {
        // Copy the data and pointer of the next node over
        // Clever move
        if (n == null || n.next == null) {
            return false;
        }
        n.data = n.next.data;
        n.next = n.next.next;
        return true;
    }
}
