package com.tutorial.sec01;

import java.util.stream.Stream;

public class Lec01Stream {
    public static void main(String[] args) {
        Stream<Integer> intStream = Stream.of(10).map(ev -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            return ev * 20;
        });

        System.out.println(intStream);
        //the stream is lazily initialized, even though the thread is being slept for 2 seconds, only when the terminal operation is called, the stream gets to work.
        
        intStream.forEach(System.out::println);
    }
}
