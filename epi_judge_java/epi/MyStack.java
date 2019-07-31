package epi;

import java.util.Stack;

public class MyStack {

	public static void main(String[] args) {
		Stack<Integer> s = new Stack<>();
		s.push(4);
		s.push(-1);
		s.push(4);
		s.push(-3);
		s.push(2);
		s.push(1);
		s.push(-1);
		s.push(10);
		s.push(0);
		
		System.out.println("Orignal stack: " + s);
		
		Stack<Integer> orderedStack = new Stack<>();
		
		while (!s.isEmpty()) {
			int curr = s.pop();
			
			while(!orderedStack.isEmpty() && orderedStack.peek() > curr) {
				s.push(orderedStack.pop());
			}
			
			orderedStack.push(curr);
		}

		System.out.println("Ordered stack: " + orderedStack);
	}

}
