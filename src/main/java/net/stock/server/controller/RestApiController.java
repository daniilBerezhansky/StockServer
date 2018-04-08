package net.stock.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.stock.server.model.Product;
import net.stock.server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@RestController
public class RestApiController {


    @Autowired
    ProductService service;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    //@ResponseBody
    public List<Product> getAllPersons() {
        List<Product> all = service.getAll();
        return all;

    }
    @RequestMapping(
            value = "/products",
            params = { "page", "size" },
            method = RequestMethod.GET
    )
    public Page<Product> findPaginated(
            @RequestParam("page") int page, @RequestParam("size") int size) {

        Page<Product> resultPage = service.findPaginated(page, size);
        if (page > resultPage.getTotalPages()) {
            throw new HTTPException(228);
        }

        return resultPage;
    }
    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Product getPerson(@PathVariable("id") long personID){
        return service.getById(personID);

    }
    /*@RequestMapping(value = "/products", method = RequestMethod.POST)
    public Product savePerson(@RequestBody Product person){

        return service.save(person);

    }*/
    @RequestMapping(value = "/products", method = RequestMethod.POST,consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Product savePerson(HttpEntity<String> httpEntity){
        String json = httpEntity.getBody().trim().replaceAll("\uFFFD", "");
        String res ="";
        try {
             res = URLDecoder.decode(json, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        Product product = null;
        try {
            product = mapper.readValue(res,Product.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return service.save(product);
    }
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public void deletePerson(@PathVariable("id") long personID){
        service.remove(personID);

    }


}
