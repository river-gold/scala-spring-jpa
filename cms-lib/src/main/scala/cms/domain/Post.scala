package cms.domain

import java.util.Date
import javax.persistence._

import com.fasterxml.jackson.annotation.JsonBackReference
import org.hibernate.annotations.{Fetch, FetchMode}
import org.springframework.data.annotation.{CreatedDate, LastModifiedDate}
import org.springframework.data.jpa.domain.support.AuditingEntityListener

import scala.beans.BeanProperty


@Entity
@EntityListeners(value = Array(classOf[AuditingEntityListener]))
class Post extends ScalaObject {
    @Id
    @GeneratedValue
    var id: Integer = null

    var subject: String = null

    @CreatedDate
    var createDate: Date = null

    @LastModifiedDate
    var updateDate: Date = null

    @Fetch(FetchMode.JOIN)
    @OneToOne(mappedBy = "post", fetch = FetchType.LAZY)
    var postContent: PostContent = null

    var viewCount = 0

    @Fetch(FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
    @JsonBackReference
    var category: Category = null

    def canEqual(other: Any): Boolean = other.isInstanceOf[Post]

    override def equals(other: Any): Boolean = other match {
        case that: Post =>
            (that canEqual this) &&
                id == that.id
        case _ => false
    }

    override def hashCode(): Int = {
        val state = Seq(id)
        state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
    }
}
