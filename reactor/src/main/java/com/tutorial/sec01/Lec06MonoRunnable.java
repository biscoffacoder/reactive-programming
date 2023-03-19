package com.tutorial.sec01;

import com.tutorial.utils.FakerDemo;
import com.tutorial.utils.ReactiveFunctionHelper;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Lec06MonoRunnable {
    public static void main(String[] args) {
        Mono.fromRunnable(getName()).subscribe(ReactiveFunctionHelper.onNext(), ReactiveFunctionHelper.onError(), () -> {
            System.out.println("Time consuming task completed");
            System.out.println("onComplete invoked");
        });

    }

    private static Runnable getName() {

        ReactiveFunctionHelper.sleeper(5);
        System.out.println("OperationCompleted");
        return () -> FakerDemo.getName();
    }
}
