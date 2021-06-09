import application.pluginsInterface.Archivator;
import archivator.ArchivatorPlugin;

module archivation {
    requires core;
    provides Archivator with ArchivatorPlugin;
}