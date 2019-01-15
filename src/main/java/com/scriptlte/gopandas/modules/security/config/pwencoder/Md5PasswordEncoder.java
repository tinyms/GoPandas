package com.scriptlte.gopandas.modules.security.config.pwencoder;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
public class Md5PasswordEncoder implements PasswordEncoder {
    private static final Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
    @Override
    public String encode(CharSequence originPwd) {
        if (StringUtils.isBlank(originPwd)){
            return "";
        }
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String digestHex = md5.digestHex(originPwd.toString());//5393554e94bf0eb6436f240a4fd71282
        return digestHex;
    }

    /**
     * @param originPwd 用户登录输入的密码字符串
     * @param handledPwd 数据库中经过加密后的密码字符串
     * @return
     */
    @Override
    public boolean matches(CharSequence originPwd, String handledPwd) {
        if (StringUtils.isBlank(originPwd) || StringUtils.isBlank(handledPwd)){
            if (log.isDebugEnabled()){
                log.debug(String.format("originPwd:[%s] Or handledPwd:[%s] is empty! Authentication failed!", originPwd,handledPwd));
            }
            return false;
        }
        return StringUtils.equals(encode(originPwd),handledPwd);
    }

    private Md5PasswordEncoder(){}
    public static Md5PasswordEncoder getInstance(){
        return md5PasswordEncoder;
    }

}
