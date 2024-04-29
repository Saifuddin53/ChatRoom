package com.myprojects.chatroom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myprojects.chatroom.Injection
import com.myprojects.chatroom.data.Result
import com.myprojects.chatroom.data.Room
import com.myprojects.chatroom.data.RoomRepository
import kotlinx.coroutines.launch

class RoomViewModel: ViewModel() {

    private val roomRepository: RoomRepository
    init {
        roomRepository = RoomRepository(
            Injection.instance()
        )
        loadRooms()
    }

    private val _rooms = MutableLiveData<List<Room>>()
    val rooms: LiveData<List<Room>> get()  = _rooms

    fun createRoom(name: String) {
        viewModelScope.launch {
            roomRepository.createRoom(name)
        }
    }

    fun loadRooms() {
        viewModelScope.launch {
            when(val result = roomRepository.getRooms()) {
                is Result.Success -> {
                    _rooms.value = result.data
                }
                else -> {

                }
            }
        }
    }
}

