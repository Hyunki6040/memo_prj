package poly.service.impl;



import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import poly.dto.KeywordDTO;

import poly.keyword.Keyword;
import poly.keyword.Keyword_freq;

import poly.persistance.mapper.KeywordMapper;

import poly.service.IKeywordService;


@Service("KeywordService")
public class KeywordService implements IKeywordService{
	
	@Resource(name="KeywordMapper")
	private KeywordMapper keywordMapper;
	
	@Override
	public List addKeyword(String contents, String user_id, List freq_list) throws Exception{
		List list = Keyword.main(contents);
		System.out.println("Service 진입");
		System.out.println(list);
		
		//키워드 등록
		/*for(int i = 0; i < 10; i++) {
			KeywordDTO mDTO = new KeywordDTO();
			mDTO.setUser_id(user_id);
			mDTO.setKEYWORD(list.get(i));
			mDTO.setFREQ(freq_list.get(i));
			System.out.println("Service : "+mDTO);
			keywordMapper.insertKeyword(mDTO);
		}*/
		System.out.println("Service list : "+list);
        return list;
    }
	
	@Override
	public int deleteKeyword(String user_id) throws Exception {
		return keywordMapper.deleteKeyword(user_id);
	}
	
}
