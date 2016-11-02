package com.gdgxwl.base.domain;

import com.gdgxwl.base.common.Constant;
import com.gdgxwl.base.common.DictUtil;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.List;

/**
 * @author Will WM. Zhang
 * 
 */
@Entity
@Table(name = "GXWL_SYS_DICT_H")
public class GxwlSysDictH extends BaseEntity {

	private static final long serialVersionUID = 1314235515011976689L;

	/**
	 * 数据字典类型ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DICT_TYPE_ID", nullable = false)
	private Integer dictTypeId;

	/**
	 * 数据字典类型名称
	 */
	@Column(name = "DICT_TYPE_NAME", length = 128, nullable = false)
	private String dictTypeName;

	/**
	 * 数据字典类型编码
	 */
	@Column(name = "DICT_TYPE_CODE", length = 128, nullable = false)
	private String dictTypeCode;

	/**
	 * 是否有效
	 */
	@Column(name = "ENABLE", length = 128, nullable = false)
	private String enable;
	
	/**
	 * 备注
	 */
	@Column(name = "REMARK", length = 4000)
	private String remark;

	/**
	 * 数据字典
	 */
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, mappedBy = "gxwlSysDictH")
	private List<GxwlSysDictL> gxwlSysDictLs;

	@Transient
	public String getEnableName() {
		if (StringUtils.isEmpty(getEnable())) {
			return "";
		} else {
			return DictUtil.getDictName(Constant.YES_OR_NO, getEnable());
		}
	}
	
	public Integer getDictTypeId() {
		return dictTypeId;
	}

	public void setDictTypeId(Integer dictTypeId) {
		this.dictTypeId = dictTypeId;
	}

	public String getDictTypeName() {
		return dictTypeName;
	}

	public void setDictTypeName(String dictTypeName) {
		this.dictTypeName = dictTypeName;
	}

	public String getDictTypeCode() {
		return dictTypeCode;
	}

	public void setDictTypeCode(String dictTypeCode) {
		this.dictTypeCode = dictTypeCode;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<GxwlSysDictL> getGxwlSysDictLs() {
		return gxwlSysDictLs;
	}

	public void setGxwlSysDictLs(List<GxwlSysDictL> gxwlSysDictLs) {
		this.gxwlSysDictLs = gxwlSysDictLs;
	}

}
