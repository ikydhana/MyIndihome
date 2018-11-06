
package frame;
import db.Koneksi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Pelanggan;


public class PelangganFrame extends JFrame{
    JLabel jLabel1 =new JLabel("Cari");
    JTextField eCari =new JTextField();
    JButton bCari =new JButton("Cari");
    
    String header []={"Id","Nama Pelanggan","Jenis Paket","Alamat Email","No Aktif","No Alternatif"};
    TableModel tableModel = new DefaultTableModel(header, 0);
    JTable tPelanggan = new JTable(tableModel);
    JScrollPane jScrollPane = new JScrollPane(tPelanggan);
    
    JButton bTambah =new JButton("Tambah");
    JButton bUbah =new JButton("Ubah");
    JButton bHapus =new JButton("Hapus");
    JButton bBatal =new JButton("Batal");
    JButton bKeluar =new JButton("Keluar");
 
 
 Pelanggan pelanggan;
    

    public void setKomponen () {

getContentPane().setLayout(null);
getContentPane().add (jLabel1);
getContentPane().add (eCari);
getContentPane().add (jScrollPane);
getContentPane().add (bCari);
getContentPane().add (bTambah);
getContentPane().add (bUbah);
getContentPane().add (bHapus);
getContentPane().add (bBatal);
getContentPane().add (bKeluar);

jLabel1.setBounds(10,10,100,25);
eCari.setBounds(60,10,300,25);
bCari.setBounds(400,10,70,25);
bKeluar.setBounds(500,220,70,25);
bTambah.setBounds(10,220,80,25);
bUbah.setBounds(100,220,80,25);
bHapus.setBounds(195,220,80,25);
bBatal.setBounds(295,220,80,25);
jScrollPane.setBounds(10,45,760,160);
resetTable("");
setListener();
setVisible (true);

}
    public PelangganFrame () {
    setSize (800,500);
    setLocationRelativeTo (null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setKomponen();


}
    public static void main(String[] args) {
        PelangganFrame pelangganFrame =new PelangganFrame();
    
    }
    
     public ArrayList<Pelanggan> getPelangganList (String keyword) {
ArrayList<Pelanggan> pelangganList =new ArrayList<>();
    Koneksi koneksi =new Koneksi();
    Connection connection = koneksi.getConnection();
    
    String query ="SELECT * FROM tb_pelanggan  "+keyword;
    Statement statement;
    ResultSet resultSet;
    
    try {
    statement = connection.createStatement();
     resultSet = statement.executeQuery(query);
    while (resultSet.next()){
            pelanggan = new Pelanggan (resultSet.getInt("id_pelanggan"),
                    resultSet.getString("nama_pelanggan"),resultSet.getString("paket"),resultSet.getString("alamat_email")
            ,resultSet.getString("no_aktif"),resultSet.getString("no_alternatif"));
            
            pelangganList.add(pelanggan);
    }
    
    }
    catch (SQLException | NullPointerException ex){
        System.err.println("Koneksi Null Gagal : "+ex);
    
    }
return pelangganList;

}
     
     public  final  void selectPelanggan (String keyword)  {
        ArrayList<Pelanggan> list = getPelangganList(keyword);
        DefaultTableModel model = (DefaultTableModel)tPelanggan.getModel();
        Object [] row = new Object[6];
        
        for (int i = 0; i < list.size();i++){
            row [0] = list.get(i).getId_pelanggan();
            row [1] = list.get(i).getNama_pelanggan();
            row [2] = list.get(i).getPaket();
            row [3] = list.get(i).getAlamat_email();
            row [4] = list.get(i).getNo_aktif();
            row [5] = list.get(i).getNo_alternatif();
            
            model.addRow(row);
        }
    }
     public  final void resetTable(String keyword) 
    {
        DefaultTableModel model = (DefaultTableModel)tPelanggan.getModel();
        model.setRowCount(0);
        selectPelanggan(keyword);
    
    }
     public  void setListener (){
        bKeluar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose () ;
            }
        });
        bCari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetTable(" WHERE nama_pelanggan like '% "+eCari.getText()+"%'");
            }
        });
    bBatal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetTable("");
            }
        });
    bHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = tPelanggan.getSelectedRow();
                int pilihan = JOptionPane.showConfirmDialog(null, 
                        "Yakin ingin Menghapus   ?",
                        "Konfirmasi Hapus ",
                        JOptionPane.YES_NO_OPTION);
              if (pilihan==0){
                  if(i==0){
                      try {
                          TableModel model =tPelanggan.getModel();
                          Koneksi koneksi = new Koneksi();
                          Connection con = koneksi.getConnection();
                          String executeQuery = "delete from tb_pelanggan where id_pelanggan = ?";
                          PreparedStatement ps = con.prepareStatement(executeQuery);
                          ps.setString (1, model.getValueAt(i, 1).toString());
                          ps.executeUpdate();
                      
                      }
                  catch (SQLException ex){
                      System.err.println(ex);
                  
                  }
                  } else {
                      JOptionPane.showMessageDialog(null, "Pilih Data yang ingin dihapus ");
                  
                  }
              
              }
                resetTable("");
            }
        });
    bTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TambahPelangganFrame pelangganTambahFrame = new TambahPelangganFrame();
            }
        });
    addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowActivated (java.awt.event.WindowEvent evt){
                    resetTable("");
                    
                }
        
        
        });
        bUbah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = tPelanggan.getSelectedRow();
                if (i>=0){
                    TableModel model = tPelanggan.getModel();
                    pelanggan = new Pelanggan();
                    pelanggan.setId_pelanggan(Integer.parseInt(model.getValueAt(i,0).toString() ));
                    pelanggan.setNama_pelanggan(model.getValueAt(i, 1).toString());
                    pelanggan.setPaket(model.getValueAt(i, 3).toString());
                    pelanggan.setAlamat_email(model.getValueAt(i, 4).toString());
                    pelanggan.setNo_aktif(model.getValueAt(i, 5).toString());
                    pelanggan.setNo_alternatif(model.getValueAt(i, 6).toString());
                    
                    
                    TambahPelangganFrame tambahPelangganFrame = new TambahPelangganFrame(pelanggan);
                     tambahPelangganFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih Data yang ingin Dirubah");
                
                }
            }
        });
    
    
     }
     


}