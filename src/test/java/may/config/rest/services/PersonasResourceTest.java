package may.config.rest.services;

import io.quarkus.test.junit.QuarkusTest;
import may.config.idm.PersonaRepresentation;
import may.config.models.PersonaType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class PersonasResourceTest {

    @Test
    void addPersona() {
        PersonaRepresentation persona = new PersonaRepresentation();
        persona.setName("MyName");
        persona.setDescription("MyDescription");
        persona.setType(PersonaType.NATURAL);

        given()
            .body(persona)
            .header("Content-Type", "application/json")
        .when()
            .post("/personas")
        .then()
            .statusCode(201)
            .body("$", is(notNullValue()),
                "id", is(notNullValue()),
                    "name", is("MyName"),
                    "description", is("MyDescription"),
                    "type", is("NATURAL")
            );
    }

    @Test
    void getPersonaById() {
        // Test existing persona
        given()
            .pathParam("id", 99)
            .when()
                .get("/personas/{id}")
            .then()
            .statusCode(404);

        given()
            .pathParam("id", 100)
            .when()
                .get("/personas/{id}")
            .then()
                .statusCode(200)
                .body("$", is(notNullValue()),
                        "id", is(notNullValue()),
                        "name", is("Company1"),
                        "description", is("Description1"),
                        "type", is(nullValue())
                );
    }

    @Test
    void updatePersona() {
        PersonaRepresentation persona = new PersonaRepresentation();
        persona.setName("NewCompanyName");
        persona.setDescription("newDescription");
        persona.setType(PersonaType.JURIDICA);

        // Test non existing persona
        given()
            .body(persona)
            .header("Content-Type", "application/json")
            .pathParam("id", 99)
        .when()
            .put("/personas/{id}")
        .then()
        .statusCode(404);

        given()
            .body(persona)
            .header("Content-Type", "application/json")
            .pathParam("id", 200)
        .when()
            .put("/personas/{id}")
        .then()
            .statusCode(200)
            .body("$", is(notNullValue()),
                    "id", is(200),
                    "name", is("NewCompanyName"),
                    "description", is("newDescription"),
                    "type", is("JURIDICA")
            );
    }

    @Test
    void deletePersona() {
        // Test non existing persona
        given()
            .pathParam("id", 99)
        .when()
            .delete("/personas/{id}")
        .then()
            .statusCode(404);

        given()
            .pathParam("id", 300)
        .when()
            .delete("/personas/{id}")
        .then()
            .statusCode(204);
    }

    @Test
    void getPersonas() {
        given()
            .when()
                .get("/personas")
            .then()
            .statusCode(200)
            .body("$.size()", is(greaterThan(2)));

        given()
            .queryParam("filterText", "company1")
            .when()
            .get("/personas")
                .then()
                .statusCode(200)
                .body("$.size()", is(1));
    }
}