package com.gdgxwl.base.repository.impl;

import com.gdgxwl.base.domain.GxwlSysMsg.MsgType;
import com.gdgxwl.base.repository.GxwlSysMsgDaoPlus;
import com.gdgxwl.core.repository.impl.BaseDaoImpl;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * @author Will WM. Zhang
 *
 */
public class GxwlSysMsgDaoImpl extends BaseDaoImpl implements GxwlSysMsgDaoPlus {

	@Override
	public Integer getSendedListTotalElements(Integer sendBy, MsgType msgType) {
		String sqlString = " SELECT"
								+ " 1"
							+ " FROM"
								+ " gxwl_sys_msg a"
							+ " WHERE"
								+ " a.SEND_BY = " + sendBy;
		if (msgType != null) {
			sqlString = sqlString + " AND a.MSG_TYPE = '" + msgType.name() + "'";
		}
		Query query = entityManager.createNativeQuery(sqlString);
		return query.getResultList().size();
	}

	@Override
	public Integer getSendedListTotalElements(String sender, MsgType msgType) {
		String sqlString = " SELECT"
								+ " 1"
							+ " FROM"
								+ " gxwl_sys_msg a"
							+ " WHERE"
								+ " a.SEND_ADDRESS = '" + sender + "'";
		if (msgType != null) {
			sqlString = sqlString + " AND a.MSG_TYPE = '" + msgType.name() + "'";
		}
		Query query = entityManager.createNativeQuery(sqlString);
		return query.getResultList().size();
	}

	@Override
	public Integer getRecieveredListTotalElements(Integer recieverBy,
			MsgType msgType) {
		String sqlString = " SELECT"
								+ " 1"
							+ " FROM"
								+ " gxwl_sys_msg a,"
								+ " gxwl_sys_msg_reciever b"
							+ " WHERE"
								+ "	a.MSG_ID = b.MSG_ID"
								+ " AND b.RECEIVER_BY = " + recieverBy;
		if (msgType != null) {
			sqlString = sqlString + " AND a.MSG_TYPE = '" + msgType.name() + "'";
		}
		Query query = entityManager.createNativeQuery(sqlString);
		return query.getResultList().size();
	}

	@Override
	public Integer getRecieveredListTotalElements(String reciever,
			MsgType msgType) {
		String sqlString = " SELECT"
								+ " 1"
							+ " FROM"
								+ " gxwl_sys_msg a,"
								+ " gxwl_sys_msg_reciever b"
							+ " WHERE"
								+ "	a.MSG_ID = b.MSG_ID"
								+ " AND b.RECEIVER_ADDRESS = '" + reciever + "'";
		if (msgType != null) {
		sqlString = sqlString + " AND a.MSG_TYPE = '" + msgType.name() + "'";
		}
		Query query = entityManager.createNativeQuery(sqlString);
		return query.getResultList().size();
	}	
	
	@Override
	public Map<String, Object> readMsg(Integer recieverId) {
		String sqlString = " SELECT"
								+ " a.MSG_TITLE as msgTitle"
								+ " ,a.MSG_BODY as msgBody"
								+ " ,a.MSG_TYPE as msgType"
								+ " ,a.SEND_BY as sendBy"
								+ " ,a.SEND_ADDRESS as sender"
								+ " ,b.IS_READ as isRead"
							+ " FROM"
								+ " gxwl_sys_msg a,"
								+ " gxwl_sys_msg_reciever b"
							+ " WHERE"
								+ " a.MSG_ID = b.MSG_ID"
								+ " AND b.RECEIVER_ID = " + recieverId;
		return selectOneBySql(sqlString);
	}


	@Override
	public List<Map<String, Object>> getSendedList(Integer sendBy,
			MsgType msgType, Integer page, Integer rows) {
		String sqlString = " SELECT"
								+ " a.MSG_TITLE as msgTitle"
								+ " ,a.MSG_BODY as msgBody"
								+ " ,a.MSG_TYPE as msgType"
								+ " ,a.SEND_BY as sendBy"
								+ " ,a.SEND_ADDRESS as sender"
								+ " ,a.IS_SENDED as isSended"
								+ " ,a.SENDED_TIME as sendedTime"
							+ " FROM"
								+ " gxwl_sys_msg a"
							+ " WHERE"
								+ " a.SEND_BY = " + sendBy;
		if (msgType != null) {
			sqlString = sqlString + " AND a.MSG_TYPE = '" + msgType.name() + "'";
		}
		sqlString = sqlString + " ORDER BY a.SENDED_TIME DESC";
		if (page != null && rows != null) {
			if (page < 0) {
				page = 1;
			}
			if (rows < 1) {
				rows = 10;
			}
			sqlString = sqlString + " LIMIT " + (rows*page) + ", " + rows;
		}
		return selectBySql(sqlString);
	}

	@Override
	public List<Map<String, Object>> getSendedList(String sender,
			MsgType msgType, Integer page, Integer rows) {
		String sqlString = " SELECT"
								+ " a.MSG_TITLE as msgTitle"
								+ " ,a.MSG_BODY as msgBody"
								+ " ,a.MSG_TYPE as msgType"
								+ " ,a.SEND_BY as sendBy"
								+ " ,a.SEND_ADDRESS as sender"
								+ " ,a.IS_SENDED as isSended"
								+ " ,a.SENDED_TIME as sendedTime"
							+ " FROM"
								+ " gxwl_sys_msg a"
							+ " WHERE"
								+ " a.SEND_ADDRESS = '" + sender + "'";
		if (msgType != null) {
		sqlString = sqlString + " AND a.MSG_TYPE = '" + msgType.name() + "'";
		}
		sqlString = sqlString + " ORDER BY a.SENDED_TIME DESC";
		if (page != null && rows != null) {
			if (page < 0) {
				page = 1;
			}
			if (rows < 1) {
				rows = 10;
			}
			sqlString = sqlString + " LIMIT " + (rows*page) + ", " + rows;
		}
		return selectBySql(sqlString);
	}

	@Override
	public List<Map<String, Object>> getRecieveredList(Integer recieverBy,
			MsgType msgType, Integer page, Integer rows) {
		String sqlString = " SELECT"
								+ " a.MSG_TITLE as msgTitle"
								+ " ,a.MSG_BODY as msgBody"
								+ " ,a.MSG_TYPE as msgType"
								+ " ,a.SEND_BY as sendBy"
								+ " ,a.SEND_ADDRESS as sender"
								+ " ,a.SENDED_TIME as sendedTime"
								+ " ,b.IS_READ as isRead"
							+ " FROM"
								+ " gxwl_sys_msg a,"
								+ " gxwl_sys_msg_reciever b"
							+ " WHERE"
								+ "	a.MSG_ID = b.MSG_ID"
								+ " AND b.RECEIVER_BY = " + recieverBy;
		if (msgType != null) {
			sqlString = sqlString + " AND a.MSG_TYPE = '" + msgType.name() + "'";
		}
		sqlString = sqlString + " ORDER BY a.SENDED_TIME DESC";
		if (page != null && rows != null) {
			if (page < 0) {
				page = 1;
			}
			if (rows < 1) {
				rows = 10;
			}
			sqlString = sqlString + " LIMIT " + (rows*page) + ", " + rows;
		}
		return selectBySql(sqlString);
	}

	@Override
	public List<Map<String, Object>> getRecieveredList(String reciever,
			MsgType msgType, Integer page, Integer rows) {
		String sqlString = " SELECT"
						+ " a.MSG_TITLE as msgTitle"
						+ " ,a.MSG_BODY as msgBody"
						+ " ,a.MSG_TYPE as msgType"
						+ " ,a.SEND_BY as sendBy"
						+ " ,a.SEND_ADDRESS as sender"
						+ " ,a.SENDED_TIME as sendedTime"
						+ " ,b.IS_READ as isRead"
					+ " FROM"
						+ " gxwl_sys_msg a,"
						+ " gxwl_sys_msg_reciever b"
					+ " WHERE"
						+ "	a.MSG_ID = b.MSG_ID"
						+ " AND b.RECEIVER_ADDRESS = '" + reciever + "'";
		if (msgType != null) {
			sqlString = sqlString + " AND a.MSG_TYPE = '" + msgType.name() + "'";
		}
		sqlString = sqlString + " ORDER BY a.SENDED_TIME DESC";
		if (page != null && rows != null) {
			if (page < 0) {
				page = 1;
			}
			if (rows < 1) {
				rows = 10;
			}
			sqlString = sqlString + " LIMIT " + (rows*page) + ", " + rows;
		}
		return selectBySql(sqlString);
	}

}
