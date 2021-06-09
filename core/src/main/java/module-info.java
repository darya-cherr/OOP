import application.pluginsInterface.Archivator;
import application.pluginsInterface.XMLtoJson;

module core {
    requires java.desktop;


    exports application.pluginsInterface;
    exports application.pizza;
    exports application.serialization;

    uses Archivator;
    uses XMLtoJson;
}