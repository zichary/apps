package com.apps.vm;

import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.annotation.Init;

/**
 * @author SimonYang
 * @version 1.0.0
 * @date 2019-03-25
 */
@Slf4j
public class MemberVM {

    @Init
    public void init(){
            log.info("載入member頁面！");
    }
}
