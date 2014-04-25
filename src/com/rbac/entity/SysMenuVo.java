package com.rbac.entity;

import java.util.ArrayList;
import java.util.List;

public class SysMenuVo implements Comparable{

	private Long id;
	
	private String text;
	
	private String url;
	
	private Integer orderSeq;
	
	private Long parentId;
	
	private List<SysMenuVo> children = new ArrayList<SysMenuVo>();
	

	/**
	 * 按orderSeq排序
	 */
	public int compareTo(Object menuVo){
		if(menuVo instanceof SysMenuVo){
			SysMenuVo vo = (SysMenuVo)menuVo;
			if(vo!=null && this.orderSeq!=null){
				return this.orderSeq.compareTo(vo.getOrderSeq());
			}
		}
		return this.compareTo(menuVo);
	}
		
	public Boolean getLeaf(){
		return children.size()>0?false:true;
	}
	
		
	public List<SysMenuVo> getChildren() {
		return children;
	}

	public void setChildren(List<SysMenuVo> children) {
		this.children = children;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOrderSeq() {
		return orderSeq;
	}

	public void setOrderSeq(Integer orderSeq) {
		this.orderSeq = orderSeq;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
