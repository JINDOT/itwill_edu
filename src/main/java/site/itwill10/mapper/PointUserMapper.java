package site.itwill10.mapper;

import site.itwill10.dto.PointUser;

public interface PointUserMapper {
	int insertPointUser(PointUser user);
	int updatePlusPointUser(String id);
	int updateMinusPointUser(String id);
	PointUser selectPointUser(String id);
}
