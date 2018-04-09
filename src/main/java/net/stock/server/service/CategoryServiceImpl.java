package net.stock.server.service;

import net.stock.server.model.Category;
import net.stock.server.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category getById(long categoryID) {
        return categoryRepository.findOne(categoryID);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.saveAndFlush(category);
    }

    @Override
    public void remove(long categoryID) {
        categoryRepository.delete(categoryID);
    }
}
