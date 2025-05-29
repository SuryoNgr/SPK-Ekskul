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
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
public class DataEkskul extends javax.swing.JFrame {
 private koneksi conn = new koneksi();
 private DefaultTableModel tabmode;
 String pilihan;
    
    public DataEkskul() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(this);
        datatable();
    }
        
    protected void kosong() {
        txt_ekskul.setText("");
        txt_jam.setText("");
        
    }
    
    protected void datatable() {
    Object[] baris = {"No", "Nama Ekstrakulikuler", "Jam Ekstrakulikuler", "Hari Ekstrakulikuler"};
    tabmode = new DefaultTableModel(null, baris);
    String cari = txt_cari.getText();
    int nomor = 1; 

    try {
        String sql = "SELECT * FROM tbl_ekskul WHERE nama_ekskul like'%"+cari+"%' ORDER BY id_ekskul asc";
        Connection connection = conn.getconnetion(); 
        Statement stat = connection.createStatement();
        ResultSet hasil = stat.executeQuery(sql);
        while (hasil.next()) {
            tabmode.addRow(new Object[]{
                nomor++, 
                hasil.getString("nama_ekskul"),
                hasil.getString("jam"),
                hasil.getString("hari")
            });
        }
        tb_eks.setModel(tabmode);
        
        //ukuran kolom abaikan dibawah ini
        TableColumn column = tb_eks.getColumnModel().getColumn(0);
        column.setPreferredWidth(30); 
        column.setMaxWidth(30);
        column.setMinWidth(10); 
        //akhir ukuran kolom
        
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Data gagal dipanggil: " + e);
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_ekskul = new javax.swing.JTextField();
        txt_jam = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_eks = new javax.swing.JTable();
        txt_cari = new javax.swing.JTextField();
        btn_cari = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnctk = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cb_hari = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(null);
        jPanel1.add(txt_ekskul);
        txt_ekskul.setBounds(160, 130, 174, 30);
        jPanel1.add(txt_jam);
        txt_jam.setBounds(160, 170, 174, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Nama Ekstrakulikuler");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(40, 130, 119, 15);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Jam Ekstrakulikuler");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(40, 170, 119, 15);

        tb_eks.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_eks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_eksMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_eks);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(20, 410, 452, 151);
        jPanel1.add(txt_cari);
        txt_cari.setBounds(40, 370, 174, 20);

        btn_cari.setText("Cari");
        jPanel1.add(btn_cari);
        btn_cari.setBounds(220, 370, 51, 23);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(60, 270, 90, 60);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/del.jpg"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(160, 270, 80, 60);

        btnctk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/print.jpg"))); // NOI18N
        btnctk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnctkActionPerformed(evt);
            }
        });
        jPanel1.add(btnctk);
        btnctk.setBounds(250, 270, 80, 60);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Hari Ekstrakulikuler");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(40, 210, 119, 15);

        cb_hari.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Hari", "Senin", "Selasa", "Rabu", "Kamis", "Jum'at" }));
        cb_hari.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_hariItemStateChanged(evt);
            }
        });
        jPanel1.add(cb_hari);
        cb_hari.setBounds(160, 210, 174, 30);

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pngwing.com (1).png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(-50, -20, 1140, 960);

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel5.setText("EKSTRAKULIKULER");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(210, 30, 220, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tb_eksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_eksMouseClicked
 int bar = tb_eks.getSelectedRow();
   
    
    String a = tabmode.getValueAt(bar,1).toString();
    String b = tabmode.getValueAt(bar,2).toString();
    String c = tabmode.getValueAt(bar,3).toString();
    
        
    txt_ekskul.setText(a);
    txt_jam.setText(b);
    cb_hari.setSelectedItem(c);
    
    txt_ekskul.setEnabled(false);
    txt_jam.setEnabled(false);
    cb_hari.setEnabled(false);
    }//GEN-LAST:event_tb_eksMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         
    String sql ="INSERT INTO tbl_ekskul (nama_ekskul, jam, hari) VALUES (?, ?, ?)";
    try {
        PreparedStatement stat = conn.getconnetion().prepareStatement(sql);
        stat.setString(1, txt_ekskul.getText());
        stat.setString(2, txt_jam.getText());
        stat.setString(3, pilihan);
        
        stat.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
        kosong();
        datatable();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Data gagal disimpan " + e);
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      int ok = JOptionPane.showConfirmDialog(null, "hapus", "Anda Yakin ingin menghapus data ?",JOptionPane.YES_NO_OPTION);
        if (ok==0) {
            String sql  ="DELETE FROM tbl_ekskul WHERE nama_ekskul ='"+txt_ekskul.getText()+"'";
            try {
                PreparedStatement stat = conn.getconnetion().prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data berhasil Hapus");
            kosong();
          
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Data gagal dihapus "+ e);
            }
            datatable();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnctkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnctkActionPerformed
try {
        String path = "./src/laporan/reportekskul.jasper";
        HashMap<String, Object> parameter = new HashMap<>();
        Connection conn = koneksi.getconnetion(); // Gunakan metode yang ada di kelas koneksi

        JasperPrint print = JasperFillManager.fillReport(path, parameter, conn);
        JasperViewer.viewReport(print, false);

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(rootPane, "Dokumen tidak ada: " + ex.getMessage());
    }
    }//GEN-LAST:event_btnctkActionPerformed

    private void cb_hariItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_hariItemStateChanged
     pilihan = cb_hari.getSelectedItem().toString();
    }//GEN-LAST:event_cb_hariItemStateChanged

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
            java.util.logging.Logger.getLogger(DataEkskul.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataEkskul.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataEkskul.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataEkskul.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataEkskul().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btn_cari;
    private javax.swing.JButton btnctk;
    private javax.swing.JComboBox<String> cb_hari;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_eks;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_ekskul;
    private javax.swing.JTextField txt_jam;
    // End of variables declaration//GEN-END:variables
}
