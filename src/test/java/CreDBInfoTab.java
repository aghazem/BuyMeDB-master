import java.sql.Connection;
import java.sql.SQLException;
public class CreDBInfoTab {
    private static final String USER_NAME = "JbfbjSYbiD";
    private static final String DATABASE_NAME = "JbfbjSYbiD";
//    private static final String PASSWORD = "x1Euka4HQK";
//    private static final String PORT = "3306";
//    private static final String SERVER = "remotemysql.com";
    private static Connection con;
    private static HandleDB handleDB;
    public static void main(String[] args) throws SQLException {
        con=handleDB.connectDB();
        dropOldTable1();
        createTable1(con);
        insertTable1();
        dropOldTable2();
        createTable2(con);
        dropOldTable3();
        createTable3(con);

        con.close();
    }

    /**********************************************************************\
     * Handle config table
    \**********************************************************************/
    public static void createTable1(Connection con) throws SQLException {
        String statementToExecute = "CREATE TABLE " + DATABASE_NAME + ".`config` (`config_id` INT NOT NULL,`config_name` VARCHAR(45) NOT NULL, " +
                "`config_data` VARCHAR(100) NOT NULL, PRIMARY KEY(`config_id`));";
        con.createStatement().execute(statementToExecute);
    }
    //Delete the old table. If does not exists continue, if other reason, stop the run
    public static void dropOldTable1() throws SQLException {

        String statementToExecute ="drop table "+DATABASE_NAME+".`config`;";
        try {
            con.createStatement().execute(statementToExecute);
        } catch (SQLException e)
        {     if(e.getMessage()=="42S02")
            return;
            // Print error
        }

    }
    public static void insertTable1() throws SQLException {
        boolean insertSW = false;
        try {
            String statementToExecute = "insert into " + DATABASE_NAME + ".`config` values ('1','URL','https://buyme.co.il/');";
            con.createStatement().execute(statementToExecute);
            statementToExecute = "insert into " + DATABASE_NAME + ".`config` values ('2','BROWSER','chrome');";
            con.createStatement().execute(statementToExecute);
            insertSW=true;
        }catch (SQLException e) {
            System.out.println("Insert failed becuase of " + e.getMessage());
        }
        finally {
            if(insertSW)
                System.out.println("Insert was O.K.");
        }
    }
    /**********************************************************************\
     * Handle results table
    \**********************************************************************/
    public static void createTable2(Connection con) throws SQLException {
        String statementToExecute = "CREATE TABLE " + DATABASE_NAME+".`results` (`test_id` INT NOT NULL,`test_date` VARCHAR(45) NOT NULL, " +
                "PRIMARY KEY(`test_id`));";
        con.createStatement().execute(statementToExecute);
    }

    //Delete the old table. If does not exists continue, if other reason, stop the run
    public static void dropOldTable2() throws SQLException {

        String statementToExecute ="drop table "+DATABASE_NAME+".`results`;";
        try {
            con.createStatement().execute(statementToExecute);
        } catch (SQLException e)
        {     if(e.getMessage()=="42S02")
            return;
            // Print error
        }

    }
    /**********************************************************************\
     * Handle results_detailed table
    \**********************************************************************/
    public static void createTable3(Connection con) throws SQLException {
        String statementToExecute = "CREATE TABLE " + DATABASE_NAME+".`results_detailed` (`test_id` INT NOT NULL,`test_date` VARCHAR(45) NOT NULL, " +
                "`test_result` VARCHAR(150) NOT NULL, PRIMARY KEY(`test_id`,`test_date`));";
        con.createStatement().execute(statementToExecute);
    }

    //Delete the old table. If does not exists continue, if other reason, stop the run
    public static void dropOldTable3() throws SQLException {

        String statementToExecute ="drop table "+DATABASE_NAME+".`results_detailed`;";
        try {
            con.createStatement().execute(statementToExecute);
        } catch (SQLException e)
        {     if(e.getMessage()=="42S02")
            return;
            // Print error
        }

    }

}
