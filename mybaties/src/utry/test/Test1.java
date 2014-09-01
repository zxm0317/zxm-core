package utry.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utry.pojo.Orders;
import utry.pojo.UserInfo;
import utry.service.IUserInfoService;

@RunWith(SpringJUnit4ClassRunner.class)
// 指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration(locations = { "classpath:spring-servlet.xml" })
public class Test1 {
	@Autowired
	IUserInfoService infoService;

	@Test
	public void t1() {
		UserInfo info = new UserInfo();
		info.setAge(29);
		info.setName("lisi");
		info.setHobbies("唱歌，旅游，游泳");
		infoService.insertUserinfo(info);
	}

	@Test
	public void t2() {
		List<UserInfo> list = infoService.getList();
		for (UserInfo userInfo : list) {
			System.out.println(userInfo);
		}
	}

	@Test
	public void t3() {
		UserInfo info = new UserInfo();
		info.setId(1);
		List<UserInfo> list = infoService.getListByUser(info);
		for (UserInfo userInfo : list) {
			System.out.println(userInfo.getOrders().size());
		}
	}

	@Test
	public void t4() {
		Orders orders = new Orders();
		orders.setId(1);
		List<Orders> list = infoService.getListByUser(orders);
				for (Orders order : list) {
					System.out.println(order.getUserInfo().getName());
				}
	}
}
