/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.dosenModel;
import model.mahasiswaModel;
import model.usersModel;
import tb.koneksi;

/**
 * FXML Controller class
 *
 * @author IT SUPPORT
 */
public class DashboardController implements Initializable {

    @FXML
    private Label menuHome;
    @FXML
    private Label menuDosen;
    @FXML
    private Label menuMahasiswa;
    @FXML
    private Label menuLogout;
    @FXML
    private Label menuPetugas;
    @FXML
    private AnchorPane pane_dashboard;
    @FXML
    private AnchorPane pane_home;
    @FXML
    private AnchorPane pane_dosen;
    @FXML
    private AnchorPane pane_mahasiswa;
    @FXML
    private AnchorPane pane_petugas;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    //-------------------------------TABEL PETUGAS------------------------------
    @FXML
    private TextField txt_id_up;
    @FXML
    private TextField txt_username_up;
    @FXML
    private PasswordField txt_password_up;
    @FXML
    private TextField txt_nama_up;
    @FXML
    private ComboBox level_up;

    @FXML
    private TableView<usersModel> table_users;
    @FXML
    private TableColumn<usersModel, Integer> users_id;
    @FXML
    private TableColumn<usersModel, String> users_username;
    @FXML
    private TableColumn<usersModel, String> users_password;
    @FXML
    private TableColumn<usersModel, String> users_nama;
    @FXML
    private TableColumn<usersModel, String> users_level;

    ObservableList<usersModel> listP;
    int indexP = -1;

    //----------------------------TABEL DOSEN-----------------------------------
    @FXML
    private TableView<dosenModel> table_dosen;
    @FXML
    private TableColumn<dosenModel, Integer> dosen_id;
    @FXML
    private TableColumn<dosenModel, String> dosen_nama;
    @FXML
    private TableColumn<dosenModel, String> dosen_nip;
    @FXML
    private TableColumn<dosenModel, String> dosen_hp;
    @FXML
    private TableColumn<dosenModel, String> dosen_status;
    @FXML
    private TextField txt_nama_dos;
    @FXML
    private TextField txt_nip_dos;
    @FXML
    private TextField txt_no_dos;
    @FXML
    private ComboBox status_dos;
    @FXML
    private TextField txt_id_dos;

    ObservableList<dosenModel> listD;
    int indexD = -1;

    //---------------------------TABLE MAHASISWA--------------------------------
    @FXML
    private TableView<mahasiswaModel> table_mahasiswa;
    @FXML
    private TableColumn<mahasiswaModel, Integer> mahasiswa_id;
    @FXML
    private TableColumn<mahasiswaModel, String> mahasiswa_nama;
    @FXML
    private TableColumn<mahasiswaModel, String> mahasiswa_nim;
    @FXML
    private TableColumn<mahasiswaModel, String> mahasiswa_prodi;
    @FXML
    private TableColumn<mahasiswaModel, String> mahasiswa_dosen;
    @FXML
    private TableColumn<mahasiswaModel, String> mahasiswa_dosbing;
    @FXML
    private TextField txt_nama_mhs;
    @FXML
    private TextField txt_nim_mhs;
    @FXML
    private ComboBox prodi_mhs;
    @FXML
    private ComboBox dosbing_mhs;
    @FXML
    private TextField txt_id_mhs;
    @FXML
    private TextField txt_alamat_mhs;

    ObservableList<mahasiswaModel> listM;
    int indexM = -1;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //--------------------------TABLE PETUGAS-------------------------------
        users_id.setCellValueFactory(new PropertyValueFactory<usersModel, Integer>("id"));
        users_username.setCellValueFactory(new PropertyValueFactory<usersModel, String>("username"));
        users_password.setCellValueFactory(new PropertyValueFactory<usersModel, String>("password"));
        users_nama.setCellValueFactory(new PropertyValueFactory<usersModel, String>("nama"));
        users_level.setCellValueFactory(new PropertyValueFactory<usersModel, String>("level"));

        listP = koneksi.listDataP();
        table_users.setItems(listP);

        //LEVEL PETUGAS
        level_up.getItems().addAll("Admin", "User");

        //---------------------------TABLE DOSEN--------------------------------
        dosen_id.setCellValueFactory(new PropertyValueFactory<dosenModel, Integer>("id_dosen"));
        dosen_nama.setCellValueFactory(new PropertyValueFactory<dosenModel, String>("nama"));
        dosen_nip.setCellValueFactory(new PropertyValueFactory<dosenModel, String>("nip"));
        dosen_hp.setCellValueFactory(new PropertyValueFactory<dosenModel, String>("no_hp"));
        dosen_status.setCellValueFactory(new PropertyValueFactory<dosenModel, String>("status"));

        listD = koneksi.listDataD();
        table_dosen.setItems(listD);

        //STATUS DOSEN
        status_dos.getItems().addAll("Permanent", "Contract");

        //-----------------------------TABLE MAHASISWA--------------------------
        mahasiswa_id.setCellValueFactory(new PropertyValueFactory<mahasiswaModel, Integer>("id_mahasiswa"));
        mahasiswa_nama.setCellValueFactory(new PropertyValueFactory<mahasiswaModel, String>("nama"));
        mahasiswa_nim.setCellValueFactory(new PropertyValueFactory<mahasiswaModel, String>("nim"));
        mahasiswa_prodi.setCellValueFactory(new PropertyValueFactory<mahasiswaModel, String>("prodi"));
        mahasiswa_dosen.setCellValueFactory(new PropertyValueFactory<mahasiswaModel, String>("alamat"));
        mahasiswa_dosbing.setCellValueFactory(new PropertyValueFactory<mahasiswaModel, String>("dosbing"));

        listM = koneksi.listDataM();
        table_mahasiswa.setItems(listM);

        //PRODI MAHASISWA
        prodi_mhs.getItems().addAll("Teknik Informatika", "Sistem Informasi", "Psikologi", "Manajemen Informatika");
    }

    @FXML
    private void menuHomeClicked(MouseEvent event) {
        menuHome.setBackground(new Background(new BackgroundFill(Color.valueOf("#29B6F6"), CornerRadii.EMPTY, Insets.EMPTY)));
        menuDosen.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        menuMahasiswa.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        menuPetugas.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        menuLogout.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        pane_dashboard.setVisible(true);
        pane_home.setVisible(true);
        pane_dosen.setVisible(false);
        pane_mahasiswa.setVisible(false);
        pane_petugas.setVisible(false);
    }

    @FXML
    private void menuDosenClicked(MouseEvent event) {
        menuHome.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        menuDosen.setBackground(new Background(new BackgroundFill(Color.valueOf("#29B6F6"), CornerRadii.EMPTY, Insets.EMPTY)));
        menuMahasiswa.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        menuPetugas.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        menuLogout.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        pane_dashboard.setVisible(true);
        pane_home.setVisible(false);
        pane_dosen.setVisible(true);
        pane_mahasiswa.setVisible(false);
        pane_petugas.setVisible(false);
    }

    @FXML
    private void menuMahasiswaClicked(MouseEvent event) {
        menuHome.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        menuDosen.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        menuMahasiswa.setBackground(new Background(new BackgroundFill(Color.valueOf("#29B6F6"), CornerRadii.EMPTY, Insets.EMPTY)));
        menuPetugas.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        menuLogout.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        pane_dashboard.setVisible(true);
        pane_home.setVisible(false);
        pane_dosen.setVisible(false);
        pane_mahasiswa.setVisible(true);
        pane_petugas.setVisible(false);
    }

    @FXML
    private void menuLogoutClicked(MouseEvent event) throws IOException {
        menuHome.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        menuDosen.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        menuMahasiswa.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        menuPetugas.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        menuLogout.setBackground(new Background(new BackgroundFill(Color.valueOf("#29B6F6"), CornerRadii.EMPTY, Insets.EMPTY)));
        menuLogout.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/tb/login.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void menuPetugasClicked(MouseEvent event) {
        menuHome.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        menuDosen.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        menuMahasiswa.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        menuPetugas.setBackground(new Background(new BackgroundFill(Color.valueOf("#29B6F6"), CornerRadii.EMPTY, Insets.EMPTY)));
        menuLogout.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        pane_dashboard.setVisible(true);
        pane_home.setVisible(false);
        pane_dosen.setVisible(false);
        pane_mahasiswa.setVisible(false);
        pane_petugas.setVisible(true);
    }

    //-------------------------ADD USER TABEL PETUGAS---------------------------
    @FXML
    public void add_users(ActionEvent event) {
        
        String id = txt_id_up.getText();
        String username = txt_username_up.getText();
        String password = txt_password_up.getText();
        String nama = txt_nama_up.getText();
        String level = level_up.getValue().toString();

        if ("".equals(id)) {
            txt_id_up.requestFocus();
            JOptionPane.showMessageDialog(null, "isi ID Petugas terlebih dahulu!");
        } else if ("".equals(username)) {
            txt_username_up.requestFocus();
            JOptionPane.showMessageDialog(null, "isi Username Petugas terlebih dahulu!");
        } else if ("".equals(password)) {
            txt_password_up.requestFocus();
            JOptionPane.showMessageDialog(null, "isi Password Petugas terlebih dahulu!");
        } else if ("".equals(nama)) {
            txt_nama_up.requestFocus();
            JOptionPane.showMessageDialog(null, "isi Nama Petugas terlebih dahulu!");
        } else if ("".equals(level)) {
            level_up.requestFocus();
            JOptionPane.showMessageDialog(null, "isi Level Petugas terlebih dahulu!");
        } else {

        conn = koneksi.koneksi();

        String sql = "insert into users (id, username, password, nama, level) values (?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id_up.getText());
            pst.setString(2, txt_username_up.getText());
            pst.setString(3, txt_password_up.getText());
            pst.setString(4, txt_nama_up.getText());
            pst.setString(5, level_up.getValue().toString());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Data petugas berhasil disimpan");
            updateTableP();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
      }
    }

    //---------------------SELECT TABLE PETUGAS---------------------------------
    @FXML
    public void getTabPetugas(MouseEvent event) {
        indexP = table_users.getSelectionModel().getSelectedIndex();
        if (indexP <= -1) {

            return;
        }
        txt_id_up.setText(users_id.getCellData(indexP).toString());
        txt_username_up.setText(users_username.getCellData(indexP).toString());
        txt_password_up.setText(users_password.getCellData(indexP).toString());
        txt_nama_up.setText(users_nama.getCellData(indexP).toString());
        level_up.setValue(users_level.getCellData(indexP).toString());
    }

    //-----------------------EDIT TABLE PETUGAS---------------------------------
    @FXML
    public void editP() {
        
        String id = txt_id_up.getText();
        String username = txt_username_up.getText();
        String password = txt_password_up.getText();
        String nama = txt_nama_up.getText();
        String level = level_up.getValue().toString();

        if ("".equals(id)) {
            txt_id_up.requestFocus();
            JOptionPane.showMessageDialog(null, "ID Petugas tidak boleh kosong!");
        } else if ("".equals(username)) {
            txt_username_up.requestFocus();
            JOptionPane.showMessageDialog(null, "Username Petugas tidak boleh kosong!");
        } else if ("".equals(password)) {
            txt_password_up.requestFocus();
            JOptionPane.showMessageDialog(null, "Password Petugas tidak boleh kosong!");
        } else if ("".equals(nama)) {
            txt_nama_up.requestFocus();
            JOptionPane.showMessageDialog(null, "Nama Petugas tidak boleh kosong!");
        } else if ("".equals(level)) {
            level_up.requestFocus();
            JOptionPane.showMessageDialog(null, "Level Petugas tidak boleh kosong!");
        } else {
            
        try {
            conn = koneksi.koneksi();
            String value1 = txt_id_up.getText();
            String value2 = txt_username_up.getText();
            String value3 = txt_password_up.getText();
            String value4 = txt_nama_up.getText();
            String value5 = (String) level_up.getValue();

            String sql = "update users set id= '" + value1 + "', username= '" + value2 + "', password= '" + value3 + "', nama= '" + value4 + "', level= '" + value5 + "' where id='" + value1 + "' ";
            pst = conn.prepareStatement(sql);
            pst.execute();

            JOptionPane.showMessageDialog(null, "Data petugas berhasil diupdate");
            updateTableP();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }
    }

    //-----------------------UPDATE DATA TABLE PETUGAS--------------------------
    public void updateTableP() {
        users_id.setCellValueFactory(new PropertyValueFactory<usersModel, Integer>("id"));
        users_username.setCellValueFactory(new PropertyValueFactory<usersModel, String>("username"));
        users_password.setCellValueFactory(new PropertyValueFactory<usersModel, String>("password"));
        users_nama.setCellValueFactory(new PropertyValueFactory<usersModel, String>("nama"));
        users_level.setCellValueFactory(new PropertyValueFactory<usersModel, String>("level"));

        listP = koneksi.listDataP();
        table_users.setItems(listP);
    }

    //--------------------------------DELETE PETUGAS----------------------------
    @FXML
    public void deleteP() {
        conn = koneksi.koneksi();
        String sql = "delete from users where id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id_up.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data petugas berhasil dihapus");
            updateTableP();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //---------------------------ADD DATA TABEL DOSEN---------------------------
    @FXML
    public void add_dosen(ActionEvent event) {
        
        String id = txt_id_dos.getText();
        String nama = txt_nama_dos.getText();
        String nip = txt_nip_dos.getText();
        String no = txt_no_dos.getText();
        String status = status_dos.getValue().toString();

        if ("".equals(id)) {
            txt_id_dos.requestFocus();
            JOptionPane.showMessageDialog(null, "isi ID Dosen terlebih dahulu!");
        } else if ("".equals(nama)) {
            txt_nama_dos.requestFocus();
            JOptionPane.showMessageDialog(null, "isi Nama Dosen terlebih dahulu!");
        } else if ("".equals(nip)) {
            txt_nip_dos.requestFocus();
            JOptionPane.showMessageDialog(null, "isi NIP Dosen terlebih dahulu!");
        } else if ("".equals(no)) {
            txt_no_dos.requestFocus();
            JOptionPane.showMessageDialog(null, "isi No HP Dosen terlebih dahulu!");
        } else if ("".equals(status)) {
            status_dos.requestFocus();
            JOptionPane.showMessageDialog(null, "isi Status Dosen terlebih dahulu!");
        } else {

        conn = koneksi.koneksi();

        String sql = "insert into dosen (id_dosen, nama, nip, no_hp, status) values (?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id_dos.getText());
            pst.setString(2, txt_nama_dos.getText());
            pst.setString(3, txt_nip_dos.getText());
            pst.setString(4, txt_no_dos.getText());
            pst.setString(5, status_dos.getValue().toString());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Data dosen berhasil disimpan");
            updateTableD();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
      }
    }

    //------------------------SELECT TABLE DOSEN--------------------------------
    @FXML
    public void getTabDosen(MouseEvent event) {
        indexD = table_dosen.getSelectionModel().getSelectedIndex();
        if (indexD <= -1) {

            return;
        }
        txt_id_dos.setText(dosen_id.getCellData(indexD).toString());
        txt_nama_dos.setText(dosen_nama.getCellData(indexD).toString());
        txt_nip_dos.setText(dosen_nip.getCellData(indexD).toString());
        txt_no_dos.setText(dosen_hp.getCellData(indexD).toString());
        status_dos.setValue(dosen_status.getCellData(indexD).toString());
    }

    //---------------------------EDIT TABLE DOSEN-------------------------------
    @FXML
    public void editD() {
        
        String id = txt_id_dos.getText();
        String nama = txt_nama_dos.getText();
        String nip = txt_nip_dos.getText();
        String no = txt_no_dos.getText();
        String status = status_dos.getValue().toString();

        if ("".equals(id)) {
            txt_id_dos.requestFocus();
            JOptionPane.showMessageDialog(null, "ID Dosen tidak boleh kosong!");
        } else if ("".equals(nama)) {
            txt_nama_dos.requestFocus();
            JOptionPane.showMessageDialog(null, "Nama Dosen tidak boleh kosong!");
        } else if ("".equals(nip)) {
            txt_nip_dos.requestFocus();
            JOptionPane.showMessageDialog(null, "NIP Dosen tidak boleh kosong!");
        } else if ("".equals(no)) {
            txt_no_dos.requestFocus();
            JOptionPane.showMessageDialog(null, "No HP Dosen tidak boleh kosong!");
        } else if ("".equals(status)) {
            status_dos.requestFocus();
            JOptionPane.showMessageDialog(null, "Status Dosen tidak boleh kosong!");
        } else {
            
        try {
            conn = koneksi.koneksi();
            String value1 = txt_id_dos.getText();
            String value2 = txt_nama_dos.getText();
            String value3 = txt_nip_dos.getText();
            String value4 = txt_no_dos.getText();
            String value5 = (String) status_dos.getValue();

            String sql = "update dosen set id_dosen= '" + value1 + "', nama= '" + value2 + "', nip= '" + value3 + "', no_hp= '" + value4 + "', status= '" + value5 + "' where id_dosen='" + value1 + "' ";
            pst = conn.prepareStatement(sql);
            pst.execute();

            JOptionPane.showMessageDialog(null, "Data dosen berhasil diupdate");
            updateTableD();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
      }  
    }

    //-------------------------UPDATE DATA TABLE DOSEN--------------------------
    public void updateTableD() {
        dosen_id.setCellValueFactory(new PropertyValueFactory<dosenModel, Integer>("id_dosen"));
        dosen_nama.setCellValueFactory(new PropertyValueFactory<dosenModel, String>("nama"));
        dosen_nip.setCellValueFactory(new PropertyValueFactory<dosenModel, String>("nip"));
        dosen_hp.setCellValueFactory(new PropertyValueFactory<dosenModel, String>("no_hp"));
        dosen_status.setCellValueFactory(new PropertyValueFactory<dosenModel, String>("status"));

        listD = koneksi.listDataD();
        table_dosen.setItems(listD);
    }

    //---------------------------DELETE DATA DOSEN------------------------------
    @FXML
    public void deleteD() {
        conn = koneksi.koneksi();
        String sql = "delete from dosen where id_dosen = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id_dos.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data dosen berhasil dihapus");
            updateTableD();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //---------------------------ADD DATA MAHASISWA-----------------------------
    @FXML
    public void add_mahasiswa(ActionEvent event) {

        String id = txt_id_mhs.getText();
        String nama = txt_nama_mhs.getText();
        String nim = txt_nim_mhs.getText();
        String prodi = prodi_mhs.getValue().toString();
        String alamat = txt_alamat_mhs.getText();
        String dosbing = dosbing_mhs.getValue().toString();

        if ("".equals(id)) {
            txt_id_mhs.requestFocus();
            JOptionPane.showMessageDialog(null, "isi ID Mahasiswa terlebih dahulu!");
        } else if ("".equals(nama)) {
            txt_nama_mhs.requestFocus();
            JOptionPane.showMessageDialog(null, "isi Nama Mahasiswa terlebih dahulu!");
        } else if ("".equals(nim)) {
            txt_nim_mhs.requestFocus();
            JOptionPane.showMessageDialog(null, "isi NIM Mahasiswa terlebih dahulu!");
        } else if ("".equals(prodi)) {
            prodi_mhs.requestFocus();
            JOptionPane.showMessageDialog(null, "isi Prodi Mahasiswa terlebih dahulu!");
        } else if ("".equals(alamat)) {
            txt_alamat_mhs.requestFocus();
            JOptionPane.showMessageDialog(null, "isi Alamat Mahasiswa terlebih dahulu!");
        } else if ("".equals(dosbing)) {
            dosbing_mhs.requestFocus();
            JOptionPane.showMessageDialog(null, "isi Dosen Pembimbing terlebih dahulu!");
        } else {
            conn = koneksi.koneksi();

            String sql = "insert into mahasiswa (id_mahasiswa, nama, nim, prodi, alamat, dosbing) values (?,?,?,?,?,?)";
            try {
                pst = conn.prepareStatement(sql);
                pst.setString(1, txt_id_mhs.getText());
                pst.setString(2, txt_nama_mhs.getText());
                pst.setString(3, txt_nim_mhs.getText());
                pst.setString(4, prodi_mhs.getValue().toString());
                pst.setString(5, txt_alamat_mhs.getText());
                pst.setString(6, dosbing_mhs.getValue().toString());
                pst.execute();

                    JOptionPane.showMessageDialog(null, "Data mahasiswa berhasil disimpan");
                    updateTableM();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    //----------------------SELECT TABLE MAHASISWA------------------------------
    @FXML
    public void getTabMahasiswa(MouseEvent event) {
        indexM = table_mahasiswa.getSelectionModel().getSelectedIndex();
        if (indexM <= -1) {

            return;
        }
        txt_id_mhs.setText(mahasiswa_id.getCellData(indexM).toString());
        txt_nama_mhs.setText(mahasiswa_nama.getCellData(indexM).toString());
        txt_nim_mhs.setText(mahasiswa_nim.getCellData(indexM).toString());
        prodi_mhs.setValue(mahasiswa_prodi.getCellData(indexM).toString());
        txt_alamat_mhs.setText(mahasiswa_dosen.getCellData(indexM).toString());
        dosbing_mhs.setValue(mahasiswa_dosbing.getCellData(indexM).toString());
    }

    //------------------------EDIT DATA TABLE MAHASISWA-------------------------
    @FXML
    public void editM() {
        
        String id = txt_id_mhs.getText();
        String nama = txt_nama_mhs.getText();
        String nim = txt_nim_mhs.getText();
        String prodi = prodi_mhs.getValue().toString();
        String alamat = txt_alamat_mhs.getText();
        String dosbing = dosbing_mhs.getValue().toString();

        if ("".equals(id)) {
            txt_id_mhs.requestFocus();
            JOptionPane.showMessageDialog(null, "ID Mahasiswa tidak boleh kosong!");
        } else if ("".equals(nama)) {
            txt_nama_mhs.requestFocus();
            JOptionPane.showMessageDialog(null, "Nama Mahasiswa tidak boleh kosong!");
        } else if ("".equals(nim)) {
            txt_nim_mhs.requestFocus();
            JOptionPane.showMessageDialog(null, "NIM Mahasiswa tidak boleh kosong!");
        } else if ("".equals(prodi)) {
            prodi_mhs.requestFocus();
            JOptionPane.showMessageDialog(null, "Prodi Mahasiswa tidak boleh kosong!");
        } else if ("".equals(alamat)) {
            txt_alamat_mhs.requestFocus();
            JOptionPane.showMessageDialog(null, "Alamat Mahasiswa tidak boleh kosong!");
        } else if ("".equals(dosbing)) {
            dosbing_mhs.requestFocus();
            JOptionPane.showMessageDialog(null, "Dosen Pembimbing tidak boleh kosong!");
        } else {
        try {
            conn = koneksi.koneksi();
            String value1 = txt_id_mhs.getText();
            String value2 = txt_nama_mhs.getText();
            String value3 = txt_nim_mhs.getText();
            String value4 = (String) prodi_mhs.getValue();
            String value5 = txt_alamat_mhs.getText();
            String value6 = (String) dosbing_mhs.getValue();

            String sql = "update mahasiswa set id_mahasiswa= '" + value1 + "', nama= '" + value2 + "', nim= '" + value3 + "', prodi= '" + value4 + "', alamat= '" + value5 + "', dosbing= '" + value6 + "' where id_mahasiswa='" + value1 + "' ";
            pst = conn.prepareStatement(sql);
            pst.execute();

            JOptionPane.showMessageDialog(null, "Data mahasiswa berhasil diupdate");
            updateTableM();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
      }
    }

    //------------------------UPDATE DATA TABLE MAHASISWA-----------------------
    public void updateTableM() {
        mahasiswa_id.setCellValueFactory(new PropertyValueFactory<mahasiswaModel, Integer>("id_mahasiswa"));
        mahasiswa_nama.setCellValueFactory(new PropertyValueFactory<mahasiswaModel, String>("nama"));
        mahasiswa_nim.setCellValueFactory(new PropertyValueFactory<mahasiswaModel, String>("nim"));
        mahasiswa_prodi.setCellValueFactory(new PropertyValueFactory<mahasiswaModel, String>("prodi"));
        mahasiswa_dosen.setCellValueFactory(new PropertyValueFactory<mahasiswaModel, String>("alamat"));
        mahasiswa_dosbing.setCellValueFactory(new PropertyValueFactory<mahasiswaModel, String>("dosbing"));

        listM = koneksi.listDataM();
        table_mahasiswa.setItems(listM);
    }

    //---------------------------DELETE DATA MAHASISWA--------------------------
    @FXML
    public void deleteM() {
        conn = koneksi.koneksi();

        String sql = "delete from mahasiswa where id_mahasiswa = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id_mhs.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data mahasiswa berhasil dihapus");
            updateTableM();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //-----------------------LOAD NAMA DOSEN PANEL MAHASISWA--------------------
    @FXML
    public void LoadDosen() {
        conn = koneksi.koneksi();
        
        String sql = "select nama from dosen";
        try {
            pst = conn.prepareStatement(sql);
           ObservableList dosbing = FXCollections.observableArrayList();
            rs = pst.executeQuery();
            while (rs.next()) {
                int columnIndex;
                dosbing.add(new String(rs.getString(columnIndex = 1)));
            }
            dosbing_mhs.setItems(dosbing);
            updateTableM();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
