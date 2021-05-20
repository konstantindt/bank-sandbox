package com.terziev.konstantin.accountapi;

import org.apache.meecrowave.Meecrowave;

public class Server {

    public static void main(String[] args) {
        final Meecrowave.Builder builder = new Meecrowave.Builder();
        builder.setHttpPort(8080);

        try (Meecrowave meecrowave = new Meecrowave(builder)) {
            meecrowave.bake().await();
        }
    }

}
