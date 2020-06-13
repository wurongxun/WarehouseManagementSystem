package com.shiro.test.mvc.service.impl;

import com.shiro.test.mvc.dao.WarehousingEntryInformationMapper;
import com.shiro.test.mvc.pojo.WarehousingEntryInformation;
import com.shiro.test.mvc.service.WarehousingEntryInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Description WarehousingEntryService
 * @Author Rongxun.Wu
 * @Date 2020/2/10 14:03
 * @Version 1.0
 */

@Service
public class WarehousingEntryInformationServiceImpl implements WarehousingEntryInformationService {

    @Autowired
    WarehousingEntryInformationMapper warehousingEntryMapper;

    @Override
    public List<WarehousingEntryInformation> QureyAllWarehousingEntryInformation() {
        return warehousingEntryMapper.QureyAllWarehousingEntryInformation();
    }

    @Override
    public int AddWarehousingEntryInformation(WarehousingEntryInformation warehousingEntry) {

        return 0;
    }
}
