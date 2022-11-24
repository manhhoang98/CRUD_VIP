package codegym.config.controller;

import codegym.config.model.Category;
import codegym.config.model.Product;
import codegym.config.validate.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import codegym.config.repository.ICategoryRepo;
import codegym.config.repository.IProductRepo;


import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ICategoryRepo iCategoryRepo;

    @Autowired
    IProductRepo iProductRepo;

    @Autowired
    Validate validate;



    @GetMapping("/products")
    public ModelAndView show(@RequestParam (defaultValue = "0") int page){
        ModelAndView modelAndView = new ModelAndView("show");
        Page<Product> products = iProductRepo.findAll(PageRequest.of(page,2));
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @ModelAttribute(name = "categories")
    public List<Category> categories(){
        return (List<Category>) iCategoryRepo.findAll();
    }

    @GetMapping("/create")
    public ModelAndView showCreate(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@Validated  @ModelAttribute Product product,BindingResult bindingResult ,@RequestParam int idCategory, @RequestParam MultipartFile imgFile  ) throws IOException {
        validate.validate(product,bindingResult);
        if (bindingResult.hasFieldErrors()){
            return new ModelAndView("/create") ;
        }
        Category category = iCategoryRepo.findById(idCategory).get();
        category.setId(idCategory);
        product.setCategory(category);
        product.setStatus(true);
        String name = imgFile.getOriginalFilename();
        FileCopyUtils.copy(imgFile.getBytes(), new File("C:\\Users\\admin\\Desktop\\Module 4\\CRUD_CSDL\\src\\main\\webapp\\file\\" + name));
        product.setImg("/" + name);
        iProductRepo.save(product);
        return new ModelAndView("redirect:/products");
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam int id){
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        iProductRepo.deleteById(id);
        return modelAndView;
    }

    @GetMapping("/edit")
    public ModelAndView showEdit(@RequestParam int id){
        ModelAndView modelAndView = new ModelAndView("edit");
        Product products = iProductRepo.findById(id).get();
        modelAndView.addObject("product", products);
        modelAndView.addObject("categories", iCategoryRepo.findAll());
        return modelAndView;
    }

    @PostMapping ("/edit")
    public ModelAndView edit(@ModelAttribute Product product, @RequestParam int idCategory ,@RequestParam MultipartFile imgFile1)throws IOException{
        Category category = new Category();
        category.setId(idCategory);
        product.setCategory(category);
        product.setStatus(true);
        String name = imgFile1.getOriginalFilename();
        assert name != null;
        if (!name.equals("") ){
            FileCopyUtils.copy(imgFile1.getBytes(), new File("C:\\Users\\admin\\Desktop\\Module 4\\CRUD_CSDL\\src\\main\\webapp\\file\\" + name));
            product.setImg("/" + name);
        }
        iProductRepo.save(product);
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        return modelAndView;
    }

}

