package com.tinkey.one.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author tangfeng
 * @fileName AuthenticationExample
 * @date 2019/6/5 16:46
 * @description xxxx
 */
@Slf4j
public class AuthenticationExample  {

    private static AuthenticationManager am=new SampleAuthenticationManager();

    public static void main(String[] args) throws IOException  {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            System.out.println("Please enter your username:");
            String name = in.readLine();
            System.out.println("Please enter your password:");
            String password = in.readLine();
            try {
                Authentication request = new UsernamePasswordAuthenticationToken(name, password);
                Authentication result = am.authenticate(request);
                SecurityContextHolder.getContext().setAuthentication(result);
                break;
            } catch(AuthenticationException e) {
                System.out.println("Authentication failed: " + e.getMessage());
            }
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Successfully authenticated. Security context contains: " + authentication);
    }
}
