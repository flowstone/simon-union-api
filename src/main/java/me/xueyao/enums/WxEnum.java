package me.xueyao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Simon.Xue
 * @date 2020-03-05 23:48
 **/

public interface WxEnum {

    @AllArgsConstructor
    @Getter
    enum MsgType {
        TEXT("text"),
        IMAGE("image"),
        VOICE("voice"),
        VIDEO("video"),
        SHORT_VIDEO("shortvideo"),
        LOCATION("location"),
        LINK("link");
        String msg;
    }

    @AllArgsConstructor
    @Getter
    enum Event {
        subscribe("subscribe"),
        unsubscribe("unsubscribe");
        String msg;
    }

    @AllArgsConstructor
    @Getter
    enum ResultCode {
        errCode("errcode");
        String msg;
    }

}
