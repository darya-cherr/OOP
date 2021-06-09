import application.pluginsInterface.XMLtoJson;
import converter.ConvertToJson;

module XMLtoJSON {
    requires core;
    requires org.json;

    provides XMLtoJson with ConvertToJson;
}