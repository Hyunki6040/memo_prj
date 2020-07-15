package poly.persistance.mapper;

import java.util.List;


import config.Mapper;
import poly.dto.MemberDTO;

@Mapper("MemberMapper")
public interface MemberMapper {
	public int insertMember(MemberDTO mDTO) throws Exception;
	public List<MemberDTO> getAllMember() throws Exception;
	public MemberDTO getMemberOne(String memberId) throws Exception;
	public int deleteMember(String id) throws Exception;
	public int updateMember(MemberDTO mDTO) throws Exception;
	public int updatePassword(MemberDTO mDTO) throws Exception;
	public String getIdChk(MemberDTO mDTO) throws Exception;
	public List<MemberDTO> getSearchList(MemberDTO mDTO) throws Exception;
	public MemberDTO getUserLogin(MemberDTO eDTO) throws Exception;
	public String getAuth_Code(String id) throws Exception;
	public void updateEmail_state(String id) throws Exception;
	public void updateKey(MemberDTO mDTO) throws Exception;
	public String getName(String id) throws Exception;
}
