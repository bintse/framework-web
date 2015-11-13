package cn.janine.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.springframework.stereotype.Component;

/**
 * 密码服务类
 * 
 *
 */
@Component
public class SnovaPasswordService implements PasswordService {
    private static final String SNOVA_SALT = "tsp2015@dflzm.com.cn";// password salt

    @Override
    public String encryptPassword(Object plaintextPassword) throws IllegalArgumentException {
        String base64Sha512HashPwd = new Sha512Hash(plaintextPassword, SNOVA_SALT).toBase64();
        return new Md5Hash(base64Sha512HashPwd, SNOVA_SALT).toHex();
    }

    @Override
    public boolean passwordsMatch(Object submittedPlaintext, String encrypted) {
        String pwd = (String) submittedPlaintext;
        return StringUtils.equals(encryptPassword(pwd), encrypted);
    }

}
