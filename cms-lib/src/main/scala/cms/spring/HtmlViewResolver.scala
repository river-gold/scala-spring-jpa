package cms.spring

import java.util.Locale

import org.springframework.web.servlet.{View, ViewResolver}

import org.springframework.web.servlet.view.UrlBasedViewResolver

class HtmlViewResolver extends ViewResolver{

    val prefix = "cms.view."

    override def resolveViewName(viewName: String, locale: Locale): View = {
        Class.forName(prefix + viewName).newInstance().asInstanceOf[View]
    }
}
