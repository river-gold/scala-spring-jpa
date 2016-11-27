package cms.view

import java.util
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

import org.springframework.web.servlet.View
import org.springframework.web.servlet.view.AbstractView

trait Html extends View{

    override def getContentType: String = AbstractView.DEFAULT_CONTENT_TYPE

    def html(model: util.Map[String, _], request: HttpServletRequest, response: HttpServletResponse): String
}
