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
import org.springframework.web.bind.annotation.RequestMethod;

import poly.dto.MemoDTO;

import poly.service.IMemoService;

import poly.util.CmmUtil;

@Controller
public class MemoController{
	private Logger log = Logger.getLogger(this.getClass());
	
	
	@Resource(name="MemoService") 
	private IMemoService memoService;

	/*
	 * 여기는 localhost:8080/a.do라는 
	 * 요청이 들어오고
	 */
	
	
	@RequestMapping(value="memo/memoRegProc")
	public String memoRegProc(HttpServletRequest req, Model model) throws Exception{
		log.info("메모장...");
		
		String contents = CmmUtil.nvl( req.getParameter("contents"));
		String id = CmmUtil.nvl(req.getParameter("id"));
		String user_id = (String)memoService.getUser_id(id);
		String memo_class = null;
		
		//메모 라벨 처리
		if(CmmUtil.nvl(req.getParameter("new_label")).equals(null) || CmmUtil.nvl(req.getParameter("new_label")).equals("")) {
			memo_class = CmmUtil.nvl(req.getParameter("label"));
		}else {
			memo_class = CmmUtil.nvl(req.getParameter("new_label"));
		}
		
        contents = contents.replace("\r\n", "<br/>").replace("& #39;", "\'").replace("\\", "").replace("& #40;", "\\(").replace("& #41;", "\\)").replace("& lt;", "<").replace("& gt;", ">").replace("&nbsp;", "&nbsp;").replace("&nbsp", "&nbsp");
		
		MemoDTO mDTO = new MemoDTO();
		mDTO.setContents(contents);
		mDTO.setUser_id(user_id);
		mDTO.setMemo_class(memo_class);
		
		log.info(mDTO);
		
		int result = memoService.insertMemo(mDTO);
		if(result != 1){
			model.addAttribute("msg", "메모 저장에 실패 하셨습니다.");
			model.addAttribute("url", "/main/main.do");
		}else{
			model.addAttribute("msg", "메모 저장에 성공 하셨습니다.");
			model.addAttribute("url", "/main/main.do");
		}
		
		return "/alert";
	}
	

	@RequestMapping(value="memo/single_memo", method=RequestMethod.GET)
	public String single_memo(@ModelAttribute("memoDTO") MemoDTO sDTO, HttpServletRequest req, Model model, HttpSession session) throws Exception{
		log.info("메모장 이동");
		String memo_no = CmmUtil.nvl((String)req.getParameter("memo_no")); 	
		String id = CmmUtil.nvl((String)session.getAttribute("id"));
		String user_id = (String)memoService.getUser_id(id);
		
		sDTO.setMemo_no(memo_no);
		sDTO.setUser_id(user_id);
		
		List<MemoDTO> list = memoService.selectMemo(sDTO);
		model.addAttribute("list", list);
		System.out.println(list);
		
		session.setAttribute("memo_no", memo_no);
		
		//메모장 내용 받아서(model) 싱글페이지로 전달
		
		return "/memo/single_memo";
	}
	
	@RequestMapping(value="memo/deleted_single_memo", method=RequestMethod.GET)
	public String deleted_single_memo(@ModelAttribute("memoDTO") MemoDTO sDTO, HttpServletRequest req, Model model, HttpSession session) throws Exception{
		String memo_no = CmmUtil.nvl((String)req.getParameter("memo_no")); 	
		String id = CmmUtil.nvl((String)session.getAttribute("id"));
		String user_id = (String)memoService.getUser_id(id);
		
		sDTO.setMemo_no(memo_no);
		sDTO.setUser_id(user_id);
		
		List<MemoDTO> list = memoService.selectMemo(sDTO);
		model.addAttribute("list", list);
		System.out.println(list);
		
		session.setAttribute("memo_no", memo_no);
		
		//메모장 내용 받아서(model) 싱글페이지로 전달
		List<MemoDTO> label_list = memoService.selectLabelList(user_id);
        label_list.remove(null);
        model.addAttribute("label_list", label_list);
		return "/memo/deleted_single_memo";
	}
	
	
	/*@RequestMapping(value="main/lit")
	public String lit(@ModelAttribute("memoDTO") MemoDTO memoDTO, Model model) throws Exception{
		log.info("메모장을 불러오는중");
		System.out.println("메모장");
        List<MemoDTO> list = memoService.selectMemoList(memoDTO);
        System.out.println(list.get(0).getContents());
        model.addAttribute("list", list);
        log.info("메모장을 모두 불러옴");
		return "/main/lit";
	}*/
    //메모 리스트
	@RequestMapping(value="main/main")
	public String main(@ModelAttribute("memoDTO") MemoDTO memoDTO, Model model, HttpSession session, HttpServletRequest req) throws Exception{
        String search = "";
		System.out.println("입력된 검색어" + (String)req.getParameter("search"));
        String id = CmmUtil.nvl((String)session.getAttribute("id"));
        String user_id = (String)memoService.getUser_id(id);
        
        //오래된 메모 삭제
        memoService.rm_oldMemo(user_id);
        
        memoDTO.setUser_id(user_id);
        
        if(session.getAttribute("memo_no") != "" || session.getAttribute("memo_no") != null) {
        	session.removeAttribute("memo_no");
        }
        
        search = CmmUtil.nvl((String)req.getParameter("search"));
        if(search.equals("") || search.equals(null)){
        	System.out.println("메모 불러오는 중");
        	List<MemoDTO> list = memoService.selectMemoList(memoDTO);
	        model.addAttribute("list", list);
        }else{
        	memoDTO.setSearch(search);
        	System.out.println("search" + memoDTO.getSearch());
        	List<MemoDTO> list = memoService.searchMemoList(memoDTO);
        	System.out.println(list);
        	model.addAttribute("list", list);
        }
        List<MemoDTO> label_list = memoService.selectLabelList(user_id);
        label_list.remove(null);
        model.addAttribute("label_list", label_list);
        session.setAttribute("label_list", label_list);
			return "/main/main";
		
	}
	
	@RequestMapping(value="main/trash")
	public String trash(@ModelAttribute("memoDTO") MemoDTO memoDTO, Model model, HttpSession session, HttpServletRequest req) throws Exception{
        String id = CmmUtil.nvl((String)session.getAttribute("id"));
        String user_id = (String)memoService.getUser_id(id);
         
        memoDTO.setUser_id(user_id);
        
        if(session.getAttribute("memo_no") != "" || session.getAttribute("memo_no") != null) {
        	session.removeAttribute("memo_no");
        }
        
        
        List<MemoDTO> list = memoService.deletedMemoList(memoDTO);
	    model.addAttribute("list", list);
       
		return "/main/trash";
		
	}
	
	//휴지통으로 보내기
	@RequestMapping(value="memo/delete")
	public String delete(Model model, HttpSession session) throws Exception{
        String memo_no = CmmUtil.nvl((String)session.getAttribute("memo_no"));
		String id = CmmUtil.nvl((String)session.getAttribute("id"));
        String user_id = (String)memoService.getUser_id(id);
        
        MemoDTO mDTO = new MemoDTO();
        mDTO.setMemo_no(memo_no);
        mDTO.setUser_id(user_id);
        
        int result = memoService.deleteMemo(mDTO);
		if(result != 1){
			model.addAttribute("msg", "메모 삭제에 실패 하셨습니다.");
			model.addAttribute("url", "/main/main.do");
		}else{
			model.addAttribute("msg", "메모를 삭제 했습니다.");
			model.addAttribute("url", "/main/main.do");
		}
		
		return "/alert";
	}
	
	@RequestMapping(value="memo/recover")
	public String recover(Model model, HttpSession session) throws Exception{
        String memo_no = CmmUtil.nvl((String)session.getAttribute("memo_no"));
		String id = CmmUtil.nvl((String)session.getAttribute("id"));
        String user_id = (String)memoService.getUser_id(id);
        
        MemoDTO mDTO = new MemoDTO();
        mDTO.setMemo_no(memo_no);
        mDTO.setUser_id(user_id);
        
        int result = memoService.recoverMemo(mDTO);
		if(result != 1){
			model.addAttribute("msg", "메모 복구에 실패했습니다.");
			model.addAttribute("url", "/main/trash.do");
		}else{
			model.addAttribute("msg", "메모를 복구 했습니다.");
			model.addAttribute("url", "/main/main.do");
		}
		
		return "/alert";
	}
	
	//메모 삭제
		@RequestMapping(value="memo/remove")
		public String remove(Model model, HttpSession session) throws Exception{
	        String memo_no = CmmUtil.nvl((String)session.getAttribute("memo_no"));
			String id = CmmUtil.nvl((String)session.getAttribute("id"));
	        String user_id = (String)memoService.getUser_id(id);
	        
	        MemoDTO mDTO = new MemoDTO();
	        mDTO.setMemo_no(memo_no);
	        mDTO.setUser_id(user_id);
	        
	        int result = memoService.removeMemo(mDTO);
			if(result != 1){
				model.addAttribute("msg", "메모 삭제에 실패 하셨습니다.");
				model.addAttribute("url", "/main/trash.do");
			}else{
				model.addAttribute("msg", "메모를 삭제 했습니다.");
				model.addAttribute("url", "/main/trash.do");
			}
			
			return "/alert";
		}
		
		@RequestMapping(value="memo/deleteMemoAll")
		public String deleteMemoAll(Model model, HttpSession session) throws Exception{
			String id = CmmUtil.nvl((String)session.getAttribute("id"));
	        String user_id = (String)memoService.getUser_id(id);
	        
	        MemoDTO mDTO = new MemoDTO();
	        mDTO.setUser_id(user_id);
	        
	        memoService.deleteMemoAll(user_id);
			model.addAttribute("msg", "사용자의 모든 메모를 삭제 했습니다.");
			model.addAttribute("url", "/member/deleteMember.do");
			
			return "/alert";
		}
	
	//수정할 메모 선택
	@RequestMapping(value="memo/memo_modi", method=RequestMethod.GET)
	public String memo_modi(@ModelAttribute("memoDTO") MemoDTO sDTO, HttpServletRequest req, Model model, HttpSession session) throws Exception{
		log.info("메모장 이동");
		String memo_no = CmmUtil.nvl((String)req.getParameter("memo_no")); 	
		String id = CmmUtil.nvl((String)session.getAttribute("id"));
		String user_id = (String)memoService.getUser_id(id);
		
		sDTO.setMemo_no(memo_no);
		sDTO.setUser_id(user_id);
		
		List<MemoDTO> list = memoService.selectMemo(sDTO);
		
		model.addAttribute("list", list);
		System.out.println(list);
		
		session.setAttribute("memo_no", memo_no);
		
		//메모장 내용 받아서(model) 싱글페이지로 전달
		List<MemoDTO> label_list = memoService.selectLabelList(user_id);
        label_list.remove(null);
        model.addAttribute("label_list", label_list);
		return "/memo/memo_modi";
	}
		
	//메모 수정
		@RequestMapping(value="memo/modify")
		public String modify(HttpServletRequest req, Model model, HttpSession session) throws Exception{
	        String memo_no = CmmUtil.nvl( req.getParameter("memo_no"));
	        String contents = CmmUtil.nvl( req.getParameter("contents"));
			String id = CmmUtil.nvl( req.getParameter("id"));
	        String user_id = (String)memoService.getUser_id(id);
	        String memo_class = null;
			
			//메모 라벨 처리
			if(CmmUtil.nvl(req.getParameter("new_label")).equals(null) || CmmUtil.nvl(req.getParameter("new_label")).equals("")) {
				memo_class = CmmUtil.nvl(req.getParameter("label"));
			}else {
				memo_class = CmmUtil.nvl(req.getParameter("new_label"));
			}
	        
	        contents = contents.replace("\r\n", "<br/>").replace("& #39;", "\'").replace("\\", "").replace("& #40;", "\\(").replace("& #41;", "\\)").replace("& lt;", "<").replace("& gt;", ">").replace("&nbsp;", "&nbsp;").replace("&nbsp", "&nbsp");
	        
	        
	        MemoDTO mDTO = new MemoDTO();
	        mDTO.setMemo_no(memo_no);
	        mDTO.setContents(contents);
	        mDTO.setUser_id(user_id);
	        mDTO.setMemo_class(memo_class);
	        
	        int result = memoService.modifyMemo(mDTO);
			if(result != 1){
				model.addAttribute("msg", "메모 수정에 실패 하셨습니다.");
				model.addAttribute("url", "/memo/single_memo.do?memo_no=" + memo_no);
			}else{
				model.addAttribute("msg", "메모를 수정 했습니다.");
				model.addAttribute("url", "/memo/single_memo.do?memo_no=" + memo_no);
			}
			
			return "/alert";
		}
		
		//video_rec: 메모내용 String으로
		@RequestMapping(value="main/video_rec1")
		public String video_rec(@ModelAttribute("memoDTO") MemoDTO memoDTO, Model model, HttpSession session) throws Exception{
	        if(session.getAttribute("memo_no") != "" || session.getAttribute("memo_no") != null || session.getAttribute("contents") != "" || session.getAttribute("contents") != null) {
	        	session.removeAttribute("memo_no");
	        	session.removeAttribute("contents");
	        	
	        }
			String id = CmmUtil.nvl((String)session.getAttribute("id"));
	        String user_id = (String)memoService.getUser_id(id);
	        memoDTO.setUser_id(user_id);
	        
	        String contents = "";
			List<MemoDTO> list = memoService.selectMemoContents(memoDTO);
			System.out.println(list);
			for(int i=0; i<list.size(); i++) {
				contents += (String)list.get(i).getContents();
			}
			
			session.setAttribute("contents", contents);
			session.setAttribute("user_id", user_id);
			model.addAttribute("url", "/main/video_rec.do");
			
			System.out.println(contents);
			return "/main/video_rec1";
		}
}
