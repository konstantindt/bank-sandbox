package com.terziev.konstantin.statementapi;

import org.apache.meecrowave.Meecrowave;

public class Server {

    public static void main(String[] args) {
        final Meecrowave.Builder builder = new Meecrowave.Builder();
        builder.setHttpPort(8020);

        try (Meecrowave meecrowave = new Meecrowave(builder)) {
            meecrowave.bake().await();
        }
    }

}
