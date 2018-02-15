<#if !pojo.isComponent()>
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

${pojo.getPackageDeclaration()}
// Generated ${date} by Hibernate Tools ${version}

<#assign classbody>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
import javax.ejb.LocalBean;
import co.com.zacsoft.common.persistence.dao.impl.GenericJPADAOImpl;
import co.com.zacsoft.gcms.persistence.entities.mapper.GenericJPAEntityMapper;
import co.com.zacsoft.gcms.persistence.entities.mapper.impl.${declarationName}Mapper;
import org.mapstruct.factory.Mappers;

import co.com.zacsoft.gcms.ejb.dto.${declarationName}DTO;
<#if clazz.identifierProperty?has_content>
<#if c2j.isComponent(clazz.identifierProperty)>
import co.com.zacsoft.gcms.ejb.dto.${pojo.getJavaTypeName(clazz.identifierProperty, jdk5)}DTO;
</#if>
</#if>

import ${modelPackage}.${declarationName};
<#if clazz.identifierProperty?has_content>
<#if pojo.getJavaTypeName(clazz.identifierProperty, jdk5).contains(declarationName)>
import ${modelPackage}.${pojo.getJavaTypeName(clazz.identifierProperty, jdk5)};
import co.com.zacsoft.gcms.persistence.entities.mapper.impl.${declarationName}IdMapper;
</#if>
</#if>
<#if clazz.identifierProperty?has_content>

import javax.persistence.Query;
</#if>

/**
 * DAO class for domain model class ${declarationName}.
 * @see ${modelPackage}.${declarationName};
 * @author ZacjacK
 */
<#if ejb3>
@${pojo.importType("javax.ejb.Stateless")}(name="${declarationName}DAO",mappedName="${declarationName}DAO")
@LocalBean
</#if>
<#if c2j.isComponent(clazz.identifierProperty)>
public class ${declarationName}DAO extends GenericJPADAOImpl<${declarationName}DTO, ${declarationName}IdDTO, ${declarationName}, ${pojo.getJavaTypeName(clazz.identifierProperty, jdk5)}>{
<#else>
public class ${declarationName}DAO extends GenericJPADAOImpl<${declarationName}DTO, ${pojo.getJavaTypeName(clazz.identifierProperty, jdk5)}, ${declarationName}, ${pojo.getJavaTypeName(clazz.identifierProperty, jdk5)}>{
</#if>
    private static final ${pojo.importType("org.apache.commons.logging.Log")} log = ${pojo.importType("org.apache.commons.logging.LogFactory")}.getLog(${pojo.getDeclarationName()}DAO.class);

<#if ejb3>

    @Override
    protected ${declarationName}Mapper getEntityMapper() {
        if (this.entityMapper == null){
            this.entityMapper = Mappers.getMapper(${declarationName}Mapper.class);
        }
        return (${declarationName}Mapper)this.entityMapper.getMapper();
    }
<#if c2j.isComponent(clazz.identifierProperty)>
    @Override
    protected ${declarationName}IdMapper getEntityIdMapper() {
        if (this.entityIdMapper == null){
            this.entityIdMapper = Mappers.getMapper(${declarationName}IdMapper.class);
        }
        return (${declarationName}IdMapper)this.entityIdMapper.getMapper();
    }
<#else>
    @Override
    protected GenericJPAEntityMapper<${pojo.getJavaTypeName(clazz.identifierProperty, jdk5)}, ${pojo.getJavaTypeName(clazz.identifierProperty, jdk5)}> getEntityIdMapper() {
        throw new UnsupportedOperationException();
    }
</#if>
<#if clazz.identifierProperty?has_content>
    @Override
<#if jdk5>
    public ${pojo.importType("java.util.Collection")}<${declarationName}DTO> findAll() {
<#else>
    public ${pojo.importType("java.util.Collection")} findAll() {
</#if>
        log.debug("findAll");
        try {
<#if haveStatusAttr>
	    Query query = entityManager.createNamedQuery("getAllActive${pojo.getDeclarationName()}");
<#else>   
            Query query = entityManager.createNamedQuery("getAll${pojo.getDeclarationName()}");
</#if>  	    
<#if jdk5>
            Collection<${declarationName}DTO> results = this.getEntityMapper().toDTOCollection(query.getResultList());
<#else>
            Collection results = this.getEntityMapper().toDTOCollection(query.getResultList());
</#if>
            log.debug("getAllActive${declarationName} successful");
            return results;
        }
        catch (RuntimeException re) {
            log.error("query failed", re);
            throw re;
        }
    }

<#if haveGatedCommunitiesAttr>
<#if jdk5>
    public ${pojo.importType("java.util.Collection")}<${declarationName}DTO> findAllByGatedCommunity(Integer gatedCommunityId) {
<#else>
    public ${pojo.importType("java.util.Collection")} findAllGatedCommunity(Integer gatedCommunityId) {
</#if>
        log.debug("findAll");
        try {
	    Query query = entityManager.createNamedQuery("getAllActive${pojo.getDeclarationName()}ByGatedCommunity");
            query.setParameter("gatedCommunityId", gatedCommunityId);
<#if jdk5>
            Collection<${declarationName}DTO> results = this.getEntityMapper().toDTOCollection(query.getResultList());
<#else>
            Collection results = this.getEntityMapper().toDTOCollection(query.getResultList());
</#if>
            log.debug("getAllActive${declarationName} successful");
            return results;
        }
        catch (RuntimeException re) {
            log.error("query failed", re);
            throw re;
        }
    }
</#if>

</#if>

}
</#if>
</#assign>

${pojo.generateImports()}
${classbody}
</#if>