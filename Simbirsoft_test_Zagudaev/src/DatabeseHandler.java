import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabeseHandler extends Config {
    Connection dbConnection;

    public Connection getDbConnection() throws  SQLException, ClassNotFoundException {
        String connectionString = "jdbc:postgresql://"+dbHost+":"+dbPort+"/"+dbName;
        Class.forName("org.postgresql.Driver");

        dbConnection = DriverManager.getConnection(connectionString,dbUser,dbPass);
        return dbConnection;
    }
    public void AddWord(String word,int sum) throws SQLException, ClassNotFoundException {
        String  insert = "INSERT INTO " + Const.WORD_TABLE+ "(" + Const.WORDS + "," + Const.SUM + ")" + "VALUES(?,?)";

        PreparedStatement prST = getDbConnection().prepareStatement(insert);
        prST.setString(1, word);
        prST.setInt(2, sum);
        prST.executeUpdate();
        dbConnection.close();

    }
}
