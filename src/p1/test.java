package p1;

//Objective write a class called product add 10 items sort according to price then
//price and type using both comparator and streams

import java.io.*;
import java.util.*;
import java.util.random.*;
import java.util.stream.Collectors;


public class test {

    public static void main(String[] args) {

        Random rand = new Random();

        long r1 = rand.nextLong(100000000);
        long r2 = rand.nextLong(100000000);
        long r3 = rand.nextLong(100000000);
        long r4 = rand.nextLong(100000000);
        long r5 = rand.nextLong(100000000);
        long r6 = rand.nextLong(100000000);
        long r7 = rand.nextLong(100000000);
        long r8 = rand.nextLong(100000000);
        long r9 = rand.nextLong(100000000);
        long r10 = rand.nextLong(100000000);


        List<product> listproducts = new ArrayList<product>();
        listproducts.add(new product(r1,"Cheese",5.20,"dairy product",300));
        listproducts.add(new product(r2,"Bread",2.50,"bakery",250));
        listproducts.add(new product(r3,"Chicken",7.20,"meat",230));
        listproducts.add(new product(r4,"Cake",15.20,"bakery",100));
        listproducts.add(new product(r5,"Milk",3.70,"dairy product",320));
        listproducts.add(new product(r6,"Whip Cream",10.30,"dairy product",200));
        listproducts.add(new product(r7,"Steak",25.20,"meat",100));
        listproducts.add(new product(r8,"Cream",7.10,"dairy product",140));
        listproducts.add(new product(r9,"Goat",5.20,"meat",400));
        listproducts.add(new product(r10,"Pizza",15.50,"bakery",300));

        //listproducts.forEach(c->c.print());
        listproducts.forEach(c->{System.out.println(c);});

        List<product> filteredproducts = new ArrayList<>();
        filteredproducts = listproducts.stream()
        .filter(p->p.getCategory().equals("meat"))
        //.sorted(Comparator.comparingDouble(product::getCost).reversed())
        .sorted(Comparator.comparing(product::getName))
        .collect(Collectors.toList());

        filteredproducts.forEach(c->c.print());








    
    
    
    
    
    
    
    
    
    }
    
}
