import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "rentals")
data class Rental(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,  // Nullable로 설정하여 JPA가 자동으로 생성할 수 있도록 함

        @ManyToOne(fetch = FetchType.LAZY)  // Book과의 관계 정의
        @JoinColumn(name = "book_id", nullable = false)
        var book: Book,

        var rentalDate: LocalDate,

        var returnDate: LocalDate?
)
