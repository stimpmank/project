package org.profamilia.gen.rest.oauth2.util;

import org.profamilia.gen.rest.oauth2.common.consts.Const;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import org.hashids.Hashids;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import java.nio.charset.Charset;

public class EncoderUtil {

    public static final StandardPasswordEncoder pwdEncoder = new StandardPasswordEncoder(Const.cryptSecret);

    public static final Hashids HASHID = new Hashids("TadalinHashId", 6,"0123456789abcdefghijklmnopqrstuvwxyz");

    public static String hashidEncode(Long id) {
        return HASHID.encode(id);
    }

    public static long[] hashidDecode(String str) {
        return HASHID.decode(str);
    }
}
