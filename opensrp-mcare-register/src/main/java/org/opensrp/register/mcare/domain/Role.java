package org.opensrp.register.mcare.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;
import org.ektorp.support.TypeDiscriminator;
import org.motechproject.model.MotechBaseDataObject;
import org.opensrp.dto.PrivilegeDTO;

@TypeDiscriminator("doc.type === 'Role'")
public class Role extends MotechBaseDataObject {
	
	@JsonProperty
	private String name;
	@JsonProperty
	private String status;	
	@JsonProperty
	private List<SimplifiedPrivilege> privileges;
	@JsonProperty
	private Map<String, String> meta;
	@JsonProperty
	private String created_at;
	@JsonProperty
	private String updated_at;
	@JsonProperty
	private String created_by;
	@JsonProperty
	private String updated_by;
	
	public Role(){
		this.created_at = new Date().toString();
		this.updated_at = new Date().toString();
		this.created_by = "Admin";
		this.updated_by = null;
	}
	public Role withName(String roleName) {
		this.name = roleName;
		return this;
	}
	public Role withStatus(String status) {
		this.status = status;
		return this;
	}
	public Role withPrivileges(List<SimplifiedPrivilege> privileges){
		this.privileges = privileges;
		return this;
	}
	public String getName() {
		return name;
	}
	public String getStatus() {
		return status;
	}
	public List<SimplifiedPrivilege> getPrivileges(){
		return privileges;
	}

	@Override
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o, "id", "revision");
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "id", "revision");
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
