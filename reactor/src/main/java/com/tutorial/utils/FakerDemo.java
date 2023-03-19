package com.tutorial.utils;


import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class FakerDemo {


    public static @NotNull List<Name> names(int value) {
        System.out.println("Generating names");

        List<Name> namesList = new ArrayList<>();
        for (int val = 0; val < value; val++) {
            namesList.add(Faker.instance().name());
        }
        Flux.fromStream(namesList.stream());
        return namesList;
    }

    public static String getName() {
        return Faker.instance().name().fullName();
    }
}
