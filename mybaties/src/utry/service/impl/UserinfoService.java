package utry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utry.dao.UserInfoDao;
import utry.pojo.Orders;
import utry.pojo.UserInfo;
import utry.service.IUserInfoService;

@Service
public class UserinfoService implements IUserInfoService{

	@Autowired
	UserInfoDao userInfoDao;
	@Override
	public void insertUserinfo(UserInfo info) {
		userInfoDao.insertUser(info);
	}
	@Override
	public List<UserInfo> getList() {
		return userInfoDao.getList();
	}
	@Override
	public List<UserInfo> getListByUser(UserInfo info) {
		return userInfoDao.getListByUser(info);
	}
	@Override
	public List<Orders> getListByUser(Orders info) {
		return userInfoDao.getListByOrder(info);
	}

}
