package com.jaronet;

import java.awt.Font;
import java.awt.Graphics;

public class Part {
    String number;
    String name;
    String  price;
    String condition;
    String amount;
    String catalog;
    String shelf;
    String opis;
    String com;
    String spacja = " ";

    public Part(String number, String name, String price, String condition, String amount, String catalog, String shelf, String com)
    {
        this.number = number;
        this.name = name;
        this.price = price;
        this.condition = condition;
        this.amount = amount;
        this.catalog = catalog;
        this.shelf = shelf;
        this.opis = opis;
        this.com = com;
    }

    public boolean info(){
        System.out.println("Tytuł: " + this.getName() + " \n" + "Numer częśći: " +  this.getNumber() + " \n"
                + "Stan: " + this.getCondition() + " \n" + "Numer magazynowy: " + this.getCatalog() + " \n"
        + "Regał: " + this.getShelf() + " \n" + "Cena: " + this.getPrice() + " \n" + "ILość: " + this.getAmount()
        + "\n" + "Opis: " + this.getOpis());
        return false;
    }

    public String getOpis(){
        return opis;
    }
    public String getNumber()
    {
        return number;
    }

    public String com(){return com;}

    public String getSpacja(){return spacja;}


    public String getName()
    {
        return name;
    }

    public String getPrice()
    {
        return price;
    }

    public String getCondition() {return condition;}

    public String getAmount() {
        return amount;
    }

    public String getCatalog() {
        return catalog;
    }

    public String getShelf() {
        return shelf;
    }
}
