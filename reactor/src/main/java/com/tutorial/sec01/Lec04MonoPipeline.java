package com.tutorial.sec01;

import com.tutorial.utils.FakerDemo;
import com.tutorial.utils.ReactiveFunctionHelper;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class Lec04MonoPipeline {
    //pipeline: the pipeline is lazily initialized, only when there is a sub, the code of the pipeline is executed, otherwise the method is just called.
    // execution: once there is a sub, the code in pipeline is executed
    public static void main(String[] args) {
        getName();
        getName().subscribe(ReactiveFunctionHelper.onNext());
        getName();

        // if we notice the print statements, we can see that the thread is blocked until the 2nd method is completed,
        // the Reactor library is by default working on the main thread, which means the main thread is handling all execution,
        // ideally it should be async, to make it to async we can do subscribeOn(Schedulers.boundedElastic) and see this will
        // move that task to a separate thread. Sleeping the main thread to 5 bcz the async sends data after3 and main thread
        // will have already stopped.
        getName();
        getName().subscribeOn(Schedulers.boundedElastic()).subscribe(ReactiveFunctionHelper.onNext());
        getName();
        ReactiveFunctionHelper.sleeper(5);

    }

    private static Mono<String> getName() {
        System.out.println("Calling getName");
        return Mono.fromSupplier(() -> {
            ReactiveFunctionHelper.sleeper(3);
            return FakerDemo.getName();
        }).map(String::toUpperCase);
    }
}
