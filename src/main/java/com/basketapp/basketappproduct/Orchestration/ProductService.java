package com.basketapp.basketappproduct.Orchestration;

import com.basketapp.basketappproduct.entity.Product;
import com.basketapp.basketappproduct.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository)
    {
        this.productRepository=productRepository;
    }
    public Iterable<Product> getAllProducts()
    {
        return  productRepository.findAll();
    }
    public void saveProduct(Product product)
    {
        productRepository.save(product);
    }
    public Optional<Product> getProductBySerialNumber(String serialNumber)
    {
        return productRepository.findById(serialNumber);
    }

    public void updateProduct(String serialNumber)
    {
        Optional<Product> product = getProductBySerialNumber(serialNumber);
        if(product.isPresent())
        {
            productRepository.save(product.get());
        }
    }

    public void upDateProductByProductData(Product product)
    {
        productRepository.save(product);
    }

}
