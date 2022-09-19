package com.example;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.pojo.Brand;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Ʒ�����ݵ���ɾ�Ĳ����
 */
public class BrandTest {

    /**
     * ��ѯ����
     * 1.SQL��select * from tb_brand
     * 2.��������Ҫ
     * 3.�������ʲô�������ݽṹ���棺list
     */
    @Test
    public void testSelectAll() throws Exception {
        // ��ȡconnection��������(����druid�������ӳ�)
        Properties prop = new Properties();
        System.out.println(System.getProperty("user.dir"));
        //E:\Projects\Java\JDBC\JDBC-Demo
        prop.load(new FileInputStream("src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        Connection conn = dataSource.getConnection();

        //����sql���
        String sql = "select * from tb_brand";

        // ��ȡstmt����
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // ���ò�������Ϊ��ѯ�������������������ã���    ��    ����

        // ִ��sql
        ResultSet rs = pstmt.executeQuery();

        // ������
        Brand brand;
        List<Brand> brands = new ArrayList<>();
        while (rs.next()) {
            // ��ȡ����
            int id = rs.getInt("id");
            String brandName = rs.getString("brand_name");
            String companyName = rs.getString("company_name");
            int ordered = rs.getInt("ordered");
            String description = rs.getString("description");
            int status = rs.getInt("status");

            // װ��Brand����
            brand = new Brand();
            brand.setBrandName(brandName);
            brand.setCompanyName(companyName);
            brand.setOrdered(ordered);
            brand.setDescription(description);
            brand.setStatus(status);

            // װ�ؼ���
            brands.add(brand);
        }

        System.out.println(brands);
        // �ͷ���Դ
        rs.close();
        pstmt.close();
        conn.close();
    }

    /**
     * ���
     * 1.SQL��insert into tb_brand(brand_name, company_name, ordered, description, status) values(?,?,?,?,?);
     * 2.����������id֮������в�����Ϣ
     * 3.�����Boolean
     */
    @Test
    public void testAdd() throws Exception {

        // ����ҳ���ύ�Ĳ���
        String brandName = "��ƮƮ";
        String companyName = "��ƮƮ";
        int ordered = 1;
        String description = "�Ƶ���";
        int status = 1;


        // ��ȡconnection��������(����druid�������ӳ�)
        Properties prop = new Properties();
        System.out.println(System.getProperty("user.dir"));
        //E:\Projects\Java\JDBC\JDBC-Demo
        prop.load(new FileInputStream("src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        Connection conn = dataSource.getConnection();

        //����sql���
        String sql = "insert into tb_brand(brand_name, company_name, ordered, description, status) values(?,?,?,?,?);";

        // ��ȡstmt����
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // ���ò���
        pstmt.setString(1, brandName);
        pstmt.setString(2, companyName);
        pstmt.setInt(3, ordered);
        pstmt.setString(4, description);
        pstmt.setInt(5, status);

        // ִ��sql ����Ӱ������
        int count = pstmt.executeUpdate();

        // ������
        System.out.println(count > 0);

        // �ͷ���Դ
        pstmt.close();
        conn.close();
    }

    /**
     * �޸�(����id�޸�)
     * 1.SQL��update tb_brand
     * set brand_name  = ?,
     * company_name= ?,
     * ordered     = ?,
     * description = ?,
     * status      = ?
     * where id = ?
     * 2.���������в�����Ϣ
     * 3.�����Boolean
     */
    @Test
    public void testUpdate() throws Exception {

        // ����ǰ��ҳ���ύ�Ĳ���
        String brandName = "��ƮƮ";
        String companyName = "��ƮƮ";
        int ordered = 1000;
        String description = "�Ƶ�";
        int status = 1;
        int id = 4;


        // ��ȡconnection��������(����druid�������ӳ�)
        Properties prop = new Properties();
        System.out.println(System.getProperty("user.dir"));
        //E:\Projects\Java\JDBC\JDBC-Demo
        prop.load(new FileInputStream("src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        Connection conn = dataSource.getConnection();

        //����sql���
        String sql = "update tb_brand\n" +
                "         set brand_name  = ?,\n" +
                "         company_name= ?,\n" +
                "         ordered     = ?,\n" +
                "         description = ?,\n" +
                "         status      = ?\n" +
                "     where id = ?";

        // ��ȡstmt����
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // ���ò���
        pstmt.setString(1, brandName);
        pstmt.setString(2, companyName);
        pstmt.setInt(3, ordered);
        pstmt.setString(4, description);
        pstmt.setInt(5, status);
        pstmt.setInt(6, id);

        // ִ��sql ����Ӱ������
        int count = pstmt.executeUpdate();

        // ������
        System.out.println(count > 0);

        // �ͷ���Դ
        pstmt.close();
        conn.close();
    }

    /**
     * ɾ��(����id�޸�)
     * 1.SQL��delete from tb_brand where id = ?
     * 2.������id
     * 3.�����Boolean
     */
    @Test
    public void testDelete() throws Exception {

        // ����ǰ��ҳ���ύ�Ĳ���
        int id = 4;

        // ��ȡconnection��������(����druid�������ӳ�)
        Properties prop = new Properties();
        System.out.println(System.getProperty("user.dir"));
        //E:\Projects\Java\JDBC\JDBC-Demo
        prop.load(new FileInputStream("src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        Connection conn = dataSource.getConnection();

        //����sql���
        String sql = "delete from tb_brand where id = ?";

        // ��ȡstmt����
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // ���ò���
        pstmt.setInt(1, id);

        // ִ��sql ����Ӱ������
        int count = pstmt.executeUpdate();

        // ������
        System.out.println(count > 0);

        // �ͷ���Դ
        pstmt.close();
        conn.close();
    }
}
