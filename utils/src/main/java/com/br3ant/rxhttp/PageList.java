package com.br3ant.rxhttp;

import java.util.List;

/**
 * <pre>
 *     copyright: datedu
 *     @author : br3ant
 *     e-mail : xxx@xx
 *     time   : 2020/8/12
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class PageList<T> {
    public int total_rows;
    public int page;
    public int page_count;
    public List<T> rows;
}
