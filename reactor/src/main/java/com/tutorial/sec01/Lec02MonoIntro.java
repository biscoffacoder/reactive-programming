package com.tutorial.sec01;

import com.github.javafaker.Name;
import com.tutorial.utils.FakerDemo;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec02MonoIntro {
    //Mono.just should onlyt be used when you have the data ready as this eagerly calls the method that needs to be executed without any subscriber to it.
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
        }, () -> System.out.println("Task has been completed"));
        // the subscription can take all the three overridden methods, onNext, onError, onComplete as arguments
        // ,we can directly provide the impls for them. The onComplete method does not return anything and is a Runnable.

        Mono<Integer> errMono = Mono.just("Hello World!").map(String::length).map(len -> len / 0);

        errMono.subscribe(nextEve -> System.out.println("onNext " + nextEve), errorEve -> System.out.println("ERROR OCCURRED " + errorEve.getMessage()), () -> System.out.println("Task has been completed"));

        Mono<List<Name>> listMono = Mono.just(FakerDemo.names(10));
        listMono.subscribe(nextEve -> {
            System.out.println("onNext");
            nextEve.stream().forEach(val -> System.out.println(val.firstName()));
        }, onError -> {
            System.out.println("onError" + onError.getMessage());
        }, () -> System.out.println("OnComplete"));

    }


}
