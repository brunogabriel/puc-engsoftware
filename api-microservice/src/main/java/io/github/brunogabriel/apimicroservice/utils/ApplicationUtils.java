package io.github.brunogabriel.apimicroservice.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by brunogabriel on 2019-06-19.
 */
public class ApplicationUtils {
    public static void simulateTimeout() {
        int time = ThreadLocalRandom.current().nextInt(0, 5);
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) { }
    }
}
