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
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.io.File;
import java.util.Formatter;

import javafx.scene.media.AudioClip;

import java.awt.PopupMenu;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import java.awt.MenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.MenuShortcut;


public class AutoLaunch {
    private long size;
    private String driveName;
    private boolean end=false;
    private Formatter fileFormatter;
    private AudioClip audioClip;
    private PopupMenu menu;
    
    public AutoLaunch(){
        try{
        Thread checker=new Thread(run);
        checker.start();
        menu=new PopupMenu();
        MenuItem item2=new MenuItem();
        MenuItem item3=new MenuItem();
        item3.setLabel("Exit");
        item3.addActionListener(
         new ActionListener(){
             public void actionPerformed(ActionEvent event){
                 System.exit(0);
             }
         }
        );
        item2.setLabel("Open");
        item2.addActionListener(
             new ActionListener(){
                 public void actionPerformed(ActionEvent evt){
                     Gui gui=new Gui();
                       gui.setVisible(true);
                       gui.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
                 }
             }
        );
        MenuItem item1=new MenuItem();
        item1.setLabel("About");
        item1.addActionListener(
           new ActionListener(){
               public void actionPerformed(ActionEvent event){
                   JFrame frame=new JFrame();
                   JLabel label=new JLabel();
                   Icon icon=new ImageIcon(getClass().getResource("/flashscanner/nahom/about.png"));
                   int width=icon.getIconWidth();
                   int height=icon.getIconHeight();
                   label.setIcon(icon);
                   frame.add(label);
                   
                   frame.setSize(width,height);
                   frame.setVisible(true);
               }
           }
        );
        menu.add(item2);
        menu.add(item1);
        menu.add(item3);
        menu.setEnabled(true);
        menu.setShortcut(new MenuShortcut(KeyEvent.VK_N));
        ImageIcon image=new ImageIcon(getClass().getResource("/flashscanner/nahom/logo.png"));
        SystemTray tray=SystemTray.getSystemTray();
        
        TrayIcon icon=new TrayIcon(image.getImage(),"Nahom Antivirus Solution");
        icon.setImageAutoSize(true);
        icon.setPopupMenu(menu);
        icon.getPopupMenu().setEnabled(true);
        System.out.println(image.getIconWidth()+"  H= "+image.getIconHeight());
        icon.addActionListener(
               new ActionListener(){
                   public void actionPerformed(ActionEvent e){
                       Gui gui=new Gui();
                       gui.setVisible(true);
                       gui.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
                   }
               }
        );
        
        icon.addMouseListener(
              new MouseListener(){
                  public void mouseClicked(MouseEvent evt){
                       
                  }
                  public void mouseExited(MouseEvent evt){}
                  public void mouseEntered(MouseEvent evt){}
                  public void mouseReleased(MouseEvent evt){}
                  public void mousePressed(MouseEvent evt){}
              }
        );
        tray.add(icon);
        
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    Runnable run=new Runnable(){
        public void run(){
            while(true){
                try{
                    
                        
                    char c='A';
                    while(c<='K'){
                        Thread.sleep(1500);
                        System.out.println("Checking in background ...");
                        String name=String.valueOf(c);
                        File file=new File(name+":/");
                        if(file.isDirectory()){
                            size=file.getTotalSpace()/(1000000*1000);
                            System.out.println("Drive: "+name+" size: "+size+" GB  found");
                            if(size>=0 && size<40){
                                driveName=name+":/";
                                String files[]=file.list();
                                for(String names:files)
                                  if(names.equalsIgnoreCase("NahomAntivirusSolution.txt"))
                                    end=true;
                                if(!end){
                                    //launching Scanner program
                                    //writing NahomAntivirusSolution.txt file to the drive
                                    fileFormatter=new Formatter(name+":/NahomAntivirusSolution.txt");
                                    fileFormatter.format("%s","Call me: 0965290133 \n Email: nahometete@gmail.com \n Arbaminch University Main Campus");
                                    fileFormatter.close();
                                    
                                    //Launching program
                                    AudioClip clip2=new AudioClip(getClass().getResource("VirusDetected.mp3").toURI().toURL().toString());
                                    clip2.play();
                                    Gui scan=new Gui(driveName);
                                    scan.setVisible(true);
                                    scan.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
                                    //Slowing down the background thread
                                }
                            
                            }
                        }
                        c++;
                        end=false;
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    };
}
