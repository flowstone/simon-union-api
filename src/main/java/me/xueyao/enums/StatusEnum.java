package me.xueyao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Simon.Xue
 * @date 2020-03-20 17:19
 **/
@AllArgsConstructor
@Getter
public enum StatusEnum {
    SUCCESS(200, "请求成功"),
    BAD_PARAM(403, "请求参数错误"),
    SYSTEM(500, "服务器错误");
    Integer code;
    String msg;
}
