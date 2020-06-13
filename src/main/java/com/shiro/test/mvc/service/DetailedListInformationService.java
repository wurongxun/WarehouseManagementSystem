package com.shiro.test.mvc.service;

import com.shiro.test.mvc.base.Bean.DetailedListCommodityInformationBean;
import com.shiro.test.mvc.pojo.DetailedListInformation;

import java.util.List;
import java.util.Map;

/**
 * @Description DetailedListInformationService
 * @Author Rongxun.Wu
 * @Date 2020/2/10 16:15
 * @Version 1.0
 */
public interface DetailedListInformationService {

    List<DetailedListInformation> QureyAllDetailedListInformation(Map map);

    int AddDetailedList(DetailedListCommodityInformationBean detailedListCommodityInformation);

    int updateByPrimaryKeySelective(DetailedListInformation record);

    int ApplicantOutOfStockEntryInformation(DetailedListCommodityInformationBean detailedListCommodityInformation);

    Integer SelectWarehousingOutOfStackCount(Map map);

    Integer SelectDetailedListTypeCount(Map map);

    Integer SelectDetailedListApplicantIdCount(Map map);
}
