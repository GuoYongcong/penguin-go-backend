package com.ryzin.penguin.admin.model;


public class FunUserExp extends BaseModel{

	/** 用户ID（主试） */
	private Long userId;
	/** 实验ID */
	private Long expId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getExpId() {
		return expId;
	}

	public void setExpId(Long expId) {
		this.expId = expId;
	}

}