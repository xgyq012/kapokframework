package com.gdgxwl.base.service.impl;

import com.gdgxwl.base.domain.GxwlSysCoderule;
import com.gdgxwl.base.repository.GxwlSysCoderuleDao;
import com.gdgxwl.base.service.GxwlSysCoderuleService;
import com.gdgxwl.core.service.BaseService;
import com.gdgxwl.core.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Will WM. Zhang
 * 
 */
@Service(value = "gxwlSysCoderuleService")
@Transactional(readOnly = true)
public class GxwlSysCoderuleServiceImpl extends
		BaseServiceImpl<GxwlSysCoderuleDao, GxwlSysCoderule, Integer> implements
		GxwlSysCoderuleService,
		BaseService<GxwlSysCoderuleDao, GxwlSysCoderule, Integer> {

	@Autowired
	public GxwlSysCoderuleDao gxwlSysCoderuleDao;

	@Autowired
	public GxwlSysCoderuleServiceImpl(GxwlSysCoderuleDao gxwlSysCoderuleDao) {
		super(gxwlSysCoderuleDao);
	}

	@Override
	@Transactional
	public String generateCode(String coderuleCode) {
		GxwlSysCoderule gxwlSysCoderule = gxwlSysCoderuleDao.findByCoderuleCode(coderuleCode);
		
		if (gxwlSysCoderule != null) {
			String isRandom = gxwlSysCoderule.getIsRandom();
			Integer randomNumber = gxwlSysCoderule.getRandomNumber();
			
			if ("Y".equalsIgnoreCase(isRandom)) {
				return RandomStringUtils.randomAlphanumeric(randomNumber);
			} else {
				StringBuffer sb = new StringBuffer();
				
				String prefix = gxwlSysCoderule.getPrefix();
				String suffix = gxwlSysCoderule.getSuffix();
				String cycFormat = gxwlSysCoderule.getCycFormat();
				Integer serialNumber = gxwlSysCoderule.getSerialNumber();
				Integer currentValue = gxwlSysCoderule.getCurrentValue();
				Integer minValue = gxwlSysCoderule.getMinValue();
				Integer maxValue = gxwlSysCoderule.getMaxValue();
				Integer serialStep = gxwlSysCoderule.getSerialStep();
				Integer nextValue;
				
				if (currentValue == null) {
					nextValue = minValue;
				} else {
					nextValue = currentValue + serialStep;
				}
				if (nextValue > maxValue) {
					return null;
				}
				
				gxwlSysCoderule.setCurrentValue(nextValue);
				gxwlSysCoderuleDao.save(gxwlSysCoderule);
				
				sb.append(prefix == null ? "" : prefix);
				sb.append(DateTime.now().toString(cycFormat));
				sb.append(StringUtils.leftPad(nextValue.toString(), serialNumber, '0'));
				sb.append(suffix == null ? "" : suffix);
				
				return sb.toString();
			}
		}
		return null;
	}
	
}
