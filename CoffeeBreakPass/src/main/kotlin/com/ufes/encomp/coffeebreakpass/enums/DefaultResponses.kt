package com.ufes.encomp.coffeebreakpass.enums

fun defaultSuccessMessage (action : String, entityName : String, entityID : String) : String {
    return "{ \"success\" : \"$entityName associated with ID $entityID was successfully $action\" }"
}

fun defaultNotFoundMessage (entityName: String) : String{
    return "{ \"errors\" : \"No matching $entityName was found\" }"
}

fun defaultDuplicateMessage(entityName: String) : String{
    return "$entityName already in use"
}