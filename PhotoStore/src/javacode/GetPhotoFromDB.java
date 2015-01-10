/**
 * 从数据库读取图片保存到本地硬盘
 */
package javacode;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.db.MyDBConnection;

/**
 * @author Taomaokun
 * @date 2015-01-10
 *
 */
public class GetPhotoFromDB {
	
	private static Connection dbConnect = MyDBConnection.createDBConnection();
	
	String SELECT_SQL = "select * from phototest where id = ? "; 
	
	public boolean createPhotoFromDB(int photoID , String newPhotoName){
		
		try {
			
			PreparedStatement ps = dbConnect.prepareStatement(SELECT_SQL);
			ps.setInt(1, photoID);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			InputStream in = rs.getBinaryStream("photo");
			
			try {
				
				byte[] b = new byte[in.available()];
				
				in.read(b);
				
				OutputStream out = new FileOutputStream("Copy_" + newPhotoName);
				
				out.write(b);
				out.flush();
				
				out.close();
				
				dbConnect.close();
				
				return true;
				
			} catch (IOException e) {
				
				System.err.println("Throw an IOException if this input stream has been closed by invoking the close() method.");
				e.printStackTrace();
				
				return false;
			}
			
			
			
		} catch (SQLException e) {
			
			System.err.println("SQL语法有误~");
			e.printStackTrace();
			
			return false;
		}
		
	}

}
