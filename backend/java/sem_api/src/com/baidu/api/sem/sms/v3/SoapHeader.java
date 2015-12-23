package com.baidu.api.sem.sms.v3;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

import org.apache.axis.message.SOAPHeaderElement;

public class SoapHeader {
    private static final String username = "baidu-陆金所借贷bdPC1-8140018";
    private static final String password = "Lujinsuo20140901";
    private static final String token = " 072583e41ec8bf63ff7f5914fb4816bd";
    private static final String namespace = "https://api.baidu.com/sem/sms/v3";

    public SOAPHeaderElement getHeader() throws SOAPException {
        SOAPHeaderElement header = new SOAPHeaderElement(namespace,
                "AuthHeader");
        SOAPElement username_e = header.addChildElement("username");
        username_e.addTextNode(username);
        SOAPElement password_e = header.addChildElement("password");
        password_e.addTextNode(password);
        SOAPElement token_e = header.addChildElement("token");
        token_e.addTextNode(token);

        return header;
    }
}
