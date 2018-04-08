package net.stock.server.event;

import net.stock.server.model.Product;

public class ProductCreatedEvent {

    private  final Product product;
    public ProductCreatedEvent(Product product){

        this.product = product;
    }

    public Product getProduct(){
        return product;
    }
}
