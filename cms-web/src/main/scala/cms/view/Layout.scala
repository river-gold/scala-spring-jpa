package cms.view

import java.util
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

import scala.xml.dtd.DocType

object Layout {
    val docType = DocType("html").toString()
    private val layout =
        <html>
            <head>
                <meta charset="utf-8"/>
                <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

                <title>윤블로그</title>

                <link rel="stylesheet" href="/resources/css/pure/0.6.0/pure-min.css"/>
                <link rel="stylesheet" href="/resources/css/pure/0.6.0/grids-responsive-min.css"/>

            </head>
            <body>
                <div id="layout" class="pure-g">
                    <div class="content pure-u-1 pure-u-md-3-4 pure-u-xl-5-8">
                        %s
                    </div>
                </div>
            </body>
        </html>
            .toString().split("%s")
    val layoutHead = layout(0)
    val layoutFoot = layout(1)
}

trait Layout extends Html {

    override def render(model: util.Map[String, _], request: HttpServletRequest, response: HttpServletResponse): Unit = {
        val writer = response.getWriter
        writer.write(Layout.docType)
        writer.println()
        writer.write(Layout.layoutHead)
        writer.write(html(model, request, response))
        writer.write(Layout.layoutFoot)
    }

}
