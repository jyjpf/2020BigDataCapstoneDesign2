import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'

// Global vuex
import AppModule from './app'

// Apps
import DictationModule from '../apps/dictation/store'

Vue.use(Vuex)

/**
 * Main Vuex Store
 */
const store = new Vuex.Store({
  modules: {
    app: AppModule,
    'dictation-app': DictationModule
  },
  plugins: [
    createPersistedState({
      path: ['dictation-app']
    })
  ]
})

export default store
