package com.company.hxs.sys.menu.vo;

import java.util.List;

public class TSysMenuVO {
	
	private Integer menuid;
	
	private String icon;
	
	private String menuname;
	
	private String url;

	private List<TSysMenuVO> menus;

	public Integer getMenuid() {
		return menuid;
	}

	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<TSysMenuVO> getMenus() {
		return menus;
	}

	public void setMenus(List<TSysMenuVO> menus) {
		this.menus = menus;
	}
	
}
