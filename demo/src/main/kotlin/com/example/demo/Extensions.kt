import com.example.demo.domain.dto.AuthorDto
import com.example.demo.domain.dto.UserDto
import com.example.demo.domain.entities.AuthorEntity
import com.example.demo.domain.entities.UserEntity
import java.util.*

fun AuthorEntity.toAuthorDto() = AuthorDto(
    id = this.id,
    name = this.name,
    age = this.age,
    description = this.description,
    image = this.image
)

fun AuthorDto.toAuthorEntity() = AuthorEntity(
    id = this.id,
    name = this.name,
    age = this.age,
    description = this.description,
    image = this.image
)

fun UserEntity.toUserDto() = UserDto(
    id = this.id,
    email = this.email,
    password = this.password,
    role = this.role
)

fun UserDto.toUserEntity() = UserEntity(
    id = this.id ?: UUID.randomUUID(),
    email = this.email,
    password = this.password,
    role = this.role
)