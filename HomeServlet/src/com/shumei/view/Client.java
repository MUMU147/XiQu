package com.shumei.view;

import com.shumei.controller.ProductController;

import java.util.Scanner;

public class Client {
    public static  void main(String[] args){
        System.out.println("================ 欢迎使用文创产品管理系统 ================");
        System.out.println("1.查看文创库存列表");
        System.out.println("2.下架文创产品");
        System.out.println("3.增加文创产品");
        System.out.println("4.修改文创产品");
        System.out.println("5.其他操作");
        Scanner input=new Scanner(System.in);
        int slt=input.nextInt();
        ProductController pc=new ProductController();
        switch (slt){
            case 1:
                //把查询结果显示出来
                pc.showproductList();
                break;
            case 2:
                pc.delProduct();
                break;
            case 3:
                pc.addProduct();
                break;
            case 4:
                pc.updateProduct();
                break;
            default:System.out.println("其他操作");
        }
    }
}
