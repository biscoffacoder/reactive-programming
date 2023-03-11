package com.tutorial.sec01;

import reactor.core.publisher.Mono;

public class Lec02MonoIntro {
    public static void main(String[] args) {
        Mono<Integer> intMono = Mono.just(10);
        System.out.println(intMono);
        intMono.subscribe(subs -> System.out.println("Received subscription " + subs));
// the same logic of Streams is applied in Mono as well,
// here the publisher emits only when a subscription is assigned.

        intMono.subscribe(nextEve -> {
                    System.out.println(nextEve);
                }, errorEve -> {
                    System.out.println("ERROR OCCURRED" + errorEve.getMessage());
                },
                () -> System.out.println("Task has been completed"));
        // the subscription can take all the three overridden methods, onNext, onError, onComplete as arguments
        // ,we can directly provide the impls for them. The onComplete method does not return anything and is a Runnable.

        Mono<Integer> errMono = Mono.just("Hello World!").map(String::length).map(len -> len / 0);

        errMono.subscribe(nextEve -> System.out.println("onNext " + nextEve),
                errorEve -> System.out.println("ERROR OCCURRED " + errorEve.getMessage()),
                () -> System.out.println("Task has been completed"));
    }


}
