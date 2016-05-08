package com.dreamycity.ali.dao.impl;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.sql.Statement;

import com.dreamycity.ali.dao.ItemDAO;
import com.dreamycity.ali.model.Item;
import com.dreamycity.ali.model.AliOrder;

public class JdbcItemDAO implements ItemDAO {
    private static final Logger logger = LogManager.getLogger(JdbcItemDAO.class);

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int select(Item item) {
        int id = -1;
        String sql = "SELECT item_id FROM items "+
            "WHERE name = ?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,item.getName());
            logger.info(ps);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("item_id");
            }
            
            ps.close();

        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            if(conn != null) {
                try{
                    conn.close();
                }catch(SQLException e) {}
            }
            return id;
        }
    }

    public void insert(AliOrder aliOrder) {

        Connection conn = null;
        //Connection conn2 = null;
        PreparedStatement ps;
        String sql;
        try {
            conn = dataSource.getConnection();
            for(Item item : (ArrayList<Item>)aliOrder.getItems()) {
                if(select(item) == -1) {
                    sql = "INSERT INTO items " +
                        "(name,short_name,category," +
                        "image,suggest_price,cost," +
                        "quantity,unit,supplier," +
                        "link,reoder_level,is_serialized," +
                        "deleted,comment)" +
                        " VALUES (?, ?, ?, ?, ?, ?, ?," +
                            "?, ?, ?, ?, ?, ?, ?)";
                    //conn2 = dataSource.getConnection();
                    ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

                    ps.setString(1,item.getName());
                    ps.setString(2,item.getShort_name());
                    ps.setString(3,item.getCategory());
                    ps.setString(4,item.getImage());
                    ps.setString(5,item.getSuggest_price());
                    ps.setString(6,item.getCost());    
                    ps.setString(7,item.getQuantity());
                    ps.setString(8,item.getUnit());
                    ps.setString(9,item.getSupplier());
                    ps.setString(10,item.getLink());
                    ps.setString(11,item.getReorder_level());
                    ps.setInt(12,item.getIs_serialized());
                    ps.setInt(13,item.getDeleted());
                    ps.setString(14,item.getComment());

                    logger.info(ps);
                    ps.executeUpdate();
                    ResultSet rs = ps.getGeneratedKeys();
                    if(rs.next())
                    {
                        item.setItem_id(rs.getInt(1));
                    }
                    ps.close();

                    //conn2.close();

                }

                    sql = "INSERT INTO inventory " +
                        "(item_id,trans_date,trans_user,trans_inventory)" +
                        " VALUES (?, ?, 0, ?)";

                    ps = conn.prepareStatement(sql);
                    ps.setInt(1,item.getItem_id());
                    ps.setString(2,aliOrder.getCreatetime());
                    ps.setString(3,item.getQuantity());

                    ps.executeUpdate();

                    ps.close();

                    sql = "INSERT INTO purchase_order_items " +
                        "(item_id,order_id,name,image,cost,quantity,unit,supplier,createtime,i_link)" +
                        " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                    ps = conn.prepareStatement(sql);
                    ps.setInt(1,item.getItem_id());
                    ps.setString(2,aliOrder.getOrder_id());
                    ps.setString(3,item.getName());
                    ps.setString(4,item.getImage());
                    ps.setString(5,item.getCost());
                    ps.setString(6,item.getQuantity());
                    ps.setString(7,item.getUnit());
                    ps.setString(8,item.getSupplier());
                    ps.setString(9,aliOrder.getCreatetime());
                    ps.setString(10,item.getLink());

                    ps.executeUpdate();

                    ps.close();

            }

        } catch (SQLException e) {
            logger.error(e.getMessage()+"line:"+e.getStackTrace()[2].getLineNumber());
            //e.printStackTrace();

        } finally {
            if(conn != null) {
                try {
                    conn.close();
                }catch(SQLException e) {}
            }
        }
    }

}
