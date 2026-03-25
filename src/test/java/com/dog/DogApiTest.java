package com.dog;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class DogApiTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://dog.ceo/api";
    }

    @Test(description = "CT01 - GET /breeds/list/all retorna status 200 e lista de racas")
    public void deveRetornarListaDeTodasAsRacas() {
        Response response = RestAssured.get("/breeds/list/all");

        Assert.assertEquals(response.getStatusCode(), 200, "Status code deve ser 200");
        Assert.assertEquals(response.jsonPath().getString("status"), "success");

        Map<String, Object> message = response.jsonPath().getMap("message");
        Assert.assertNotNull(message, "Lista de racas nao deve ser nula");
        Assert.assertFalse(message.isEmpty(), "Lista de racas nao deve estar vazia");
    }

    @Test(description = "CT02 - GET /breed/{breed}/images retorna imagens de uma raca valida")
    public void deveRetornarImagensDaRaca() {
        Response response = RestAssured.get("/breed/hound/images");

        Assert.assertEquals(response.getStatusCode(), 200, "Status code deve ser 200");
        Assert.assertEquals(response.jsonPath().getString("status"), "success");

        java.util.List<String> imagens = response.jsonPath().getList("message");
        Assert.assertNotNull(imagens, "Lista de imagens nao deve ser nula");
        Assert.assertFalse(imagens.isEmpty(), "Lista de imagens nao deve estar vazia");
        Assert.assertTrue(imagens.get(0).endsWith(".jpg") || imagens.get(0).endsWith(".jpeg"),
                "URLs devem ser de imagens JPG");
    }

    @Test(description = "CT03 - GET /breeds/image/random retorna uma imagem aleatoria")
    public void deveRetornarImagemAleatoria() {
        Response response = RestAssured.get("/breeds/image/random");

        Assert.assertEquals(response.getStatusCode(), 200, "Status code deve ser 200");
        Assert.assertEquals(response.jsonPath().getString("status"), "success");

        String imageUrl = response.jsonPath().getString("message");
        Assert.assertNotNull(imageUrl, "URL da imagem nao deve ser nula");
        Assert.assertTrue(imageUrl.startsWith("https://"), "URL deve comecar com https://");
    }
}