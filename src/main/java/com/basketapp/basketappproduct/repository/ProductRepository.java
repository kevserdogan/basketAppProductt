package com.basketapp.basketappproduct.repository;

import com.basketapp.basketappproduct.entity.Product;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CouchbaseRepository<Product, String> {
}

