package com.terziev.konstantin.tranzactionapi;

import org.apache.meecrowave.Meecrowave;

public class Server {

    public static void main(String[] args) {
        final Meecrowave.Builder builder = new Meecrowave.Builder();
        builder.setHttpPort(8070);

        try (Meecrowave meecrowave = new Meecrowave(builder)) {
            meecrowave.bake().await();
        }
    }

}
