package com.dfbz.service.Impl;

import com.dfbz.entity.WasteType;
import com.dfbz.service.WasteTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/6 19:14
 * @desciption
 */
@Service
@Transactional
public class WasteTypeServiceImpl extends BaseServiceImpl<WasteType> implements WasteTypeService {
}
