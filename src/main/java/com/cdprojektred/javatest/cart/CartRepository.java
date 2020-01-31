package com.cdprojektred.javatest.cart;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CartRepository extends MongoRepository<Cart, String> {
}
