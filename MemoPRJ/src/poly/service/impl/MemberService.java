package poly.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import poly.dto.MemberDTO;
import poly.mail.MailHandler;
import poly.mail.TempKey;
import poly.persistance.mapper.MemberMapper;
import poly.service.IMemberService;

@Service("MemberService")
public class MemberService implements IMemberService {
	
	@Resource(name="MemberMapper")
	private MemberMapper memberMapper;
		
	
	@Override
	public int insertMember(MemberDTO mDTO) throws Exception {
		return memberMapper.insertMember(mDTO);
		}


	@Override
	public List<MemberDTO> getAllMember() throws Exception {
		return memberMapper.getAllMember();
	}

	@Override
	public MemberDTO getMemberOne(String memberId) throws Exception {
		return memberMapper.getMemberOne(memberId);
	}

	@Override
	public int deleteMember(String id) throws Exception {
		return memberMapper.deleteMember(id);
	}

	@Override
	public int updateMember(MemberDTO mDTO) throws Exception {
		return memberMapper.updateMember(mDTO);
	}
	
	@Override
	public int updatePassword(MemberDTO mDTO) throws Exception {
		return memberMapper.updatePassword(mDTO);
	}

	@Override
	public String getIdChk(MemberDTO mDTO) throws Exception {
		return memberMapper.getIdChk(mDTO);
	}

	@Override
	public List<MemberDTO> getSearchList(MemberDTO mDTO) throws Exception {
		return memberMapper.getSearchList(mDTO);
	}
	
	@Override
	   public MemberDTO getUserLogin(MemberDTO eDTO) throws Exception {
	      return memberMapper.getUserLogin(eDTO);
	   }
	@Override
	   public String getAuth_Code(String id) throws Exception {
	      return memberMapper.getAuth_Code(id);
	   }
	@Override
	   public String getName(String id) throws Exception {
	      return memberMapper.getName(id);
	   }
	@Override
	   public void updateEmail_state(String id) throws Exception {
	    memberMapper.updateEmail_state(id);
	   }
	
	@Override
	   public void updateKey(MemberDTO mDTO) throws Exception {
	    memberMapper.updateKey(mDTO);
	   }
	
	@Inject
	private JavaMailSender mailSender;
	@Override
	   public void mailSender(MemberDTO mDTO, String key) throws Exception {
		MailHandler sendMail = new MailHandler(mailSender);
		sendMail.setSubject("[TostIdea! 서비스 이메일 인증]");
		sendMail.setText(
				new StringBuffer().append("<h1>아래의 인증 번호를 홈페이지에 입력해주세요</h1><br/><p>인증 번호 :</p>").append(key).toString());
		sendMail.setFrom("tostit.korea@gmail.com", "TostIdea!");
		sendMail.setTo(mDTO.getId());
		sendMail.send();
	   }
	
}