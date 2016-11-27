package cms.view

import java.util
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

import org.springframework.stereotype.Component

@Component("posts")
class Posts extends Layout{
    var html =
        <table class="pure-table pure-table-horizontal">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Make</th>
                    <th>Model</th>
                    <th>Year</th>
                </tr>
            </thead>

            <tbody>
                <tr>
                    <td>1</td>
                    <td>Honda</td>
                    <td>Accord</td>
                    <td>2009</td>
                </tr>

                <tr>
                    <td>2</td>
                    <td>Toyota</td>
                    <td>Camry</td>
                    <td>2012</td>
                </tr>

                <tr>
                    <td>3</td>
                    <td>Hyundai</td>
                    <td>Elantra</td>
                    <td>2010</td>
                </tr>
            </tbody>
        </table>
        .toString
    override def html(model: util.Map[String, _], request: HttpServletRequest, response: HttpServletResponse): String = {
        html
    }
}