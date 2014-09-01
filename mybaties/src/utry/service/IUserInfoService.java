package utry.service;

import java.util.List;

import utry.pojo.Orders;
import utry.pojo.UserInfo;

public interface IUserInfoService {

	public void insertUserinfo(UserInfo info);
	
	List<UserInfo> getList();
	
	List<UserInfo> getListByUser(UserInfo info);

	List<Orders> getListByUser(Orders info);
}
