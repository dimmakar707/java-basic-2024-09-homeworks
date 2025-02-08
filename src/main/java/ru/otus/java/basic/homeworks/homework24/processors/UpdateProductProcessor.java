package ru.otus.java.basic.homeworks.homework24.processors;

import com.google.gson.Gson;
import ru.otus.java.basic.homeworks.homework24.HttpRequest;
import ru.otus.java.basic.homeworks.homework24.application.Product;
import ru.otus.java.basic.homeworks.homework24.application.ProductsService;
import ru.otus.java.basic.homeworks.homework24.application.ResponseBody;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateProductProcessor implements RequestProcessor {

    private static final Logger LOGGER = LogManager.getLogger(HttpRequest.class);
    private ProductsService productsService;
    private ResponseBody responseBody;

    public UpdateProductProcessor(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        String jsonResult = null;
        Gson gson = new Gson();
        Product updatedProduct = gson.fromJson(request.getBody(), Product.class);
        if(productsService.updateProductById(updatedProduct)) {
            responseBody = new ResponseBody("Продукт успешно обновлён");
        } else {
            responseBody = new ResponseBody("Продукт с id не найден");
        }
        jsonResult = gson.toJson(responseBody);
        String response = "" +
                "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n" +
                "\r\n" +
                jsonResult;
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
