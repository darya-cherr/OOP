package application.pluginsInterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.util.Optional;
import java.util.ServiceLoader;

public interface Archivator {
    void archivate(FileInputStream in, FileOutputStream out);

    static Optional<Archivator> getService(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, Archivator.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .findFirst();
    }
}
