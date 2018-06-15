/**
 * 
 */
package com.dog.soa.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jianglong
 * @date 2017年7月5日 上午10:19:16
 */
@RestController
@RequestMapping("/rest")
public class BaiseAction {

	public static final String SUCCESS = "success";
	public static final String FAIL = "fail";
	public static final String ERROR = "error";
	
}
