<#if ejb3>
<#if pojo.hasIdentifierProperty()>
<#if property.equals(clazz.identifierProperty)>
 ${pojo.generateAnnIdGenerator()}
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "${clazz.table.name}_SEQ")
@SequenceGenerator(name = "${clazz.table.name}_SEQ", sequenceName = "AGENDA.${clazz.table.name}_SEQ", allocationSize = 1, initialValue = 1)
<#-- if this is the id property (getter)-->
<#-- explicitly set the column name for this property-->
</#if>
</#if>
<#if c2h.isManyToOne(property)>
<#--TODO support @OneToOne true and false-->    
${pojo.generateManyToOneAnnotation(property)}
<#--TODO support optional and targetEntity-->    
${pojo.generateJoinColumnsAnnotation(property, cfg)}
<#elseif c2h.isCollection(property)>
${pojo.generateCollectionAnnotation(property, cfg)}
<#else>
${pojo.generateBasicAnnotation(property)}
${pojo.generateAnnColumnAnnotation(property)}
</#if>
</#if>