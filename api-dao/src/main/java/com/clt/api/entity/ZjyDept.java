package com.clt.api.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.baomidou.mybatisplus.enums.FieldFill;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 组织部门表
 * </p>
 *
 * @author zhangquansong
 * @since 2019-01-02
 */
@Data
@Accessors(chain = true)
@TableName("zjy_dept")
public class ZjyDept extends Model<ZjyDept> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	@TableField("zjy_dept_name")
	private String zjyDeptName;
	@TableField("zjy_company_id")
	private Long zjyCompanyId;
	@TableField("zjy_dept_level")
	private Integer zjyDeptLevel;
	@TableField("zjy_parent_id")
	private Long zjyParentId;
	@TableField("zjy_dept_order")
	private Integer zjyDeptOrder;
	@TableField("zjy_dept_desc")
	private String zjyDeptDesc;
	@TableField("zjy_mamager_id")
	private String zjyMamagerId;
	@TableField("zjy_create_people")
	private String zjyCreatePeople;
	@TableField("zjy_create_time")
	private Date zjyCreateTime;
	@TableField("zjy_modity_people")
	private byte[] zjyModityPeople;
	@TableField("zjy_modify_time")
	private Date zjyModifyTime;
	@TableField("zjy_deleted")
	private Integer zjyDeleted;
	@TableField("zjy_tenant_id")
	private Integer zjyTenantId;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ZjyDept{" +
			"id=" + id +
			", zjyDeptName=" + zjyDeptName +
			", zjyCompanyId=" + zjyCompanyId +
			", zjyDeptLevel=" + zjyDeptLevel +
			", zjyParentId=" + zjyParentId +
			", zjyDeptOrder=" + zjyDeptOrder +
			", zjyDeptDesc=" + zjyDeptDesc +
			", zjyMamagerId=" + zjyMamagerId +
			", zjyCreatePeople=" + zjyCreatePeople +
			", zjyCreateTime=" + zjyCreateTime +
			", zjyModityPeople=" + zjyModityPeople +
			", zjyModifyTime=" + zjyModifyTime +
			", zjyDeleted=" + zjyDeleted +
			", zjyTenantId=" + zjyTenantId +
			"}";
	}
}
