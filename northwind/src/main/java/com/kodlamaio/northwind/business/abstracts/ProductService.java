package com.kodlamaio.northwind.business.abstracts;

import java.util.List;

import com.kodlamaio.northwind.core.utilities.results.DataResult;
import com.kodlamaio.northwind.core.utilities.results.Result;
import com.kodlamaio.northwind.entities.concretes.Product;
import com.kodlamaio.northwind.entities.dto.ProductWithCategoryDto;

public interface ProductService {
    // core katmanında mesajlar kısmını yaptık
    // artık cevap olarak service mesaj da döndürsün diyoruz
    // core üzerinden erişeceğiz
    // List<Product> getAll();
    DataResult<List<Product>> getAll();
    // bir sayfada kaç veri gözükek kısmı
    //örnek 7.sayfa 71. ile 79. id ile gibi
    DataResult<List<Product>> getAll(int pageNo , int pageSize);
    DataResult<List<Product>> getAllSorted();

    Result add(Product product);

    DataResult<Product> getByProductName(String productName);

    DataResult<Product> getByProductNameAndCategoryId(String productName, int categoryId);

    DataResult<List<Product>> getByProductNameOrCategoryId(String productName, int categoryId);

    DataResult<List<Product>> getByCategoryIdIn(List<Integer> categories);

    DataResult<List<Product>> getByProductNameContains(String productName);

    DataResult<List<Product>> getByProductNameStartsWith(String productName);

    DataResult<List<Product>> getByNameAndCategoryId(String productName, int categoryId);

    DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails();

}
