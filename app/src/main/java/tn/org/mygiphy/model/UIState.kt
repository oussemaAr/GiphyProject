package tn.org.mygiphy.model

sealed class UIState {
    object Loading : UIState()
    class Success(val data: List<GifItem>) : UIState()
    class Error(val message: String) : UIState()
}