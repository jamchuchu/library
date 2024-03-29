package com.korit.library.security;

import com.korit.library.entity.UserMst;
import com.korit.library.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2DetailsService extends DefaultOAuth2UserService {

    private final AccountRepository accountRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        PrincipalDetails principalDetails = null;

        System.out.println("clientRegistration>>" +  userRequest.getClientRegistration());
        System.out.println("atributes>>" +  oAuth2User.getAuthorities());

        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");
        String username = ((String) attributes.get("email")).substring(0,email.indexOf("@"));

        String provider = userRequest.getClientRegistration().getClientName();
        UserMst userMst =  accountRepository.findUserByUsername(username);

        if(userMst == null) {
            String name = (String)attributes.get("name");
            String password = new BCryptPasswordEncoder().encode(UUID.randomUUID().toString());
            userMst = UserMst.builder()
                    .username(username)
                    .password(password)
                    .name(name)
                    .email(email)
                    .provider(provider)
                    .build();

            accountRepository.saveUser(userMst);
            accountRepository.saveRole(userMst);
            userMst =  accountRepository.findUserByUsername(username);//빈공강 채워서 다시 들고옴


        }else if(userMst.getProvider() == null){
            userMst.setProvider(provider);
            accountRepository.setUserProvider(userMst);
        }

        principalDetails = new PrincipalDetails(userMst, attributes);


        return principalDetails;
    }
}
