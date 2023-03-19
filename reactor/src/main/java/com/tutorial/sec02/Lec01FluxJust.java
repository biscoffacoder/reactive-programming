package com.tutorial.sec02;

import com.tutorial.utils.FakerDemo;
import com.tutorial.utils.ReactiveFunctionHelper;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.io.Serializable;

public class Lec01FluxJust {
    public static void main(String[] args) {
        //A flux can emits 0,1...N items
        Flux<Integer> fluxInt = Flux.just(1);
        Flux<Integer> fluxIntMany = Flux.just(1, 2, 3, 4, 5);
        Flux<? extends Serializable> anyFlux = Flux.just(1, 2, 3, "A", FakerDemo.getName());
        fluxInt.subscribe(ReactiveFunctionHelper.onNext());
        fluxIntMany.subscribe(ReactiveFunctionHelper.onNext(), ReactiveFunctionHelper.onError(), ReactiveFunctionHelper.onComplete());

        anyFlux.subscribe(ReactiveFunctionHelper.onNext(), ReactiveFunctionHelper.onError(), ReactiveFunctionHelper.onComplete());

        //Multiple subscribers can subscribe to a publisher in both Mono and Flux.
        fluxIntMany.subscribeOn(Schedulers.boundedElastic()).subscribe(onNext -> {
            System.out.println("Sub1 " + onNext);
        }, ReactiveFunctionHelper.onError(), () -> System.out.println("completed"));
        fluxIntMany.subscribeOn(Schedulers.boundedElastic()).subscribe(onNext -> {
            System.out.println("Sub2 " + onNext);
        }, ReactiveFunctionHelper.onError(), () -> System.out.println("completed"));
    }
}
