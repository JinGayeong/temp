package member.service;

import member.model.Member;


public class MypageData {

	//강의 자료 AritcleData는 article과 content 둘다 가져오려고 만든것 같은데
	//여기는 멤버만 가져오는데 굳이 이렇게 할 필요 가 있나?? 나중에 확인해볼 필요가 있는 부분.
	
	private Member member;

	public MypageData(Member member) {
		this.member = member;
	}
	
	public Member getMypage() {
		return member;
	}
	
}
