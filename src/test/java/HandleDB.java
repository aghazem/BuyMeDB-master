import java.sql.*;
import java.text.SimpleDateFormat;

public class HandleDB {
    private static final String USER_NAME = "JbfbjSYbiD";
    private static final String DATABASE_NAME = "JbfbjSYbiD";
    private static final String PASSWORD = "x1Euka4HQK";
    private static final String PORT = "3306";
    private static final String SERVER = "remotemysql.com";
    public static String errorMsg = null;

//    public static void main(String[] args) throws SQLException {
//
//       Connection con=connectDB();
////       System.out.println("URL is: " +getConfig(con,"URL"));
////       System.out.println("Browser is: " +getConfig(con,"Browser"));
////       System.out.println("Error is: " +getConfig(con,"error"));
//
//        //insertResults(con,"AA");
//        String a = insertResults(con);
//
//        con.close();
//
//    }


    public static Connection connectDB() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306", USER_NAME, PASSWORD);
    }
    // Select SQL query from CONFIG table.

    /****************************************************************************\
     *   Select from config table
     * @param con
     * @param config_name
     * @return the config data
     * @throws SQLException
     \***************************************************************************/
    public static String  getConfig(Connection con,String config_name) throws SQLException {

        String selectedfld =null;
        String selectReq ="config_data";
        String query = "SELECT * FROM " + DATABASE_NAME + ".config where config_name ='" + config_name + "' ;";

        return  (generalSelect(con,query,selectReq));
    }

    /*********************************************************************\
    |*   Insert 1 row to result table
    \*********************************************************************/
    public static String  insertResults(Connection con,String dateTime) throws SQLException {
        boolean insertSW = false;
        String selectedfld =null;
        String selectReq ="max(test_id)";
        String query = "SELECT max(test_id) FROM " + DATABASE_NAME + ".results;";

        // getting the last test_id from the table and increasing it by 1
        int curId=incTestId(generalSelect(con,query,selectReq));

        try {
            String statementToExecute = "INSERT INTO " +DATABASE_NAME+ ".results (`test_id`, `test_date`) VALUES ('" + curId + "', '" + dateTime + "');";
            con.createStatement().execute(statementToExecute);
        }catch (SQLException e)
        {    System.out.println("Insert error is: " +e.getMessage());
            selectedfld = null;
            errorMsg=e.getMessage();
        }
        return errorMsg;

    }
    /*********************************************************************\
    |*   Getting the current results_detailed table's ID
    \*********************************************************************/
    public static int  getRsltDtldID(Connection con) throws SQLException {
        String selectReq ="max(test_id)";
        String query = "SELECT max(test_id) FROM " + DATABASE_NAME + ".results_detailed;";

        // getting the last test_id from the table and increasing it by 1
        return (incTestId(generalSelect(con,query,selectReq)));
    }
    /*********************************************************************\
    |*   Insert 1 row to results_detailed table
    \*********************************************************************/
    public static String  insertResDetaild(Connection con,int curId,String test_result) throws SQLException {
        int strLen =150;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        String dateTime = formatter.format(System.currentTimeMillis());

        if(test_result.length()<strLen)
            strLen=test_result.length();

        String insertSTR2 = test_result.substring(0,strLen);
        try {
            String statementToExecute = "INSERT INTO " +DATABASE_NAME+ ".results_detailed (`test_id`, `test_date`,`test_result`) " +
                    "VALUES ('" + curId + "', '" + dateTime + "', '" +insertSTR2+ "');";
            con.createStatement().execute(statementToExecute);
        }catch (SQLException e)
        {    System.out.println("Insert error is: " +e.getMessage());
            errorMsg=e.getMessage();
        }
        return errorMsg;

    }
    /************************************************************\
    |* Increasing the test_id
    \************************************************************/
     public static int incTestId(String curTestId){
         if(curTestId==null)
             return 1 ;
         else
         {   int tmpNum = Integer.valueOf(curTestId);
             tmpNum++;
             return tmpNum;
         }
     }
    /***********************************************************************\
     *  Will perform 1 select
     * @param con
     * @param query
     * @param selectReq
     * @return what you asked for....
     * @throws SQLException
     \**********************************************************************/
    public static String  generalSelect(Connection con,String query,String selectReq) throws SQLException {
        String selectedfld=null;
        boolean selectSW = false;
        try {
            String statementToExecute = query;
            Statement stmt = con.createStatement();
            // Execute the select
            ResultSet data = stmt.executeQuery(statementToExecute);
            data.next();
            selectedfld  = data.getString(selectReq);

        }catch(SQLException e)
        {
            System.out.println("Select error is: " +e.getMessage());
            //selectedfld = e.getMessage();
            selectedfld = null;
            errorMsg=e.getMessage();
        }
        return  selectedfld;
    }
}