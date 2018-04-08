package net.stock.server.service;


import net.stock.server.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Page<Product> findPaginated(int page, int size);
    Product getById(long personID);
    Product save(Product person);
    void remove(long personID);
}
