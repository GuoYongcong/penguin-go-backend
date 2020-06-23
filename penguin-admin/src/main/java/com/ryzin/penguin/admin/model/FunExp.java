package com.ryzin.penguin.admin.model;


public class FunExp extends BaseModel{

	/** 实验名称 */
	private String name;
	/** 实验状态 */
	private Integer status;
	/** 实验类型 0：审核中 1：未发布 2：发布中 3：已结束 */
	private String types;
	/** 实验报酬下限 */
	private String payment;
	/** 联系方式 */
	private String contact;
	/** 实验人数 */
	private Integer peopleNum;
	/** 实验地点 */
	private String location;
	/** 实验内容 */
	private String content;
	/** 实验时间段 */
	private String time;
	/** 实验时长 */
	private Integer duration;
	/** 实验要求 */
	private String requirements;
	/** 实验偏好 */
	private String preferences;
	/** 问卷ID */
	private Integer questionnaireId;
	/** 图片 */
	private String fileList;
	/** 备注 */
	private String note;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Integer getPeopleNum() {
		return peopleNum;
	}

	public void setPeopleNum(Integer peopleNum) {
		this.peopleNum = peopleNum;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public String getPreferences() {
		return preferences;
	}

	public void setPreferences(String preferences) {
		this.preferences = preferences;
	}

	public Integer getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(Integer questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public String getFileList() {
		return fileList;
	}

	public void setFileList(String fileList) {
		this.fileList = fileList;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}