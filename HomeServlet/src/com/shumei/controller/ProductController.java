package com.shumei.controller;

import com.shumei.dao.ProductDAO;
import com.shumei.dao.impl.ProductDAOImpl;
import com.shumei.pojo.Product;

import java.util.List;
import java.util.Scanner;

public class ProductController {
    ProductDAO productDAO = new ProductDAOImpl();


    Scanner input = new Scanner(System.in);
    public void showproductList() {
        List<Product> productList = productDAO.getproductList();
        System.out.println("--------------------------------------");
        System.out.println("编号\t\t名称\t\t单价\t\t库存\t\t其他");
        if (productList == null || productList.size() <= 0) {
            System.out.println("对不起，库存为空！");
        } else {
            for (int i = 0; i < productList.size(); i++) {
                System.out.println("名称:  " + productList.get(i).getPname());
                System.out.println("编号:  " + productList.get(i).getPid());
//                product product=productList.get(i);
//                System.out.println(product.toString());
            }
        }
    }

    public void delProduct() {
        System.out.print("请输入id：");
        int pid = input.nextInt();
        System.out.print("是否确认下架?(Y/N)");
        String slt = input.next();
        if ("y".equalsIgnoreCase(slt)) {
            if (productDAO.delproduct(pid))
                System.out.println("下架成功");
            else
                System.out.println("查无此品");
        } else {
            System.out.println("无需下架");
        }
    }

    public void addProduct() {
        System.out.print("请输入文创名称：");
        String pname = input.next();
        System.out.print("请输入文创单价：");
        Double price = input.nextDouble();
        System.out.print("请输入文创库存量：");
        int stock = input.nextInt();
        Product product = new Product(pname, price, stock, "");
        productDAO.addProduct(product);
        System.out.println("添加成功！");
    }

//    public void updateProduct() {
//        System.out.print("请输入待修改的id：");
//        int pid = input.nextInt();
//        System.out.print("是否确认修改?(Y/N)");
//        String slt = input.next();
//        if ("y".equalsIgnoreCase(slt)) {
//                System.out.print("请输入文创名称：");
//                String pname = input.next();
//                System.out.print("请输入文创单价：");
//                Double price = input.nextDouble();
//                System.out.print("请输入文创库存量：");
//                int stock = input.nextInt();
//                product product = new product(pname, price, stock, "");
//                productDAO.addProduct(product);
//                System.out.println("修改成功！");
//        } else {
//            System.out.println("无法修改");
//        }
//    }
public void updateProduct(){
    System.out.print("请输入待修改文创id：");
    int pid = input.nextInt() ;
    System.out.print("请输入待修改文创名称：");
    String pname = input.next() ;
    System.out.print("请输入文创单价：");
    Double price = input.nextDouble() ;
    System.out.print("请输入文创库存量：");
    int stock = input.nextInt() ;
    Product product=new Product( pid,pname , price,stock,"") ;
    //调用DAO的添加方法
    productDAO.updateProduct(product);
    System.out.println("修改成功！");

}
}
