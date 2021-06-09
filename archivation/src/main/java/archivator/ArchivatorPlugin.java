package archivator;
import application.pluginsInterface.Archivator;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ArchivatorPlugin implements Archivator{
    @Override
    public void archivate(FileInputStream in, FileOutputStream out) {
        System.out.println("архив");
        try
        {
            ZipOutputStream zout = new ZipOutputStream(out);
            zout.putNextEntry(new ZipEntry("list"));

            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) > 0) {
                zout.write(buffer, 0, len);
            }
            zout.closeEntry();
        } catch (Exception e) {

        }
    }
}
