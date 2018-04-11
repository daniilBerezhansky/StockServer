package net.stock.server.service;


import net.stock.server.repository.ProductRepository;
import net.stock.server.event.ProductCreatedEvent;
import net.stock.server.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository personRepository;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public List<Product> getAll() {
        return personRepository.findAll();
    }

    public Page<Product> findPaginated(int page, int size){
        return  personRepository.findAll(new PageRequest(page,size));
    }
   public Page<Product> getAll(Pageable pageable){
       return  personRepository.findAll(pageable);
   }

    public Product getById(long personID) {
        return personRepository.findOne(personID);


    }

    @Transactional
    public Product save(Product product) {
        final ProductCreatedEvent event = new ProductCreatedEvent(product);
        long start = System.currentTimeMillis();
        sendEmail(event);
        //applicationEventPublisher.publishEvent(event);
        long end = System.currentTimeMillis();
        return personRepository.saveAndFlush(product);
    }

    public void remove(long personID) {
         personRepository.delete(personID);
    }

    private void sendEmail(ProductCreatedEvent event){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10,
                10, 1, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                    applicationEventPublisher.publishEvent(event);
            }
        });
    }
}

