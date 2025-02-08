package ru.otus.java.basic.homeworks.homework24.processors;

import com.google.gson.Gson;
import ru.otus.java.basic.homeworks.homework24.HttpRequest;
import ru.otus.java.basic.homeworks.homework24.application.ProductsService;
import ru.otus.java.basic.homeworks.homework24.application.ResponseBody;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class DeleteProductsProcessor implements RequestProcessor {
    private ProductsService productsService;
    private ResponseBody responseBody;
    public DeleteProductsProcessor(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        String jsonResult = null;
        Gson gson = new Gson();
        if (request.containsParameter("id")) {
            Long id = Long.parseLong(request.getParameter("id"));
            if(productsService.deleteProductById(id)) {
                responseBody = new ResponseBody("Продукт успешно удален");
            } else {
                responseBody = new ResponseBody("Продукт c данным id не найден");
            }
            jsonResult = gson.toJson(responseBody);
        } else {
            productsService.deleteAllProducts();
            responseBody = new ResponseBody("Все товары успешно удалены");
            jsonResult = gson.toJson(responseBody);
        }
        String response = "" +
                "HTTP/1.1 200 OK\r\n" +
                "Content-Type: application/json\r\n" +
                "\r\n" +
                jsonResult;
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
