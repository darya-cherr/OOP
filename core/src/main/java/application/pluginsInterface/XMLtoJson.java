package application.pluginsInterface;

import java.io.*;
import java.util.Optional;
import java.util.ServiceLoader;

public interface XMLtoJson {
    void convert(FileInputStream in, FileOutputStream out) throws IOException;

    static Optional<XMLtoJson> getService(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, XMLtoJson.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .findFirst();
    }
}
