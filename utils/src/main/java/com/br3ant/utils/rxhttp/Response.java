package com.br3ant.utils.rxhttp;

/**
 * <pre>
 *     copyright: datedu
 *     @author : br2ant3
 *     e-mail : xxx@xx
 *     time   : 2019/07/27
 *     desc   : 戴特通用Response
 *     version: 1.0
 * </pre>
 */
public class Response<T> {
    public int code;
    public String message = "未正常进行解析,请联系管理员";
    public T data;
}
