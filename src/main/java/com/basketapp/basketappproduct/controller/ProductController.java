package com.basketapp.basketappproduct.controller;

import com.basketapp.basketappproduct.Orchestration.Consumer;
import com.basketapp.basketappproduct.Orchestration.ProductService;
import com.basketapp.basketappproduct.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    @Autowired
    private final Consumer consumer;
    public ProductController(ProductService productService,Consumer consumer) {
        this.productService = productService;
        this.consumer=consumer;
    }

    @GetMapping
    public Iterable<Product> getAllProduct()
    {
        return  productService.getAllProducts();
    }
    @GetMapping("/{serialNumber}")
    public Product getProductById(@PathVariable  String serialNumber)
    {
        Optional<Product> product= productService.getProductBySerialNumber(serialNumber);
        if(product.isPresent()) {

        return  product.get();
        }
        return new Product();
    }
    @PostMapping
    public void  saveProduct(@RequestBody Product product)
    {
        productService.saveProduct(product);
    }
    @PutMapping("/{serialNumber}")
    public void updateProduct(@RequestBody Product product,@PathVariable  String serialNumber)
    {
        Product beforeProduct=getProductById(serialNumber);

        if(product.getPrice()<beforeProduct.getPrice())
        {
            consumer.publishProductPriceChangeEvent(product.getSerialNumber());
        }
        if(product.getTotalProduct()<3 && product.getTotalProduct()!=0 )
        {
            consumer.publishProductStockDecreasedEvent(product.getSerialNumber());
        }
        if(product.getTotalProduct()==0)
        {
            consumer.publishProductSoldOutEvent(product.getSerialNumber());
        }
        productService.upDateProductByProductData(product);
    }


}
