/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dosenModel;
import model.mahasiswaModel;
import model.usersModel;

/**
 *
 * @author IT SUPPORT
 */
public class koneksi {

    Connection conn = null;

    public static Connection koneksi() {
        String driver = "com.mysql.jdbc.Driver";
        String host = "jdbc:mysql://localhost/db_kuliah";
        String user = "root";
        String pass = "";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(host, user, pass);
            System.out.println("Koneksi Berhasil");
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Koneksi Gagal " + e);
        }
        return null;
    }

    public static ObservableList<usersModel> listDataP() {

        Connection conn = koneksi.koneksi();
        ObservableList<usersModel> petugaslist = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                petugaslist.add(new usersModel(Integer.parseInt(rs.getString("id")), rs.getString("username"), rs.getString("password"), rs.getString("nama"), rs.getString("level")));
            }
        } catch (Exception e) {

        }
        return petugaslist;
    }

    public static ObservableList<dosenModel> listDataD() {

        Connection conn = koneksi.koneksi();
        ObservableList<dosenModel> dosenlist = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from dosen");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                dosenlist.add(new dosenModel(Integer.parseInt(rs.getString("id_dosen")), rs.getString("nama"), rs.getString("nip"), rs.getString("no_hp"), rs.getString("status")));
            }
        } catch (Exception e) {
        }
        return dosenlist;
    }

    public static ObservableList<mahasiswaModel> listDataM() {

        Connection conn = koneksi.koneksi();
        ObservableList<mahasiswaModel> mahasiswalist = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from mahasiswa");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                mahasiswalist.add(new mahasiswaModel(Integer.parseInt(rs.getString("id_mahasiswa")), rs.getString("nama"), rs.getString("nim"), rs.getString("prodi"), rs.getString("alamat"), rs.getString("dosbing")));
            }
        } catch (Exception e) {
        }
        return mahasiswalist;
    }
}
