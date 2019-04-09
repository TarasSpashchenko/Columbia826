package com.folijet.columbia;

import io.vertx.core.Vertx;

public class App {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(OrdersVerticle.class.getName());
    }

}
