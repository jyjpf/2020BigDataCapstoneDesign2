/*
|---------------------------------------------------------------------
| 받아쓰기 Vuex Actions
|---------------------------------------------------------------------
*/
export default {
  setRole: ({ commit }, role) => {
    commit('loadRole', role)
  }
}
