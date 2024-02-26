package com.cmach.todoapikotlin.model

import com.fasterxml.uuid.Generators
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Table(name = "todo")
@Entity(name = "todo")
data class Todo (
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    val id : String = Generators.timeBasedGenerator().generate().toString(),

    @Column
    var name: String,

    @Column
    var content: String
)