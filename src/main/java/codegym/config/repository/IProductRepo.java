package codegym.config.repository;


import codegym.config.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IProductRepo extends PagingAndSortingRepository<Product,Integer> {
}
