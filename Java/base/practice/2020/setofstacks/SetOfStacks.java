package setofstacks;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Cracking the Coding Interview: Question 3.3
 * Imagine a literal stack of plates.  If the stack gets too high, it might topple.  Therefore, in real life, we would
 * likely start a new stack when the previous stack exceeds some threshold.  Implement a data structure SetOfStacks
 * that mimics this.  SetOfStacks should be composed of several stacks and should create a new stack once the
 * previous one exceeds capacity.  SetOfStacks.push() and SetOfStacks.pop() should behave identically to a single
 * stack (that is, pop() should return the same values as it would if there were just a single stack).
 *
 * FOLLOW UP:
 * Implement a function popAt(int index) which performs a pop operation on a specific sub-stack.
 * @author Andrew Jarombek
 * @since 1/2/2020
 */

// Racing the 2 mile tonight!

public class SetOfStacks<T> {
    private List<Stack<T>> stacks;
    private int max;

    public SetOfStacks() {
        max = 5;
        stacks = new ArrayList<>();
        stacks.add(new Stack<>());
    }

    public void push(T item) {
        Stack<T> stack = stacks.get(stacks.size() - 1);

        if (stack.size() >= max) {
            Stack<T> newStack = new Stack<>();
            newStack.push(item);
            stacks.add(newStack);
        } else {
            stack.push(item);
        }
    }

    public T pop() {
        Stack<T> stack = stacks.get(stacks.size() - 1);
        T result = stack.pop();

        if (stack.empty()) {
            stacks.remove(stacks.size() - 1);
        }

        return result;
    }

    public T popAt(int index) {
        Stack<T> stack = stacks.get(index);
        T result = stack.pop();

        if (stack.empty()) {
            stacks.remove(index);
        }

        return result;
    }
}
