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
    //2.3 set of stacks

    public class SetOfStacks {
        ArrayList<Stack> stacks;
        int capacity;
        public SetOfStacks(int c) {
            capacity = c;
            stacks = new ArrayList<Stack>();
        }

        public Stack getLastStack() {
            if (!stacks.isEmpty()) {
                return stacks.get(stacks.size() - 1);
            } else {
                return null;
            }
        }

        public void push(int v) {
            Stack last = getLastStack();
            if (last != null && !last.isFull()) {
                last.push(v);
            } else {
                Stack stack = new Stack(capacity);
                stack.push(v);
                stacks.add(stack);
            }
        }

        public int pop() {
            Stack last = getLastStack();
            int v = last.pop();
            if (last.size == 0) {
                stacks.remove(stacks.size() - 1);
            }
            return v;
        }
    }

    //=======================================================

    public static void main(String args[]) {
        System.out.println();
    }
}
