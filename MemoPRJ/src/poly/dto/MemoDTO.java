package poly.dto;

public class MemoDTO {
	private String memo_no;
	private String contents;
	private String user_id;
	private String reg_dt;
	private String memo_class;
	private String memo_state;
	private String del_dt;
	private String search;
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getMemo_no() {
		return memo_no;
	}
	public void setMemo_no(String memo_no) {
		this.memo_no = memo_no;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	public String getMemo_class() {
		return memo_class;
	}
	public void setMemo_class(String memo_class) {
		this.memo_class = memo_class;
	}
	public String getMemo_state() {
		return memo_state;
	}
	public void setMemo_state(String memo_state) {
		this.memo_state = memo_state;
	}
	public String getDel_dt() {
		return del_dt;
	}
	public void setDel_dt(String del_dt) {
		this.del_dt = del_dt;
	}
	
}
