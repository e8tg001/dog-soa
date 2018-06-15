/**
 * 
 */
package com.dog.soa.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * java8 读取集合的多种写法
 * 性能待议；
 * @author jianglong
 * @date 2017年7月10日 上午10:22:05
 */
public class Java8Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		joiningList();
		listTest();
	}

	
	public static void listTest(){
		Java8Test jt = new Java8Test();
		UserBean u1 = jt.new UserBean();
		u1.setName("jiang");
		u1.setAge(10);
		u1.setCourses(Arrays.asList("语文","数学","自然"));
		
		UserBean u2 = jt.new UserBean();
		u2.setName("li");
		u2.setAge(10);
		u2.setCourses(Arrays.asList("英语","数学","语文"));
		
		UserBean u3 = jt.new UserBean();
		u3.setName("zhang");
		u3.setAge(11);
		u3.setCourses(Arrays.asList("英语","几何","自然"));
		
		List<UserBean> uList = new ArrayList();
		uList.add(u1);
		uList.add(u2);
		uList.add(u3);
		jt.getFirstYuWen(uList);
		jt.getAllYuWen(uList);
		jt.getGroupAge(uList);
		jt.getAllCourse(uList);
	}
	
	/**
	 * 找到集合中第一个包含“语文”课程的学生
	 * @param uList
	 */
	public void getFirstYuWen(List<UserBean> uList){
		System.out.println("--------------->>打印第一个有语文课程学生");
		Optional <UserBean> ous = uList.stream().filter(userBean -> userBean.getCourses().contains("语文")).findFirst();
		System.out.println(ous.get().getName());
	}
	
	/**
	 * 找到集合中第一个包含“语文”课程的学生
	 * @param uList
	 */
	public void getAllYuWen(List<UserBean> uList){
		System.out.println("--------------->>打印所有有语文课程学生");
		Stream <UserBean> ous = uList.stream().filter(userBean -> userBean.getCourses().contains("语文"));
		List<UserBean> nList = ous.collect(Collectors.toList());
		for (UserBean bean : nList){
			System.out.println(bean.getName());
		}
	}
	
	/**
	 * 打印所有年龄分组
	 * @param uList
	 */
	public void getGroupAge(List<UserBean> uList){
		System.out.println("--------------->>打印所有年龄分组");
		Map<Integer,List<UserBean>> map = uList.stream().collect(Collectors.groupingBy(UserBean :: getAge));
		for (Entry<Integer,List<UserBean>> entry : map.entrySet()){
			System.out.println(entry.getKey());
		}
	}
	
	/**
	 * 打印所有课程
	 * @param uList
	 */
	public void getAllCourse(List<UserBean> uList){
		System.out.println("--------------->>打印所有课程");
		Set<String> courses = uList.stream().flatMap(userBean -> userBean.getCourses().stream()).collect(Collectors.toSet());
		for (String s : courses){
			System.out.println(s);
		}
	}
	
	/**
	 * list测试
	 */
	public static void joiningList() {
	    // 生成一段[0,20)序列
	    List<Integer> list = IntStream.range(0, 20)
	            .boxed()
	            .collect(Collectors.toList());

	    // 将list内的偶数提取反向排序后聚合为一个String
	    String string = list.stream()
	            .filter(n -> n % 2 == 0)
	            .sorted(Comparator.comparing((Integer i) -> i).reversed())
	            .limit(3)
	            .peek((i) -> System.out.println("remained: " + i))
	            .map(String::valueOf)
	            .collect(Collectors.joining());

	    System.out.println(string);
	}
	
	
	public class UserBean{
		private String name;//名称
		private int age;//年龄
		private List<String> courses;//课程
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public List<String> getCourses() {
			return courses;
		}
		public void setCourses(List<String> courses) {
			this.courses = courses;
		}
	}
	
}
