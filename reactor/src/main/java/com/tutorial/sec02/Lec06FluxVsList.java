package com.tutorial.sec02;

import com.tutorial.utils.FakerDemo;
import com.tutorial.utils.ReactiveFunctionHelper;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lec06FluxVsList {
    //When we use a blocking method that is supposed to return some data after 5 seconds, using List will give you the entire data after 5 seconds,
    // but Flux will you give data as and when it recieves data, List will give entire data after 5 seconds. Flux will give each data after 1 second,
    // and 5th data will be given after 5th second.

    public static List<String> nameGenerator(int count) {
        List<String> names = new ArrayList<>();

        for (int ind = 0; ind < count; ind++) {
            names.add(getName());
        }
        return names;
    }

    public static Flux<String> nameGenFlux(int count) throws InterruptedException {
        return Flux.range(1, count).map(val -> getName());
    }

    public static String getName() {
        ReactiveFunctionHelper.sleeper(1);
        return FakerDemo.getName();
    }

    public static void main(String[] args) {
        long timeStart = System.currentTimeMillis();
        List<String> uppList = nameGenerator(5).stream().map(val -> val.toUpperCase()).collect(Collectors.toList());
        System.out.println(uppList.toString() + "Time taken " + (System.currentTimeMillis() - timeStart));

        long timeStart1 = System.currentTimeMillis();
        try {
            List<String> uppList1 = new ArrayList<>();
            nameGenFlux(5).subscribe((onNext) -> {
                System.out.println("Received : " + onNext);
                uppList1.add(onNext.toUpperCase());
            }, ReactiveFunctionHelper.onError(), ReactiveFunctionHelper.onComplete());
            System.out.println(uppList1.toString() + "Time taken by flux " + (System.currentTimeMillis() - timeStart1));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
