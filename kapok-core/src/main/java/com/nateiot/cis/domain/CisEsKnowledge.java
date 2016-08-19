package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;

/**
 * 知识库管理
 * 
 * @author  guohuawen 
 * 
 */

@Entity
@Table(name = "cis_es_knowledge")
public class CisEsKnowledge extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键ID 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "KNOWLEDGE_ID", length = 10, nullable = false)
	private Integer knowledgeId;
	
	/**
	 *  编码 
	 */
	@Column(name = "KNOWLEDGE_CODE",length = 50)
	private String knowledgeCode;
	
	/**
	 *  标题
	 */
	@Column(name = "KNOWLEDGE_TITLE",length = 200)
	private String knowledgeTitle;

	/**
	 *  内容
	 */
	@Column(name = "KNOWLEDGE_CONTENT",length = 4000)
	private String knowledgeContent;

	/**
	 *  知识库类型
	 */
	@Column(name = "KNOWLEDGE_TYPE",length = 128)
	private String knowledgeType;

	/**
	 *  是否有效
	 */
	@Column(name = "ENABLE", length = 1)
	private String enable;

	/**
	 *  删除标记
	 */
	@Column(name = "DEL_SIGN", length = 1)
	private String delSign;
	
	/**
	 * 文件ID
	 */
	@Column(name = "KNOWLEDGE_DOC_ID", length = 14)
	private Integer knowledgeDocId;
	
	/**
	 *上传文件名 
	 */
	@Column(name="KNOWLEDGE_DOCNAME",length=50)
	private String knowledgeDocName;

	public Integer getKnowledgeId() {
		return knowledgeId;
	}

	public void setKnowledgeId(Integer knowledgeId) {
		this.knowledgeId = knowledgeId;
	}

	public String getKnowledgeCode() {
		return knowledgeCode;
	}

	public void setKnowledgeCode(String knowledgeCode) {
		this.knowledgeCode = knowledgeCode;
	}

	public String getKnowledgeTitle() {
		return knowledgeTitle;
	}

	public void setKnowledgeTitle(String knowledgeTitle) {
		this.knowledgeTitle = knowledgeTitle;
	}

	public String getKnowledgeContent() {
		return knowledgeContent;
	}

	public void setKnowledgeContent(String knowledgeContent) {
		this.knowledgeContent = knowledgeContent;
	}

	public String getKnowledgeType() {
		return knowledgeType;
	}

	public void setKnowledgeType(String knowledgeType) {
		this.knowledgeType = knowledgeType;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public Integer getKnowledgeDocId() {
		return knowledgeDocId;
	}

	public void setKnowledgeDocId(Integer knowledgeDocId) {
		this.knowledgeDocId = knowledgeDocId;
	}

	public String getKnowledgeDocName() {
		return knowledgeDocName;
	}

	public void setKnowledgeDocName(String knowledgeDocName) {
		this.knowledgeDocName = knowledgeDocName;
	}
	
	
}
