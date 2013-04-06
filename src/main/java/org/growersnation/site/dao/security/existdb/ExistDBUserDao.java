package org.growersnation.site.dao.security.existdb;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.transform.OutputKeys;

/**
 * <p>DAO to provide the following to {@link org.growersnation.site.model.security.User}:</p>
 * <ul>
 * <li>Access to </li>
 * </ul>
 * <p>Example:</p>
 * <pre>
 * </pre>
 *
 * @since 0.0.1
 *         
 */
public class ExistDBUserDao {

  private static String URI = "xmldb:exist://ec2-46-137-56-2.eu-west-1.compute.amazonaws.com/exist/xmlrpc";
  /**
   * args[0] Should be the name of the collection to access
   * args[1] Should be the name of the resource to read from the collection
   */
  public static void main(String args[]) throws Exception {

    final String driver = "org.exist.xmldb.DatabaseImpl";

    // initialize database driver
    Class cl = Class.forName(driver);
    Database database = (Database) cl.newInstance();
    database.setProperty("create-database", "true");
    DatabaseManager.registerDatabase(database);

    Collection col = null;
    XMLResource res = null;
    try {
      // get the collection
      col = DatabaseManager.getCollection(URI + "/security");
      col.setProperty(OutputKeys.INDENT, "no");
      res = (XMLResource)col.getResource("example");

      if(res == null) {
        System.out.println("document not found!");
      } else {
        System.out.println(res.getContent());
      }
    } finally {
      //dont forget to clean up!

      if(res != null) {
        //try { ((EXistResource)res).freeResources(); } catch(XMLDBException xe) {xe.printStackTrace();}
      }

      if(col != null) {
        try { col.close(); } catch(XMLDBException xe) {xe.printStackTrace();}
      }
    }
  }
}
