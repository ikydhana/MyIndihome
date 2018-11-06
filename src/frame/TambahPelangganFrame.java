
package frame;
import com.mysql.jdbc.PreparedStatement;
import db.Koneksi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import model.Pelanggan;


public class TambahPelangganFrame extends JFrame{
    
    int status;
    
    private final int SEDANG_TAMBAH = 101;
    private final int SEDANG_UBAH = 102;
    
    JLabel jLabel1 = new JLabel("Id");
    JLabel jLabel2 = new JLabel("Nama Pelanggan");
    JLabel jLabel3 = new JLabel("Alamat Email");
    JLabel jLabel4 = new JLabel("No Aktif");
    JLabel jLabel5 = new JLabel("No Alternatif");
    JLabel jLabel6 = new JLabel("Paket");
    
    JTextField eid_pelanggan = new JTextField();
    JTextField enama_pelanggan = new JTextField();
    JTextField ealamat_email = new JTextField();
    JTextField eno_aktif = new JTextField();
    JTextField eno_alternatif = new JTextField();
        
     String[] jp={"Pilih","2P Useetv+Internet Bundling",
         "2P Telepon+Internet Bundling","3P Useetv+Telepon+Internet Bundling"};
     JComboBox cbjp=new JComboBox(jp);
 
    JButton bSimpan = new JButton("Save");
    JButton bBatal = new JButton ("Cancel");
    
    public void setKomponen (){
    getContentPane().setLayout(null);
    getContentPane().add (jLabel1);
    getContentPane().add (jLabel2);
    getContentPane().add (jLabel3);
    getContentPane().add (jLabel4);
    getContentPane().add (jLabel5);
    getContentPane().add (jLabel6);
    getContentPane().add (eid_pelanggan);
    getContentPane().add (enama_pelanggan);
    getContentPane().add (ealamat_email);
    getContentPane().add (eno_aktif);
    getContentPane().add (eno_alternatif);
    getContentPane().add (bSimpan);
    getContentPane().add (bBatal);
    
    jLabel1.setBounds(30,10,50,25);
    jLabel2.setBounds(30,40,50,25);
    jLabel3.setBounds(30,70,110,25);
    jLabel4.setBounds(30,100,110,25);
    jLabel5.setBounds(30,130,110,25);
    
    eid_pelanggan.setBounds(160,10,50,25);
    enama_pelanggan.setBounds(160,40,270,25);
    ealamat_email.setBounds(160,70,270,25);
    eno_aktif.setBounds(160,100,270,25);
    eno_alternatif.setBounds(160,130,270,25);
    cbjp.setBounds(160,170,100,20);
    getContentPane().add(cbjp);
    cbjp.setBounds(160,170,100,20);
    
    
    bSimpan.setBounds(160,260,100,25);
    bBatal.setBounds(270,260,100,25);
    
    eid_pelanggan.setEditable(false);
    setVisible(true);
    enama_pelanggan.requestFocus();
    setListener();







}

    public TambahPelangganFrame() {
        status = SEDANG_TAMBAH;
    setSize (800,400);
    setLocationRelativeTo (null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setKomponen();
    }
    public TambahPelangganFrame(Pelanggan pelanggan) {
    status = SEDANG_UBAH;
    setSize (800,400);
    setLocationRelativeTo (null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    eid_pelanggan.setText(String.valueOf(pelanggan.getId_pelanggan()));
    enama_pelanggan.setText(pelanggan.getNama_pelanggan());
    ealamat_email.setText(pelanggan.getAlamat_email());
    eno_aktif.setText(pelanggan.getNo_aktif());
    eno_alternatif.setText(pelanggan.getNo_alternatif());
    setKomponen();
    
    }
    public void setListener (){
        bBatal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            
            }
        });
    
    bSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             
                
                try{
                    Koneksi koneksi = new Koneksi();
                    Connection con = koneksi.getConnection();
                    PreparedStatement ps;
                    if (status== SEDANG_TAMBAH){
                        String executeQuery = "insert into tb_pelanggan "+
                                "(nama_pelanggan,paket,alamat_email,no_aktif,no_alternatif) value (?,?,?,?,?)";
                        ps = (PreparedStatement) con.prepareStatement(executeQuery);
                        ps.setString(1, enama_pelanggan.getText());
                        ps.setString(2, (String) cbjp.getSelectedItem());
                        ps.setString(3, ealamat_email.getText());
                        ps.setString(4, eno_aktif.getText());
                        ps.setString(5, eno_alternatif.getText());
                        }
                    else {
                        String executeQuery = "update * from tb_pelanggan set"
                                +"nama_pelanggan=?,no_aktif=?,no_alternatif=? where nama_pelanggan=?";
                        ps= (PreparedStatement) con.prepareStatement(executeQuery);
                       
                        ps.setString(1, enama_pelanggan.getText());
                        ps.setString(2, eno_aktif.getText());
                        ps.setString(3, eno_alternatif.getText());
                       
                        
                        
                    }
                ps.executeUpdate();
                }
                catch (SQLException ex){
                System.err.println(ex);
            }
            dispose();
            }
    });
    }

}