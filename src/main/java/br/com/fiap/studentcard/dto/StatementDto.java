package br.com.fiap.studentcard.dto;


public class StatementDto {
    
    private String store;
    private Double value;
    private String date;
    private String name;

    StatementDto(){}


    StatementDto(String store,  Double value, String date, String name){
        this.store = store;
        this.value = value;
        this.date = date;
        this.name = name;
    }


    public String getStore() {
        return store;
    }
    public void setStore(String store) {
        this.store = store;
    }
    public Double getValue() {
        return value;
    }
    public void setValue(Double value) {
        this.value = value;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    

}
