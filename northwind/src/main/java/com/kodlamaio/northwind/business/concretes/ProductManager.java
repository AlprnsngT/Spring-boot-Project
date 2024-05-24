package com.kodlamaio.northwind.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kodlamaio.northwind.business.abstracts.ProductService;
import com.kodlamaio.northwind.core.utilities.results.DataResult;
import com.kodlamaio.northwind.core.utilities.results.Result;
import com.kodlamaio.northwind.core.utilities.results.SuccessDataResult;
import com.kodlamaio.northwind.core.utilities.results.SuccessResult;
import com.kodlamaio.northwind.dataAccess.abstracts.ProductDao;
import com.kodlamaio.northwind.entities.concretes.Product;
import com.kodlamaio.northwind.entities.dto.ProductWithCategoryDto;

@Service
public class ProductManager implements ProductService {

    private ProductDao productDao;

    @Autowired
    public ProductManager(ProductDao productDao) {
        super();
        this.productDao = productDao;
    }

    @Override
    public DataResult<List<Product>> getAll() {
        return new SuccessDataResult<List<Product>>(this.productDao.findAll(),
                "Data Listelendi");
    }

    @Override
    public Result add(Product product) {
        this.productDao.save(product);
        return new SuccessResult("Ürün eklendi");
    }

    @Override
    public DataResult<Product> getByProductName(String productName) {
        return new SuccessDataResult<Product>(this.productDao.getByProductName(productName), "Data Listelendi");
    }

    @Override
    public DataResult<Product> getByProductNameAndCategoryId(String productName, int categoryId) {
        return new SuccessDataResult<Product>(this.productDao.getByProductNameAndCategory_CategoryId(productName, categoryId),
                "Data ve categoryId listelendi");
    }

    @Override
    public DataResult<List<Product>> getByProductNameOrCategoryId(String productName, int categoryId) {
        return new SuccessDataResult<List<Product>>
        (this.productDao.getByProductNameOrCategory_CategoryId(productName, categoryId),"Data veya categoryId listelendi");
    }

    @Override
    public DataResult<List<Product>> getByCategoryIdIn(List<Integer> categories) {
        return new SuccessDataResult<List<Product>>
        (this.productDao.getByCategoryIn(categories),"Data id ile listelendi");
    }

    @Override
    public DataResult<List<Product>> getByProductNameContains(String productName) {
        return new SuccessDataResult<List<Product>>
        (this.productDao.getByProductNameContains(productName),"Data ismi ile listelendi");
    }

    @Override
    public DataResult<List<Product>> getByProductNameStartsWith(String productName) {
       return new SuccessDataResult<List<Product>>
       (this.productDao.getByProductNameStartsWith(productName),"Ürün startsWith ile listelendi");
    }

    @Override
    public DataResult<List<Product>> getByNameAndCategoryId(String productName, int categoryId) {
        return new SuccessDataResult<List<Product>>
        (this.productDao.getByNameAndCategory_CategoryId(productName, categoryId),"Adıynan sanıynan Data gelirverdi");
    }

    @Override
    public DataResult<List<Product>> getAll(int pageNo, int pageSize) {
        
        PageRequest pageable = PageRequest.of(pageNo-1, pageSize);
        return new SuccessDataResult<List<Product>>
        (this.productDao.findAll(pageable).getContent());
    }

    @Override
    public DataResult<List<Product>> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.ASC,"productName");
        return new SuccessDataResult<List<Product>>
        (this.productDao.findAll(sort),"BAŞARILI");
    }

    @Override
    public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails() {
        return new SuccessDataResult<List<ProductWithCategoryDto>>
        (this.productDao.getProductWithCategoryDetails(),"Data Listelendi");
    }

}
