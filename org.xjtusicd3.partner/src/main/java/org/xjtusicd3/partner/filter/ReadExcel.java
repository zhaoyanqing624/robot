package org.xjtusicd3.partner.filter;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.GeneraluserPersistence;
import org.xjtusicd3.database.model.UserPersistence;

/**
 * author:zhaoyanqing
 * abstract:读取EXCEL表格数据
 * data:2017年9月19日 14:31:13
 */
public class ReadExcel {
	public static void main(String[] args) throws Exception {
		List<List<String>> sEs = readXls("e:\\oa.xls");
		for(int i=0;i<sEs.size();i++){
			for(int j=0;j<sEs.get(i).size();j++){
				if (sEs.get(i).size()==3) {
					List<UserPersistence> userPersistences = UserHelper.getEmail_name(sEs.get(i).get(1).toString());
					if (userPersistences.size()==0) {
						String uuid = UUID.randomUUID().toString();
						String password = "670b14728ad9902aecba32e22fa4f6bd";
						String username = sEs.get(i).get(1).toString();
						String roleid = "168ab2db-ea09-44d9-bf50-af9879cb43cb";
						String userimage = "images/user.png";
						UserHelper.login_register2(uuid, password, username, 1, userimage, roleid);
						GeneraluserPersistence generaluserPersistence = new GeneraluserPersistence();
						generaluserPersistence.setUSERID(uuid);
						generaluserPersistence.setEMPLOYER("西安市区(分公司)");
						generaluserPersistence.setDEPARTMENT(sEs.get(i).get(0).toString());
						generaluserPersistence.setREALNAME(sEs.get(i).get(2).toString());
						UserHelper.addGeneralUser(generaluserPersistence);
					}
				}else if (sEs.get(i).size()==4) {
					List<UserPersistence> userPersistences = UserHelper.getEmail_name(sEs.get(i).get(2).toString());
					if (userPersistences.size()==0) {
						String uuid = UUID.randomUUID().toString();
						String password = "670b14728ad9902aecba32e22fa4f6bd";
						String username = sEs.get(i).get(2).toString();
						String roleid = "168ab2db-ea09-44d9-bf50-af9879cb43cb";
						String userimage = "images/user.png";
						UserHelper.login_register2(uuid, password, username, 1, userimage, roleid);
						GeneraluserPersistence generaluserPersistence = new GeneraluserPersistence();
						generaluserPersistence.setUSERID(uuid);
						generaluserPersistence.setEMPLOYER(sEs.get(i).get(0).toString());
						generaluserPersistence.setDEPARTMENT(sEs.get(i).get(1).toString());
						generaluserPersistence.setREALNAME(sEs.get(i).get(3).toString());
						UserHelper.addGeneralUser(generaluserPersistence);
					}
				}
			}
		}
		System.out.println(sEs.get(0).get(0).toString());
	}
	
	public static List<List<String>> readXls(String path) throws Exception{
		FileInputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		List<List<String>> result = new ArrayList<List<String>>();
		//循环每一页，读取每一页
		for(int numSheet=0;numSheet<hssfWorkbook.getNumberOfSheets();numSheet++){
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if(hssfSheet==null){
				continue;
			}
			//处理当前页面，读取每一行
			for(int rowNum=0;rowNum<=hssfSheet.getLastRowNum();rowNum++){
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				int minColIx = hssfRow.getFirstCellNum();
				int maxColIx = hssfRow.getLastCellNum();
				List<String> rowList = new ArrayList<String>();
				//遍历改行，获取每个元素
				for(int colIx = minColIx;colIx<maxColIx;colIx++){
					HSSFCell cell = hssfRow.getCell(colIx);
					if (cell==null) {
						continue;
					}
					rowList.add(cell.toString());
				}
				result.add(rowList);
			}
		}
		return result;
	}
}
