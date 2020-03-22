package me.xueyao.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.xueyao.enums.StatusEnum;

/**
 * @author Simon.Xue
 * @date 2020-03-03 20:26
 **/
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class R<T> {
    private Integer code;
    private String message;
    private T data;

    public R(String msg, T object) {
        this.message = msg;
        this.data = object;
    }

    public R(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }


    public T getData() {
        return this.data;
    }


    public static <T> R<T> ofParam(String msg) {
        return new R(StatusEnum.BAD_PARAM.getCode(), msg);
    }

    public static <T> R<T> ofSuccess(String msg) {
        return new R(StatusEnum.SUCCESS.getCode(), msg);
    }

    public static <T> R<T> ofSuccess(String msg, T data) {
        return new R(StatusEnum.SUCCESS.getCode(), msg, data);
    }

    public static <T> R<T> ofSystem(String msg) {
        return new R(StatusEnum.SYSTEM.getCode(), msg);
    }

    public boolean getSuccess() {
        return StatusEnum.SUCCESS.getCode().equals(this.code);
    }

}
