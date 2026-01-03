package com.klu.skill3;

public class HqlMainApp {

    public static void main(String[] args) {

        ProductHqlComponent comp = new ProductHqlComponent();

        comp.insertProducts();
        comp.priceAscending();
        comp.priceDescending();
        comp.quantityDescending();
        comp.pagination(0, 3);
        comp.pagination(3, 3);
        comp.aggregateFunctions();
        comp.groupByDescription();
        comp.priceRange(1000, 10000);
        comp.likeQueries();
    }
}
