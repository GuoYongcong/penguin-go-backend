package com.ryzin.penguin.admin.model;


public class FunRate extends BaseModel {
	
	/** 评分者ID */
	private Long raterId;
	/** 被评分者ID */
	private Long ratedId;
	/** 实验ID */
	private Long expId;
	/** 评分 */
	private Byte rate;
	
	public Long getRaterId() {
		return raterId;
	}

	public void setRaterId(Long raterId) {
		this.raterId = raterId;
	}

	public Long getRatedId() {
		return ratedId;
	}

	public void setRatedId(Long ratedId) {
		this.ratedId = ratedId;
	}

	public Long getExpId() {
		return expId;
	}

	public void setExpId(Long expId) {
		this.expId = expId;
	}
	
	public Byte getRate() {
		return rate;
	}

	public void setRate(Byte rate) {
		this.rate = rate;
	}
}