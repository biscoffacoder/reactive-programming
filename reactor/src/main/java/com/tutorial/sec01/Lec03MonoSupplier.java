package com.tutorial.sec01;

import com.tutorial.utils.FakerDemo;
import com.tutorial.utils.ReactiveFunctionHelper;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class Lec03MonoSupplier {
    // we should not use Mono.just as it calls the method without having a subscriber, we can use Supplier interface to create a Mono, which is called only when a subscriber subscribes to the supllier
    private static String getName() {
        System.out.println("Calling getName");
        return FakerDemo.getName();
    }

    ;

    public static void main(String[] args) {
        Mono<String> names = Mono.just(getName());
// when we run this class, even though 0 subscriber the method getName is being executed.
        Mono<String> name = Mono.fromSupplier(() -> getName());
        name.subscribe(ReactiveFunctionHelper.onNext(),
                ReactiveFunctionHelper.onError(),
                ReactiveFunctionHelper.onComplete());
        //when using supplier, the method is called only when there is a subscritpion.
        Supplier<String> stringSupplier = () -> getName();
        Callable<String> stringCallable = () -> getName();
        System.out.println("using fromCallable");
        Mono<String> fromCall = Mono.fromCallable(stringCallable);
        fromCall.subscribe(ReactiveFunctionHelper.onNext());
        System.out.println("using fromSupplier");
        Mono<String> fromSupp = Mono.fromSupplier(stringSupplier);
        fromSupp.subscribe(ReactiveFunctionHelper.onNext());
    }
}
