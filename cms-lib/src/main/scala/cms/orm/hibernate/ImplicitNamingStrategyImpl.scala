package cms.orm.hibernate

import com.google.common.base.CaseFormat
import org.hibernate.boot.model.naming.{ImplicitBasicColumnNameSource, ImplicitIdentifierColumnNameSource, Identifier, ImplicitNamingStrategyJpaCompliantImpl}
import org.hibernate.boot.model.source.spi.AttributePath

class ImplicitNamingStrategyImpl extends ImplicitNamingStrategyJpaCompliantImpl{
    override def transformAttributePath (attributePath: AttributePath) = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE).convert(attributePath.getProperty)
}
