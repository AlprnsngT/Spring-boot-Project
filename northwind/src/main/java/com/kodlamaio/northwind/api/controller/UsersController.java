package com.kodlamaio.northwind.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.northwind.business.abstracts.UserService;
import com.kodlamaio.northwind.core.entities.User;
import com.kodlamaio.northwind.core.utilities.results.ErrorDataResult;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/users")
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/add") // * ? işaretine result yazabilirdik fakat sonucun bize duruma göre dönmesini
                                 // * sağlayalım - eğer result dersek hatalı giriş olsa dahil 200 döner ? ile
                                 // * unknown yapıyoruz
                                 // * @valid demek doğrulama yapılması gerektiğini söylüyoruz
    public ResponseEntity<?> add(@Valid @RequestBody User user) {
        return ResponseEntity.ok(this.userService.add(user));
    }

    // eğer user classında bir hata olursa : bunu
    // MethodArgumentNotValidException.class yazarak belirtiyoruz
    // handleValidationException methodunu çağır ve validation işleminde hataları
    // exceptions parametresine ata
    // gelen bütün hataları foreach ile dönüp map ile oluşturduğum listeye atıyorum
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
        Map<String, String> validationErrors = new HashMap<String, String>();
        for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        // şimdi bütün hataları fielderror ile mape aktardık
        // fakat fonksiyon ErrorDataResult döneceği için ona atmalıyız
        ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Doğrulama Hataları");
        return errors;
    }

}
