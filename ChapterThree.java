import java.util.*;

public class ChapterThree {
    // 3.2 stack with min value
    public class NodeWithMin{
        //associate min with each node
        public int value;
        public int min;
        public NodeWithMin(int v, int min) {
            value = v;
            this.min = min;
        }
    }

    public class StackWithMin extends Stack<NodeWithMin> {
        //first approach
        public void push(int value) {
            int newMin = Math.min(value, min());
            super.push(new NodeWithMin(value, newMin));
        }

        public int min() {
            if (this.isEmpty()) {
                return Integer.MAX_VALUE;
            } else {
                return peek().min;
            }
        }
    }

    //-------------------------------------------------------

    public class StackWithMin2 extends Stack<Integer> {
        //second approach
        //s2 stores all unique min value
        Stack<Integer> s2;
        public StackWithMin2() {
            s2 = new Stack<Integer>();
        }

        public Integer push(Integer value) {
            if (value < s2.peek()) {
                s2.push(value);
            }
            super.push(value);
            return value;
        }

        public Integer pop() {
            Integer value = super.pop();
            if (value == min()) {
                s2.pop();
            }
            return value;
        }

        public Integer min() {
            if (s2.isEmpty()) {
                return Integer.MAX_VALUE;
            } else {
                return s2.peek();
            }
        }
    }

    //=======================================================
    //3.3 set of stacks
    //see prob3_3.java

    //=======================================================
    //3.4 Hanoi

    static class Tower {
        private Stack<Integer> s = new Stack<Integer>();
        public void push(int i) {
            s.push(i);
        }

        public int pop() {
            return s.pop();
        }
    }

    static class Hanoi {
        private Tower[] towers;
        private int n;
        public Hanoi(int disks) {
            towers = new Tower[3];
            for(int i = 0 ; i < 3 ; i++) {
                towers[i] = new Tower();
            }
            n = disks;
            for(int i = disks-1 ; i >= 0 ; i--) {
                towers[0].push(i);
            }
        }

        public void moveTop(int origin, int dest) {
            int disk = towers[origin].pop();
            System.out.println("Move disk" + disk + " from T" + origin + " to T" + dest);
            towers[dest].push(disk);
        }

        public void moveDisks(int n, int origin, int buffer, int dest) {
            if (n == 1) {
                moveTop(origin, dest);
                return;
            }
            moveDisks(n-1, origin, dest, buffer);
            moveTop(origin, dest);
            moveDisks(n-1, buffer, origin, dest);
        }
    }

    public static void test3_4() {
        int disks = 5;
        Hanoi myHanoi = new Hanoi(disks);
        myHanoi.moveDisks(disks, 0, 1, 2);
    }

    //=======================================================

    public static void main(String args[]) {
        test3_4();
    }
}
