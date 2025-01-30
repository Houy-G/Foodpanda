package model;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
@Data
@Getter
public class User {
    public static final Scanner scanner = new Scanner(System.in);
    private Long id;
    public UUID uuid =UUID.randomUUID();
    private String name;
    private String email;
    private Integer phone_number;
    public boolean user_login;
    public ArrayList<Product> cart;
    public User(){}
    public void login(){
        System.out.print("Name: ");
        name=scanner.next();
        System.out.print("Email: ");
        email=scanner.next();
        System.out.print("Phone number: ");
        phone_number=scanner.nextInt();
        setUser_login(true);
    }
    public void Detail(){
        System.out.println("Name: "+getName());
        System.out.println("Email: "+getEmail());
        System.out.println("Phone number: "+"+855 "+getPhone_number());
    }

    public Long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getPhone_number() {
        return phone_number;
    }

    public ArrayList<Product> getCart() {
        return cart;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone_number(Integer phone_number) {
        this.phone_number = phone_number;
    }

    public void setUser_login(boolean user_login) {
        this.user_login = user_login;
    }

    public void setCart(ArrayList<Product> cart) {
        this.cart = cart;
    }
}

