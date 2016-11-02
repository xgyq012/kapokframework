package com.gdgxwl.base.domain;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

/**
 * @author Will WM. Zhang
 * 
 */
@Entity
@Table(name = "GXWL_SYS_DICT_L")
public class GxwlSysDictL extends BaseEntity {

	private static final long serialVersionUID = 8569801991390469271L;

	/**
	 * 数据字典ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DICT_ID", nullable = false)
	private Integer dictId;

	/**
	 * 数据字典类型,
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "DICT_TYPE_ID")
	private GxwlSysDictH gxwlSysDictH;

	/**
	 * 数据字典名称
	 */
	@Column(name = "DICT_NAME", length = 200, nullable = false)
	private String dictName;

	/**
	 * 数据字典编码
	 */
	@Column(name = "DICT_CODE", length = 128, nullable = false)
	private String dictCode;

	/**
	 * 序号
	 */
	@Column(name = "SEQ")
	private Integer seq;

	/**
	 * 是否显示
	 */
	@Column(name = "IS_DISPLAY", length = 1, nullable = false)
	private String isDisplay;
	
	/**
	 * 备注
	 */
	@Column(name = "REMARK", length = 4000)
	private String remark;
	
	/**
	 * 扩展字段01
	 */
	@Column(name = "ATTRIBUTE01", length = 200)
	private String attribute01;
	
	/**
	 * 扩展字段02
	 */
	@Column(name = "ATTRIBUTE02", length = 200)
	private String attribute02;
	
	/**
	 * 扩展字段03
	 */
	@Column(name = "ATTRIBUTE03", length = 200)
	private String attribute03;
	
	/**
	 * 扩展字段04
	 */
	@Column(name = "ATTRIBUTE04", length = 200)
	private String attribute04;
	
	/**
	 * 扩展字段05
	 */
	@Column(name = "ATTRIBUTE05", length = 200)
	private String attribute05;
	
	public void setDictTypeId(Integer dictTypeId) {
		if (dictTypeId != null) {
			this.gxwlSysDictH = new GxwlSysDictH();
			this.gxwlSysDictH.setDictTypeId(dictTypeId);
		}
	}

	@Transient
	public Integer getDictTypeId() {
		return this.gxwlSysDictH == null ? null : this.gxwlSysDictH.getDictTypeId();
	}
	
//	@Transient
//	public String getDictTypeCode() {
//		GxwlSysDictH gxwlSysDictH = getGxwlSysDictH();
//		return gxwlSysDictH == null ? "" : gxwlSysDictH.getDictTypeCode();
//	}

	public Integer getDictId() {
		return dictId;
	}

	public void setDictId(Integer dictId) {
		this.dictId = dictId;
	}

	public GxwlSysDictH getGxwlSysDictH() {
		return gxwlSysDictH;
	}

	public void setGxwlSysDictH(GxwlSysDictH gxwlSysDictH) {
		this.gxwlSysDictH = gxwlSysDictH;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAttribute01() {
		return attribute01;
	}

	public void setAttribute01(String attribute01) {
		this.attribute01 = attribute01;
	}

	public String getAttribute02() {
		return attribute02;
	}

	public void setAttribute02(String attribute02) {
		this.attribute02 = attribute02;
	}

	public String getAttribute03() {
		return attribute03;
	}

	public void setAttribute03(String attribute03) {
		this.attribute03 = attribute03;
	}

	public String getAttribute04() {
		return attribute04;
	}

	public void setAttribute04(String attribute04) {
		this.attribute04 = attribute04;
	}

	public String getAttribute05() {
		return attribute05;
	}

	public void setAttribute05(String attribute05) {
		this.attribute05 = attribute05;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dictId == null) ? 0 : dictId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GxwlSysDictL other = (GxwlSysDictL) obj;
		if (dictId == null) {
			if (other.dictId != null)
				return false;
		} else if (!dictId.equals(other.dictId))
			return false;
		return true;
	}

}
