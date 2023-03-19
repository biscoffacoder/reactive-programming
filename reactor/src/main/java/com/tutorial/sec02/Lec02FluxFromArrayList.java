package com.tutorial.sec02;

import com.tutorial.utils.FakerDemo;
import com.tutorial.utils.ReactiveFunctionHelper;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec02FluxFromArrayList {
    //To create a flux from an array or a List
    // fromStream, fromIterable, fromArray are all similar to just method as it is for data ready.

    public static void main(String[] args) {
        List<String> someList = List.of("A", FakerDemo.getName(), FakerDemo.getName());

        Flux<Object> someflux = Flux.fromStream(someList.stream());
        someflux.subscribe(ReactiveFunctionHelper.onNext(), ReactiveFunctionHelper.onError(), ReactiveFunctionHelper.onComplete());
        someflux.subscribe(ReactiveFunctionHelper.onNext(), ReactiveFunctionHelper.onError(), ReactiveFunctionHelper.onComplete());
        //since in J8, once the stream is consumed, it cannot be reused as the stream is closed, so we need to provide a anonymous function to invoke this on each method call so that a new stream is created in each call.
        Flux<Object> someflux2 = Flux.fromStream(()->someList.stream());
        someflux2.subscribe(ReactiveFunctionHelper.onNext(), ReactiveFunctionHelper.onError(), ReactiveFunctionHelper.onComplete());
        someflux2.subscribe(ReactiveFunctionHelper.onNext(), ReactiveFunctionHelper.onError(), ReactiveFunctionHelper.onComplete());



        Integer arr[] = {1, 2, 3};
        Flux<Integer> arrInt = Flux.fromArray(arr);
        arrInt.subscribe(ReactiveFunctionHelper.onNext(), ReactiveFunctionHelper.onError(), ReactiveFunctionHelper.onComplete());
        Flux<Object> someflux1 = Flux.fromIterable(someList);
        someflux1.subscribe(ReactiveFunctionHelper.onNext(), ReactiveFunctionHelper.onError(), ReactiveFunctionHelper.onComplete());


    }
}
