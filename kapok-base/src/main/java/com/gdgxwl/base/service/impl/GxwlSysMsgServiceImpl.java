package com.gdgxwl.base.service.impl;

import com.gdgxwl.base.domain.GxwlSysMsg;
import com.gdgxwl.base.domain.GxwlSysMsg.MsgType;
import com.gdgxwl.base.domain.GxwlSysMsgReciever;
import com.gdgxwl.base.repository.GxwlSysMsgDao;
import com.gdgxwl.base.repository.GxwlSysMsgRecieverDao;
import com.gdgxwl.base.service.GxwlSysMsgService;
import com.gdgxwl.core.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
@Service(value = "gxwlSysMsgService")
@Transactional(readOnly = true)
public class GxwlSysMsgServiceImpl extends
		BaseServiceImpl<GxwlSysMsgDao, GxwlSysMsg, Integer> implements
		GxwlSysMsgService {

	@Autowired
	private GxwlSysMsgDao gxwlSysMsgDao;
	
	@Autowired
	private GxwlSysMsgRecieverDao gxwlSysMsgRecieverDao;
	
	@Autowired
	public GxwlSysMsgServiceImpl(GxwlSysMsgDao gxwlSysMsgDao) {
		super(gxwlSysMsgDao);
	}

	private boolean validate(GxwlSysMsg msg) {
		if (StringUtils.isEmpty(msg.getMsgTitle())) {
			setResultStatus(1, "消息标题必须填写！");
			return false;
		}
		if (StringUtils.isEmpty(msg.getMsgBody())) {
			setResultStatus(1, "消息内容必须填写！");
			return false;
		}
		if (msg.getMsgType() == null) {
			msg.setMsgType(MsgType.INTERNAL.name());
		}
		if (msg.getSendBy() == null && StringUtils.isEmpty(msg.getSender())) {
			setResultStatus(1, "发送人必须填写！");
			return false;
		}
		if (msg.getRecievers().size() == 0) {
			setResultStatus(1, "接收人必须填写！");
			return false;
		}
		return true;
	}
	
	@Transactional
	@Override
	public Map<String, Object> sendMsg(GxwlSysMsg msg) {
		resetResultMap();
		try {
			if (!validate(msg)) {
				return resultMap;
			} else {
				switch (msg.getMsgType()) {
					case INTERNAL:
						msg.setIsSended("Y");
						msg.setSendedTime(DateTime.now().toDate());
						gxwlSysMsgDao.save(msg);
						for (GxwlSysMsgReciever reciever : msg.getRecievers()) {
							reciever.setMsgId(msg.getMsgId());
							reciever.setIsSend("Y");
							reciever.setSendTime(DateTime.now().toDate());
						}
						setResultStatus(0, "发送消息成功！");
						resultMap.put(RESULT_ROW, gxwlSysMsgDao.save(msg));
						break;
					default:
						break;
				}
				return resultMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "发送消息时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	@Transactional
	@Override
	public Map<String, Object> readMsg(Integer recieverId) {
		resetResultMap();
		try {
			GxwlSysMsgReciever reciever = gxwlSysMsgRecieverDao.findOne(recieverId);
			Map<String, Object> msg = gxwlSysMsgDao.readMsg(recieverId);
			if (!"Y".equalsIgnoreCase(reciever.getIsRead())) {
				reciever.setIsRead("Y");
				reciever.setReadTime(DateTime.now().toDate());
			}
			setResultStatus(0, "读取消息成功！");
			resultMap.put(RESULT_ROW, msg);
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "读取消息时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	@Override
	public Map<String, Object> getSendedList(Integer sendBy,
			MsgType msgType) {
		resetResultMap();
		try {
			setResultStatus(0, "获取发送列表成功！");
			resultMap.put(RESULT_ROWS, gxwlSysMsgDao.getSendedList(sendBy, msgType, null, null));
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "获取发送列表时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	@Override
	public Map<String, Object> getSendedList(String sender,
			MsgType msgType) {
		resetResultMap();
		try {
			setResultStatus(0, "获取发送列表成功！");
			resultMap.put(RESULT_ROWS, gxwlSysMsgDao.getSendedList(sender, msgType, null, null));
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "获取发送列表时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	@Override
	public Map<String, Object> getRecieveredList(Integer recieverBy,
			MsgType msgType) {
		resetResultMap();
		try {
			setResultStatus(0, "获取接收列表成功！");
			resultMap.put(RESULT_ROWS, gxwlSysMsgDao.getRecieveredList(recieverBy, msgType, null, null));
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "获取接收列表时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	@Override
	public Map<String, Object> getRecieveredList(String reciever,
			MsgType msgType) {
		resetResultMap();
		try {
			setResultStatus(0, "获取接收列表成功！");
			resultMap.put(RESULT_ROWS, gxwlSysMsgDao.getRecieveredList(reciever, msgType, null, null));
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "获取接收列表时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	@Override
	public Map<String, Object> getSendedList(Integer sendBy, MsgType msgType, Pageable pageable) {
		resetResultMap();
		try {
			setResultStatus(0, "获取发送列表成功！");
			Integer total = gxwlSysMsgDao.getSendedListTotalElements(sendBy, msgType);
			Integer rows = pageable.getPageSize();
			resultMap.put(RESULT_TOTAL, total);
			resultMap.put("totalPage", Math.ceil(total/rows));
			resultMap.put(RESULT_ROWS, gxwlSysMsgDao.getSendedList(sendBy, msgType, pageable.getPageNumber(), rows));
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "获取发送列表时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	@Override
	public Map<String, Object> getSendedList(String sender, MsgType msgType, Pageable pageable) {
		resetResultMap();
		try {
			setResultStatus(0, "获取发送列表成功！");
			Integer total = gxwlSysMsgDao.getSendedListTotalElements(sender, msgType);
			Integer rows = pageable.getPageSize();
			resultMap.put(RESULT_TOTAL, total);
			resultMap.put("totalPage", Math.ceil(total/rows));
			resultMap.put(RESULT_ROWS, gxwlSysMsgDao.getSendedList(sender, msgType, pageable.getPageNumber(), rows));
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "获取发送列表时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	@Override
	public Map<String, Object> getRecieveredList(Integer recieverBy,
			MsgType msgType, Pageable pageable) {
		resetResultMap();
		try {
			setResultStatus(0, "获取接收列表成功！");
			Integer total = gxwlSysMsgDao.getRecieveredListTotalElements(recieverBy, msgType);
			Integer rows = pageable.getPageSize();
			resultMap.put(RESULT_TOTAL, total);
			resultMap.put("totalPage", Math.ceil(total/rows));
			resultMap.put(RESULT_ROWS, gxwlSysMsgDao.getRecieveredList(recieverBy, msgType, pageable.getPageNumber(), rows));
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "获取接收列表时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	@Override
	public Map<String, Object> getRecieveredList(String reciever,
			MsgType msgType, Pageable pageable) {
		resetResultMap();
		try {
			setResultStatus(0, "获取接收列表成功！");
			Integer total = gxwlSysMsgDao.getRecieveredListTotalElements(reciever, msgType);
			Integer rows = pageable.getPageSize();
			resultMap.put(RESULT_TOTAL, total);
			resultMap.put("totalPage", Math.ceil(total/rows));
			resultMap.put(RESULT_ROWS, gxwlSysMsgDao.getRecieveredList(reciever, msgType, pageable.getPageNumber(), rows));
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "获取接收列表时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

}
