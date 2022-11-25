package com.puru.puruboard.controller;

import com.puru.puruboard.domain.User;
import com.puru.puruboard.dto.CreateUserDto;
import com.puru.puruboard.dto.UserInfo;
import com.puru.puruboard.service.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    
    private final UserService userService;
    
    // 회원 가입 폼
    @GetMapping
    public String signUpForm(@ModelAttribute("user") CreateUserDto signUpDto) {
        return "user/signup";
    }
    
    // 회원 가입
    @PostMapping
    public String signUp(@Valid @ModelAttribute CreateUserDto signUpDto, BindingResult result)
        throws Exception {
        
        if (result.hasErrors()) {
            return "user/signup";
        }
        userService.createUser(new CreateUserDto(signUpDto.getEmail(), signUpDto.getPassword(),
                                                 signUpDto.getNickname()));
        return "user/success-sign-up";
    }
    
    // 로그인 폼
//    @GetMapping("/login")
//    public String loginForm() {
//        return "/login";
//    }
    
    // 로그인
    @PostMapping("/login")
    public String loginSuccess() {
        return "redirect:/";
    }
    
    // 마이 페이지
    @GetMapping("/info")
    public String getMyInfo(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = user.getEmail();
        System.out.println("email = " + email);
        
        if (email.isEmpty()) {
            return "error";
        }
        
        UserInfo userInfo = userService.getUserInfo(email);
        model.addAttribute("userInfo", userInfo);
        
        return "user/my-page";
    }
}
