package utry.dao;

import java.util.List;

import utry.pojo.Orders;
import utry.pojo.UserInfo;

public interface UserInfoDao {

	void insertUser(UserInfo info);

	List<UserInfo> getList();

	List<UserInfo> getListByUser(UserInfo info);
	List<Orders> getListByOrder(Orders info);

}
