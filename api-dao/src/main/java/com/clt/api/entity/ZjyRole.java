package com.clt.api.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.baomidou.mybatisplus.enums.FieldFill;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangquansong
 * @since 2018-12-29
 */
@Data
@Accessors(chain = true)
@TableName("zjy_role")
public class ZjyRole extends Model<ZjyRole> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 角色名称
     */
	@TableField("zjy_role_name")
	private String zjyRoleName;
    /**
     * 角色描述
     */
	@TableField("zjy_role_desc")
	private String zjyRoleDesc;
    /**
     * 角色权限内容json
     */
	@TableField("zjy_right_detail")
	private String zjyRightDetail;
    /**
     * 所属组
     */
	@TableField("zjy_group_id")
	private Long zjyGroupId;
    /**
     * 集团id
     */
	@TableField("zjy_company_id")
	private Long zjyCompanyId;
	@TableField("zjy_create_people")
	private String zjyCreatePeople;
	@TableField("zjy_create_time")
	private Date zjyCreateTime;
	@TableField("zjy_modify_people")
	private String zjyModifyPeople;
	@TableField("zjy_modify_time")
	private Date zjyModifyTime;
    /**
     * 1锛氬凡鍒犻櫎 0锛氭湭鍒犻櫎
     */
	@TableField("zjy_deleted")
	private Integer zjyDeleted;
    /**
     * 绉熸埛id
     */
	@TableField("zjy_tenant_id")
	private Integer zjyTenantId;
	@TableField("zjy_sys_code")
	private String zjySysCode;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ZjyRole{" +
			"id=" + id +
			", zjyRoleName=" + zjyRoleName +
			", zjyRoleDesc=" + zjyRoleDesc +
			", zjyRightDetail=" + zjyRightDetail +
			", zjyGroupId=" + zjyGroupId +
			", zjyCompanyId=" + zjyCompanyId +
			", zjyCreatePeople=" + zjyCreatePeople +
			", zjyCreateTime=" + zjyCreateTime +
			", zjyModifyPeople=" + zjyModifyPeople +
			", zjyModifyTime=" + zjyModifyTime +
			", zjyDeleted=" + zjyDeleted +
			", zjyTenantId=" + zjyTenantId +
			", zjySysCode=" + zjySysCode +
			"}";
	}
}
