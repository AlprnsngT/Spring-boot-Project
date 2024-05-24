package com.kodlamaio.northwind.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kodlamaio.northwind.entities.concretes.Product;
import com.kodlamaio.northwind.entities.dto.ProductWithCategoryDto;

public interface ProductDao extends JpaRepository<Product, Integer> { // entity ver id t�r�n� ver

    // fonksiyonun başında getBy ifadesi olduğu için
    // JpaRepository bunu çözümlüyor
    // ve devamında olanı bir Column olarak algılıyor
    Product getByProductName(String productName);
    // ! select * from products where product_name = productName

    // getBy ile başlayarak JpaRepository çözümlemesini yapıyor
    // sql için de iki Column bağlayan AND operatörü için de
    // and yazıyoruz
    Product getByProductNameAndCategory_CategoryId(String productName, int categoryId);

    // burada or kullandığımız için
    // birden fazla veri döneceğinden dolayı list yapıyoruz
    List<Product> getByProductNameOrCategory_CategoryId(String productName, int categoryId);

    // birden fazla cetegoryId gönderirsek
    // yani şu şu categorileri getir dediğimiz de
    List<Product> getByCategoryIn(List<Integer> categories);
    // ! select * from products where category_id in(1,2,3,4)

    List<Product> getByProductNameContains(String productName);

    List<Product> getByProductNameStartsWith(String productName);

    // JPQuery
    // Objeler üzerinden sql sorguları atmamızı sağlıyor
    // biz burada artık veritabanı üzerindeki columnlara
    // table name üzerinden değil
    // entites içerisinde oluşturmuş olduğumuz parametreler
    // üzerinden sorgular atacağız
    // :product_name burada fonksiyonun parametresi
    // product_name ise Product.java entitiesi içerisinde bizim
    // oluşturduğumuz variable
    @Query("From Product where productName=:productName and category.categoryId=:categoryId")
    List<Product> getByNameAndCategory_CategoryId(String productName, int categoryId);

    // burada tanımladığım query şu:
    // select * from Category c Inner Join Product p on c.categoryId=p.categoryId;
    // ? select p.product_id , p.product_name, c.category_name from products p inner
    // join categories c on p.category_id=c.category_id
    @Query("SELECT new com.kodlamaio.northwind.entities.dto.ProductWithCategoryDto"
            + "(p.id, p.productName, c.categoryName)"
            + "FROM Category c INNER JOIN c.products p") // iki tablonun joininden gelen verileri alacağız
    // !bu queryi biraz açıklayalım
    // DataBasedeki category tablosundan c parametresiyle tabloyu çektik
    // entites category de biz zaten join yapmıştık bu yüzden c.products diyerek
    // joini tekrar söylüyoruz
    // ve bu join nesnesine p diyoruz
    // select new diyerek de çekilen dataların nereye gideceğini söylüyoruz -
    // constructor çekiyoruz
    // p.id p.productName ve c.categoryName parametreleri DB isimlendirmeleri değil
    // yazdığımız classlardaki variable isimlendirmeleri
    List<ProductWithCategoryDto> getProductWithCategoryDetails();

}