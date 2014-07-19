package dbutils;//package com.test.dbutil;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

//import org.apache.commons.dbutils.handlers.BeanHandler;
//import org.apache.commons.dbutils.handlers.BeanListHandler;

public class UserDaoImpl {

    public DataSource getDataSource() {
        String url = "jdbc:mysql://192.168.1.125:3306/wind";
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName("192.168.1.125");
        //
//		ds.setDatabaseName("testdbutils");
        ds.setURL(url);
        ds.setUser("root");
        //
        ds.setPassword("");
        ds.setCharacterEncoding("utf8");
        return ds;
    }

    public BeanListHandler<User> getBeanListHandler() {
        return new BeanListHandler<User>(User.class);
    }

    public BeanHandler<User> getBeanHandler() {
        return new BeanHandler<User>(User.class);
    }

    public void delete(int id) {
        QueryRunner runner = new QueryRunner(getDataSource());
        try {
            int affectedRows = runner.update("delete from people where id = ?", id);
            System.out.println("删除成功，影响的行数：" + affectedRows);
        } catch (SQLException e) {
            System.out.println("删除id为" + id + "的记录失败。错误为：" + e.getMessage());
        }
    }

    public void delete(User user) {
        delete(user.getId());
    }

    public List<User> getAllUsers() {
        QueryRunner runner = new QueryRunner(getDataSource());
        try {
            return runner.query("select * from people", getBeanListHandler());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getById(int id) {
        QueryRunner runner = new QueryRunner(getDataSource());
        User user = null;
        try {
            user = runner.query("select * from people where id = ?", getBeanHandler(), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public void save(User user) {
        QueryRunner runner = new QueryRunner(getDataSource());

        try {

            Date date = new Date();
            Timestamp timeStamp = new Timestamp(date.getTime());
            int myu;
//            myu = runner.update("insert into people values(13,'name',33)");
//            myu = runner.update("insert into publishdate values(23,'"+timeStamp+"','时间')");
            myu = runner.update("insert into publishdate (publishdate,hello) values('" + timeStamp + "','时间2')");
        } catch (SQLException e) {
            System.out.println("插入失败，失败原因:" + e.getMessage() + "--");
        }
    }

    public static void main(String[] args) throws SQLException {
        UserDaoImpl udi = new UserDaoImpl();
        User user = new User();
        user.setName("myu");
        user.setAge("33");
        udi.save(user);
        long count = udi.getCount();

        udi.getHashMap();
        System.out.println(count);
    }

    public long getCount() {
        QueryRunner runner = new QueryRunner(getDataSource());
        try {


            Long count = runner.query("select count(*) as count from people", new ResultSetHandler<Long>() {

                public Long handle(ResultSet rs) throws SQLException {
                    if (rs.next()) {
                        return rs.getLong(1); //或者rs.getLong("count");
                    }
                    return 0L;
                }

            });

            return (long) count;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0L;
        }
    }


    public void getHashMap() throws SQLException {
        ResultSetHandler h = new KeyedHandler("id");
        QueryRunner runner = new QueryRunner(getDataSource());

        Map found = (Map) runner.query("select id, name,age from people", h);
        Map jane = (Map) found.get(3); // jane's id is 1
        String janesName = (String) jane.get("name");
        Integer janesAge = (Integer) jane.get("age");
        System.out.println("janesName=="+janesName);
        System.out.println("janesAge=="+janesAge);
        System.out.println("code is ok");
    }



}
