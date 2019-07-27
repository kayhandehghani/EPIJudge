package epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class Heap {
	
	public static List<String> topK(int k, Iterator<String> iter) {
		int count = 0;
		
		if (k <= 0) {
			return new ArrayList<String>();
		}
		
		PriorityQueue<String> minHeap = new PriorityQueue<>((s1, s2) -> s1.length() - s2.length());
		
		while(iter.hasNext()) {
			String curr = iter.next();
			
			if (minHeap.peek() != null && minHeap.peek().length() >= curr.length() && minHeap.size() >= k) {
				continue; // improving performance
			}
			
			minHeap.add(curr);
			count++;
			if (minHeap.size() > k) {
				minHeap.poll();
			}
		}
		System.out.println("Number of insertion was: " + count);
		
		return new ArrayList<>(minHeap);
	}
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<>(Arrays.asList(new String[]{"a", "b", "", "aa", "bb", "abc", "cddd", "cddd", "", "aaaaa", "a"}));
		
		System.out.println(topK(-1, list.iterator()));
		System.out.println(topK(0, list.iterator()));
		System.out.println(topK(1, list.iterator()));
		System.out.println(topK(2, list.iterator()));
		System.out.println(topK(3, list.iterator()));
		System.out.println(topK(4, list.iterator()));
		System.out.println(topK(5, list.iterator()));
		System.out.println(topK(list.size(), list.iterator()));
		System.out.println(topK(list.size() + 1, list.iterator()));
		
		A a = new B();
		a = new B(10);
	}

}

class A {
	
	int a = 4;
	{
		System.out.println("Initi A, before: a is " + a);
		this.a = 5;
		System.out.println("Initi A, after: a is " + a);
	}
	
	A (int a) {
		this.a = a;
		System.out.println("Constructor A: a is " + a);
	}
	
	A () {
		System.out.println("Constructor A: a is " + a);
	}
	
}

class B extends A {
	
	{
		System.out.println("Initi B, before: a is " + a);
		this.a = 6;
		System.out.println("Initi B, after: a is " + a);
	}

	B (int a) {
		super(a);
		System.out.println("Constructor B: a is " + a);
	}
	
	B () {
		super();
		System.out.println("Constructor B: a is " + a);
	}
	
}
