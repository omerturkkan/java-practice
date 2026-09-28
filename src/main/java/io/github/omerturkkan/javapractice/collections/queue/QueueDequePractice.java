package io.github.omerturkkan.javapractice.collections.queue;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueDequePractice {
    record Task(String name, int priority) {
    }

    public static void main(String[] args) {
        // Queue: first in, first out
        Queue<String> waitingLine = new ArrayDeque<>();
        waitingLine.offer("Ayse");
        waitingLine.offer("Omer");
        waitingLine.offer("Selin");

        System.out.println("queue      : " + waitingLine);
        System.out.printf("peek       : %s (not removed)%n", waitingLine.peek());
        System.out.printf("poll       : %s%n", waitingLine.poll());
        System.out.printf("poll       : %s%n", waitingLine.poll());
        System.out.println("remaining  : " + waitingLine);
        waitingLine.poll();
        System.out.printf("poll empty : %s (null, no exception)%n", waitingLine.poll());

        // Deque as a stack: last in, first out
        Deque<String> history = new ArrayDeque<>();
        history.push("page1");
        history.push("page2");
        history.push("page3");
        System.out.printf("%nstack      : %s%n", history);
        System.out.printf("pop        : %s -> back to %s%n", history.pop(), history.peek());

        // Deque works from both ends
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(3);
        deque.addLast(4);
        System.out.printf("%ndeque      : %s%n", deque);
        System.out.printf("removeFirst: %d, removeLast: %d -> %s%n",
                deque.removeFirst(), deque.removeLast(), deque);

        // PriorityQueue: ordered by priority, not by arrival
        PriorityQueue<Task> tasks = new PriorityQueue<>(Comparator.comparingInt(Task::priority));
        tasks.offer(new Task("write report", 3));
        tasks.offer(new Task("fix outage", 1));
        tasks.offer(new Task("reply email", 2));
        tasks.offer(new Task("deploy hotfix", 1));

        System.out.println("\nprocessing order:");
        while (!tasks.isEmpty()) {
            Task task = tasks.poll();
            System.out.printf("  p%d %s%n", task.priority(), task.name());
        }
    }
}
