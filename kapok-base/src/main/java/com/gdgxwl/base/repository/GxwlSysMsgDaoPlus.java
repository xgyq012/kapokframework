package com.gdgxwl.base.repository;

import com.gdgxwl.base.domain.GxwlSysMsg.MsgType;
import com.gdgxwl.core.repository.BaseDaoPlus;

import java.util.List;
import java.util.Map;

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
