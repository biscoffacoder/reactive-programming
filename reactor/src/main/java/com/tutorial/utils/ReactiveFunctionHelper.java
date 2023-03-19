package com.tutorial.utils;

import java.util.function.Consumer;

public class ReactiveFunctionHelper {
//    @Nullable Consumer<? super T> consumer, @Nullable Consumer<? super Throwable> errorConsumer, @Nullable Runnable completeConsumer

    public static Consumer<Object> onNext() {
        return obj -> {
            System.out.println("Received " + obj);
        };
    }

    public static Consumer<Throwable> onError() {
        return obj -> {
            System.out.println("Error " + obj.getMessage());
        };
    }

    public static Runnable onComplete() {
        return () -> System.out.println("Completed ");
    }

    public static void sleeper(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
