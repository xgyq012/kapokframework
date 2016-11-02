package com.gdgxwl.base.service;

import com.gdgxwl.base.domain.GxwlSysMsg;
import com.gdgxwl.base.domain.GxwlSysMsg.MsgType;
import com.gdgxwl.base.repository.GxwlSysMsgDao;
import com.gdgxwl.core.service.BaseService;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
public interface GxwlSysMsgService extends
		BaseService<GxwlSysMsgDao, GxwlSysMsg, Integer> {

	/**
	 * 发送消息
	 * 
	 * @param msg 为消息对象其中包含了以下属性：<br/>
	 * 	      msgTitle（消息标题）必填<br/>
	 * 	      msgBody（消息内容）必填<br/>
	 * 	      msgType（消息类型：INTERNAL, SMS, EMAIL）必填，默认为INTERNAL<br/>
	 * 	      sendBy（发送人，当发送人是基础应用中的用户时（userId），使用此字段）<br/>
	 * 	      sender（发送人，当发送人是非基础应用中的用户时，使用此字段）<br/>
	 * 		  sendBy与sender二选一必填
	 * @return
	 */
	public Map<String, Object> sendMsg(GxwlSysMsg msg);

	/**
	 * 读取消息
	 * 
	 * @param recieverId
	 * @return 
	 */
	public Map<String, Object> readMsg(Integer recieverId);

	/**
	 * 根据发送人获取消息的发送列表
	 * 
	 * @param sendBy 发送人（基础应用中的用户（userId））
	 * @param msgType 消息类型：INTERNAL, SMS, EMAIL，为null时返回所有类型的消息
	 * @return
	 */
	public Map<String, Object> getSendedList(Integer sendBy, MsgType msgType);

	/**
	 * 根据发送人获取消息的发送列表
	 * 
	 * @param sender 发送人（非基础应用中的用户）
	 * @param msgType 消息类型：INTERNAL, SMS, EMAIL，为null时返回所有类型的消息
	 * @return
	 */
	public Map<String, Object> getSendedList(String sender, MsgType msgType);

	/**
	 * 根据接收人获取消息的接收列表
	 * 
	 * @param recieverBy 接收人（基础应用中的用户（userId））
	 * @param msgType 消息类型：INTERNAL, SMS, EMAIL，为null时返回所有类型的消息
	 * @return
	 */
	public Map<String, Object> getRecieveredList(Integer recieverBy, MsgType msgType);

	/**
	 * 根据接收人获取消息的接收列表
	 * 
	 * @param reciever 接收人（非基础应用中的用户）
	 * @param msgType 消息类型：INTERNAL, SMS, EMAIL，为null时返回所有类型的消息
	 * @return
	 */
	public Map<String, Object> getRecieveredList(String reciever, MsgType msgType);

	/**
	 * 根据发送人获取消息的发送列表
	 * 
	 * @param sendBy 发送人（基础应用中的用户（userId））
	 * @param msgType 消息类型：INTERNAL, SMS, EMAIL，为null时返回所有类型的消息
	 * @param pageable 分页对象：为null时返回第1页的10条数据
	 * @return
	 */
	public Map<String, Object> getSendedList(Integer sendBy, MsgType msgType, Pageable pageable);

	/**
	 * 根据发送人获取消息的发送列表
	 * 
	 * @param sender 发送人（非基础应用中的用户）
	 * @param msgType 消息类型：INTERNAL, SMS, EMAIL，为null时返回所有类型的消息
	 * @param pageable 分页对象：为null时返回第1页的10条数据
	 * @return
	 */
	public Map<String, Object> getSendedList(String sender, MsgType msgType, Pageable pageable);

	/**
	 * 根据接收人获取消息的接收列表
	 * 
	 * @param recieverBy 接收人（基础应用中的用户（userId））
	 * @param msgType 消息类型：INTERNAL, SMS, EMAIL，为null时返回所有类型的消息
	 * @param pageable 分页对象：为null时返回第1页的10条数据
	 * @return
	 */
	public Map<String, Object> getRecieveredList(Integer recieverBy, MsgType msgType, Pageable pageable);

	/**
	 * 根据接收人获取消息的接收列表
	 * 
	 * @param reciever 接收人（非基础应用中的用户）
	 * @param msgType 消息类型：INTERNAL, SMS, EMAIL，为null时返回所有类型的消息
	 * @param pageable 分页对象：为null时返回第1页的10条数据
	 * @return
	 */
	public Map<String, Object> getRecieveredList(String reciever, MsgType msgType, Pageable pageable);

}
