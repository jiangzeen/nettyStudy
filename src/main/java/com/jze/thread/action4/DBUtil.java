package com.jze.thread.action4;

import com.mysql.jdbc.Connection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.jze.thread.action4.StringUtil.hasContain;
import static com.jze.thread.action4.StringUtil.hasContainCount;

/**
 * @author: 蒋泽恩
 * @date: 2019/5/24 22:16
 * @description:
 */
public class DBUtil {

    @Getter
    @Setter
    @ToString
    static class FileData{
        String id;
        String fileName;
        String content;
        String cId;
        String dir;
    }

    public static Connection getConnection(String url, String user,
                                           String password){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = (Connection) DriverManager.
                    getConnection(url, user, password);
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<FileData> getFileData(Connection connection){
        try {
            List<FileData> fileData = new ArrayList<>();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM file_data limit 1000;";
            statement.executeQuery(sql);
            ResultSet set = statement.getResultSet();
            System.out.println("查询完成");
            while (set.next()){
                FileData temp = new FileData();
                temp.setId(set.getString(1));
                temp.setFileName(set.getString(2));
                temp.setContent(set.getString(3));
                temp.setCId(set.getString(4));
                temp.setDir(set.getString(5));
                fileData.add(temp);
            }
            return fileData;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Optional<String> getContent(Connection connection){
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM file_data WHERE d_id=68883";
            statement.executeQuery(sql);
            ResultSet rs = statement.getResultSet();
            if (rs.next()){
                return Optional.of(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/keyword?useSSL=false";
        Connection conn = getConnection(url,"root","jiang.141201");
        assert conn != null;
        Objects.requireNonNull(getFileData(conn)).forEach(it->
                System.out.println("文章id:" + it.getId() + "包含的关键词个数：" +
                        hasContain(it.getContent()==null?"":it.getContent(),
                                "股票")));
    }
}
