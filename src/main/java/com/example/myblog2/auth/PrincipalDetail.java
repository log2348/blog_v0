package com.example.myblog2.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.myblog2.model.User;

import lombok.Data;

@Data
public class PrincipalDetail implements UserDetails{	
	
	/**
	 * 모든 클래스는 UID를 가지고 있다.
	 * 클래스의 내용이 변경되면 UID 값 역시 변경된다.
	 * 직렬화하여 통신하고 UID값으로 통신한 게 정상인지 확인하는데,
	 * 그 값이 바뀌면 다른 클래스로 인식하게 된다.
	 * 이를 방지하기 위해 고유값으로 미리 명시를 해주는 부분이 serialVersionUID이다.
	 */
	private static final long serialVersionUID = 1L;
	
	private User user; // 컴포지션 관계
	
	public PrincipalDetail(User user) {
		this.user = user;
	}	

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	// 계정 만료 여부
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호 만료 여부
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정 활성화 여부
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> collectors = new ArrayList<>();
		
		collectors.add(() -> {
			return "ROLE_" + user.getRole();
		});
		
		return collectors;
	}

}
