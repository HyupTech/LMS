import jakarta.persistence.*

@Entity
@Table(name = "books")
data class Book(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        var title: String = "",  // 기본값 추가
        var author: String = ""   // 기본값 추가
) {
    constructor(id: Long?) : this(id, "", "")  // ID만으로 생성할 수 있는 생성자 추가
}
