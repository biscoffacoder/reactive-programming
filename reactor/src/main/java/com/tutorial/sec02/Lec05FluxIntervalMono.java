package com.tutorial.sec02;

import com.tutorial.utils.ReactiveFunctionHelper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec05FluxIntervalMono {

    public static void main(String[] args) {
        Mono<String> testData = Mono.just("TestData");
        doSomething(Flux.from(testData));
        Flux.interval(Duration.ofSeconds(1)).subscribe(ReactiveFunctionHelper.onNext(), ReactiveFunctionHelper.onError(), ReactiveFunctionHelper.onComplete());
        ReactiveFunctionHelper.sleeper(2);


    }

    public static void doSomething(Flux<String> value) {
        value.subscribe(ReactiveFunctionHelper.onNext());
    }

}
