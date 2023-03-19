package com.tutorial.sec01;

import com.tutorial.utils.FakerDemo;
import com.tutorial.utils.ReactiveFunctionHelper;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class Lec05MonoFuture {
    public static void main(String[] args) {
        Mono.fromFuture(Lec05MonoFuture::getName).subscribe(ReactiveFunctionHelper.onNext());
        ReactiveFunctionHelper.sleeper(1);
    }

    private static CompletableFuture<String> getName() {
        System.out.println(":Calling getName");
        return CompletableFuture.supplyAsync(() -> FakerDemo.getName());
    }
}
