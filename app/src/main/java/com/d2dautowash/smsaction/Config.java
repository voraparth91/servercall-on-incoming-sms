package com.d2dautowash.smsaction;

import java.util.HashMap;
import java.util.Map;

class Config {
    static Map<String, String> SMS_RULES = new HashMap<>();

    static {
        SMS_RULES.put("AMAZONSDK","http://yourbaseurl1.com/api/n2/sms/inbound");
        SMS_RULES.put("AMAZONUPI","https://yourbaseurl2.com/api/n2/sms/inbound");
    }
}
