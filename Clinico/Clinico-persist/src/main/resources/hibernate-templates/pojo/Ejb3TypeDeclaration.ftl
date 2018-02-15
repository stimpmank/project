<#if ejb3?if_exists>
<#assign haveStatusAttr = false>
<#assign haveGatedCommunitiesAttr = false>
<#foreach property in pojo.getAllPropertiesIterator()>
<#if property.name = "status">
    <#assign haveStatusAttr = true>
</#if>
<#if property.name = "gatedCommunities">
    <#assign haveGatedCommunitiesAttr = true>
</#if>
</#foreach>
<#if !pojo.isComponent()>
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

@NamedQueries({
<#if haveStatusAttr>
<#if haveGatedCommunitiesAttr>
	@NamedQuery(
	name = "getAllActive${pojo.getDeclarationName()}ByGatedCommunity",
	query = "select e from ${pojo.getDeclarationName()} e where e.status = 1 and e.gatedCommunities.idGatedCommunity = :gatedCommunityId"
	),
</#if>
	@NamedQuery(
	name = "getAllActive${pojo.getDeclarationName()}",
	query = "select e from ${pojo.getDeclarationName()} e where e.status = 1"
	),
</#if>
	@NamedQuery(
	name = "getAll${pojo.getDeclarationName()}",
	query = "select e from ${pojo.getDeclarationName()} e"
	)
})
</#if>
<#if pojo.isComponent()>
@${pojo.importType("javax.persistence.Embeddable")}
<#else>
@${pojo.importType("javax.persistence.Entity")}
@${pojo.importType("javax.persistence.Table")}(name="${clazz.table.name}"
<#if clazz.table.schema?exists>
    ,schema="${clazz.table.schema}"
</#if><#if clazz.table.catalog?exists>
    ,catalog="${clazz.table.catalog}"
</#if>
<#assign uniqueConstraint=pojo.generateAnnTableUniqueConstraint()>
<#if uniqueConstraint?has_content>
    , uniqueConstraints = ${uniqueConstraint} 
</#if>)
</#if>
</#if>