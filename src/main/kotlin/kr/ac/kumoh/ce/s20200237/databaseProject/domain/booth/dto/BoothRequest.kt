package kr.ac.kumoh.ce.s20200237.databaseProject.domain.booth.dto

data class BoothRequest(
        var name : String,
        var description : String,
        var location : Int,
        var type : BoothType,
        var email : String
)

