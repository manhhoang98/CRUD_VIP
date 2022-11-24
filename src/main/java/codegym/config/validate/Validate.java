package codegym.config.validate;



import codegym.config.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import codegym.config.repository.IProductRepo;

import java.util.List;

@Component
public class Validate implements Validator {
    @Autowired
    IProductRepo iProductRepo;
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;
        List<Product> products = (List<Product>) iProductRepo.findAll();
        for (Product s:products) {
            if (product.getName().equals(s.getName())){
                errors.rejectValue("name", "", "Trung name");
                return;
            }
        }
    }
}