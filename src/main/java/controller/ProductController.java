package controller;

import dao.CategoryDao;
import dao.ProductDao;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.CategoryService;
import service.ProductService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@PropertySource("classpath:upload_file.properties")
@Controller
@RequestMapping("/product")
public class ProductController {
    @Value("${file-upload}")
    private String fileUpload;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private CategoryDao categoryDao;
    private CategoryService categoryService = new CategoryService();
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String index(Model model) {
        List<Product> products = productDao.getList();
        model.addAttribute("product", products);
        return "/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("category", categoryDao.getList());
        return "/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Product product, @RequestParam int cate, @RequestParam(value = "file", required = false) MultipartFile file) {
        if (file.getSize() != 0) {
            String nameImg = file.getOriginalFilename();
            try {
                FileCopyUtils.copy(file.getBytes(), new File(fileUpload + nameImg));
            } catch (IOException e) {
                e.printStackTrace();
            }
            product.setImg("/"+nameImg);
            product.setCategory(categoryDao.findById(cate));
            productDao.save(product);
        }
        return "redirect:/product";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("category", categoryDao.getList());
        model.addAttribute("product", productDao.findById(id));
        return "/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Product product, @RequestParam int cate, @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            File file1 = new File(fileUpload + product.getImg());
            file1.deleteOnExit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (file.getSize() != 0) {
            String nameImg = file.getOriginalFilename();
            try {
                FileCopyUtils.copy(file.getBytes(), new File(fileUpload + nameImg));
            } catch (IOException e) {
                e.printStackTrace();
            }

            product.setImg("/"+nameImg);
        }
        product.setCategory(categoryDao.findById(cate));
        productDao.edit(product);
        return "redirect:/product";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("product", productDao.findById(id));
        return "/delete";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam int idProduct, RedirectAttributes redirect, @RequestParam String img) {
        try {
            File file1 = new File(fileUpload + img);
            file1.deleteOnExit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        productDao.delete(idProduct);
        redirect.addFlashAttribute("success", "Removed product successfully!");
        return "redirect:/product";
    }

    @PostMapping("/search")
    public String search(@RequestParam(value = "search",required = false) String search, Model model) {
        List<Product> products = productDao.findByName(search);
        ;
        model.addAttribute("product", products);
        return "/index";
    }
}
