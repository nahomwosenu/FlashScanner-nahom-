/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flashscanner.nahom;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import javax.swing.JFrame;

/**
 *
 * @author Nahom
 */
public class Main {

    private boolean unhide = true;
    private boolean delete = false;
    private boolean unlock = false;//sets read-only to read/write
    private String command = "attrib ";
    private String driveLetter;
    private int virusCount = 0;

    public boolean check(String drive) {

        File file = new File(drive + ":/");
        if (file.isDirectory()) {
            return true;
        } else {
            return false;
        }
    }

    public void setHidden(boolean hidden) {
        this.unhide = hidden;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public void setReadOnly(boolean lock) {
        this.unlock = !lock;
    }

    public void fix() {
        try {
            buildCommand();
            System.out.println(command+" fix");
            command += this.driveLetter + ":/";
            Process p = Runtime.getRuntime().exec(command);
            if (this.delete) {
                p = Runtime.getRuntime().exec("del " + this.driveLetter + ":/*.lnk");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fix(File file) {
        try {
            buildCommand();
            String cmd = new String("");
            
            if (file.isDirectory()) {
                cmd = command + file.getAbsolutePath() + "/*.*";
                System.out.println(cmd);
            }

            else {
                cmd = command + file.getAbsolutePath();
            }
            Process p = Runtime.getRuntime().exec(cmd);
            File subFile = null;
            
            if (this.delete) {
                if (file.isDirectory()) {
                    System.out.println("file is a directory");
                    File[] files=file.listFiles(new FilenameFilter(){
                        public boolean accept(File dir,String name){
                            return name.toLowerCase().endsWith(".lnk");
                        }
                    });
                    File[] files2=file.listFiles();
                    for(File f2:files2){
                        if(f2.isDirectory()){
                            File[] files3=file.listFiles(new FilenameFilter(){
                        public boolean accept(File dir,String name){
                            return name.toLowerCase().endsWith(".lnk");
                        }
                    });
                            for(File f:files3){
                        System.out.println("Deleting file: "+f.getName());
                        f.delete();
                        this.virusCount++;
                    }
                        }
                    }
                    for(File f:files){
                        System.out.println("Deleting file: "+f.getName());
                        f.delete();
                        this.virusCount++;
                    }
                    }
                

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] getListOfDrives() {
        char c = 'A';
        String[] list = new String[10];
        int i = 0;
        while (c <= 'K') {
            if (check(String.valueOf(c))) {
                System.out.println(c + " drive found");
                File file = new File(String.valueOf(c) + ":/");
                long size = file.getTotalSpace();
                size = size / (1000000 * 1000);
                System.out.println(size + " GB");
                if (size < 40) {
                    list[i] = String.valueOf(c);
                }
                i++;
            }
            c++;

        }
        return list;
    }

    private void buildCommand() {
        if (this.unhide) {
            command += "-h ";
        } else if (!this.unhide) {
            command += "+h ";
        }
        if (this.unlock) {
            command += "-s ";
        } else if (!this.unlock) {
            command += "+s ";
        }
    command+=" /s /d ";
    }

    public void setDrive(String drive) {
        this.driveLetter = drive;
    }

    public static void main(String args[]) {
        /*Main main=new Main();
         String[] drives=main.getListOfDrives();
         for(String s:drives){
             if(s!=null)
                 System.out.println("Flash drive found: "+s);
         }*/
        Gui gui = new Gui();
        gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        AutoLaunch launch=new AutoLaunch();
    }
}
