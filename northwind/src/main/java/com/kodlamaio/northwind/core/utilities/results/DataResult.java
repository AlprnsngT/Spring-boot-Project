package com.kodlamaio.northwind.core.utilities.results;

// her sınıfa ait bir şey yapacağım için generic yapı oluşturuyorum
//  T data ile yapıyorum bunu
public class DataResult<T> extends Result {

    private T data;
    public DataResult(T data, Boolean success, String message) {
        super(success, message);
        this.data = data;
    }
    public DataResult(T data,Boolean success){
        super(success);
        this.data = data;
    }
    public T getData(){
        return this.data;
    }
}
