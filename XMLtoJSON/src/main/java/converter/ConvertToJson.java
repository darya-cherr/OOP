package converter;

import application.pluginsInterface.XMLtoJson;

import java.io.*;

import org.json.XML;

public class ConvertToJson implements XMLtoJson {

    @Override
    public void convert(FileInputStream in, FileOutputStream out) throws IOException {
        System.out.println("конвертация");
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) > 0) {
            result.write(buffer, 0, len);
        }
        String res = XML.toJSONObject(result.toString()).toString();
        out.write(res.getBytes());
    }
}
