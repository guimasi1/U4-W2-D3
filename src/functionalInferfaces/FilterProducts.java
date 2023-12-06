package functinoalInferfaces;

import entities.Product;

import java.util.List;

@FunctionalInterface
public interface FilterProducts {
    public List<Product> filterProduct(List<Product> products);
    
}
