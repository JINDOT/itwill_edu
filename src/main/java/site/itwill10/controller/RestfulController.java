package site.itwill10.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import site.itwill10.dto.Member;

//REST(RePresentation State Transfer) ����� �����ϴ� ��Ʈ�ѷ�
// => �ϳ��� ��û URL �ּҿ� �ϳ��� ���ҽ�(TEXT, XML, JSON ��) ����
// => �پ��� ��⿡�� ��û�� ���� �������� ���������� ó�� ������ �ؽ�Ʈ ����Ÿ�� �����ϴ� ���� ����
// => �����α׷����� AJAX ������� ��û�� ���� �������� �����޾� DHTML�� ��ȯ�Ͽ� ����
@Controller
public class RestfulController {
	@RequestMapping(value = "/rest", method = RequestMethod.GET)
	public String rest() {
		return "rest/input";
	}
	
	/*
	@RequestMapping(value = "/rest", method = RequestMethod.POST)
	public String rest(@RequestParam String id, @RequestParam String name, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		return "rest/output";
	}
	*/
	
	//@ResponseBody : ��û ó�� �޼ҵ��� ��ȯ��(���ڿ�)�� ViewResolver���� �������� �ʰ� 
	//�޼ҵ��� ��ȯ������ ���� �����ϵ��� �����ϴ� ������̼�
	//@RequestBody : POST, PUT, PATCH, DELETE ���� ��û�� ���� ���޵� ��� ���� JSON ������
	//�ؽ�Ʈ ����Ÿ(JavaScript�� Object ��ü)�� ���޹޾� �����ϱ� ���� ������̼�
	// => GET ������� ��û�Ͽ� ���޵� ���� �Ű������� ���� �Ұ���
	// => Ajax ��û�� ���޵� ���� �����ϱ� ���� ���
	@RequestMapping(value = "/rest", method = RequestMethod.POST)
	@ResponseBody
	public String rest(@RequestBody String input) {
		return input;
	}
	
	//ȸ�������� JSON ������ �ؽ�Ʈ ����Ÿ�� �����ϴ� ��û ó�� �޼ҵ�
	// => ���ڿ��� �ƴ� Java �ڷ����� ��ü�� ������ ��� 500 ���� �߻�
	//Java �ڷ����� ��ü�� JSON ������ �ؽ�Ʈ ����Ÿ(���ڿ�)�� �ڵ� ��ȯ�Ͽ� ����
	// => jackson-databind ���̺귯�� ���� ó�� - pom.xml
	@RequestMapping("/rest_member")
	@ResponseBody
	public Member restMember() {
		Member member=new Member();
		member.setId("abc123");
		member.setPasswd("123456");
		member.setName("ȫ�浿");
		member.setEmail("abc@itwill.site");
		
		return member;
	}
	
	//ȸ������� JSON ������ �ؽ�Ʈ ����Ÿ�� �����ϴ� ��û ó�� �޼ҵ�
	@RequestMapping("/rest_memberList")
	@ResponseBody
	public List<Member> restMemberList() {
		List<Member> memberList=new ArrayList<Member>();
		
		Member member1=new Member();
		member1.setId("abc123");
		member1.setPasswd("123456");
		member1.setName("ȫ�浿");
		member1.setEmail("abc@itwill.site");
		
		Member member2=new Member();
		member2.setId("xyz789");
		member2.setPasswd("789123");
		member2.setName("�Ӳ���");
		member2.setEmail("xyz@itwill.site");
		
		memberList.add(member1);
		memberList.add(member2);
		
		return memberList;
	}

	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String input() {
		return "rest/input";
	}
	
	//�Ű������� Map���� �����ϸ� ��� ���ް��� Map ��ü�� ��Ʈ���� �����޾� ����
	// => @RequestParam ������̼��� �ݵ�� ���
	//JavaBean(DTO) Ŭ������ ���� ��� Map ��ü�� �̿��Ͽ� ���ް��� �����ްų� ���� ó��
	@RequestMapping(value = "/map", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> input(@RequestParam Map<String, String> map) {
		return map;
	}
}