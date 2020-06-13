package com.shiro.test.mvc.pojo;

import java.util.HashMap;
import java.util.List;


/**
 * <p>
 * 用户身份信息:<br>
 * 存入session 由于tomcat将session会序列化在本地硬盘上，<br>
 * 所以使用Serializable接口<br>
 * </p>
 */

public class ActiveUser implements java.io.Serializable {
	/**  */
	private static final long serialVersionUID = -4500748422849176791L;
	/** 用户id（主键） */
	private String id;

	private String userName;

	private String email;

	private RoleInformation role;

	public ActiveUser(String id,String userName,String email,RoleInformation role){
		this.email=email;
		this.userName=userName;
		this.id=id;
		this.role=role;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RoleInformation getRole() {
		return role;
	}

	public void setRole(RoleInformation role) {
		this.role = role;
	}
}
