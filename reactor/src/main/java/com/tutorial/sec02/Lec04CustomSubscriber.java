package com.tutorial.sec02;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicReference;

public class Lec04CustomSubscriber {
    // Until now, the subscribe method was automatically handling the 4 overriden methods of the Subscriber interface. When we manually implement the Subcriber, we need to provide the logic behind each of these methods and specially request for items to be emitted from the publisher. Once the subs is cancelled, requesting more items do not generate any output.

    public static void main(String[] args) {
        AtomicReference<Subscription> atmRef = new AtomicReference<>();
        Flux.range(1, 15).log().subscribeWith(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("Received subs");
                atmRef.set(subscription);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext "+integer);

            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError "+throwable);
            }

            @Override
            public void onComplete() {
                System.out.println("oncomplete ");
            }
        });
        System.out.println("requesting 3 item");
        atmRef.get().request(3);
        System.out.println("Cancelling request");
        atmRef.get().cancel();
        System.out.println("Requesting 1 item again");
        atmRef.get().request(1);


    }
}
