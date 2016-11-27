package cms.spring

import org.springframework.context.annotation.Bean
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.{ModelAndViewContainer, HandlerMethodArgumentResolver}
import cms.domain.ScalaObject
import scala.collection.JavaConversions._

class ScalaObjectArgumentResolver extends HandlerMethodArgumentResolver{
    override def resolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer, webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory): AnyRef = {
        val bindObject = parameter.getParameterType().newInstance().asInstanceOf[AnyRef]
        def fieldBinder[B,C](setter: (AnyRef, B) => C, value:B): C = setter(bindObject, value)

        for ((k, v) <- webRequest.getParameterMap) {
            try{
                val field = bindObject.getClass.getDeclaredField(k)
                field.setAccessible(true)
                val value = v.asInstanceOf[Array[AnyRef]].apply(0).asInstanceOf[String]

                field.getType.getTypeName match{
                    case "int" => fieldBinder(field.setInt, Integer.parseInt(value))
                    case "boolean" => fieldBinder(field.setBoolean, java.lang.Boolean.parseBoolean(value))
                    case "byte" => fieldBinder(field.setByte, java.lang.Byte.parseByte(value))
                    case "char" => fieldBinder(field.setChar, value.charAt(0))
                    case "double" => fieldBinder(field.setDouble, java.lang.Double.parseDouble(value))
                    case "float" => fieldBinder(field.setFloat, java.lang.Float.parseFloat(value))
                    case "long" => fieldBinder(field.setLong, java.lang.Long.parseLong(value))
                    case "short" => fieldBinder(field.setShort, java.lang.Short.parseShort(value))
                    case "java.lang.Character" => fieldBinder(field.set, java.lang.Character.valueOf(value.charAt(0)))
                    case "java.lang.String" => fieldBinder(field.set, value)
                    case className => fieldBinder(field.set, Class.forName(className).getDeclaredMethod("valueOf", classOf[String]).invoke(null, value))
                }

            } catch {
                case ex: Exception => ex.printStackTrace()
            }
        }
        bindObject
    }

    override def supportsParameter(parameter: MethodParameter): Boolean = {
        classOf[ScalaObject].isAssignableFrom(parameter.getParameterType())
    }
}
