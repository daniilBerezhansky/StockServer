package net.stock.server.service;

import net.stock.server.model.Category;

public interface CategoryService {
    Category getById(long categoryID);
    Category save(Category category);
    void remove(long categoryID);
}
