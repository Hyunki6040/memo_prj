package poly.persistance.mapper;


import java.util.HashMap;
import java.util.List;

import config.Mapper;
import poly.dto.KeywordDTO;
import poly.dto.MemoDTO;

@Mapper("MemoMapper")
public interface MemoMapper {
	public String getUser_id(String id) throws Exception;
	public int insertMemo(MemoDTO mDTO) throws Exception;
	public List<MemoDTO> searchMemoList(MemoDTO memoDTO) throws Exception;
	public List<MemoDTO> deletedMemoList(MemoDTO memoDTO) throws Exception;
	public List<MemoDTO> selectMemoList(MemoDTO memoDTO) throws Exception;
	public List<MemoDTO> selectLabelList(String user_id) throws Exception;
	public List<MemoDTO> selectMemo(MemoDTO memoDTO) throws Exception;
	public List<MemoDTO> selectMemoContents(MemoDTO memoDTO) throws Exception;
	public int removeMemo(MemoDTO mDTO) throws Exception;
	public int deleteMemo(MemoDTO mDTO) throws Exception;
	public int recoverMemo(MemoDTO mDTO) throws Exception;
	public int modifyMemo(MemoDTO mDTO) throws Exception;
	public void rm_oldMemo(String user_id) throws Exception;
	public void deleteMemoAll(String user_id) throws Exception;
}