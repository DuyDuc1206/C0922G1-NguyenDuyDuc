package repository;

import model.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> findAll();
    Product findById(int id);
    Product findByName(String name);
    void add(Product product);
    void update(int id,Product product);
    void delete(int id);
    Product findByName();
}
