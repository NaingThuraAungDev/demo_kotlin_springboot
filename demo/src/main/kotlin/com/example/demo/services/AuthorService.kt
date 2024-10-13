package com.example.demo.services

import com.example.demo.domain.models.AuthorModel
import com.example.demo.domain.entities.AuthorEntity
import com.example.demo.repositories.AuthorRepository
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AuthorService(private  val authorRepository: AuthorRepository) {
    private  val logger: org.slf4j.Logger? = LoggerFactory.getLogger(AuthorService::class.java)
    fun save(authorEntity: AuthorEntity): AuthorEntity {
        require(null == authorEntity.id)
        return authorRepository.save(authorEntity)
    }
    @Cacheable(value = ["authors"],key = "'getList'")
    fun getList(): List<AuthorEntity>{
        logger?.info("getList: Fetch data from database")
        return authorRepository.findAll()
    }

    @Transactional
    //@CachePut(value = ["authors"],key = "#updateAuthor.id")
    @CacheEvict(value = ["authors"], key = "'getList'")
    fun  updateAuthor(updateAuthor: AuthorModel): AuthorEntity{
        val existingAuthor = authorRepository.findByIdOrNull(updateAuthor.id)
        checkNotNull(existingAuthor)
        val updatedAuthor = existingAuthor.copy(
            name = updateAuthor.name ?: existingAuthor.name,
            age = updateAuthor.age ?: existingAuthor.age,
            description = updateAuthor.description ?: existingAuthor.description,
            image = updateAuthor.image ?: existingAuthor.image
        )
        return  authorRepository.save(updatedAuthor)
    }
    @CacheEvict(value = ["authors"], key = "'getList'")
    fun delete(id: Long) {
        authorRepository.deleteById(id)
    }
}