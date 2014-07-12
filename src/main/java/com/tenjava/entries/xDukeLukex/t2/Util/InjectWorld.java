package com.tenjava.entries.xDukeLukex.t2.Util;

import com.tenjava.entries.xDukeLukex.t2.TenJava;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipInputStream;

/**
 * Created by Luke on 12/07/2014.
 */
public class InjectWorld {
    public static boolean injectWorld() {

        try {
            File file = new File(TenJava.getInstance().getServer().getWorldContainer().toString().substring(0, TenJava.getInstance().getServer().getWorldContainer().toString().length() - 1) + File.separator + "world");
            file.setReadable(true);
            file.setWritable(true);
            if (file.exists() && !file.delete()) {
                return false;
            }

            FileInputStream jar = new FileInputStream(new File(TenJava.getInstance().getClass().getProtectionDomain().getCodeSource().getLocation().toURI()));
            ZipInputStream zip_inputstream = new ZipInputStream(jar);

            while ((zip_inputstream.getNextEntry()) != null) {
                FileOutputStream outputfile = new FileOutputStream(file);

                int data = 0;
                while ((data = zip_inputstream.read()) != -1) {
                    outputfile.write(data);
                }

                outputfile.close();
            }

            zip_inputstream.close();
            jar.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }
}
