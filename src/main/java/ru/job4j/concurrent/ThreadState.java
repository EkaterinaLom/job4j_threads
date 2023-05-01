package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        first.start();
        System.out.println(first.getState());
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        second.start();
        System.out.println(second.getState());
        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getName() + "," + first.getState());
            System.out.println(second.getName() + "," + second.getState());
        }
        System.out.println("Work completed");
    }
}
