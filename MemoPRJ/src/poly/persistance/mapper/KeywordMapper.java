package poly.persistance.mapper;




import config.Mapper;
import poly.dto.KeywordDTO;


@Mapper("KeywordMapper")
public interface KeywordMapper {
	public int insertKeyword(KeywordDTO mDTO) throws Exception;
	public int deleteKeyword(String user_id) throws Exception;
}
