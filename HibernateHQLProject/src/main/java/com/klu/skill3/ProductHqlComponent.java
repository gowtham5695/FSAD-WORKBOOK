package com.klu.skill3;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ProductHqlComponent {

    /* Insert 5–8 Products */
    public void insertProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.save(new Product("Laptop", "Electronics", 55000, 5));
        session.save(new Product("Mouse", "Electronics", 500, 20));
        session.save(new Product("Keyboard", "Electronics", 1500, 10));
        session.save(new Product("Chair", "Furniture", 3000, 8));
        session.save(new Product("Table", "Furniture", 7000, 2));
        session.save(new Product("Notebook", "Stationery", 60, 50));
        session.save(new Product("Pen", "Stationery", 10, 100));

        tx.commit();
        session.close();
    }

    /* Sort by price ASC */
    public void priceAscending() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.createQuery(
                "FROM Product p ORDER BY p.price ASC",
                Product.class)
                .list().forEach(System.out::println);
        session.close();
    }

    /* Sort by price DESC */
    public void priceDescending() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.createQuery(
                "FROM Product p ORDER BY p.price DESC",
                Product.class)
                .list().forEach(System.out::println);
        session.close();
    }

    /* Sort by quantity (highest first) */
    public void quantityDescending() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.createQuery(
                "FROM Product p ORDER BY p.quantity DESC",
                Product.class)
                .list().forEach(System.out::println);
        session.close();
    }

    /* Pagination */
    public void pagination(int start, int limit) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Product> q = session.createQuery("FROM Product", Product.class);
        q.setFirstResult(start);
        q.setMaxResults(limit);
        q.list().forEach(System.out::println);
        session.close();
    }

    /* Aggregate operations */
    public void aggregateFunctions() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Long total = session.createQuery(
                "SELECT COUNT(p) FROM Product p",
                Long.class).uniqueResult();

        Long available = session.createQuery(
                "SELECT COUNT(p) FROM Product p WHERE p.quantity > 0",
                Long.class).uniqueResult();

        Object[] minMax = (Object[]) session.createQuery(
                "SELECT MIN(p.price), MAX(p.price) FROM Product p")
                .uniqueResult();

        System.out.println("Total Products: " + total);
        System.out.println("Available Products: " + available);
        System.out.println("Min Price: " + minMax[0]);
        System.out.println("Max Price: " + minMax[1]);

        session.close();
    }

    /* Group by description */
    public void groupByDescription() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> list = session.createQuery(
                "SELECT p.description, COUNT(p) FROM Product p GROUP BY p.description")
                .list();

        for (Object[] o : list) {
            System.out.println(o[0] + " -> " + o[1]);
        }
        session.close();
    }

    /* Filter by price range */
    public void priceRange(double min, double max) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.createQuery(
                "FROM Product p WHERE p.price BETWEEN :min AND :max",
                Product.class)
                .setParameter("min", min)
                .setParameter("max", max)
                .list().forEach(System.out::println);
        session.close();
    }

    /* LIKE queries */
    public void likeQueries() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.createQuery("FROM Product p WHERE p.name LIKE 'L%'", Product.class)
                .list().forEach(System.out::println);

        session.createQuery("FROM Product p WHERE p.name LIKE '%r'", Product.class)
                .list().forEach(System.out::println);

        session.createQuery("FROM Product p WHERE p.name LIKE '%top%'", Product.class)
                .list().forEach(System.out::println);

        session.createQuery("FROM Product p WHERE LENGTH(p.name) = 5", Product.class)
                .list().forEach(System.out::println);

        session.close();
    }
}
