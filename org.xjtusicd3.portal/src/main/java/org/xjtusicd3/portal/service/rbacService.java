package org.xjtusicd3.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.xjtusicd3.database.helper.RoleHelper;
import org.xjtusicd3.database.helper.RolePermissionHelper;
import org.xjtusicd3.database.model.PermissionPersistence;
import org.xjtusicd3.database.model.RolePermissionPersistence;
import org.xjtusicd3.portal.view.rolePerView;

public class rbacService
{
	/*
	 * zpz_rbacindex展示
	 */
	public static List<rolePerView> getRolePer(){
		List<rolePerView> rolePerViews = new ArrayList<rolePerView>();
		List<String> roleId = RolePermissionHelper.getAllRoleId(); 
		for(String roleIds:roleId){
			rolePerView rolePerView = new rolePerView();
			//List<PermissionPersistence> permissionPersistences = new ArrayList<PermissionPersistence>();
			List<PermissionPersistence> list = RolePermissionHelper.getRolePermissionByRId(roleIds);
			String string = "";
			for (PermissionPersistence permissionPersistence:list){
				string = string+permissionPersistence.getPERMISSIONPHYSICALNAME()+" ";
			}
			rolePerView.setPermission(string);
			rolePerView.setRoleName(RoleHelper.getRoleName(roleIds));
			rolePerViews.add(rolePerView);
		}
		return rolePerViews;
	}
}
