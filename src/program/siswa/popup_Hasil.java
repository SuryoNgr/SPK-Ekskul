
package program.siswa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import program.admin.MenuUtama;
import program.admin.ProsesData;
import program.admin.koneksi;


public class popup_Hasil extends javax.swing.JFrame {
private koneksi conn = new koneksi();
DefaultTableModel tabelPeringkat;
halSiswa phsl;


    public popup_Hasil(halSiswa phsl) {
        this.phsl = phsl;
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        tabelmodelPeringkat();
        kesimpulan_akhir();
    }

    private popup_Hasil() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
   public void tabelmodelPeringkat() {
    tabelPeringkat = new DefaultTableModel();
    tabelPeringkat.addColumn("Nama");
    tabelPeringkat.addColumn("Nama Ekskul");
    tabelPeringkat.addColumn("Nilai");
    tabelPeringkat.addColumn("Peringkat");
    
    tabel_peringkat.setModel(tabelPeringkat);
    
    try {
        String namaLogin = phsl.getNamaLogin(); 
        String sql = "SELECT * FROM tbl_peringkat WHERE nama = ? ORDER BY nilai DESC";
        PreparedStatement stat = conn.getconnetion().prepareStatement(sql);
        stat.setString(1, namaLogin);
        ResultSet res = stat.executeQuery();
        
        int peringkat = 1;
        while (res.next()) {
            tabelPeringkat.addRow(new Object[]{
                res.getString("nama"), 
                res.getString("nama_ekskul"), 
                res.getFloat("nilai"), 
                peringkat
            });
            peringkat++;
        }
    } catch (SQLException ex) {
        Logger.getLogger(popup_Hasil.class.getName()).log(Level.SEVERE, null, ex);
    }
}
public void kesimpulan_akhir(){
        try {
                    String namaLogin = phsl.getNamaLogin(); 

            String sql = "SELECT nama_ekskul FROM tbl_peringkat WHERE nama = ? ORDER BY nilai ASC LIMIT 1";
            PreparedStatement stat = conn.getconnetion().prepareStatement(sql);
            stat.setString(1, namaLogin);
            ResultSet res = stat.executeQuery();
            
            if (res.next()) {
                kata.setText("Ekskul paling cocok adalah:");
                nama.setText(res.getString("nama_ekskul"));
            } else {
               kata.setText("Ekskul paling cocok adalah:");
               nama.setText(" Anda belum isi ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }





    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_peringkat = new javax.swing.JTable();
        kata = new javax.swing.JLabel();
        nama = new javax.swing.JLabel();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabel_peringkat.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabel_peringkat);

        kata.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N

        nama.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(kata, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(117, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kata, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(popup_Hasil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(popup_Hasil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(popup_Hasil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(popup_Hasil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new popup_Hasil().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel kata;
    private javax.swing.JLabel nama;
    private javax.swing.JTable tabel_peringkat;
    // End of variables declaration//GEN-END:variables
}
