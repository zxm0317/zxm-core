package utry.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import utry.pojo.UserInfo;
import utry.service.IUserInfoService;

@Controller
@RequestMapping("/user")
public class UserInfoController {
	@Resource
	private IUserInfoService infoService;
	@RequestMapping("/info")
	public @ResponseBody List<UserInfo> getList() {
		return infoService.getList();
	}
}
