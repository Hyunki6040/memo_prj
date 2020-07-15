package poly.service.impl;


import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.dto.KeywordDTO;
import poly.dto.MemoDTO;
import poly.keyword.Keyword;
import poly.keyword.Keyword_freq;
import poly.keyword.Keyword.Input;
import poly.persistance.mapper.MemoMapper;
import poly.service.IMemoService;

@Service("MemoService")
public class MemoService implements IMemoService{
		
	@Resource(name="MemoMapper")
	private MemoMapper memoMapper;
	
	
	
	@Override
	public String getUser_id(String id) throws Exception {
		return memoMapper.getUser_id(id);
	}
	
	@Override
	public int insertMemo(MemoDTO mDTO) throws Exception {
		return memoMapper.insertMemo(mDTO);
	}
	
	@Override
    public List<MemoDTO> searchMemoList(MemoDTO memoDTO) throws Exception {
        List<MemoDTO> list = null;
        list = memoMapper.searchMemoList(memoDTO);
        return list;
    }
	
	@Override
    public List<MemoDTO> selectMemoList(MemoDTO memoDTO) throws Exception {
        List<MemoDTO> list = null;
        list = memoMapper.selectMemoList(memoDTO);
        return list;
    }
	
	@Override
    public List<MemoDTO> selectLabelList(String user_id) throws Exception {
        List<MemoDTO> list = null;
        list = memoMapper.selectLabelList(user_id);
        return list;
    }
	
	@Override
    public List<MemoDTO> deletedMemoList(MemoDTO memoDTO) throws Exception {
        List<MemoDTO> list = null;
        list = memoMapper.deletedMemoList(memoDTO);
        return list;
    }
	
	@Override
    public List<MemoDTO> selectMemo(MemoDTO sDTO) throws Exception {
        List<MemoDTO> list = null;
        list = memoMapper.selectMemo(sDTO);
        return list;
    }
	
	@Override
	public List<MemoDTO> selectMemoContents(MemoDTO memoDTO) throws Exception{
        List<MemoDTO> list = null;
        list = memoMapper.selectMemoContents(memoDTO);
        return list;
    }
	
	@Override
	public int deleteMemo(MemoDTO mDTO) throws Exception {
		return memoMapper.deleteMemo(mDTO);
	}
	
	@Override
	public int removeMemo(MemoDTO mDTO) throws Exception {
		return memoMapper.removeMemo(mDTO);
	}
	
	@Override
	public int recoverMemo(MemoDTO mDTO) throws Exception {
		return memoMapper.recoverMemo(mDTO);
	}
	
	@Override
	public int modifyMemo(MemoDTO mDTO) throws Exception {
		return memoMapper.modifyMemo(mDTO);
	}
	
	@Override
	public void rm_oldMemo(String user_id) throws Exception {
		memoMapper.rm_oldMemo(user_id);
	}
	
	@Override
	public void deleteMemoAll(String user_id) throws Exception {
		System.out.println("모든 메모 삭제 서비스 진입");
		memoMapper.deleteMemoAll(user_id);
	}
}
