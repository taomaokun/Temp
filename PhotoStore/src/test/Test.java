/**
 * 测试类
 */
package test;

import java.io.File;

import javacode.GetPhotoFromDB;
import javacode.UploadPhotoToDB;

/**
 * @author Taomaokun
 *
 */
public class Test {

	public static void main(String[] args) {
		
		UploadPhotoToDB up = new UploadPhotoToDB();
		GetPhotoFromDB get = new GetPhotoFromDB();
		
		String photo = "天地之间.jpg";
		
		if(up.insertIntoDB(new File("D:/Temp Files/"+photo))){
			System.out.println("Upload to DB : Success!");
		}
		
		if(get.createPhotoFromDB(1, photo)){
			System.out.println("Download From DB : Success!");
		}
	}
}
