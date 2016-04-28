package com.dreamycity.lottery.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.dreamycity.lottery.dao.LotteryDAO;
import com.dreamycity.lottery.model.Lottery;

public class JdbcLotteryDAO implements LotteryDAO {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(Lottery lottery) {
        String sql = "INSERT INTO kj "+
            "(kj_id,red1,red2,red3,red4,red5,red6,blue,time)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,lottery.getKj_id());
            ps.setInt(2,lottery.getRed1());
            ps.setInt(3,lottery.getRed2());
            ps.setInt(4,lottery.getRed3());
            ps.setInt(5,lottery.getRed4());
            ps.setInt(6,lottery.getRed5());
            ps.setInt(7,lottery.getRed6());
            ps.setInt(8,lottery.getBlue());
            ps.setString(9,lottery.getTime());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if(conn != null) {
                try{
                    conn.close();
                }catch(SQLException e) {}
            }
        }
    }

}
