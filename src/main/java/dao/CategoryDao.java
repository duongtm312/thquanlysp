package dao;

import model.Category;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CategoryDao {
    @Autowired
    EntityManager entityManager;

    public List<Category> getList(){
        String queryStr = "SELECT c FROM Category c";
        TypedQuery<Category> query = entityManager.createQuery(queryStr, Category.class);
        return query.getResultList();
    }
    public Category findById(int id){
        String query = "SELECT c FROM Category c where c.id=:id";
        Category category = entityManager.createQuery(query,Category.class).setParameter("id",id).getSingleResult();
        return category;
    }
}
