package exitProject.config.auth;

import exitProject.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable()
            .and()
                //URL별 권한관리 시작점 얘가 선언되어야함
                .authorizeRequests()
                //모든 사람들이 접속가능
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                //USER 권한을 가진 사람들만 접속 가능
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                //이외의 나머지들은 인증된 사용자 (로그인된 사용자)
                .anyRequest().authenticated()
            .and()
                //로그아웃시 이동하는 URL (로그아웃에 관련된 설정은 여기에)
                .logout().logoutSuccessUrl("/")
            .and()
                //OAuth2 로그인 기능에대한 설정 진입
                .oauth2Login()
                //로그인 성공 이후 사용자 정보 가져올떄의 설정
                .userInfoEndpoint()
                //로그인 성공이후 후속 조치를 진행할 UserService 인터페이스 등록
                //리소스 서버에서 사용자정보를 가져온 뒤 추가로 할 작업들 명시
                .userService(customOAuth2UserService);
    }
}
