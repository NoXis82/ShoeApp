package ru.umarsh.shoeapp.presentation.shoe_app

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import ru.umarsh.shoeapp.data.CarouselDataModel
import ru.umarsh.shoeapp.enum.ButtonState

class MainViewModel : ViewModel() {

    val screenState = mutableStateOf<UiState>(UiState.Home)
    val buttonState = mutableStateOf(ButtonState.DEFAULT)
    val cartFlow = MutableSharedFlow<Boolean>()

    fun onBackClick() {
        screenState.value = UiState.Home
    }

    fun changeButtonState() {
        if (buttonState.value == ButtonState.LOADED) {
            viewModelScope.launch {
                cartFlow.emit(true)
            }
            return
        }
        viewModelScope.launch {
            buttonState.value = ButtonState.LOADING
            delay(1500)
            buttonState.value = ButtonState.LOADED
        }
    }

    sealed class UiState {
        class Details(val carouselDataModel: CarouselDataModel) : UiState()
        object Home : UiState()
    }
}