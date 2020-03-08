package com.example.ahmadqosam.footballmatchschedule.utils

import android.view.View

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun isNotEmptyOrNull(value: String?): Boolean{
    return (value != "null" && value != "" && value != null)
}

fun parseSpace(uri: String?): String{
    if(uri!=null){
        if(uri.contains(" ")){
            return uri.replace(" ","%20",true)
        } else {
            return uri
        }
    } else{
        return ""
    }
}

fun parseLeagueNameList(leagueList: List<String>): MutableList<String>{
    var leagueNameList: MutableList<String> = mutableListOf()
    for(league: String in leagueList){
        leagueNameList.add(league.split("|")[1])
    }
    return leagueNameList
}

fun retrieveLeagueId(leagueName: String, leagueList: List<String>): String{
    for(league: String in leagueList){
        if(league.split("|")[1] == leagueName){
            return(league.split("|")[0])
        }
    }
    return ""
}