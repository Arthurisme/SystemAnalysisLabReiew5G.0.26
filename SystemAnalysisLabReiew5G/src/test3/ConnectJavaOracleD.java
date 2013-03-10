//try 3 f
package test3;
//from m

import java.sql.*;

public class ConnectJavaOracleD
{

    public static void main(String[] args) throws ClassNotFoundException, SQLException 
    {
    	// Connect to oracle here:
    			//home:
    					  connecttoOracle("system", "liuliu");
    			//school:
    			//  connecttoOracle("scott", "tiger");
    }

    public static void connecttoOracle (String username, String password)
    {
        Connection c = null;

        try
        {
            System.out.println (" **************************************");
            System.out.println (" * CREATION OF TABLES FOR LAB REVIEW 5 * ");
            System.out.println (" **************************************");

            System.out.println (" * Loading the driver *");
            Class.forName ("oracle.jdbc.driver.OracleDriver");
                //  String url = "jdbc:oracle:thin:@ E10818:1521:orcl";
                  
               // home url:
                   String url = "jdbc:oracle:thin:@192.168.12.2:1521:XE";
            //or:
      			//String url = "jdbc:oracle:thin:@localhost:1521:XE";
      			// school url:
      			 //String url = "jdbc:oracle:thin:@localhost:1521:orcl";
      			
                  c = DriverManager.getConnection(url, username, password);

            c.setAutoCommit(true);

            Statement s = c.createStatement();
            System.out.println (" ******** CREATING FACULTY TABLE ********");
            String query = "CREATE TABLE faculty";
            query = query + "(f_id number (5), f_last VARCHAR2 (30), f_first VARCHAR2 (30), " +
                    "CONSTRAINT faculty_f_id_pk PRIMARY KEY (f_id))";

            s.executeUpdate(query);

            System.out.println (" ******** INSERTING INTO FACULTY TABLE ********");

            System.out.println (" ******** 1 - Robertson - Myra ********");
            query = "INSERT INTO faculty " ;
            query = query + "(f_id, f_last, f_first ) ";
            query = query + "values";
            query = query + "(1, 'Robertson', 'Myra') ";
            s.executeUpdate(query);
            c.commit ();
            c.setAutoCommit(true);
            c.close ();
            
            System.out.println (" ******** Success ********");
        }
        catch (Exception e)
        {
            try
            {
                c.rollback();
            }
            catch (Exception ee)
            {
                System.out.println ("Error !" );
            }
            System.out.println ("Error - Database Management for creating tables () : " + e);
        }

    }

}
