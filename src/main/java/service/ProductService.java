package service;

import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements CRUD<Product> {
    private static List<Product> products = new ArrayList<>();
    private static CategoryService categoryService = new CategoryService();

    static {
        products.add(new Product(1, "SamsungGalaxy S22", 100, "https://images.samsung.com/is/image/samsung/p6pim/vn/2202/gallery/vn-galaxy-s22-ultra-s908-sm-s908edrgxxv-530768590?$1300_1038_PNG$", categoryService.findById(1), true));
        products.add(new Product(2, "SamsungGalaxy S22", 100, "https://images.samsung.com/is/image/samsung/p6pim/vn/2202/gallery/vn-galaxy-s22-ultra-s908-sm-s908edrgxxv-530768590?$1300_1038_PNG$", categoryService.findById(1), true));
        products.add(new Product(3, "SamsungGalaxy S22", 100, "https://images.samsung.com/is/image/samsung/p6pim/vn/2202/gallery/vn-galaxy-s22-ultra-s908-sm-s908edrgxxv-530768590?$1300_1038_PNG$", categoryService.findById(1), false));
    }

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public Product findById(int id) {
        for (Product p : products
        ) {
            if (p.getIdProduct() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void update(int id, Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getIdProduct() == id) {
                products.set(i, product);
            }
        }
    }

    @Override
    public void remove(int id) {
        products.remove(findById(id));
    }
    public List<Product> SearchByName(String name){
        List<Product> productList = new ArrayList<>();
        for (Product p:products
             ) {
            if (p.getNameProduct().contains(name)){
                productList.add(p);
            }
        }
        return productList;
    }
}
