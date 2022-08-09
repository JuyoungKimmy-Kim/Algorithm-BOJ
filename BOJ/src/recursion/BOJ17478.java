package recursion;

import java.util.Scanner;

public class BOJ17478 {

	static int N;
	static String[] MSG_ARRAY = 
	{
		"\"�� ����. �������� �� �� ����⿡ �̼��� ��� ������ ����� ������ �־���.",
		"���� ������� ��� �� ���ο��� ������ ������ �߰�, ��� �����Ӱ� ����� �־���.",
		"���� ���� ��κ� �ǾҴٰ� �ϳ�. �׷��� ��� ��, �� ���ο��� �� ���� ã�ƿͼ� ������.\""
	};
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner (System.in);
		
		N=sc.nextInt();
		
		System.out.println("��� �� ��ǻ�Ͱ��а� �л��� ������ �������� ã�ư� ������.");
		
		//whatIsRecursion(0,0);
		//whatIsRecursion2(0,0);
		
		chatbot(0);
	} 
	
	//��Ǯ�� 1
	static void whatIsRecursion (int cnt, int under_cnt) {
		
		if (cnt==N) {
			for (int i=0; i<under_cnt; i++) System.out.print("_");
			System.out.println("\"����Լ��� ������?\"");
			for (int i=0; i<under_cnt; i++) System.out.print("_");
			System.out.println("\"����Լ��� �ڱ� �ڽ��� ȣ���ϴ� �Լ����\"");
			for (int i=0; i<under_cnt; i++) System.out.print("_");
			System.out.println("��� �亯�Ͽ���.");
			
			return ;
		}
		
		for (int i=0; i<under_cnt; i++) System.out.print("_");
		System.out.println("\"����Լ��� ������?\"");
		for (int i=0; i<under_cnt; i++) System.out.print("_");
		System.out.println("\"�� ����. �������� �� �� ����⿡ �̼��� ��� ������ ����� ������ �־���.");
		for (int i=0; i<under_cnt; i++) System.out.print("_");
		System.out.println("���� ������� ��� �� ���ο��� ������ ������ �߰�, ��� �����Ӱ� ����� �־���.");
		for (int i=0; i<under_cnt; i++) System.out.print("_");
		System.out.println("���� ���� ��κ� �ǾҴٰ� �ϳ�. �׷��� ��� ��, �� ���ο��� �� ���� ã�ƿͼ� ������.\"");
		
		whatIsRecursion (cnt+1, under_cnt+4);
		
		for (int i=0; i<under_cnt; i++) System.out.print("_");
		System.out.println("��� �亯�Ͽ���.");
		
	}
	//��Ǯ��2
	static void whatIsRecursion2(int cnt, int under_cnt) {

		
		for (int i=0; i<under_cnt; i++) System.out.print("_");
		System.out.println("\"����Լ��� ������?\"");
		
		if (cnt==N) {

			for (int i=0; i<under_cnt; i++) System.out.print("_");
			System.out.println("\"����Լ��� �ڱ� �ڽ��� ȣ���ϴ� �Լ����\"");
			for (int i=0; i<under_cnt; i++) System.out.print("_");
			System.out.println("��� �亯�Ͽ���.");
			
			return ;
		}
		
		for (int i=0; i<under_cnt; i++) System.out.print("_");
		System.out.println("\"�� ����. �������� �� �� ����⿡ �̼��� ��� ������ ����� ������ �־���.");
		for (int i=0; i<under_cnt; i++) System.out.print("_");
		System.out.println("���� ������� ��� �� ���ο��� ������ ������ �߰�, ��� �����Ӱ� ����� �־���.");
		for (int i=0; i<under_cnt; i++) System.out.print("_");
		System.out.println("���� ���� ��κ� �ǾҴٰ� �ϳ�. �׷��� ��� ��, �� ���ο��� �� ���� ã�ƿͼ� ������.\"");
		
		whatIsRecursion (cnt+1, under_cnt+4);
		
		for (int i=0; i<under_cnt; i++) System.out.print("_");
		System.out.println("��� �亯�Ͽ���.");
	}

	static void chatbot (int depth) {
		
		//�ؾ��� ��
		for (int j=0; j<depth; j++) System.out.print("____");
		System.out.println("\"����Լ��� ������?\"");
		
		
		//��������
		if (depth==N) {
			
			//complete code
			for (int i=0; i<depth; i++) System.out.print("____");
			System.out.println("\"����Լ��� �ڱ� �ڽ��� ȣ���ϴ� �Լ����\"");
			for (int i=0; i<depth; i++) System.out.print("____");
			System.out.println("��� �亯�Ͽ���.");
			
			return ;
		}
		
		//�ؾ��� �� ->  �� ����~
		for (int i=0; i<MSG_ARRAY.length; i++) {
			for (int j=0; j<depth; j++) System.out.print("____");
			System.out.println(MSG_ARRAY[i]);
		}
		
		//��� ȣ��
		
		chatbot (depth+1);
		//�ؾ��� ��
		for (int j=0; j<depth; j++) System.out.print("____");
		System.out.println("��� �亯�Ͽ���.");
	}
}
