package poly.service;


import java.util.List;

public interface IKeywordService {
	public List addKeyword(String contents, String user_id, List freq_list) throws Exception;
	public int deleteKeyword(String user_id) throws Exception;
}
