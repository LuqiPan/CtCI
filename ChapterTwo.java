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

    //-------------------------------------------------------
    public static LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2, int carry) {
        if (l1 == null && l2 == null && carry == 0) {
            //We are done
            return null;
        }

        LinkedListNode result = new LinkedListNode(carry, null);

        int sum = carry;
        if (l1 != null) {
            sum += l1.data;
        }
        if (l2 != null) {
            sum += l2.data;
        }
        
        result.data = sum % 10;

        if (l1 != null || l2 != null) {
            LinkedListNode more = new LinkedListNode (
                l1 == null ? null : l1.next,
                l2 == null ? null : l2.next,
                sum >= 0 ? 1 : 0);
            result.next = more;
        }

        return result;
    }

    //-------------------------------------------------------
    public class PartialSum {
        public LinkdeListNode sum = null;
        public int carry = 0;
    }

    LinkedListNode addlists(LinkedListNode l1, LinkedListNode l2) {
        int len1 = getLength(l1);
        int len2 = getLength(l2);
        if (len1 < len2) {
            l1 = padList(l1, len2 - len1);
        } else {
            l2 = padList(l2, len1 - len2);
        }

        PartialSum sum = addListsHelper(l1, l2);

        if (sum.carry == 0) {
            return sum.sum;
        } else {
            LinkedListNode result = new LinkedListNode(sum.carry, sum.sum);
            return result;
        }
    }

    PartialSum addListsHelper(LinkedListNode l1, LinkedListNode l2) {
        if (l1 == null && l2 == null) {
            return new PartialSum;
        }

        PartialSum sum = addListsHelper(l1.next, l2.next);
        int val = sum.carry + l1.data + l2.data;
        LinkedListNode curr_result = new LinkedListNode(value % 10, sum.sum);
        sum.sum = curr_result;
        sum.carry = value / 10;
        return sum;
    }

    LinkedListNode padList(LinkedListNode l, int padding) {
        LinkedListNode head = l;
        for (int i = 0 ; i < padding ; i++) {
            LinkedListNode tmp = new LinkedListNode (0, head);
            head = tmp;
        }
        return head;
    }

    //=======================================================
    //2.6 beginning of the loop
    public static LinkedListNode findBeginning(LinkedListNode head) {
        LinkedListNode runner = head;
        LinkedListNode p = head;

        //Find the collision point
        while (runner != null && runner.next != null) {
            p = p.next;
            runner = runner.next.next;
            if (p == runner) {
                break;
            }
        }

        //No meeting point, thus no loop
        if (runner == null || runner.next == null) {
            return null;
        }

        //Move p to head, then move both at the same pace,
        //They'll meet at loop start
        //Magic of math!!
        p = head;
        while (p != runner) {
            p = p.next;
            runner = runner.next;
        }

        //p or runner are both the start of loop
        return p;
    }

    //=======================================================
    //2.7
    public static boolean isPalindrome(LinkedListNode head) {
        LinkedListNode slow = head;
        LinkedListNode fast = head;

        Stack<Integer> stack = new Stack<Integer>();

        //runner approach, find the mid point
        while (fast != null && fast.next != null) {
            stack.push(slow.data);
            slow = slow.next;
            fast = fast.next.next;
        }

        //odd length, this element will have no other element to match up
        if (fast != null) {
            stack.pop();
        }

        while (slow.next != null) {
            //if they don't match up, it's not a palindrome
            if (slow.next.data != stack.pop()) {
                return false;
            }
            slow = slow.next;
        }

        return true;
    }
}
