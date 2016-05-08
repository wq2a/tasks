package com.dreamycity.ali.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.dreamycity.ali.dao.AliOrderDAO;
import com.dreamycity.ali.model.AliOrder;

public class JdbcAliOrderDAO implements AliOrderDAO {
    private static final Logger logger = LogManager.getLogger(JdbcAliOrderDAO.class);

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean insert(AliOrder aliOrder) {
        boolean flag = false;
        String sql = "INSERT INTO purchase_order "+
            "(order_id,supplier,createtime,link,order_total)" +
            " VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;
        try {
            conn = dataSource.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,aliOrder.getOrder_id());
            ps.setString(2,aliOrder.getSupplier());
            ps.setString(3,aliOrder.getCreatetime());
            ps.setString(4,aliOrder.getLink());
            ps.setFloat(5,aliOrder.getOrder_total());

            logger.info(ps);
            ps.executeUpdate();
            ps.close();
            flag = true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            if(conn != null) {
                try{
                    conn.close();
                }catch(SQLException e) {}
            }
            return flag;
        }
    }

}
