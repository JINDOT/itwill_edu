package site.itwill04.bean;

public class InitDestroyMethodBean {
	public InitDestroyMethodBean() {
		System.out.println("### InitDestroyMethodBean Ŭ������ �⺻ ������ ȣ�� ###");
	}
	
	//��ü ���� �� �ʱ�ȭ �۾��� ���� �ѹ��� ȣ���ϱ� ���� �޼ҵ�
	public void init() {
		System.out.println("*** InitDestroyMethodBean Ŭ������ init() �޼ҵ� ȣ�� ***");
	}
	
	//��ü �Ҹ� �� ������ �۾��� ���� �ѹ��� ȣ���ϱ� ���� �޼ҵ�
	public void destory() {
		System.out.println("*** InitDestroyMethodBean Ŭ������ destory() �޼ҵ� ȣ�� ***");
	}
	
	public void display() {
		System.out.println("*** InitDestroyMethodBean Ŭ������ display() �޼ҵ� ȣ�� ***");
	}
}
