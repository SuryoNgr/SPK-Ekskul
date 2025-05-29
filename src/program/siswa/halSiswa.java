/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program.siswa;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import program.admin.koneksi;

public class halSiswa extends javax.swing.JFrame {
    private koneksi conn = new koneksi();
    private DefaultTableModel tabmode;
    private String loggedInNisn;
    public String namaEkskul;
    private String namaLogin = "";

    public halSiswa() {
        initComponents();
        kosong();
        datatable();
    }
    
    protected void kosong() {
        txtekskul.setText("");
        txtHasilMinat.setText("");
        txtHasilOrtu.setText("");
        txtHasilBakat.setText("");
        txtHasilPrestasi.setText("");
        cbBakat.setEnabled(true);
        cbMinat.setEnabled(true);
        cbOrtu.setEnabled(true);
        cbPrestasi.setEnabled(true);
        
    }

    public void setLoggedInNisn(String nisn) {
        this.loggedInNisn = nisn;
        tampilDataSiswa(); 
        datatable();
    }

    public String getLoggedInNisn() {
        return loggedInNisn;
    }
    public String getNamaLogin() {
        return namaLogin;
    }
    
   private void tampilDataSiswa() {
        String nisn = getLoggedInNisn();
        

        try {
            String sql = "SELECT nisn, nama FROM siswa WHERE nisn = ?";
            PreparedStatement stat = conn.getconnetion().prepareStatement(sql);
            stat.setString(1, nisn);
            ResultSet res = stat.executeQuery();

            if (res.next()) {
                txtnisn.setText(res.getString("nisn"));
                txtnama.setText(res.getString("nama"));
                
                namaLogin = res.getString("nama");
            } else {
                JOptionPane.showMessageDialog(this, "Data siswa tidak ditemukan");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat mengambil data siswa: " + ex.getMessage());
        }
    }
    
    protected void datatable() {
    DefaultTableModel tabmode = new DefaultTableModel();
    Object[] baris = {"NISN", "Nama", "Nama Ekskul", "Minat", "Bakat", "Prestasi", "Dukungan Orang Tua"};
    tabmode.setColumnIdentifiers(baris);
    
    try {
        String nisnLogin = getLoggedInNisn();
       

        String sql = "SELECT * FROM tb_alternatif WHERE nisn = ?";
        PreparedStatement stat = conn.getconnetion().prepareStatement(sql);
        stat.setString(1, nisnLogin);

        ResultSet hasil = stat.executeQuery();

        while (hasil.next()) {
            tabmode.addRow(new Object[]{
                hasil.getString("nisn"),
                hasil.getString("nama"),
                hasil.getString("nama_ekskul"),
                hasil.getString("c1"),
                hasil.getString("c2"),
                hasil.getString("c3"),
                hasil.getString("c4")
            });
        }

        jTable1.setModel(tabmode);  // Set model tabel dengan data baru

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Data gagal dipanggil: " + e.getMessage());
    }
}




    
    public void itemTerpilih(){
        popup_Ekskul Pe = new popup_Ekskul();
        Pe.peks = this;
        txtekskul.setText(namaEkskul);
        
    }
    
    private String getNilaiFromDropdown(int index) {
    switch (index) {
        case 1: return "4"; // Sangat berminat
        case 2: return "3"; // Berminat
        case 3: return "2"; // Cukup berminat
        case 4: return "1"; // Tidak berminat
        default: return "";
    }
}
    
    private void tambahkriteria() {
        try {
            // Validasi namaLogin sebelum penggunaan
            if (namaLogin == null || namaLogin.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama login tidak valid.");
                return;
            }

            String namaEkskul = txtekskul.getText(); // Ambil dari komponen JTextfield
            
            String nilaiMinat = txtHasilMinat.getText();
            String nilaiBakat = txtHasilBakat.getText();
            String nilaiPrestasi = txtHasilPrestasi.getText();
            String nilaiDukunganOrangTua = txtHasilOrtu.getText();

            // Query untuk memasukkan data ke dalam tabel tbl_kriteria
            String sql = "INSERT INTO tbl_kriteria (nama, R, c1, c2, c3, c4) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stat = conn.getconnetion().prepareStatement(sql);
            stat.setString(1, namaLogin); 
            stat.setString(2, namaEkskul);
            stat.setString(3, nilaiMinat);
            stat.setString(4, nilaiBakat);
            stat.setString(5, nilaiPrestasi);
            stat.setString(6, nilaiDukunganOrangTua);

            // Eksekusi query
            stat.executeUpdate();

            //JOptionPane.showMessageDialog(this, "Data berhasil disimpan");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menyimpan data: " + ex.getMessage());
        }
    }
    
    private void hapusDataAlternatif(String namaEkskul) {
    try {
        String namaLogin = getNamaLogin(); // Ambil nama yang sedang login
        
        String sql = "DELETE FROM tb_alternatif WHERE nama = ? AND nama_ekskul = ?";
        PreparedStatement stat = conn.getconnetion().prepareStatement(sql);
        stat.setString(1, namaLogin);
        stat.setString(2, namaEkskul);
        
        int hasil = stat.executeUpdate();
        
        if (hasil > 0) {
            //JOptionPane.showMessageDialog(this, "Data alternatif untuk ekskul " + namaEkskul + " berhasil dihapus.");
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus.");
            datatable(); // Refresh tabel setelah penghapusan berhasil
        } else {
            JOptionPane.showMessageDialog(this, "Data alternatif untuk ekskul " + namaEkskul + " tidak ditemukan untuk dihapus.");
        }
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menghapus data alternatif: " + e.getMessage());
    }
}


    private void hapusDataKriteria(String namaEkskul) {
    try {
        String namaLogin = getNamaLogin();
        
        String sql = "DELETE FROM tbl_kriteria WHERE nama = ? AND R = ?";
        PreparedStatement stat = conn.getconnetion().prepareStatement(sql);
        stat.setString(1, namaLogin);
        stat.setString(2, namaEkskul);
        
        int hasil = stat.executeUpdate();
        
        if (hasil > 0) {
            //JOptionPane.showMessageDialog(this, "Data kriteria berhasil dihapus.");
        } else {
            JOptionPane.showMessageDialog(this, "Data kriteria tidak ditemukan untuk dihapus.");
        }
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menghapus data kriteria: " + e.getMessage());
    }
}

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        txtekskul = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        txtnisn = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnEks = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbMinat = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbBakat = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbPrestasi = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cbOrtu = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtHasilMinat = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtHasilBakat = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtHasilPrestasi = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtHasilOrtu = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();
        btncari = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btn_hasil = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 204, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(0, 51, 255));

        jPanel2.setBackground(new java.awt.Color(102, 153, 255));
        jPanel2.setLayout(null);
        jPanel2.add(txtekskul);
        txtekskul.setBounds(136, 128, 155, 40);

        jLabel5.setText("NAMA");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(223, 79, 29, 14);
        jPanel2.add(txtnama);
        txtnama.setBounds(270, 76, 117, 40);
        jPanel2.add(txtnisn);
        txtnisn.setBounds(95, 76, 110, 40);

        jLabel6.setText("Ekstrakulikuler");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(50, 130, 68, 14);

        btnEks.setText("Lihat Ekstrakulikuler");
        btnEks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEksActionPerformed(evt);
            }
        });
        jPanel2.add(btnEks);
        btnEks.setBounds(309, 127, 127, 23);

        jLabel7.setText("NISN ");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(50, 79, 27, 14);

        jLabel1.setText("Minat");

        cbMinat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilihan", "Sangat Berminat", "Berminat", "Cukup Berminat", "Tidak Berminat" }));
        cbMinat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMinatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbMinat, 0, 161, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(cbMinat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel1);
        jPanel1.setBounds(108, 212, 181, 82);

        jLabel2.setText("Bakat");

        cbBakat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilihan", "Sangat Berbakat", "Berbakat", "Cukup Berbakat", "Tidak Berbakat" }));
        cbBakat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBakatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbBakat, 0, 161, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(cbBakat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3);
        jPanel3.setBounds(307, 212, 181, 82);

        jLabel3.setText("Prestasi");

        cbPrestasi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilihan", "Sangat Berprestasi", "Berprestasi", "Cukup Berprestasi", "Tidak Berprestasi" }));
        cbPrestasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPrestasiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbPrestasi, 0, 161, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(cbPrestasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel4);
        jPanel4.setBounds(506, 212, 181, 82);

        jLabel8.setText("Dukungan Orang Tua");

        cbOrtu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilihan", "Sangat Didukung Orang Tua", "Didukung Orang Tua", "Cukup Didukung Orang Tua", "Tidak Didukung Orang Tua" }));
        cbOrtu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOrtuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbOrtu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(39, 39, 39))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(cbOrtu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel6);
        jPanel6.setBounds(705, 212, 180, 82);

        jLabel9.setText("Hasil");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtHasilMinat)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(txtHasilMinat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 32, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel7);
        jPanel7.setBounds(108, 312, 181, 84);

        jLabel12.setText("Hasil");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtHasilBakat)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(txtHasilBakat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 32, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel10);
        jPanel10.setBounds(307, 312, 181, 84);

        jLabel13.setText("Hasil");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtHasilPrestasi)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(txtHasilPrestasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 32, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel11);
        jPanel11.setBounds(506, 312, 181, 84);

        jLabel14.setText("Hasil");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtHasilOrtu)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(txtHasilOrtu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 32, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel12);
        jPanel12.setBounds(705, 312, 180, 84);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(108, 554, 777, 173);

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel2.add(btnAdd);
        btnAdd.setBounds(418, 414, 153, 53);
        jPanel2.add(txtcari);
        txtcari.setBounds(108, 521, 133, 20);

        btncari.setText("Cari");
        jPanel2.add(btncari);
        btncari.setBounds(259, 520, 51, 23);

        jLabel4.setForeground(new java.awt.Color(255, 51, 51));
        jLabel4.setText("*Note: Klik baris yang ingin dihapus untuk menghapus");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(628, 537, 257, 14);

        btn_hasil.setText("Lihat Hasil");
        btn_hasil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hasilActionPerformed(evt);
            }
        });
        jPanel2.add(btn_hasil);
        btn_hasil.setBounds(351, 520, 130, 23);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pngwing.com (3).png"))); // NOI18N
        jPanel2.add(jLabel10);
        jLabel10.setBounds(-80, 60, 1050, 800);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1005, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEksActionPerformed
      popup_Ekskul Pe = new popup_Ekskul();
      Pe.peks = this;
      Pe.setVisible(true);
      Pe.setResizable(false);
    }//GEN-LAST:event_btnEksActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
       try {
    // Ambil nilai dari dropdown untuk setiap kriteria
    String minat = cbMinat.getSelectedItem().toString();
    String bakat = cbBakat.getSelectedItem().toString();
    String prestasi = cbPrestasi.getSelectedItem().toString();
    String dukunganOrtu = cbOrtu.getSelectedItem().toString();
    
    
    if (minat.isEmpty() || bakat.isEmpty() || prestasi.isEmpty() || dukunganOrtu.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Harap lengkapi semua kriteria!");
        return;
    }
    
    
    String nisn = txtnisn.getText();
    String namaSiswa = txtnama.getText();
    String namaEkskul = txtekskul.getText();
    
    tambahkriteria();
    String sql = "INSERT INTO tb_alternatif (nisn, nama, nama_ekskul, c1, c2, c3, c4) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?)";
    PreparedStatement stat = conn.getconnetion().prepareStatement(sql);
    stat.setString(1, nisn);
    stat.setString(2, namaSiswa);
    stat.setString(3, namaEkskul);
    stat.setString(4, minat);
    stat.setString(5, bakat);
    stat.setString(6, prestasi);
    stat.setString(7, dukunganOrtu);
    
    
    stat.executeUpdate();
    
    
    JOptionPane.showMessageDialog(this, "Data berhasil disimpan");
    kosong();
    datatable();
} catch (SQLException ex) {
    JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menyimpan data: " + ex.getMessage());
}

       
    }//GEN-LAST:event_btnAddActionPerformed

    private void cbMinatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMinatActionPerformed
        String nilaiMinat = getNilaiFromDropdown(cbMinat.getSelectedIndex());
        txtHasilMinat.setText(nilaiMinat);
    }//GEN-LAST:event_cbMinatActionPerformed

    private void cbBakatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBakatActionPerformed
         String nilaiBakat = getNilaiFromDropdown(cbBakat.getSelectedIndex());
        txtHasilBakat.setText(nilaiBakat);
    }//GEN-LAST:event_cbBakatActionPerformed

    private void cbPrestasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPrestasiActionPerformed
       String nilaiPrestasi = getNilaiFromDropdown(cbPrestasi.getSelectedIndex());
       txtHasilPrestasi.setText(nilaiPrestasi);
    }//GEN-LAST:event_cbPrestasiActionPerformed

    private void cbOrtuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOrtuActionPerformed
         String nilaiDukunganOrangTua = getNilaiFromDropdown(cbOrtu.getSelectedIndex());
        txtHasilOrtu.setText(nilaiDukunganOrangTua);
    }//GEN-LAST:event_cbOrtuActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
    int row = jTable1.rowAtPoint(evt.getPoint());
    int col = jTable1.columnAtPoint(evt.getPoint());     
    
     if (row >= 0 && col >= 0) {
            String namaEkskul = jTable1.getValueAt(row, 2).toString(); 
            
            // Tampilkan konfirmasi untuk penghapusan
            int confirm = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menghapus data untuk ekskul " + namaEkskul + "?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                hapusDataAlternatif(namaEkskul); 
                hapusDataKriteria(namaEkskul);
            }
        }
    
    
    }//GEN-LAST:event_jTable1MouseClicked

    private void btn_hasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hasilActionPerformed
    popup_Hasil Ph = new popup_Hasil(this); // Mengirimkan referensi halSiswa saat membuat popup_Hasil
    Ph.setVisible(true);
    Ph.setResizable(false);
    }//GEN-LAST:event_btn_hasilActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(halSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(halSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(halSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(halSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new halSiswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnEks;
    private javax.swing.JButton btn_hasil;
    private javax.swing.JButton btncari;
    private javax.swing.JComboBox<String> cbBakat;
    private javax.swing.JComboBox<String> cbMinat;
    private javax.swing.JComboBox<String> cbOrtu;
    private javax.swing.JComboBox<String> cbPrestasi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtHasilBakat;
    private javax.swing.JTextField txtHasilMinat;
    private javax.swing.JTextField txtHasilOrtu;
    private javax.swing.JTextField txtHasilPrestasi;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtekskul;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnisn;
    // End of variables declaration//GEN-END:variables
}
