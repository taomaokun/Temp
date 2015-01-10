/**
 * 把本地图片存储到数据库
 */
package javacode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.db.MyDBConnection;

/**
 * @author Taomaokun
 * @date 2015-01-10
 *
 */
public class UploadPhotoToDB {
	
	private static Connection dbConnect = MyDBConnection.createDBConnection();
	
	String INSERT_SQL = "insert into phototest values ( ? , ? )"; 
	
	public boolean insertIntoDB(File photoFile){
		
		try {
			
			InputStream in = new FileInputStream(photoFile);
			
			try {
				
				PreparedStatement ps = dbConnect.prepareStatement(INSERT_SQL);
				ps.setInt(1, 1);
				
				try {
					
					ps.setBinaryStream(2, in, in.available());
					
					ps.executeUpdate();
					
					in.close();
					dbConnect.close();
					
					return true;
					
				} catch (IOException e) {
					
					System.err.println("Throw an IOException if this input stream has been closed by invoking the close() method. ");
					e.printStackTrace();
					
					return false;
				}
				
				
			} catch (SQLException e) {
				
				System.err.println("SQL语法有误~");
				e.printStackTrace();
				
				return false;
			}
			
		} catch (FileNotFoundException e) {
			
			System.err.println("图片路径有误~");
			e.printStackTrace();
			
			return false;
		}
	}
	

}
