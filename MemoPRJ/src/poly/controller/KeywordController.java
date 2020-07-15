package poly.controller;





import java.util.List;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import poly.dto.MemoDTO;
import poly.keyword.Keyword_freq;
import poly.service.IKeywordService;

import poly.util.CmmUtil;

@Controller
public class KeywordController{
	private Logger log = Logger.getLogger(this.getClass());
	
	
	@Resource(name="KeywordService") 
	private IKeywordService keywordService;

	/*
	 * 여기는 localhost:8080/a.do라는 
	 * 요청이 들어오고
	 */
	
		//video_rec: 기존의 키워드 삭제하고 업데이트
		@RequestMapping(value="main/video_rec")
		public String video_rec(@ModelAttribute("memoDTO") MemoDTO memoDTO, Model model, HttpServletRequest req, HttpSession session) throws Exception{
			String contents = CmmUtil.nvl((String)session.getAttribute("contents"));
			String user_id = CmmUtil.nvl((String)session.getAttribute("user_id"));
			contents = contents.replaceAll("[0-9]", "").replaceAll("br/", "");

			
			//기존 키워드 삭제
			//keywordService.deleteKeyword(user_id);
			
	        //키워드 분석
			List freq_list = Keyword_freq.main(contents);
			System.out.println(freq_list);
			
			
			
			List list = keywordService.addKeyword(contents, user_id, freq_list);
			
			model.addAttribute("user_id", user_id);
			session.setAttribute("list", list);
			session.setAttribute("freq_list", freq_list);
			
			return "/main/video_rec";
		}
		
		
}
