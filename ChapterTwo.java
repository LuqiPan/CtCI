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

    //-------------------------------------------------------
    //2.4 Partition linked list

    public static LinkedListNode partition(LinkedListNode n, int x) {
        if (n == null) return false;
        LinkedListNode less = null;
        LinkedListNode lessHead = null;
        LinkedListNode greater = null;
        LinkedListNode greaterHead = null;

        while (n != null) {
            // partition around x
            if (n.data < x) {
                // first element of less list
                if (less == null) {
                    less = n;
                    lessHead = n;
                } else {
                    // append the element
                    less.next = n;
                    less = n;
                }
            } else {
                // first element of greater list
                if (greater == null) {
                    greater = n;
                    greaterHead = n;
                } else {
                    // append the element
                    greater.next = n;
                    greater = n;
                }
            }
            n = n.next;
        }

        if (lessHead == null) {
            // No elements in less partition
            return greaterHead;
        }

        // append greater to less
        less.next = greaterHead;
        // make the last element's next null
        greater.next = null;
        // return the head
        return lessHead;
    }

    //=======================================================
    //2.5 Addition on linked list
    
    public static LinkedListNode addition( LinkedListNode n1, LinkedListNode n2) {
        //nothing to do if there's at most one list
        if (n1 == null || n2 == null) return null;

        //some variables
        int carry = 0;
        LinkedListNode head = null;
        LinkedListNode p = null;

        //loop until one list is empty
        while(n1 != null && n2 != null) {
            //calculate the result
            int sum = carry + n1.data + n2.data;
            carry = sum >= 10 ? 1 : 0;
            sum = sum % 10;

            if (p == null) {
                //The first element, record the head
                head = new LinkedListNode(sum, null);
                p = head;
            } else {
                //Move the pointer
                p.next = new LinkedListNode(sum, null);
                p = p.next;
            }
            n1 = n1.next;
            n2 = n2.next;
        }

        if (n1 == null && n2 == null) {
            //should deal with it FIRST
            if (carry == 1) {
                p.next = new LinkedListNode(1, null);
            }
            return head;
        }
        if (n1 == null) {
            //no elements in n1 but some in n2
            n2.data += carry;
            p.next = n2;
        } else {
            //no elements in n2 but some in n1
            n1.data += carry;
            p.next = n1;
        }

        return head;
    }
}
