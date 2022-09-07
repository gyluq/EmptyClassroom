package com.musen.EmptyClassRoom;

import com.musen.EmptyClassRoom.pojo.FormattedRoom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@SpringBootTest
public class test {

    @Autowired
    DataSource dataSource;

    @Test
    public void show() {
        ArrayList<FormattedRoom> arrayList = new ArrayList<>();
        arrayList.add(new FormattedRoom());
        arrayList.add(new FormattedRoom());
        arrayList.add(new FormattedRoom());
        arrayList.add(new FormattedRoom());
        arrayList.add(new FormattedRoom());
        System.out.println(arrayList);
    }
    
    @Test
    public void show2() throws SQLException {
        System.out.println(dataSource.getClass());
        //获得数据库连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
}
