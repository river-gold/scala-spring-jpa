package cms.domain

import java.util.Date
import javax.persistence._

import com.fasterxml.jackson.annotation.JsonBackReference
import org.hibernate.annotations.{FetchMode, Fetch}
import org.springframework.data.annotation.{CreatedDate, LastModifiedDate}
import org.springframework.data.jpa.domain.support.AuditingEntityListener

import scala.beans.BeanProperty


@Entity
@EntityListeners(value = Array(classOf[AuditingEntityListener]))
class PostContent extends ScalaObject {
    @Id
    var id: Integer = null

    @Lob
    var content: String = null

    @CreatedDate
    var createDate: Date = null

    @LastModifiedDate
    var updateDate: Date = null

    @Fetch(FetchMode.JOIN)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @JsonBackReference
    var post: Post = null

    def canEqual(other: Any): Boolean = other.isInstanceOf[PostContent]

    override def equals(other: Any): Boolean = other match {
        case that: PostContent =>
            (that canEqual this) &&
                id == that.id
        case _ => false
    }

    override def hashCode(): Int = {
        val state = Seq(id)
        state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
    }
}
