/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring;

import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Nate c0711874
 */
public class UpdateApplication {
  
      public static void main(String[] args) {
      Scanner keyboard = new Scanner(System.in);
            String newCode = "";
            
      ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
      ProductCodeController productCodeController = 
         (ProductCodeController)context.getBean("productCodeController");
      
      System.out.println("Please enter new product code");
      productCodeController.add(newCode);
      
      System.out.println("Database updated" );
   }
   
}
