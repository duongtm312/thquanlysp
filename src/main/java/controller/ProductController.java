package controller;

import model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.CategoryService;
import service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private CategoryService categoryService = new CategoryService();
    private ProductService productService = new ProductService();

    @GetMapping("")
    public String index(Model model) {
        List<Product> products = productService.getAll();
        model.addAttribute("product", products);
        return "/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("category", categoryService.getAll());
        return "/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Product product, @RequestParam int cate) {
        product.setCategory(categoryService.findById(cate));
        productService.save(product);
        return "redirect:/product";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "/edit";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute Product product  ,@RequestParam int cate) {
        product.setCategory(categoryService.findById(cate));
        productService.update(product.getIdProduct(), product);
        return "redirect:/product";
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "/delete";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam int idProduct, RedirectAttributes redirect) {
        productService.remove(idProduct);
        redirect.addFlashAttribute("success", "Removed product successfully!");
        return "redirect:/product";
    }
    @PostMapping("/search")
    public String search(@RequestParam String search,Model model){
        List<Product> products =  productService.SearchByName(search);;
        model.addAttribute("product", products);
        return "/index";
    }
}
