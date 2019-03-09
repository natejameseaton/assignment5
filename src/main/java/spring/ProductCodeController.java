/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nate c0711874
 */

public class ProductCodeController {

private List<ProductCode> products = new ArrayList<>();
    private ProductCode thisProductCode = new ProductCode();
    
    public ProductCodeController() {
        getProductFromDB();
    }
    
    private void getProductFromDB() {
        try {
            Connection conn = DBUtils.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Product_Code");
            while (rs.next()) {
                ProductCode p = new ProductCode();
                p.setProductCode(rs.getString("Prod_Code"));
                p.setDiscountCode(rs.getString("Discount_Code"));
                p.setDescription(rs.getString("Description"));
                products.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductCodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ProductCode getByCode(String prod_code) {
        for (ProductCode p : products) {
            if (p.getProductCode() == prod_code) {
                return p;
            }
        }
        return null;
    }
    
    public String editByID(String prod_code) {
        thisProductCode = getByCode(prod_code);
        return "editProductCode";
    }
      
        public String add() {
        try {
            Connection conn = DBUtils.getConnection();
            String sql;
            if (thisProductCode.getProductCode() == "") {
            sql = "UPDATE Prod_Code (Prod_Code, Discount_Code, Description VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, thisProductCode.getProductCode());
            pstmt.setString(2, thisProductCode.getDiscountCode());
            pstmt.setString(2, thisProductCode.getDescription());
            pstmt.executeUpdate();
        } else {
            sql = "UPDATE Prod_Code (Prod_Code, Discount_Code, Description VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, thisProductCode.getProductCode());
            pstmt.setString(2, thisProductCode.getDiscountCode());
            pstmt.setString(3, thisProductCode.getDescription());
            pstmt.executeUpdate();
        }
            getProductFromDB();
            thisProductCode = new ProductCode();
            return "index";
        } catch (SQLException ex) {
            Logger.getLogger(ProductCodeController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    void add(String newCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}