package com.tutorial.sec02;

import com.tutorial.utils.FakerDemo;
import com.tutorial.utils.ReactiveFunctionHelper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec03FluxRangeLog {
    public static void main(String[] args) {
        Flux.range(0,4).subscribe(ReactiveFunctionHelper.onNext());
        //Range works as a foreach loop, starting from 0 counts 4 values, 2,4: starts from 2 and counts
        Flux.range(2, 4).log().map(num -> FakerDemo.getName()).subscribe(ReactiveFunctionHelper.onNext());
    }
}
