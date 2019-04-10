package test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;

public class JwtTest {

    @Test
    public void test1(){
        long now = System.currentTimeMillis();
        JwtBuilder builder = Jwts.builder()
                //添加自定义信息
                .claim("roles","admin")
                .setExpiration(new Date(300*1000+now))//设置一个令牌过期时间
                .setId("666")//设置唯一标识符jti
                .setIssuedAt(new Date())//token签发的时间   iat
                .setSubject("张三") //token面向的用户是张三
                //第一个参数设置为签名的算法
                //第二哥参数是盐   加盐算法
                /*   加盐算法 的字符串不能低于四位，至少为三位字符串      */
                .signWith(SignatureAlgorithm.HS256,"wlhmr");
                //给令牌设置一个有效时间
        System.out.println(builder.compact());

    }

    @Test
    public void test2(){
        String token ="eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6ImFkbWluIiwiZXhwIjoxNTU0MjU2Nzg0LCJqdGkiOiI2NjYiLCJpYXQiOjE1NTQyNTY0ODQsInN1YiI6IuW8oOS4iSJ9.lBBhvaZ91ynHYkjALMGHxSCnMGbQGcF9ItE3aQC1h74";
        Claims claims = Jwts.parser()
                .setSigningKey("wlhmr")//设置进行解析创建的盐
                .parseClaimsJws(token)//设置需要进行 解析的token
                .getBody();//获取解析后的claims

        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(claims.getIssuedAt());
        System.out.println(claims.get("roles"));
    }
}
