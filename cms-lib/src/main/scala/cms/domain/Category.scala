package cms.domain

import java.util.Date
import javax.persistence._

import com.fasterxml.jackson.annotation.JsonBackReference
import org.hibernate.annotations.{Fetch, FetchMode}
import org.springframework.data.annotation.{CreatedDate, LastModifiedDate}
import org.springframework.data.jpa.domain.support.AuditingEntityListener

import scala.beans.BeanProperty

@Entity
@Table(indexes = Array(new Index(columnList = "PARENT_ID"), new Index(columnList = "LEVEL")))
@EntityListeners(value = Array(classOf[AuditingEntityListener]))
class Category extends ScalaObject {
    @Id
    @GeneratedValue
    var id: Integer = null

    var level: Int = 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID")
    @JsonBackReference
    var parentCategory: Category = null

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY)
    private var _childrenCategories: java.util.List[Category] = null

    def childrenCategories(): java.util.List[Category] = {
        if (hasChildren) {
            return _childrenCategories
        }
        return null
    }

    def getChildrenCategories() = childrenCategories()

    var hasChildren: Boolean = false

    var name: String = null

    @CreatedDate
    var createDate: Date = null

    @LastModifiedDate
    var updateDate: Date = null

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @JsonBackReference
    var documents: java.util.List[Post] = null

    def canEqual(other: Any): Boolean = other.isInstanceOf[Category]

    override def equals(other: Any): Boolean = other match {
        case that: Category =>
            (that canEqual this) &&
                id == that.id
        case _ => false
    }

    override def hashCode(): Int = {
        val state = Seq(id)
        state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
    }
}
