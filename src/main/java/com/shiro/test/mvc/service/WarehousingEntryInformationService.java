package com.shiro.test.mvc.service;/**
 * @Description WarehousingEntryService
 * @Author Rongxun.Wu
 * @Date 2020/2/10 14:03
 * @Version 1.0
 */

import com.shiro.test.mvc.pojo.WarehousingEntryInformation;

import java.util.List;

/**
 *
 * @ClassName WarehousingEntryService
 * @Description WarehousingEntryService
 * @Author Rongxun.Wu
 * @Date 2020/2/10 14:03
 * @Version 1.0
 *
 */
public interface WarehousingEntryInformationService {
    
    List<WarehousingEntryInformation> QureyAllWarehousingEntryInformation();

    int AddWarehousingEntryInformation(WarehousingEntryInformation warehousingEntry);
}
