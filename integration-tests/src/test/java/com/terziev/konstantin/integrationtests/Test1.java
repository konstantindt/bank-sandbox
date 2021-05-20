package com.terziev.konstantin.integrationtests;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

public class Test1 {

    @Test
    public void create_2_accounts_then_1_tranzaction() {
        final String accountsApiUrl = "http://localhost:8080/api/accounts";
        final String tranzactionsApiUrl = "http://localhost:8070/api/tranzactions";
        final String userId0 = UUID.randomUUID().toString();
        final String userId1 = UUID.randomUUID().toString();
        final String accountInitialBalance0 = "200.0000";
        final String accountInitialBalance1 = "300.5670";
        final String accountCurrentBalance0 = "170.0000";
        final String accountCurrentBalance1 = "330.5670";
        final String tranzactionValue0 = "30.0000";

        final Map<String, String> account0 = Map.of(
            "userId", userId0,
            "initialBalance", accountInitialBalance0
        );
        final Map<String, String> account1 = Map.of(
            "userId", userId1,
            "initialBalance", accountInitialBalance1
        );

        String accountId0 = given().contentType(ContentType.JSON)
            .body(account0)
            .when()
            .post(accountsApiUrl)
            .then()
            .statusCode(201)
            .body(
                "id", is(notNullValue()),
                "userId", is(userId0),
                "initialBalance", is(accountInitialBalance0),
                "createdDate", is(notNullValue())
            )
            .extract()
            .path("id");
        String accountId1 = given().contentType(ContentType.JSON)
            .body(account1)
            .when()
            .post(accountsApiUrl)
            .then()
            .statusCode(201)
            .body(
                "id", is(notNullValue()),
                "userId", is(userId1),
                "initialBalance", is(accountInitialBalance1),
                "createdDate", is(notNullValue())
            )
            .extract()
            .path("id");

        final Map<String, String> tranzaction0 = Map.of(
            "source", accountId0,
            "target", accountId1,
            "value", tranzactionValue0
        );

        given().contentType(ContentType.JSON)
            .body(tranzaction0)
            .when()
            .post(tranzactionsApiUrl)
            .then()
            .statusCode(201)
            .body(
                "id", is(notNullValue()),
                "source", is(accountId0),
                "target", is(accountId1),
                "value", is(tranzactionValue0),
                "createdDate", is(notNullValue())
            );

        given().when()
            .get(accountsApiUrl + "/" + accountId0)
            .then()
            .statusCode(200)
            .body(
                "id", is(accountId0),
                "userId", is(userId0),
                "initialBalance", is(accountInitialBalance0),
                "currentBalance", is(accountCurrentBalance0),
                "createdDate", is(notNullValue())
            );
        given().when()
            .get(accountsApiUrl + "/" + accountId1)
            .then()
            .statusCode(200)
            .body(
                "id", is(accountId1),
                "userId", is(userId1),
                "initialBalance", is(accountInitialBalance1),
                "currentBalance", is(accountCurrentBalance1),
                "createdDate", is(notNullValue())
            );

    }

}
