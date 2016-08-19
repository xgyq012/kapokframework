package com.nateiot.base.repository;

import java.util.List;
import java.util.Map;

import com.nateiot.base.domain.GxwlSysMsg.MsgType;
import com.nateiot.core.repository.BaseDaoPlus;

/**
 * @author Will WM. Zhang
 * 
 */
public interface GxwlSysMsgDaoPlus extends BaseDaoPlus {

	public Map<String, Object> readMsg(Integer recieverId);
	
	public Integer getSendedListTotalElements(Integer sendBy, MsgType msgType);
	
	public Integer getSendedListTotalElements(String sender, MsgType msgType);

	public Integer getRecieveredListTotalElements(Integer recieverBy, MsgType msgType);
	
	public Integer getRecieveredListTotalElements(String reciever, MsgType msgType);
	
	public List<Map<String, Object>> getSendedList(Integer sendBy,
			MsgType msgType, Integer page, Integer rows);
	
	public List<Map<String, Object>> getSendedList(String sender,
			MsgType msgType, Integer page, Integer rows);

	public List<Map<String, Object>> getRecieveredList(Integer recieverBy,
			MsgType msgType, Integer page, Integer rows);
	
	public List<Map<String, Object>> getRecieveredList(String reciever,
			MsgType msgType, Integer page, Integer rows);
	
}
