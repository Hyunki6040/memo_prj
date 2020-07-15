package poly.dto;

import java.sql.Date;

public class MemberDTO {
	private String member_id;
	private String id;
	private String member_name;
	private String password;
	private String auth;
	private Date regday;
	private String user_authCode;
	private String email_state;

	

	public String getEmail_state() {
		return email_state;
	}
	public void setEmail_state(String email_state) {
		this.email_state = email_state;
	}
	public String getUser_authCode() {
		return user_authCode;
	}
	public void setUser_authCode(String user_authCode) {
		this.user_authCode = user_authCode;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public Date getRegday() {
		return regday;
	}
	public void setRegday(Date regday) {
		this.regday = regday;
	}
	
	
}
