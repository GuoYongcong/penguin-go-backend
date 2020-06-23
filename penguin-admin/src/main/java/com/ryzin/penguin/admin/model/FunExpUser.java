package com.ryzin.penguin.admin.model;

public class FunExpUser extends BaseModel {

    private Long expId;

    private Long userId;
    
    private Byte status;
	/** 实验报名状态 0：未处理 1：已预约 2：进行中 3：已结束 4：已取消 */
    
    public Long getExpId() {
		return expId;
	}
    
    public void setExpId(Long expId) {
		this.expId = expId;
	}
    
    public Long getUserId() {
		return userId;
	}
    
    public void setUserId(Long userId) {
		this.userId = userId;
	}
    
    public Byte getStatus() {
		return status;
	}
    
    public void setStatus(Byte status) {
		this.status = status;
	}
}