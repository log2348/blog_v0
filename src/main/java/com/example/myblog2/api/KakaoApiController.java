package com.example.myblog2.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.example.myblog2.dto.KakaoProfile;
import com.example.myblog2.dto.KakaoProfile.KakaoAccount;
import com.example.myblog2.dto.OAuthToken;
import com.example.myblog2.model.User;
import com.example.myblog2.service.UserService;

@Controller
public class KakaoApiController {
	
	@Value("${kakao.key}")
	private String kakaoUserKey;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	private static final String APP_KEY = "a230199492e4aec300eeb42c5cdbb121"; // REST API 키
	private static final String REDIRECT_URI = "http://localhost:9090/oauth/kakao/callback";
	private static final String GRANT_TYPE = "authorization_code";
	private static final String AUTHORIZATION_CODE = "JWVdB5608wINwKX2wXLd5Fp6CFJDGzoSCXh1560NFWsDe-L3eOTEMpUjsBTQvzcLilmZaQopb1UAAAGBs1VPig";
	private static final String ACCESS_TOKEN = "UkkhbhUZ8wZKo7NeOr6U9m1GPNqjaqr_8QxmkNWdCisM1AAAAYGzWWaz";
	
	/*
	@GetMapping("/oauth/kakao/callback")
	@ResponseBody
	public String getAuthorizationCode(@RequestParam String code) {
		// 1. 인가코드 받기
		return "인가 코드 : " + code;
	}
	*/

//	@ResponseBody
	@GetMapping("/oauth/kakao/callback")
	public String kakaoCallback(@RequestParam String code) {
		
		// 2. 토큰 받기
		
		RestTemplate restTemplate = new RestTemplate();
		// 헤더
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		// 바디
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		
		params.add("grant_type", GRANT_TYPE);
		params.add("client_id", APP_KEY);
		params.add("redirect_uri", REDIRECT_URI);
		params.add("code", code);
		
		// 헤더와 바디 하나의 오브젝트로 담기
		HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<MultiValueMap<String,String>>(params, headers);
		
		// HTTP 요청에 대한 응답
		ResponseEntity<OAuthToken> response = restTemplate.exchange(
				"https://kauth.kakao.com/oauth/token",
						HttpMethod.POST,
						tokenRequest,
						OAuthToken.class);
		
		// 3. 사용자 정보 가져오기
		RestTemplate kakaoUserInfoRestTemplate = new RestTemplate();

		// 헤더
		HttpHeaders kakaoUserInfoHeaders = new HttpHeaders();
		kakaoUserInfoHeaders.add("Authorization", "Bearer " + response.getBody().getAccessToken());
		kakaoUserInfoHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		// 바디 없이 헤더만 담기
		HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(kakaoUserInfoHeaders);
		
		// HTTP 요청에 대한 응답
		ResponseEntity<KakaoProfile> kakaoUserInfoResponse = kakaoUserInfoRestTemplate.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoUserInfoRequest,
				KakaoProfile.class);
		
		/*
		 * 4. 소셜 로그인 처리하기
		 * 
		 * 신규 사용자라면 회원가입처리
		 * 한번이라도 가입 진행된 사용자라면 로그인처리
		 */
		
		KakaoAccount kakaoAccount = kakaoUserInfoResponse.getBody().getKakaoAccount();
		
		User kakaoUser = User.builder()
				.username(kakaoAccount.getEmail() + "_" + kakaoUserInfoResponse.getBody().getId())
				.password(kakaoUserKey)
				.email(kakaoAccount.getEmail())
				.oauth("Kakao")
				.build();
		
		User originUser = userService.checkOldUser(kakaoUser.getUsername());
		
		if(originUser.getUsername() == null) {
			// 신규 회원이므로 회원가입 처리
			userService.saveUser(kakaoUser);
		}
		
		// 시큐리티 세션에 유저 정보 저장
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), kakaoUserKey));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "redirect:/";
	}
	
}
