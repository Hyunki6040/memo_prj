package poly.controller;

import java.util.List;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import poly.dto.MemberDTO;
import poly.mail.TempKey;
import poly.service.IMemberService;
import poly.util.CmmUtil;

@Controller
public class MemberController {
	private Logger log = Logger.getLogger(this.getClass());
	
	
	@Resource(name="MemberService") 
	private IMemberService memberService;
	/*
	 * 여기는 localhost:8080/a.do라는 
	 * 요청이 들어오고
	 */
	
	@RequestMapping(value="post")
	public String post() throws Exception{
		return "/post";
	}
	
	
	@RequestMapping(value="detail")
	public String detail() throws Exception{
		return "/detail";
	}
	
	@RequestMapping(value="detailResult")
	public String detailResult(HttpServletRequest req, Model model) throws Exception{
		String name = CmmUtil.nvl(req.getParameter("name"));
		String id = CmmUtil.nvl(req.getParameter("id"));
		String brithday = CmmUtil.nvl(req.getParameter("brithday"));
		String regday = CmmUtil.nvl(req.getParameter("regday"));
		
		System.out.println("name : " + name);
		System.out.println("id : " + id);
		System.out.println("brithday : " + brithday);
		System.out.println("regday : " + regday);
		
		model.addAttribute("name", name);
		model.addAttribute("id", id);
		model.addAttribute("brithday", brithday);
		model.addAttribute("regday", regday);
		
		return "/detailResult";
	}
	
	
	@RequestMapping(value="/member/emailconfirm")
	public String emailconfirm() throws Exception{
		System.out.println("이메일인증");
		return "/member/emailconfirm";
	}
	
	@RequestMapping(value="/main/top")
	public String top() throws Exception{
		
		return "/main/top";
	}
	
	@RequestMapping(value="/admin/contact")
	public String contact() throws Exception{
		
		return "/admin/contact";
	}
	
	@RequestMapping(value="/member/login")
	public String login(HttpSession session) throws Exception{
		session.removeAttribute("reg_id");
		return "/member/login";
	}
	
	@RequestMapping(value="member/memberReg")
	public String memberReg() throws Exception{
		return "/member/memberReg";
	}
	
	@RequestMapping(value="member/login_pw")
	public String login_pw() throws Exception{
		return "/member/login_pw";
	}
	
	@RequestMapping(value="member/id_check")
	public String id_check(HttpSession session) throws Exception{
		session.removeAttribute("reg_id");
		return "/member/id_check";
	}
	
	@RequestMapping(value="member/find_pw")
	public String find_pw(HttpSession session) throws Exception{
		String id = CmmUtil.nvl((String)session.getAttribute("temp_id"));
		String key = new TempKey().getKey(50, false);
		MemberDTO mDTO = new MemberDTO();
		mDTO.setId(id);
		mDTO.setUser_authCode(key);
		
		memberService.mailSender(mDTO, key);
		memberService.updateKey(mDTO);
		
		return "/member/find_pw";
	}
	
	@RequestMapping(value="member/pw_modi")
	public String pw_modi() throws Exception{
		return "/member/pw_modi";
	}
	
	@RequestMapping(value="member/personal_page")
	public String personal_page(HttpServletRequest req, HttpSession session) throws Exception{
		String id = CmmUtil.nvl((String)session.getAttribute("id"));
		
		String name = memberService.getName(id);
		session.setAttribute("name", name);
		System.out.println(name);
		return "/member/personal_page";
	}
	
	@RequestMapping(value="member/memberRegProc")
	public String memberRegProc(MemberDTO user, HttpSession session, HttpServletRequest req, Model model) throws Exception{
		log.info("회원가입...");
		log.info(user.toString());
		
		String name = CmmUtil.nvl( req.getParameter("name"));
		String id = CmmUtil.nvl(req.getParameter("id"));
		String password = CmmUtil.nvl(req.getParameter("password"));
	
		
		//변수들을 각각 넘기면 코드도 길고 귀찮으니까 DTO를 사용한다.
		//DTO는 Data Fransfer Object의 약자로 데이터 전송을 위한 객체
		String key = new TempKey().getKey(50, false); // 인증키 생성
		System.out.println(key);
		
		MemberDTO mDTO = new MemberDTO();
		mDTO.setMember_name(name);
		mDTO.setId(id);
		mDTO.setPassword(password);
		mDTO.setUser_authCode(key);
		
		
		log.info("회원가입...");
		log.info(user.toString());
		
		int result = memberService.insertMember(mDTO);
		//return "redirect:/";
		
		if(result != 1){
			//회원가입이 정상적으로 이루어지지 않음
			model.addAttribute("msg", "회원가입에 실패 하셨습니다.");
			model.addAttribute("url", "/member/memberReg.do");
		}else{
			//회원가입이 정상적으로 이루어짐
			//정상적으로 회원가입이 되면 '회원가입이 되었습니다' alert창 띄우고 main.do로 이동
			model.addAttribute("msg", "회원가입에 성공 하셨습니다. 이메일인증을 해주세요.");
			model.addAttribute("url", "/");
			memberService.mailSender(mDTO, key);
		}
		
		return "/alert";
	}
	
	@RequestMapping(value="member/idCheck")
	public String idCheck(HttpSession session, HttpServletRequest req, Model model) throws Exception{
		session.removeAttribute("reg_id");
		
		String temp_id = CmmUtil.nvl(req.getParameter("id"));
		MemberDTO tempDTO = new MemberDTO();
		tempDTO.setId(temp_id);
		
		String id = memberService.getIdChk(tempDTO);
		if(id==null || id=="") {
			model.addAttribute("msg", "사용 가능한 이메일 입니다!");
			session.setAttribute("reg_id", temp_id);
			model.addAttribute("url", "/member/memberReg.do");
		}else {
			model.addAttribute("msg", "이미 가입되어있는 이메일 입니다!");
			model.addAttribute("url", "/member/id_check.do");
		}
		return "/alert";
	}
	
	@RequestMapping(value="member/memberList")
	public String memberList(Model model) throws Exception{
		List<MemberDTO> mList = memberService.getAllMember();
		model.addAttribute("mList", mList);
		System.out.println("welcome");
		return "/member/memberList";
	}
	
	@RequestMapping(value="member/memberDetail")
	public String memberDetail(HttpServletRequest request, Model model) throws Exception{
		
		String memberId = CmmUtil.nvl(request.getParameter("memberId"));
		
		MemberDTO mDTO = memberService.getMemberOne(memberId);
		
		model.addAttribute("mDTO", mDTO);
		
		return "/member/memberDetail";
	}
	
	@RequestMapping(value="member/deleteMember")
	public String deleteMember(HttpSession session, Model model) throws Exception{
		
		String id = CmmUtil.nvl((String)session.getAttribute("id"));
		
		int result = memberService.deleteMember(id);
		if(result == 1) {
		model.addAttribute("msg", "삭제되었습니다.");
		model.addAttribute("url", "/");
		session.setAttribute("id", id);
		session.invalidate();
		}else {
			model.addAttribute("msg", "삭제에 실패했습니다.");
			model.addAttribute("url", "/member/personal_page.do");
		}
		
		return "/alert";
	}
	
	@RequestMapping(value="member/updateView")
	public String updateView(HttpServletRequest request, Model model) throws Exception{
		
		String memberId = CmmUtil.nvl(request.getParameter("memberId"));
		
		MemberDTO mDTO = memberService.getMemberOne(memberId);
		
		model.addAttribute("mDTO", mDTO);
		
		return "/member/updateView";
	}
	
	
	@RequestMapping(value="member/loginProc1", method=RequestMethod.POST)
	public String loginProc1(HttpServletRequest req, Model model, HttpSession session) throws Exception{
		String temp_id = CmmUtil.nvl(req.getParameter("putid"));
		
		MemberDTO tempDTO = new MemberDTO();
		tempDTO.setId(temp_id);
		
		String id = memberService.getIdChk(tempDTO);
		
		if(id == null || id == "") {
			model.addAttribute("msg","존재하지 않는 아이디 입니다.");
			model.addAttribute("url","/");
		}else {
			session.setAttribute("temp_id", temp_id);
			model.addAttribute("msg","비밀번호를 입력해주세요!");
			model.addAttribute("url", "/member/login_pw.do");
		}
		return "/alert";
	}
	
	@RequestMapping(value="member/loginProc", method=RequestMethod.POST)
	public String loginProc(HttpServletRequest req, Model model, HttpSession session) throws Exception{
		String id = CmmUtil.nvl((String)session.getAttribute("temp_id"));
		String password = CmmUtil.nvl(req.getParameter("putpassword"));
		
		/* 로그인창에서 입력받은 값 가져오기 */
		log.info(this.getClass() + " putid : " + id);
		log.info(this.getClass() + " putpassword : " + password);
		
		MemberDTO eDTO  = new MemberDTO();
		eDTO.setId(id);
		eDTO.setPassword(password);
		
		eDTO =  memberService.getUserLogin(eDTO);
		
		if(eDTO == null) {
			model.addAttribute("msg","비밀번호가 틀렸습니다");
			model.addAttribute("url","/member/login_pw.do");
		}else if(eDTO.getEmail_state() == null){
			session.setAttribute("temp_id", id);
			log.info(id);
			model.addAttribute("msg","이메일 인증이 필요합니다");
			model.addAttribute("url","/member/emailconfirm.do");
		}else {
				session.setAttribute("id", id);
				/*session.setAttribute("id", eDTO.getUSRID());*/
				model.addAttribute("msg","로그인 성공");
				model.addAttribute("url","/main/main.do");
			}
			return "/alert";
	}
	
	@RequestMapping(value = "/member/emailProc", method=RequestMethod.POST)
	public String emailConfirm( Model model, HttpServletRequest req, HttpSession session) throws Exception { // 이메일인증
		String key = (String)CmmUtil.nvl(req.getParameter("num"));
		String id = CmmUtil.nvl(req.getParameter("id"));
		String main_key = (String)memberService.getAuth_Code(id);
		
		String msg = "";
		String url = "";
		
		if(main_key.replaceAll(" ", "").equals(key.replaceAll(" ", ""))){
			session.setAttribute("id", id);
			memberService.updateEmail_state(id);
			msg="로그인 성공";
			url="/main/main.do";
			model.addAttribute("msg",msg);
			model.addAttribute("url",url);
		}else{
			msg="로그인 실패";
			url="/member/emailconfirm.do";
			model.addAttribute("msg",msg);
			model.addAttribute("url",url);
		}
		return "/alert";
	}
	
	@RequestMapping(value = "member/emailProc1", method=RequestMethod.POST)
	public String emailProc1( Model model, HttpServletRequest req, HttpSession session) throws Exception { // 이메일인증
		String key = (String)CmmUtil.nvl(req.getParameter("num"));
		String id = CmmUtil.nvl((String)session.getAttribute("temp_id"));
		String main_key = (String)memberService.getAuth_Code(id);
		System.out.println("아이디: " + id + "  불러온 key : " + main_key);
		
		String msg = "";
		String url = "";
		
		if(main_key.replaceAll(" ", "").equals(key.replaceAll(" ", ""))){
			session.setAttribute("id", id);
			
			msg="새 비밀번호를 변경해주세요!";
			url="/member/pw_modi.do";
			model.addAttribute("msg",msg);
			model.addAttribute("url",url);
		}else{
			msg="인증번호가 틀렸습니다.";
			url="/";
			model.addAttribute("msg",msg);
			model.addAttribute("url",url);
		}
		return "/alert";
	}
	
	@RequestMapping(value="member/UpdatePassword")
	public String UpdatePassword(MemberDTO user, HttpSession session, HttpServletRequest req, Model model) throws Exception{
		
		String id = CmmUtil.nvl((String)session.getAttribute("id"));
		String password = CmmUtil.nvl(req.getParameter("password"));
	
		
		//변수들을 각각 넘기면 코드도 길고 귀찮으니까 DTO를 사용한다.
		//DTO는 Data Fransfer Object의 약자로 데이터 전송을 위한 객체
		
		MemberDTO mDTO = new MemberDTO();
		mDTO.setId(id);
		mDTO.setPassword(password);
		
		int result = memberService.updatePassword(mDTO);
		//return "redirect:/";
		
		if(result != 1){
			//회원가입이 정상적으로 이루어지지 않음
			model.addAttribute("msg", "비밀번호 변경에 실패했습니다.");
			model.addAttribute("url", "/member/pw_modi.do");
		}else{
			//회원가입이 정상적으로 이루어짐
			//정상적으로 회원가입이 되면 '회원가입이 되었습니다' alert창 띄우고 main.do로 이동
			model.addAttribute("msg", "비밀번호를 변경했습니다.");
			model.addAttribute("url", "/main/main.do");
		}
		
		return "/alert";
	}
	
	@RequestMapping(value="/member/logout")
	public String logout(HttpSession session) throws Exception{
		//세션을 초기화 하는 함수
		//세션이 초기화가 됐으니 로그인이 풀렸겠죠???
		session.invalidate();
		
		//그다음 메인 페이지를 보여줘라...
		return "redirect:/";
	}
	
	
	
	@RequestMapping(value="/member/searchList")
	public @ResponseBody List<MemberDTO> searchList(@RequestParam(value="search") String search) throws Exception{
		System.out.println(search);
		
		MemberDTO mDTO = new MemberDTO();
		mDTO.setId(CmmUtil.nvl(search));
		
		List<MemberDTO> mList = memberService.getSearchList(mDTO);
		
		for(MemberDTO dDTO : mList){
			System.out.println(dDTO.getMember_id());
			System.out.println(dDTO.getId());
			System.out.println(dDTO.getMember_name());
			System.out.println(dDTO.getAuth());
			System.out.println(dDTO.getRegday());
		}
		
		return mList;
	}
	
	@RequestMapping(value="/member/rList")
	public @ResponseBody List<MemberDTO> rList() throws Exception{
		List<MemberDTO> mList = memberService.getAllMember();
		return mList;
	}
	@RequestMapping(value="/jspset")
	public String jspset(HttpServletRequest request, Model model) throws Exception{
		
		return "/jspset";
	}
	
	
}
