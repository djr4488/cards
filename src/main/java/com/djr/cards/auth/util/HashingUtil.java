package com.djr.cards.auth.util;

import org.slf4j.Logger;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
/**
 * User: djr4488
 * Date: 1/4/14
 * Time: 6:17 PM
 */
public class HashingUtil {
    @Inject
    private Logger logger;
    @Inject
    private String hmacKey;

    public HashingUtil() { }

    public String generateHmacHash(String message) {
        logger.debug("generateHmacHash() - message:{}", message);
        String ret = "";
        try {
            byte HEX_VALUE2[];
            HEX_VALUE2 = new byte[hmacKey.length() / 2];
            for (int i = 0; i < HEX_VALUE2.length; i++) {
                HEX_VALUE2[i] = (byte) Integer.parseInt(hmacKey.substring(2 * i, 2 * i + 2),
                        16);
            }
            String resultHmacHash = "";
            StringBuffer resultHmacReceiver = new StringBuffer();
            SecretKeySpec secretKeySpec = new SecretKeySpec(HEX_VALUE2, "hmacsha512");
            Mac mac = Mac.getInstance("hmacsha512");
            mac.init(secretKeySpec);
            String message2 = message;
            byte messageFinal[] = mac.doFinal(message2.getBytes());
            byte encryptedMessage[] = org.apache.commons.codec.binary.Base64
                    .encodeBase64(messageFinal, false);
            resultHmacHash = new String(encryptedMessage);
            resultHmacReceiver.append(resultHmacHash);
            ret = resultHmacReceiver.toString();
        }
        catch (Exception e) {
            logger.error("generateHmacHash() - exception:", e);
        }
        return ret;
    }
}
