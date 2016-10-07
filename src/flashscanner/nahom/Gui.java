/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flashscanner.nahom;

/**
 *
 * @author Nahom
 */
import java.io.File;

import javax.swing.JFileChooser;
import java.awt.Color;
import javax.swing.JFrame;
import javafx.scene.media.AudioClip;


public class Gui extends javax.swing.JFrame {

    /**
     * Creates new form Gui
     */
    Main scan=new Main();
    private File file;
    private JFrame frame;
    
    public Gui() {
        initComponents();
        jLabel3.setVisible(false);
        Thread t=new Thread(backgroundChanger);
        t.start();
        this.setSize(420,330);
        frame=this;
        try{
          AudioClip clip2=new AudioClip(getClass().getResource("ChooseFlash.mp3").toURI().toURL().toString());
          clip2.play();
          }catch(Exception e){e.printStackTrace();}
    }
    public Gui(String driveName){
        initComponents();
        jLabel3.setVisible(false);
        Thread t=new Thread(backgroundChanger);
        t.start();
        this.setSize(420,330);
        frame=this;
        this.scan(driveName);
    }
    public void scan(String fileName){
     boolean unhide=this.unhideCheck.isSelected();
        boolean readOnly=this.readOnlyCheck.isSelected();
        boolean remove=this.deleteCheck.isSelected();
        String drive=(String)this.driveSelectBox.getSelectedItem();
        scan.setDelete(remove);
        scan.setHidden(unhide);
        scan.setReadOnly(readOnly);
        scan.setDrive(drive);
        file=new File(fileName);
        if(file!=null)
            scan.fix(file);
         this.progress.setMinimum(0);
         this.progress.setMaximum(100);
         this.progress.setStringPainted(true);
         Thread t=new Thread(r);
         t.start();
         try{
          AudioClip clip2=new AudioClip(getClass().getResource("ScanStarted.mp3").toURI().toURL().toString());
          clip2.play();
          }catch(Exception e){e.printStackTrace();}
    }
    Runnable backgroundChanger=new Runnable(){
            public void run(){
                while(true){
                    try{
                        float r=(float)Math.random();
                        float g=(float)Math.random();
                        float b=(float)Math.random();
                        Color color=new Color(r,g,b);
                        jPanel1.setBackground(color);
                        Thread.sleep(2000);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
         };
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        driveSelectBox = new javax.swing.JComboBox<>();
        fileSelectButton = new javax.swing.JButton();
        scanButton = new javax.swing.JButton();
        progress = new javax.swing.JProgressBar();
        status = new javax.swing.JLabel();
        unhideCheck = new javax.swing.JCheckBox();
        readOnlyCheck = new javax.swing.JCheckBox();
        deleteCheck = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        javax.swing.ImageIcon icon=new javax.swing.ImageIcon(getClass().getResource("/flashscanner/nahom/about.png"));
        int height=icon.getIconHeight();
        int width=icon.getIconWidth();
        jLabel3.setIcon(icon);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3);
        jLabel3.setBounds(-6, -6, 410, 310);
        jLabel3.setSize(width,height);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Select drive or file to scan");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(51, 74, 252, 36);

        String []driveList=scan.getListOfDrives();
        for(String s:driveList){
            if(s!=null)
            driveSelectBox.addItem(s);
        }
        jPanel1.add(driveSelectBox);
        driveSelectBox.setBounds(51, 116, 186, 29);

        fileSelectButton.setText("Browse");
        fileSelectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileSelectButtonActionPerformed(evt);
            }
        });
        jPanel1.add(fileSelectButton);
        fileSelectButton.setBounds(247, 116, 92, 29);

        scanButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        scanButton.setForeground(new java.awt.Color(255, 153, 0));
        scanButton.setText("Scan & Fix");
        scanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scanButtonActionPerformed(evt);
            }
        });
        jPanel1.add(scanButton);
        scanButton.setBounds(109, 196, 160, 44);
        jPanel1.add(progress);
        progress.setBounds(81, 246, 283, 14);

        status.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        status.setText("Now:");
        jPanel1.add(status);
        status.setBounds(76, 271, 288, 18);

        unhideCheck.setSelected(true);
        unhideCheck.setText("Unhide");
        jPanel1.add(unhideCheck);
        unhideCheck.setBounds(51, 155, 59, 23);

        readOnlyCheck.setText("Read Only");
        jPanel1.add(readOnlyCheck);
        readOnlyCheck.setBounds(139, 155, 75, 23);

        deleteCheck.setSelected(true);
        deleteCheck.setText("Remove Trojans");
        jPanel1.add(deleteCheck);
        deleteCheck.setBounds(232, 155, 108, 23);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/flashscanner/nahom/antivirus.png"))); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(51, 11, 313, 57);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fileSelectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileSelectButtonActionPerformed
        JFileChooser chooser=new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.showOpenDialog(jPanel1);
          
        file=chooser.getSelectedFile();
        System.out.println("File Selected: "+file.getAbsolutePath());
        
    }//GEN-LAST:event_fileSelectButtonActionPerformed

    private void scanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scanButtonActionPerformed
        boolean unhide=this.unhideCheck.isSelected();
        boolean readOnly=this.readOnlyCheck.isSelected();
        boolean remove=this.deleteCheck.isSelected();
        String drive=(String)this.driveSelectBox.getSelectedItem();
        scan.setDelete(remove);
        scan.setHidden(unhide);
        scan.setReadOnly(readOnly);
        scan.setDrive(drive);
        if(file!=null)
            scan.fix(file);
        else 
            scan.fix(new File(drive+":/"));
         this.progress.setMinimum(0);
         this.progress.setMaximum(100);
         this.progress.setStringPainted(true);
         Thread t=new Thread(r);
         t.start();
         try{
          AudioClip clip=new AudioClip(getClass().getResource("ScanStarted.mp3").toURI().toURL().toString());
          clip.play();
          }catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_scanButtonActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
       jLabel3.setVisible(false);
    }//GEN-LAST:event_jLabel3MouseClicked
    Runnable r=new Runnable(){
      public void run(){
          status.setText("Now: Scanning");
          scanButton.setText("Scanning");
          scanButton.setEnabled(false);
          while(progress.getValue()<100){
              try{
                  Thread.sleep(1000);
                  progress.setValue(progress.getValue()+5);
                  
              }catch(Exception e){
                  e.printStackTrace();
              }
              
          }
          
          status.setText("Completed! Fixed :)");
          scanButton.setEnabled(true);
          scanButton.setText("Fixed");
          jLabel3.setVisible(true);
          try{
          AudioClip clip2=new AudioClip(getClass().getResource("ScanCompleted.mp3").toURI().toURL().toString());
          clip2.play();
          frame.setSize(jLabel3.getWidth(),jLabel3.getHeight());
          Thread.sleep(5000);
          AudioClip clip=new AudioClip(getClass().getResource("Maintainance.mp3").toURI().toURL().toString());
          clip.play();
          }catch(Exception e){e.printStackTrace();}
          
          
          
      }  
    };
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
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox deleteCheck;
    private javax.swing.JComboBox<String> driveSelectBox;
    private javax.swing.JButton fileSelectButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar progress;
    private javax.swing.JCheckBox readOnlyCheck;
    private javax.swing.JButton scanButton;
    private javax.swing.JLabel status;
    private javax.swing.JCheckBox unhideCheck;
    // End of variables declaration//GEN-END:variables
}