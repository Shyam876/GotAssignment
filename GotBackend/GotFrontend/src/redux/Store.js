import { configureStore } from '@reduxjs/toolkit'
import rootReducer from './reducers/RootReducers'

const store = configureStore({
  reducer: rootReducer
})

export default store