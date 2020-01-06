package com.dfbz.service.Impl;

import com.dfbz.entity.Waste;
import com.dfbz.service.WasteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/6 19:28
 * @desciption
 */
@Service
@Transactional
public class WasteServiceImpl extends BaseServiceImpl<Waste> implements WasteService {
}
