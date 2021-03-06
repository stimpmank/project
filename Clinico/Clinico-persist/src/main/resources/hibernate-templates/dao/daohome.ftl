${pojo.getPackageDeclaration()}
// Generated ${date} by Hibernate Tools ${version}

<#assign classbody>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
import javax.ejb.LocalBean;
import ${modelPackage}.${declarationName};
import co.com.zacsoft.gcms.ejb.dto.${declarationName}DTO;
import co.com.zacsoft.gcms.persistence.entities.mapper.GenericMapper;
<#if clazz.identifierProperty?has_content>
<#if pojo.getJavaTypeName(clazz.identifierProperty, jdk5).contains(declarationName)>
import ${modelPackage}.${pojo.getJavaTypeName(clazz.identifierProperty, jdk5)};
</#if>
</#if>
<#if clazz.identifierProperty?has_content>
import javax.persistence.Query;
</#if>

/**
 * Home object for domain model class ${declarationName}.
 * @see ${modelPackage}.${declarationName};
 * @author Hibernate Tools
 */
<#if ejb3>
@${pojo.importType("javax.ejb.Stateless")}
@LocalBean
</#if>
public class ${declarationName}DAO {

    private GenericMapper EntityMapper = null;

    public void setMapper(GenericMapper mapper){
        this.EntityMapper = mapper;
    }

    private static final ${pojo.importType("org.apache.commons.logging.Log")} log = ${pojo.importType("org.apache.commons.logging.LogFactory")}.getLog(${pojo.getDeclarationName()}DAO.class);

<#if ejb3>
    @${pojo.importType("javax.persistence.PersistenceContext")} private ${pojo.importType("javax.persistence.EntityManager")} entityManager;
    
    public void persist(${declarationName}DTO dto) {
        ${declarationName} transientInstance = this.EntityMapper.getMapper().toEntity(dto);
        log.debug("persisting ${declarationName} instance");
        try {
            entityManager.persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void remove(${declarationName}DTO dto) {
        ${declarationName} persistentInstance = this.EntityMapper.getMapper().toEntity(dto);
        log.debug("removing ${declarationName} instance");
        try {
            entityManager.remove(persistentInstance);
            log.debug("remove successful");
        }
        catch (RuntimeException re) {
            log.error("remove failed", re);
            throw re;
        }
    }
    
    public ${declarationName} merge(${declarationName}DTO dto) {
        ${declarationName} detachedInstance = this.EntityMapper.getMapper().toEntity(dto);
        log.debug("merging ${declarationName} instance");
        try {
            ${declarationName} result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
<#if clazz.identifierProperty?has_content>    
    public ${declarationName} findById( ${pojo.getJavaTypeName(clazz.identifierProperty, jdk5)} id) {
        log.debug("getting ${declarationName} instance with id: " + id);
        try {
            ${declarationName} instance = entityManager.find(${pojo.getDeclarationName()}.class, id);
            log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
</#if>

<#if clazz.identifierProperty?has_content>
<#if jdk5>
    public ${pojo.importType("java.util.List")}<${declarationName}DTO> getAllActive${declarationName}() {
<#else>
    public ${pojo.importType("java.util.List")} getAllActive${declarationName}() {
</#if>
        log.debug("getAllActive${declarationName}");
        try {
	    Query query = entityManager.createNamedQuery("getAllActive${declarationName}");
  	    List results = query.getResultList();
            log.debug("getAllActive${declarationName} successful");
            return results;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
</#if>
<#else>    
    private final ${pojo.importType("org.hibernate.SessionFactory")} sessionFactory = getSessionFactory();
    
    protected ${pojo.importType("org.hibernate.SessionFactory")} getSessionFactory() {
        try {
            return (${pojo.importType("org.hibernate.SessionFactory")}) new ${pojo.importType("javax.naming.InitialContext")}().lookup("${sessionFactoryName}");
        }
        catch (Exception e) {
            log.error("Could not locate SessionFactory in JNDI", e);
            throw new IllegalStateException("Could not locate SessionFactory in JNDI");
        }
    }
    
    public void persist(${declarationName}DTO dto) {
        ${declarationName} transientInstance = this.EntityMapper.getMapper().toEntity(dto);
        log.debug("persisting ${declarationName} instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(${declarationName}DTO dto) {
        ${declarationName} instance = this.EntityMapper.getMapper().toEntity(dto);
        log.debug("attaching dirty ${declarationName} instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(${declarationName}DTO dto) {
        ${declarationName} instance = this.EntityMapper.getMapper().toEntity(dto);        
        log.debug("attaching clean ${declarationName} instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, ${pojo.importType("org.hibernate.LockMode")}.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(${declarationName}DTO dto) {
        ${declarationName} persistentInstance = this.EntityMapper.getMapper().toEntity(dto);        
        log.debug("deleting ${declarationName} instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ${declarationName} merge(${declarationName} dto) {
        ${declarationName} detachedInstance = this.EntityMapper.getMapper().toEntity(dto);
        log.debug("merging ${declarationName} instance");
        try {
            ${declarationName} result = (${declarationName}) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
<#if clazz.identifierProperty?has_content>
    public ${declarationName} findById( ${c2j.getJavaTypeName(clazz.identifierProperty, jdk5)} id) {
        log.debug("getting ${declarationName} instance with id: " + id);
        try {
            ${declarationName} instance = (${declarationName}) sessionFactory.getCurrentSession()
                    .get("${clazz.entityName}", id);
            if (instance==null) {
                log.debug("get successful, no instance found");
            }
            else {
                log.debug("get successful, instance found");
            }
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
</#if>
    
<#if clazz.hasNaturalId()>
    public ${declarationName} findByNaturalId(${c2j.asNaturalIdParameterList(clazz)}) {
        log.debug("getting ${declarationName} instance by natural id");
        try {
            ${declarationName} instance = (${declarationName}) sessionFactory.getCurrentSession()
                    .createCriteria("${clazz.entityName}")
<#if jdk5>
                    .add( ${pojo.staticImport("org.hibernate.criterion.Restrictions", "naturalId")}()
<#else>
                   .add( ${pojo.importType("org.hibernate.criterion.Restrictions")}.naturalId()
</#if>                    
<#foreach property in pojo.getAllPropertiesIterator()>
<#if property.isNaturalIdentifier()>
                            .set("${property.name}", ${property.name})
</#if>
</#foreach>
                        )
                    .uniqueResult();
            if (instance==null) {
                log.debug("get successful, no instance found");
            }
            else {
                log.debug("get successful, instance found");
            }
            return instance;
        }
        catch (RuntimeException re) {
            log.error("query failed", re);
            throw re;
        }
    }
</#if>    

/* TODO: [CZ] pendiente implementacion de namedquery para no ejb*/

<#if jdk5>
    public ${pojo.importType("java.util.List")}<${declarationName}> findByExample(${declarationName} instance) {
<#else>
    public ${pojo.importType("java.util.List")} findByExample(${declarationName} instance) {
</#if>
        log.debug("finding ${declarationName} instance by example");
        try {
<#if jdk5>
            ${pojo.importType("java.util.List")}<${declarationName}> results = (List<${declarationName}>) sessionFactory.getCurrentSession()
<#else>
            ${pojo.importType("java.util.List")} results = sessionFactory.getCurrentSession()
</#if>
                    .createCriteria("${clazz.entityName}")
<#if jdk5>
                    .add( ${pojo.staticImport("org.hibernate.criterion.Example", "create")}(instance) )
<#else>
                    .add(${pojo.importType("org.hibernate.criterion.Example")}.create(instance))
</#if>
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    } 
<#foreach queryName in cfg.namedQueries.keySet()>
<#if queryName.startsWith(clazz.entityName + ".")>
<#assign methname = c2j.unqualify(queryName)>
<#assign params = cfg.namedQueries.get(queryName).parameterTypes><#assign argList = c2j.asFinderArgumentList(params, pojo)>
<#if jdk5 && methname.startsWith("find")>
    public ${pojo.importType("java.util.List")}<${declarationName}> ${methname}(${argList}) {
<#elseif methname.startsWith("count")>
    public int ${methname}(${argList}) {
<#else>
    public ${pojo.importType("java.util.List")} ${methname}(${argList}) {
</#if>
        ${pojo.importType("org.hibernate.Query")} query = sessionFactory.getCurrentSession()
                .getNamedQuery("${queryName}");
<#foreach param in params.keySet()>
<#if param.equals("maxResults")>
		query.setMaxResults(maxResults);
<#elseif param.equals("firstResult")>
        query.setFirstResult(firstResult);
<#else>
        query.setParameter("${param}", ${param});
</#if>
</#foreach>
<#if jdk5 && methname.startsWith("find")>
        return (List<${declarationName}>) query.list();
<#elseif methname.startsWith("count")>
        return ( (Integer) query.uniqueResult() ).intValue();
<#else>
        return query.list();
</#if>
    }
</#if>
</#foreach></#if>
}
</#assign>

${pojo.generateImports()}
${classbody}
