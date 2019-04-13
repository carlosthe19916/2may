package may.config.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import may.config.idm.CurrencyRepresentation;
import may.config.idm.DocumentRepresentation;
import may.config.idm.InvoiceRepresentation;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class DocumentsResourceTest {

    @Test
    void addDocument() throws JsonProcessingException {
        DocumentRepresentation invoice = buildInvoice();

        given()
            .body(invoice)
            .header("Content-Type", "application/json")
        .when()
            .post("/personas/1/documents")
        .then()
            .statusCode(201)
            .body("$", is(notNullValue()),
                    "id", is(notNullValue()),
                    "name", is("MyName"),
                    "description", is("MyDescription"),
                    "type", is("NATURAL")
            );
    }

    DocumentRepresentation buildInvoice() throws JsonProcessingException {
        InvoiceRepresentation invoice = new InvoiceRepresentation();
        invoice.fechaEmision = new Date();
        invoice.moneda = new CurrencyRepresentation("PEN");
        invoice.tipoComprobante = "Factura";

        ObjectMapper objectMapper = new ObjectMapper();
        String invoiceBody = objectMapper.writeValueAsString(invoice);


        DocumentRepresentation document = new DocumentRepresentation();
        document.setType("Invoice");
        document.setAssignedId("F001-1");
        document.setBody(invoiceBody);

        return document;
    }

    DocumentRepresentation buildCreditNote() throws JsonProcessingException {
        DocumentRepresentation document = new DocumentRepresentation();
        document.setType("CreditNote");
        document.setAssignedId("FC01-1");

        return document;
    }
}