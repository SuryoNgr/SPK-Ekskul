/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program.admin;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import java.util.HashMap;
import javax.swing.JOptionPane;



public class DataMahasiswa extends javax.swing.JFrame {
    private koneksi conn = new koneksi();
    private DefaultTableModel tabmode;
    String pilihan;
    
    public DataMahasiswa() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(this);
        datatable();
        aktif();
    }

    protected void kosong() {
        txt_nama.setText("");
        txt_nisn.setText("");
        buttonGroup1.clearSelection();
        Calendar tgl = Calendar.getInstance();
        tglLahir.setValue(tgl.getTime());
        cb_kls.setEnabled(true);
        txt_tlp.setText("");
        txt_alamat.setText("");
    }
    protected void aktif(){
    tglLahir.setEditor(new JSpinner.DateEditor(tglLahir,"yyyy/MM/dd"));
    }
    protected void datatable() {
        Object[] baris = {"NISN", "Nama", "Jenis Kelamin", "Tanggal Lahir", "Kelas","No Telepon", "Alamat"};
        tabmode = new DefaultTableModel(null, baris);
        String cari = txtcari.getText();
        
        try {
            String sql = "SELECT * FROM siswa";
            Connection connection = conn.getconnetion(); 
            Statement stat = connection.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tabmode.addRow(new Object[]{
                    hasil.getString("nisn"),
                    hasil.getString("nama"),
                    hasil.getString("kelamin"),
                    hasil.getString("tgl_lahir"),
                    hasil.getString("kelas"),
                    hasil.getString("no_tlp"),
                    hasil.getString("alamat")
                });
            }
            tbl_data_siswa.setModel(tabmode);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Data gagal dipanggil: " + e);
        }
    }

     
     

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btn_simpan = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        btnL = new javax.swing.JRadioButton();
        btnP = new javax.swing.JRadioButton();
        cb_kls = new javax.swing.JComboBox<>();
        tglLahir = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        txt_tlp = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_alamat = new javax.swing.JTextArea();
        btn_hapus = new javax.swing.JButton();
        btn_ubah = new javax.swing.JButton();
        txt_nisn = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_data_siswa = new javax.swing.JTable();
        txtcari = new javax.swing.JTextField();
        bcari = new javax.swing.JButton();
        btnctk = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(null);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Nama siswa");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Tanggal Lahir");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Kelas");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Jenis kelamin");

        buttonGroup1.add(btnL);
        btnL.setText("Pria");

        buttonGroup1.add(btnP);
        btnP.setText("Wanita");

        cb_kls.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--PILIH KELAS--", "KELAS X", "KELAS XI", "KELAS XII" }));
        cb_kls.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_klsItemStateChanged(evt);
            }
        });

        tglLahir.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1720392896329L), null, null, java.util.Calendar.DAY_OF_MONTH));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("No.Telepon");

        txt_tlp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tlpActionPerformed(evt);
            }
        });
        txt_tlp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tlpKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_tlpKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Alamat");

        txt_alamat.setColumns(20);
        txt_alamat.setRows(5);
        jScrollPane2.setViewportView(txt_alamat);

        btn_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/del.jpg"))); // NOI18N
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_ubah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/edit1.png"))); // NOI18N
        btn_ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ubahActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("NISN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(20, 20, 20)
                        .addComponent(txt_nama))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(btnL)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnP))
                                .addComponent(txt_tlp)
                                .addComponent(cb_kls, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tglLahir)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)))
                        .addGap(0, 234, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(txt_nisn)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_nisn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(btnL)
                    .addComponent(btnP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tglLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_kls, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txt_tlp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_ubah, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btn_hapus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_simpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(90, 60, 590, 430);

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel6.setText("DATA SISWA");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(333, 11, 100, 21);

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(10, 38, 741, 10);

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel7.setText("DAFTAR SISWA");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(333, 491, 122, 21);

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator2);
        jSeparator2.setBounds(10, 518, 741, 10);

        tbl_data_siswa.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_data_siswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_data_siswaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_data_siswa);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(150, 590, 495, 229);
        jPanel1.add(txtcari);
        txtcari.setBounds(150, 550, 123, 23);

        bcari.setText("Cari");
        bcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcariActionPerformed(evt);
            }
        });
        jPanel1.add(bcari);
        bcari.setBounds(290, 550, 51, 23);

        btnctk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/print.jpg"))); // NOI18N
        btnctk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnctkActionPerformed(evt);
            }
        });
        jPanel1.add(btnctk);
        btnctk.setBounds(334, 822, 104, 59);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ASA.png"))); // NOI18N
        jPanel1.add(jLabel10);
        jLabel10.setBounds(-250, 140, 1180, 820);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 928, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
    String jkl = null;
    if (btnL.isSelected()) {
        jkl = "Pria";
    } else if (btnP.isSelected()) {
        jkl = "Wanita";
    }
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    String fd = sdf1.format(tglLahir.getValue());
    String sql ="INSERT INTO siswa (nisn, nama, kelamin, tgl_lahir, kelas, no_tlp, alamat) VALUES (?, ?, ?, ?, ?, ?, ?)";
    String sqlUser = "INSERT INTO tbl_user (username, password, role) VALUES (?, ?, ?)";
    try {
        PreparedStatement stat = conn.getconnetion().prepareStatement(sql);
        stat.setString(1, txt_nisn.getText());
        stat.setString(2, txt_nama.getText());
        stat.setString(3, jkl); 
        stat.setString(4, fd);
        stat.setString(5, pilihan);
        stat.setString(6, txt_tlp.getText());
        stat.setString(7, txt_alamat.getText());
        stat.executeUpdate();
        
        PreparedStatement statUser  = conn.getconnetion().prepareStatement(sqlUser);
        statUser.setString(1, txt_nisn.getText()); 
        statUser.setString(2, "SekolahKita");     
        statUser.setString(3, "siswa");           
        statUser.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
        kosong();
        datatable();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Data gagal disimpan " + e);
    }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void txt_tlpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tlpKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tlpKeyReleased

    private void txt_tlpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tlpKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tlpKeyTyped

    private void txt_tlpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tlpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tlpActionPerformed

    private void bcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcariActionPerformed
        datatable();
    }//GEN-LAST:event_bcariActionPerformed

    private void cb_klsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_klsItemStateChanged
   pilihan = cb_kls.getSelectedItem().toString();

    }//GEN-LAST:event_cb_klsItemStateChanged

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
       int ok = JOptionPane.showConfirmDialog(null, "hapus", "Anda Yakin ingin menghapus data ?",JOptionPane.YES_NO_OPTION);
        if (ok==0) {
            String sql  ="DELETE FROM siswa WHERE nisn ='"+txt_nisn.getText()+"'";
            try {
                PreparedStatement stat = conn.getconnetion().prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data berhasil Hapus");
            kosong();
            aktif();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Data gagal dihapus "+ e);
            }
            datatable();
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
        String jkl = null;
    if (btnL.isSelected()) {
        jkl = "Pria";
    } else if (btnP.isSelected()) {
        jkl = "Wanita";
    }
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    String fd = sdf1.format(tglLahir.getValue());
    String sql ="UPDATE siswa SET nisn=?,nama=?,kelamin=?,tgl_lahir=?,kelas=?,no_tlp=?,alamat=? WHERE nisn = '"+txt_nisn.getText()+"'";
    
    try {
        PreparedStatement stat = conn.getconnetion().prepareStatement(sql);
        stat.setString(1, txt_nisn.getText());
        stat.setString(2, txt_nama.getText());
        stat.setString(3, jkl); 
        stat.setString(4, fd);
        stat.setString(5, pilihan);
        stat.setString(6, txt_tlp.getText());
        stat.setString(7, txt_alamat.getText());
        stat.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data berhasil diubah");
        kosong();
        datatable();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Data gagal diubah " + e);
    }
    }//GEN-LAST:event_btn_ubahActionPerformed

    private void tbl_data_siswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_data_siswaMouseClicked
          
    int bar = tbl_data_siswa.getSelectedRow();
   
    
    String a = tabmode.getValueAt(bar,0).toString();
    String b = tabmode.getValueAt(bar,1).toString();
    String c = tabmode.getValueAt(bar,2).toString();
    String d = tabmode.getValueAt(bar,3).toString();
    String e = tabmode.getValueAt(bar,4).toString();
    String f = tabmode.getValueAt(bar,5).toString();
    String g = tabmode.getValueAt(bar,6).toString();
        
    txt_nisn.setText(a);
    txt_nama.setText(b);
    if ("Pria".equals(c)) {
        btnL.setSelected(true);
    } else {
        btnP.setSelected(true);
    }
    tglLahir.setValue(java.sql.Date.valueOf(d));
    cb_kls.setSelectedItem(e);
    txt_tlp.setText(f);
    txt_alamat.setText(g);
    

    }//GEN-LAST:event_tbl_data_siswaMouseClicked

    private void btnctkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnctkActionPerformed
try {
        String path = "./src/laporan/reportSiswa.jasper";
        HashMap<String, Object> parameter = new HashMap<>();
        Connection conn = koneksi.getconnetion(); // Gunakan metode yang ada di kelas koneksi

        JasperPrint print = JasperFillManager.fillReport(path, parameter, conn);
        JasperViewer.viewReport(print, false);

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(rootPane, "Dokumen tidak ada: " + ex.getMessage());
    }

    }//GEN-LAST:event_btnctkActionPerformed


    
    
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
            java.util.logging.Logger.getLogger(DataMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataMahasiswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bcari;
    private javax.swing.JRadioButton btnL;
    private javax.swing.JRadioButton btnP;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_ubah;
    private javax.swing.JButton btnctk;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cb_kls;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tbl_data_siswa;
    private javax.swing.JSpinner tglLahir;
    private javax.swing.JTextArea txt_alamat;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_nisn;
    private javax.swing.JTextField txt_tlp;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables
}
