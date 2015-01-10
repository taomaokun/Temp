/**
 * 负责连接MySQl数据库的类
 */
package util.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Taomaokun
 * @date 2015-01-10
 *
 */
public class MyDBConnection {
	
	private static Connection dbConnect ;
	
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/study";
	
	private static final String DB_LOGINID = "root";
	
	private static final String DB_LOGINPSW = "123456";
	
	public static Connection createDBConnection(){
		
		try {
			Class.forName(DRIVER_NAME);
			dbConnect = DriverManager.getConnection(DB_URL, DB_LOGINID, DB_LOGINPSW);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dbConnect;
	}
	
	

}
