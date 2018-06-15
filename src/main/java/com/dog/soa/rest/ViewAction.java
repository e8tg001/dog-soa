package com.dog.soa.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 进入系统主界面
 * @author jianglong
 * @date 2017年7月5日 下午5:44:26
 */
@Controller
public class ViewAction {
	
	@RequestMapping(value="/main")
	public String main(){
		return "main";
	}
}
